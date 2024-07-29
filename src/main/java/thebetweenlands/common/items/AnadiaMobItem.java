package thebetweenlands.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.common.component.entity.RotSmellData;
import thebetweenlands.common.entities.Anadia;
import thebetweenlands.common.entities.EntitySwampHag;
import thebetweenlands.common.entities.Seat;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.DataComponentRegistry;
import thebetweenlands.common.registries.EntityRegistry;
import thebetweenlands.common.registries.ItemRegistry;

import java.util.List;

public class AnadiaMobItem extends MobItem {
	public AnadiaMobItem(Properties properties) {
		super(properties, EntityRegistry.ANADIA.get(), null);
	}

	public int getDecayTime(ItemStack stack) {
		return 24000; // 20 minutes
	}

	public ItemStack getItemFromEntity(String key, ItemStack stack, Level level) {
		if (!this.isRotten(level, stack) && this.hasEntityData(stack)) {
			CompoundTag tag = this.getEntityData(stack);
			if (tag.contains(key, CompoundTag.TAG_COMPOUND)) {
				return ItemStack.parse(level.registryAccess(), tag.getCompound(key)).orElse(ItemStack.EMPTY);
			}
		}
		return new ItemStack(ItemRegistry.ANADIA_REMAINS.get());
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide()) {
			if (target instanceof EntitySwampHag && !target.isPassenger() && attacker instanceof Player) {
				if (attacker.level().getBlockState(target.blockPosition().below()).blocksMotion()) {
					Seat entitySeat = new Seat(attacker.level(), true);
					entitySeat.setPos(target.getX(), target.getY() - 0.55D, target.getZ());
					attacker.level().addFreshEntity(entitySeat);
					target.startRiding(entitySeat, true);
				}
			}
		}
		return false;
	}

	@Override
	protected InteractionResult spawnCapturedEntity(Player player, Level level, BlockPos pos, InteractionHand hand, Direction facing, Vec3 hitVec, Entity entity, boolean isNewEntity) {
		if (entity instanceof Anadia anadia && !anadia.getFishColor().isAlive()) {
			return InteractionResult.PASS;
		}
		return super.spawnCapturedEntity(player, level, pos, hand, facing, hitVec, entity, isNewEntity);
	}

	public void setRotten(Level level, ItemStack stack, boolean rotten) {
		if (stack.get(DataComponents.ENTITY_DATA) != null) {
			if (stack.get(DataComponents.ENTITY_DATA).copyTag().getByte("fish_color") != 0) {
				if (rotten) {
					stack.set(DataComponentRegistry.ROT_TIME, level.getGameTime());
				} else {
					stack.set(DataComponentRegistry.ROT_TIME, level.getGameTime() + this.getDecayTime(stack));
				}
			}
		}
	}

	public boolean isRotten(Level level, ItemStack stack) {
		if (stack.get(DataComponents.ENTITY_DATA) != null) {
			if (stack.get(DataComponents.ENTITY_DATA).copyTag().getByte("fish_color") != 0) {
				if (stack.get(DataComponentRegistry.ROT_TIME) != null) {
					return stack.get(DataComponentRegistry.ROT_TIME) - level.getGameTime() <= 0;
				}
			}
		}
		return false;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
		if (stack.get(DataComponents.ENTITY_DATA) != null) {
			byte color = stack.get(DataComponents.ENTITY_DATA).copyTag().getByte("fish_color");
			if (color != 0 && color != 1)
				if (stack.get(DataComponentRegistry.ROT_TIME) != null)
					if (level.getGameTime() >= stack.get(DataComponentRegistry.ROT_TIME))
						CustomData.update(DataComponents.ENTITY_DATA, stack, tag -> tag.putByte("fish_color", (byte) 1));
		}
		if (this.isRotten(level, stack) && entity instanceof Player player) {
			if (!level.isClientSide()) {
				RotSmellData cap = player.getData(AttachmentRegistry.ROT_SMELL);
				if (!cap.isSmellingBad(player)) {
					cap.setSmellingBad(player, Math.max(cap.getRemainingSmellyTicks(player), 24000));
				}
			}
		}
	}

	@Override
	public void onCapturedByPlayer(Player player, InteractionHand hand, ItemStack captured, LivingEntity entity) {
		if (!player.level().isClientSide()) {
			if (captured.get(DataComponents.ENTITY_DATA) != null)
				captured.set(DataComponentRegistry.ROT_TIME, player.level().getGameTime() + this.getDecayTime(captured));
			if (entity instanceof Anadia anadia && anadia.isVehicle()) {
				anadia.ejectPassengers();
			}
		}
	}

	//FIXME we cant depend on the world timestamp for the tooltip. If theres a way we can get a timestamp without the world that would be great
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
//		if (this.hasEntityData(stack)) {
//			CompoundTag entityNbt = this.getEntityData(stack);
//
//			if (entityNbt != null) {
//				tooltip.add(BuiltInRegistries.ENTITY_TYPE.get(this.getCapturedEntityId(stack)).getDescription());
//
//				if (entityNbt.getByte("fish_color") != 0) {
//					if (stack.get(DataComponentRegistry.ROT_TIME) != null) {
//						long rottingTime = stack.get(DataComponentRegistry.ROT_TIME);
//						if (rottingTime - worldIn.getTotalWorldTime() > 19200)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotting_1")));
//						else if (rottingTime - worldIn.getTotalWorldTime() <= 19200 && rottingTime - worldIn.getTotalWorldTime() > 14400)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotting_2")));
//						else if (rottingTime - worldIn.getTotalWorldTime() <= 14400 && rottingTime - worldIn.getTotalWorldTime() > 9600)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotting_3")));
//						else if (rottingTime - worldIn.getTotalWorldTime() <= 9600 && rottingTime - worldIn.getTotalWorldTime() > 4800)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotting_4")));
//						else if (rottingTime - worldIn.getTotalWorldTime() <= 4800 && rottingTime - worldIn.getTotalWorldTime() > 0)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotting_5")));
//						else if (rottingTime - worldIn.getTotalWorldTime() <= 0)
//							tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.rotten")));
//					}
//				} else {
//					tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.status").append(Component.translatable("tooltip.bl.item_mob_anadia.smoked")));
//				}
//
//				tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.health", MathHelper.ceil(living.getHealth()), MathHelper.ceil((living.getMaxHealth()))));
//				tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.size", entityNbt.getFloat("fish_size")));
//				tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.speed", (living.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue())));
//				tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.strength", entityNbt.getFloat("strength")));
//				tooltip.add(Component.translatable("tooltip.bl.item_mob_anadia.stamina", entityNbt.getFloat("stamina")));
//			}
//		} else {
//			tooltip.add(Component.translatable("tooltip.bl.item_mob.health", MathHelper.ceil(living.getHealth() / 2), MathHelper.ceil(living.getMaxHealth() / 2)));
//		}
	}
}
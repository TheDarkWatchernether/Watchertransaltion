package thebetweenlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.common.block.entity.BeamOriginBlockEntity;
import thebetweenlands.common.block.entity.SteepingPotBlockEntity;
import thebetweenlands.common.registries.BlockEntityRegistry;
import thebetweenlands.common.registries.FluidRegistry;
import thebetweenlands.common.registries.ItemRegistry;

import java.util.Optional;

public class SteepingPotBlock extends HorizontalBaseEntityBlock {

	public static final BooleanProperty HANGING = BooleanProperty.create("hanging");

	public SteepingPotBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(HANGING, false));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(HANGING, this.canHang(context.getLevel(), context.getClickedPos()));
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.UP ? state.setValue(HANGING, this.canHang(level, pos)) : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	public boolean canHang(LevelAccessor level, BlockPos pos) {
		return Block.isFaceFull(level.getBlockState(pos.above()).getCollisionShape(level, pos), Direction.DOWN);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		Optional<IFluidHandler> fluidHandler = FluidUtil.getFluidHandler(level, pos, hitResult.getDirection());

		if (fluidHandler.isPresent() && FluidUtil.getFluidHandler(stack).isPresent()) {
			FluidUtil.interactWithFluidHandler(player, hand, level, pos, hitResult.getDirection());
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}

		if (level.getBlockEntity(pos) instanceof SteepingPotBlockEntity pot) {
			if (pot.getHeatProgress() > 50 && pot.getHeatProgress() < 100 && pot.hasBundle()) {
				return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
			}
			if (!player.isCrouching()) {
				if (stack.is(ItemRegistry.SILK_BUNDLE)) {
					if (pot.getItem(0).isEmpty()) {
						ItemStack ingredient = stack.consumeAndReturn(1, player);
						pot.setItem(0, ingredient);
						pot.setHeatProgress(0);
						pot.hasCraftResult = false;
						if (pot.tank.getFluid() != null)
							level.playSound(null, pos, SoundEvents.PLAYER_SPLASH, SoundSource.BLOCKS, 0.75F, 2F);
						level.sendBlockUpdated(pos, state, state, 3);
						return ItemInteractionResult.sidedSuccess(level.isClientSide());
					}
				}
			}

		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.getBlockEntity(pos) instanceof SteepingPotBlockEntity pot) {
			if (player.isCrouching()) {
				if (!pot.getItem(0).isEmpty()) {
					if (!player.getInventory().add(pot.getItem(0)))
						player.drop(pot.getItem(0), false);
					pot.setItem(0, ItemStack.EMPTY);
					pot.setHeatProgress(0);
					pot.hasCraftResult = false;
					level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 2F);
					level.sendBlockUpdated(pos, state, state, 3);
					return InteractionResult.sidedSuccess(level.isClientSide());
				}
			}
		}

		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (level.getBlockEntity(pos) instanceof SteepingPotBlockEntity pot) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (pot.getTankFluidAmount() > 0 && pot.getHeatProgress() > 80) {
				int amount = pot.tank.getFluidAmount();
				int capacity = pot.tank.getCapacity();
				float size = 1F / capacity * amount;
				float xx = x + 0.5F;
				float yy = y + 0.35F + size * 0.25F;
				float zz = z + 0.5F;
				int color = pot.tank.getFluid().getOrDefault(DataComponents.DYED_COLOR, new DyedItemColor(0, false)).rgb();
//				BLParticles.BUBBLE_INFUSION.spawn(level, xx + 0.3F - random.nextFloat() * 0.6F, yy, zz + 0.3F - random.nextFloat() * 0.6F, ParticleArgs.get().withScale(0.3F).withColor(color));

				if (pot.getHeatProgress() >= 100) {
					for (int i = 0; i < 2 + random.nextInt(3); i++) {
//						BatchedParticleRenderer.INSTANCE.addParticle(DefaultParticleBatches.TRANSLUCENT_GLOWING_NEAREST_NEIGHBOR, BLParticles.SMOOTH_SMOKE.create(level, pos.getX() + 0.5F, pos.getY() + 0.75F, pos.getZ() + 0.5F,
//							ParticleArgs.get()
//								.withMotion((random.nextFloat() * 0.25F - 0.125F) * 0.09F, random.nextFloat() * 0.02F + 0.01F, (random.nextFloat() * 0.25F - 0.125F) * 0.09F)
//								.withScale(1.0F + random.nextFloat() * 2.0F)
//								.withColor(color)
//								.withData(80, true, 0.01F, true)));
					}
				}
			}
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SteepingPotBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, BlockEntityRegistry.STEEPING_POT.get(), SteepingPotBlockEntity::tick);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(HANGING));
	}
}
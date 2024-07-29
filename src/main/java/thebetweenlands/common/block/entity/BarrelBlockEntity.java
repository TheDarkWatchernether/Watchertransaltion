package thebetweenlands.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.common.block.BarrelBlock;
import thebetweenlands.common.registries.BlockEntityRegistry;

public class BarrelBlockEntity extends SyncedBlockEntity implements MenuProvider, Nameable, IFluidHandler {

	@Nullable
	private Component name;
	private final FluidTank fluidTank = new FluidTank(FluidType.BUCKET_VOLUME * 8);

	public BarrelBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.BARREL.get(), pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		this.fluidTank.writeToNBT(registries, tag);
		if (this.name != null) {
			tag.putString("name", Component.Serializer.toJson(this.name, registries));
		}
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.fluidTank.readFromNBT(registries, tag);
		if (tag.contains("name", 8)) {
			this.name = parseCustomNameSafe(tag.getString("name"), registries);
		}
	}

	@Override
	public Component getName() {
		return this.name != null ? this.name : Component.translatable("container.thebetweenlands.barrel");
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
	}

	@Nullable
	@Override
	public Component getCustomName() {
		return this.name;
	}

	//TODO
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return null;
	}

	@Override
	public int getTanks() {
		return this.fluidTank.getTanks();
	}

	@Override
	public FluidStack getFluidInTank(int tank) {
		return this.fluidTank.getFluidInTank(tank);
	}

	@Override
	public int getTankCapacity(int tank) {
		return this.fluidTank.getTankCapacity(tank);
	}

	@Override
	public boolean isFluidValid(int tank, FluidStack stack) {
		return this.fluidTank.isFluidValid(tank, stack);
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		if (this.getLevel() != null) {
			BlockState state = this.getLevel().getBlockState(this.getBlockPos());

			boolean isFluidHot = resource.getFluid().getFluidType().getTemperature(resource) > 473.15F /*200°C*/ || resource.is(Fluids.LAVA);

			if (!isFluidHot || (state.getBlock() instanceof BarrelBlock barrel && barrel.isHeatResistant(this.getLevel(), this.getBlockPos(), state))) {
				int filled = this.fluidTank.fill(resource, action);

				if (filled != 0 && action.execute()) {
					this.setChanged();
					this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
				}

				return filled;
			}
		}

		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		if (action.execute()) {
			this.setChanged();
			this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
		}
		return this.fluidTank.drain(resource, action);
	}

	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		if (action.execute()) {
			this.setChanged();
			BlockState stat = this.getLevel().getBlockState(this.getBlockPos());
			this.getLevel().sendBlockUpdated(this.getBlockPos(), stat, stat, 2);
		}
		return this.fluidTank.drain(maxDrain, action);
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
		super.applyImplicitComponents(componentInput);
		this.name = componentInput.get(DataComponents.CUSTOM_NAME);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		super.collectImplicitComponents(components);
		components.set(DataComponents.CUSTOM_NAME, this.name);
	}
}
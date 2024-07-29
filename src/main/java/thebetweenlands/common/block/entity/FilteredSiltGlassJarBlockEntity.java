package thebetweenlands.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import thebetweenlands.common.registries.BlockEntityRegistry;

public class FilteredSiltGlassJarBlockEntity extends SyncedBlockEntity implements IFluidHandler {

	public FluidTank tank = new FluidTank(FluidType.BUCKET_VOLUME * 4);

	public FilteredSiltGlassJarBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.FILTERED_SILT_GLASS_JAR.get(), pos, state);
	}

	@Override
	public int getTanks() {
		return this.tank.getTanks();
	}

	@Override
	public FluidStack getFluidInTank(int tank) {
		return this.tank.getFluidInTank(tank);
	}

	@Override
	public int getTankCapacity(int tank) {
		return this.tank.getTankCapacity(tank);
	}

	@Override
	public boolean isFluidValid(int tank, FluidStack stack) {
		return this.tank.isFluidValid(tank, stack);
	}

	@Override
	public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
		return this.tank.fill(resource, action);
	}

	@Override
	public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
		return this.tank.drain(resource, action);
	}

	@Override
	public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
		return this.tank.drain(maxDrain, action);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		this.tank.writeToNBT(registries, tag);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.tank.readFromNBT(registries, tag);
	}
}
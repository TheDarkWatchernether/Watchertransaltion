package thebetweenlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.common.block.entity.CrabPotBlockEntity;
import thebetweenlands.common.block.entity.CrabPotFilterBlockEntity;
import thebetweenlands.common.registries.BlockEntityRegistry;

public class CrabPotFilterBlock extends HorizontalBaseEntityBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CrabPotFilterBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof CrabPotFilterBlockEntity filter) {
			player.openMenu(filter);
		}
		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (level.getBlockEntity(pos) instanceof CrabPotFilterBlockEntity filter) {
			if (random.nextInt(3) == 0)
				if (filter.isActive() && filter.getSlotProgress() > 0) {
					for (int i = 0; i < 5 + random.nextInt(5); i++) {
//						BatchedParticleRenderer.INSTANCE.addParticle(
//							DefaultParticleBatches.TRANSLUCENT_NEAREST_NEIGHBOR,
//							BLParticles.SMOOTH_SMOKE.create(level, pos.getX() + 0.5F, pos.getY() + 0.99F, pos.getZ() + 0.5F,
//								ParticleArgs.get().withMotion((random.nextFloat() - 0.5f) * 0.01f, -random.nextFloat() * -0.05F - 0.05F, (random.nextFloat() - 0.5f) * 0.01f)
//									.withScale(0.5f + random.nextFloat() * 8.0F)
//									.withColor(0.59F, 0.29F, 0F, 0.3f).withData(80, true, 0.01F, true)));
					}
				}
		}
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CrabPotFilterBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, BlockEntityRegistry.CRAB_POT_FILTER.get(), CrabPotFilterBlockEntity::tick);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(WATERLOGGED));
	}
}
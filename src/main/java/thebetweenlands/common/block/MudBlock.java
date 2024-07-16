package thebetweenlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;
import thebetweenlands.api.block.FarmablePlant;
import thebetweenlands.common.items.RubberBootsItem;
import thebetweenlands.common.registries.AttachmentRegistry;

import javax.annotation.Nullable;

public class MudBlock extends Block {

	protected static final VoxelShape MUD_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
	public static final BooleanProperty IN_WATER = BooleanProperty.create("in_water");

	public MudBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(IN_WATER, false));
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (context instanceof EntityCollisionContext ctx && this.canEntityWalkOnMud(ctx.getEntity())) {
			return Shapes.block();
		}
		return MUD_AABB;
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
		return state.getBlock() instanceof FarmablePlant ? TriState.TRUE : super.canSustainPlant(state, level, soilPosition, facing, plant);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!this.canEntityWalkOnMud(entity)) {
			if (!entity.isInWater() && entity.getDeltaMovement().y() < 0 && entity.onGround())
				entity.setDeltaMovement(entity.getDeltaMovement().subtract(0.0D, 0.1D, 0.0D));

			entity.makeStuckInBlock(state, new Vec3(0.08D, 0.02D, 0.08D));
		}
	}

	public boolean canEntityWalkOnMud(Entity entity) {
		boolean mudWalker = entity instanceof Player player && player.getData(AttachmentRegistry.MUD_WALKER).isActive(player);
		return RubberBootsItem.canEntityWalkOnMud(entity) || mudWalker;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (random.nextInt(10) == 0 && level.isEmptyBlock(pos.below())) {
			double d3 = pos.getX() + (double) random.nextFloat();
			double d5 = pos.getY() - 0.05D;
			double d7 = pos.getZ() + (double) random.nextFloat();
//			BLParticles.CAVE_WATER_DRIP.spawn(world, d3, d5, d7).setRBGColorF(0.4118F, 0.2745F, 0.1568F);
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(IN_WATER, context.getLevel().getFluidState(context.getClickedPos().above()).is(Fluids.WATER));
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return state.setValue(IN_WATER, level.getFluidState(pos.above()).is(Fluids.WATER));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(IN_WATER);
	}
}
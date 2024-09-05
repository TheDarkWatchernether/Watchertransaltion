package thebetweenlands.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import thebetweenlands.common.block.PebblePileBlock;
import thebetweenlands.common.block.waterlog.SwampWaterLoggable;
import thebetweenlands.common.world.gen.WorldGenUtil;
import thebetweenlands.common.world.gen.feature.config.PebbleClusterConfiguration;

public class PebbleClusterFeature extends Feature<PebbleClusterConfiguration> {

	public PebbleClusterFeature(Codec<PebbleClusterConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<PebbleClusterConfiguration> context) {
		return generate(context.level(), context.random(), context.origin(), context.config());
	}

	public boolean generate(WorldGenLevel level, RandomSource rand, BlockPos pos, PebbleClusterConfiguration config) {
		boolean generated = false;
		pos = WorldGenUtil.loopUntilSolid(level, pos);

		for (int i = 0; i < config.attempts(); i++) {
			BlockPos offset = pos.offset(WorldGenUtil.randomOffset(rand, pos, config.offset(), config.offset() / 2 + 1, config.offset()));
			BlockState offsetState = level.getBlockState(offset);

			if (offsetState.isAir() && config.state().getBlock() instanceof PebblePileBlock && config.state().canSurvive(level, offset)) {
				int pileSize = rand.nextInt(4) + 1;
				this.setBlock(level, offset, config.state()
					.setValue(PebblePileBlock.PEBBLES, pileSize)
					.setValue(PebblePileBlock.PLANT, rand.nextBoolean())
					.setValue(PebblePileBlock.WATER_TYPE, SwampWaterLoggable.WaterType.getFromFluid(config.state().getFluidState().getType())));
				generated = true;
			}
		}
		return generated;
	}
}
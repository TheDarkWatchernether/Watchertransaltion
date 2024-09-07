package thebetweenlands.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import thebetweenlands.util.WorldGenUtil;
import thebetweenlands.common.world.gen.feature.config.DoublePlantConfiguration;

public class DoublePlantClusterFeature extends Feature<DoublePlantConfiguration> {

	public DoublePlantClusterFeature(Codec<DoublePlantConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<DoublePlantConfiguration> context) {
		return generate(context.level(), context.random(), context.origin(), context.config());
	}

	public boolean generate(WorldGenLevel level, RandomSource rand, BlockPos pos, DoublePlantConfiguration config) {
		boolean generated = false;
		pos = WorldGenUtil.loopUntilSolid(level, pos);
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for (int i = 0; i < config.attempts(); i++) {
			mutable = WorldGenUtil.randomOffset(rand, mutable, pos, config.offset(), config.offset() / 2 + 1, config.offset());

			if ((level.getBlockState(mutable).isAir() || (config.underwater() && !level.getFluidState(mutable).isEmpty())) && (level.dimensionType().hasSkyLight() || mutable.getY() < 254) && config.state().canSurvive(level, mutable)) {
				level.setBlock(mutable, config.state(), 2);
				generated = true;
			}
		}
		return generated;
	}
}
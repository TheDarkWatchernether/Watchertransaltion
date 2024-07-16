package thebetweenlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import thebetweenlands.common.entities.BLEntity;
import thebetweenlands.common.herblore.elixir.ElixirEffectRegistry;

public class PoisonIvyBlock extends VineBlock {
	public PoisonIvyBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!level.isClientSide() && !(entity instanceof BLEntity) && entity instanceof LivingEntity living && level.getRandom().nextInt(200) == 0 && !ElixirEffectRegistry.EFFECT_TOUGHSKIN.get().isActive(living)) {
			living.addEffect(new MobEffectInstance(MobEffects.POISON, 50, 25));
		}
	}
}
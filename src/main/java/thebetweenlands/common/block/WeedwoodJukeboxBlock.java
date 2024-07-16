package thebetweenlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import thebetweenlands.common.registries.ItemRegistry;

public class WeedwoodJukeboxBlock extends JukeboxBlock {
	public WeedwoodJukeboxBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!state.getValue(HAS_RECORD) && stack.is(ItemRegistry.GERTS_DONUT)) {
			if (!level.isClientSide()) {
				ItemStack itemstack = stack.consumeAndReturn(1, player);
				if (level.getBlockEntity(pos) instanceof JukeboxBlockEntity jukebox) {
					jukebox.setTheItem(itemstack);
					level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
				}

				player.awardStat(Stats.PLAY_RECORD);
			} else {
				player.displayClientMessage(Component.literal("DOH!"), true);
				level.playSound(player, pos, SoundEvents.GENERIC_EAT, SoundSource.RECORDS, 1.0F, 1.0F);
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}

		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) instanceof JukeboxBlockEntity jukebox && jukebox.getTheItem().is(ItemRegistry.GERTS_DONUT)) {
			return 15;
		}
		return super.getAnalogOutputSignal(blockState, level, pos);
	}
}
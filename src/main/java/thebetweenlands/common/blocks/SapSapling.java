package thebetweenlands.common.blocks;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import thebetweenlands.common.generation.trees.SapTree;

public class SapSapling extends BetweenlandsSapling {

	public SapSapling(Properties p_48756_) {
		super(p_48756_);
	}

	public InteractionResult use(BlockState p_51088_, Level p_51089_, BlockPos p_51090_, Player p_51091_, InteractionHand p_51092_, BlockHitResult p_51093_) {
		ItemStack item = p_51091_.getItemInHand(p_51092_);
		
		if (item.is(Items.BONE_MEAL)) {
			
			if (!p_51089_.isClientSide()) {
				SapTree.generate(p_51089_, p_51090_, p_51089_.getRandom());
			}
			
			return InteractionResult.SUCCESS;
		}
		
		return InteractionResult.FAIL;
	}
}
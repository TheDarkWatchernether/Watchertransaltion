package thebetweenlands.common.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import javax.annotation.Nullable;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.registries.BlockRegistry;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends net.neoforged.neoforge.common.data.BlockTagsProvider {

	public static final TagKey<Block> BETWEENSTONE_ORE_REPLACEABLE = tag("betweenstone_ore_replaceable");
	public static final TagKey<Block> PITSTONE_ORE_REPLACEABLE = tag("pitstone_ore_replaceable");
	public static final TagKey<Block> PRESENTS = tag("presents");
	public static final TagKey<Block> OCTINE_IGNITES = tag("octine_ignites");

	public static final TagKey<Block> INCORRECT_FOR_WEEDWOOD_TOOL = tag("incorrect_for_weedwood_tool");
	public static final TagKey<Block> INCORRECT_FOR_BONE_TOOL = tag("incorrect_for_bone_tool");
	public static final TagKey<Block> INCORRECT_FOR_OCTINE_TOOL = tag("incorrect_for_octine_tool");
	public static final TagKey<Block> INCORRECT_FOR_VALONITE_TOOL = tag("incorrect_for_valonite_tool");


	public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, TheBetweenlands.ID, existingFileHelper);
	}

	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BlockTags.DIRT).add(BlockRegistry.SWAMP_DIRT.get(), BlockRegistry.SWAMP_GRASS.get(), BlockRegistry.DEAD_GRASS.get(), BlockRegistry.MUD.get());
		this.tag(OCTINE_IGNITES).add(BlockRegistry.CAVE_MOSS.get(), BlockRegistry.MOSS.get(), BlockRegistry.LICHEN.get(), BlockRegistry.DEAD_MOSS.get(), BlockRegistry.DEAD_LICHEN.get(), BlockRegistry.THORNS.get());

		this.tag(BlockTags.LOGS).add(BlockRegistry.WEEDWOOD_LOG.get(), BlockRegistry.WEEDWOOD_BARK.get(),
			BlockRegistry.HEARTHGROVE_FENCE_GATE.get(), BlockRegistry.HEARTHGROVE_BARK.get(),
			BlockRegistry.NIBBLETWIG_LOG.get(), BlockRegistry.NIBBLETWIG_BARK.get(),
			BlockRegistry.PORTAL_LOG.get(), BlockRegistry.ROTTEN_BARK.get());
	}

	public static TagKey<Block> tag(String tagName) {
		return BlockTags.create(TheBetweenlands.prefix(tagName));
	}

	public static TagKey<Block> commonTag(String tagName) {
		return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
	}
}

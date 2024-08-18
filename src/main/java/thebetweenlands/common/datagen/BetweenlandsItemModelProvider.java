package thebetweenlands.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.registries.ItemRegistry;

public class BetweenlandsItemModelProvider extends ItemModelProvider {
	public BetweenlandsItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, TheBetweenlands.ID, helper);
	}

	@Override
	protected void registerModels() {
		this.basicItem(ItemRegistry.CRIMSON_SNAIL_SHELL);
		this.basicItem(ItemRegistry.OCHRE_SNAIL_SHELL);
		this.basicItem(ItemRegistry.COMPOST);
		this.basicItem(ItemRegistry.DRAGONFLY_WING);
		this.basicItem(ItemRegistry.LURKER_SKIN);
		this.basicItem(ItemRegistry.DRIED_SWAMP_REED);
		this.basicItem(ItemRegistry.REED_ROPE);
		this.basicItem(ItemRegistry.MUD_BRICK);
		this.basicItem(ItemRegistry.SYRMORITE_INGOT);
		this.basicItem(ItemRegistry.DRY_BARK);
		this.basicItem(ItemRegistry.SLIMY_BONE);
		this.basicItem(ItemRegistry.SNAPPER_ROOT);
		this.basicItem(ItemRegistry.STALKER_EYE);
		this.basicItem(ItemRegistry.SULFUR);
		this.basicItem(ItemRegistry.VALONITE_SHARD);
		this.basicItem(ItemRegistry.WEEDWOOD_STICK);
		this.basicItem(ItemRegistry.ANGLER_TOOTH);
		this.basicItem(ItemRegistry.WEEDWOOD_BOWL);
		this.basicItem(ItemRegistry.RUBBER_BALL);
		this.basicItem(ItemRegistry.TAR_BEAST_HEART);
		this.basicItem(ItemRegistry.ANIMATED_TAR_BEAST_HEART);
		this.basicItem(ItemRegistry.TAR_DRIP);
		this.basicItem(ItemRegistry.LIMESTONE_FLUX);
		this.basicItem(ItemRegistry.INANIMATE_TARMINION);
		this.basicItem(ItemRegistry.POISON_GLAND);
		this.basicItem(ItemRegistry.AMATE_PAPER);
		this.basicItem(ItemRegistry.SHOCKWAVE_SWORD_PIECE_1);
		this.basicItem(ItemRegistry.SHOCKWAVE_SWORD_PIECE_2);
		this.basicItem(ItemRegistry.SHOCKWAVE_SWORD_PIECE_3);
		this.basicItem(ItemRegistry.SHOCKWAVE_SWORD_PIECE_4);
		this.basicItem(ItemRegistry.AMULET_SOCKET);
		this.basicItem(ItemRegistry.SCABYST);
		this.basicItem(ItemRegistry.ITEM_SCROLL);
		this.basicItem(ItemRegistry.SYRMORITE_NUGGET);
		this.basicItem(ItemRegistry.OCTINE_NUGGET);
		this.basicItem(ItemRegistry.VALONITE_SPLINTER);
		this.basicItem(ItemRegistry.CREMAINS);
		this.basicItem(ItemRegistry.UNDYING_EMBERS);
		this.basicItem(ItemRegistry.INANIMATE_ANGRY_PEBBLE);
		this.basicItem(ItemRegistry.ANCIENT_REMNANT);
		this.basicItem(ItemRegistry.LOOT_SCRAPS);
		this.basicItem(ItemRegistry.FABRICATED_SCROLL);
		//pebble
		this.basicItem(ItemRegistry.ANADIA_SWIM_BLADDER);
		this.basicItem(ItemRegistry.ANADIA_EYE);
		this.basicItem(ItemRegistry.ANADIA_GILLS);
		this.basicItem(ItemRegistry.ANADIA_SCALES);
		this.basicItem(ItemRegistry.ANADIA_BONES);
		this.basicItem(ItemRegistry.ANADIA_REMAINS);
		this.basicItem(ItemRegistry.ANADIA_FINS);
		this.basicItem(ItemRegistry.SNOT);
		this.basicItem(ItemRegistry.URCHIN_SPIKE);
		this.basicItem(ItemRegistry.FISHING_FLOAT_AND_HOOK);
		this.basicItem(ItemRegistry.OLMLETTE_MIXTURE);
		this.basicItem(ItemRegistry.SILK_COCOON);
		this.basicItem(ItemRegistry.SILK_THREAD);
		this.basicItem(ItemRegistry.DIRTY_SILK_BUNDLE);
		this.basicItem(ItemRegistry.PHEROMONE_THORAXES);
		this.basicItem(ItemRegistry.SWAMP_TALISMAN);
		this.basicItem(ItemRegistry.SWAMP_TALISMAN_PIECE_1);
		this.basicItem(ItemRegistry.SWAMP_TALISMAN_PIECE_2);
		this.basicItem(ItemRegistry.SWAMP_TALISMAN_PIECE_3);
		this.basicItem(ItemRegistry.SWAMP_TALISMAN_PIECE_4);
		this.basicItem(ItemRegistry.ORANGE_DENTROTHYST_SHARD);
		this.basicItem(ItemRegistry.GREEN_DENTROTHYST_SHARD);
		this.basicItem(ItemRegistry.FISH_BAIT);
		this.basicItem(ItemRegistry.FUMIGANT);
		this.basicItem(ItemRegistry.SAP_BALL);
		this.basicItem(ItemRegistry.COOKED_MIRE_SNAIL_EGG);
		this.basicItem(ItemRegistry.RAW_FROG_LEGS);
		this.basicItem(ItemRegistry.COOKED_FROG_LEGS);
		this.basicItem(ItemRegistry.RAW_SNAIL_FLESH);
		this.basicItem(ItemRegistry.COOKED_SNAIL_FLESH);
		this.basicItem(ItemRegistry.REED_DONUT);
		this.basicItem(ItemRegistry.JAM_DONUT);
		this.basicItem(ItemRegistry.GERTS_DONUT);
		this.basicItem(ItemRegistry.PUFFSHROOM_TENDRIL);
		this.basicItem(ItemRegistry.KRAKEN_TENTACLE);
		this.basicItem(ItemRegistry.KRAKEN_CALAMARI);
		this.basicItem(ItemRegistry.MIDDLE_FRUIT);
		this.basicItem(ItemRegistry.MINCE_PIE);
		this.basicItem(ItemRegistry.CHRISTMAS_PUDDING);
		this.basicItem(ItemRegistry.CANDY_CANE);
		this.basicItem(ItemRegistry.WEEPING_BLUE_PETAL);
		this.basicItem(ItemRegistry.WIGHT_HEART);
		this.basicItem(ItemRegistry.YELLOW_DOTTED_FUNGUS);
		this.basicItem(ItemRegistry.SILT_CRAB_CLAW);
		this.basicItem(ItemRegistry.CRAB_STICK);
		this.basicItem(ItemRegistry.SLUDGE_JELLO);
		this.basicItem(ItemRegistry.MIDDLE_FRUIT_JELLO);
		this.basicItem(ItemRegistry.SAP_JELLO);
		this.basicItem(ItemRegistry.GREEN_MARSHMALLOW);
		this.basicItem(ItemRegistry.PINK_MARSHMALLOW);
		this.basicItem(ItemRegistry.FLATHEAD_MUSHROOM);
		this.basicItem(ItemRegistry.BLACK_HAT_MUSHROOM);
		this.basicItem(ItemRegistry.BULB_CAPPED_MUSHROOM);
		this.basicItem(ItemRegistry.FRIED_SWAMP_KELP);
		this.basicItem(ItemRegistry.FORBIDDEN_FIG);
		this.basicItem(ItemRegistry.BLUE_CANDY);
		this.basicItem(ItemRegistry.RED_CANDY);
		this.basicItem(ItemRegistry.YELLOW_CANDY);
		this.basicItem(ItemRegistry.CHIROMAW_WING);
		this.basicItem(ItemRegistry.TANGLED_ROOT);
		this.basicItem(ItemRegistry.MIRE_SCRAMBLE);
		this.basicItem(ItemRegistry.WEEPING_BLUE_PETAL_SALAD);
		this.basicItem(ItemRegistry.NIBBLESTICK);
		this.basicItem(ItemRegistry.SPIRIT_FRUIT);
		this.basicItem(ItemRegistry.SUSHI);
		this.basicItem(ItemRegistry.ROCK_SNOT_PEARL);
		this.basicItem(ItemRegistry.PEARLED_PEAR);
		this.basicItem(ItemRegistry.RAW_ANADIA_MEAT);
		this.basicItem(ItemRegistry.COOKED_ANADIA_MEAT);
		this.basicItem(ItemRegistry.SMOKED_ANADIA_MEAT);
		this.basicItem(ItemRegistry.BARNACLE);
		this.basicItem(ItemRegistry.COOKED_BARNACLE);
		this.basicItem(ItemRegistry.SMOKED_BARNACLE);
		this.basicItem(ItemRegistry.SMOKED_CRAB_STICK);
		this.basicItem(ItemRegistry.SMOKED_FROG_LEGS);
		this.basicItem(ItemRegistry.SMOKED_PUFFSHROOM_TENDRIL);
		this.basicItem(ItemRegistry.SMOKED_SILT_CRAB_CLAW);
		this.basicItem(ItemRegistry.SMOKED_SNAIL_FLESH);
		this.basicItem(ItemRegistry.RAW_OLM_EGG);
		this.basicItem(ItemRegistry.COOKED_OLM_EGG);
		this.basicItem(ItemRegistry.OLMLETTE);
		this.basicItem(ItemRegistry.SILK_GRUB);

		this.basicItem(ItemRegistry.BONE_HELMET);
		this.basicItem(ItemRegistry.BONE_CHESTPLATE);
		this.basicItem(ItemRegistry.BONE_LEGGINGS);
		this.basicItem(ItemRegistry.BONE_BOOTS);
		this.basicItem(ItemRegistry.LURKER_SKIN_HELMET);
		this.basicItem(ItemRegistry.LURKER_SKIN_CHESTPLATE);
		this.basicItem(ItemRegistry.LURKER_SKIN_LEGGINGS);
		this.basicItem(ItemRegistry.LURKER_SKIN_BOOTS);
		this.basicItem(ItemRegistry.SYRMORITE_HELMET);
		this.basicItem(ItemRegistry.SYRMORITE_CHESTPLATE);
		this.basicItem(ItemRegistry.SYRMORITE_LEGGINGS);
		this.basicItem(ItemRegistry.SYRMORITE_BOOTS);
		this.basicItem(ItemRegistry.VALONITE_HELMET);
		this.basicItem(ItemRegistry.VALONITE_CHESTPLATE);
		this.basicItem(ItemRegistry.VALONITE_LEGGINGS);
		this.basicItem(ItemRegistry.VALONITE_BOOTS);
		this.basicItem(ItemRegistry.ANCIENT_HELMET);
		this.basicItem(ItemRegistry.ANCIENT_CHESTPLATE);
		this.basicItem(ItemRegistry.ANCIENT_LEGGINGS);
		this.basicItem(ItemRegistry.ANCIENT_BOOTS);
		this.basicItem(ItemRegistry.RUBBER_BOOTS);
		this.otherTextureItem(ItemRegistry.MARSH_RUNNER_BOOTS, this.modLoc("rubber_boots"));

		this.basicItem(ItemRegistry.RECORD_ANCIENT);
		this.basicItem(ItemRegistry.RECORD_ASTATOS);
		this.basicItem(ItemRegistry.RECORD_BENEATH_A_GREEN_SKY);
		this.basicItem(ItemRegistry.RECORD_BETWEEN_YOU_AND_ME);
		this.basicItem(ItemRegistry.RECORD_CHRISTMAS_ON_THE_MARSH);
		this.otherTextureItem(ItemRegistry.RECORD_DEEP_WATER_THEME, this.modLoc("freshwater_urchin"));
		this.basicItem(ItemRegistry.RECORD_DJ_WIGHTS_MIXTAPE);
		this.basicItem(ItemRegistry.RECORD_HAG_DANCE);
		this.basicItem(ItemRegistry.RECORD_LONELY_FIRE);
		this.basicItem(ItemRegistry.RECORD_ONWARDS);
		this.basicItem(ItemRegistry.RECORD_STUCK_IN_THE_MUD);
		this.basicItem(ItemRegistry.RECORD_THE_EXPLORER);
		this.basicItem(ItemRegistry.RECORD_WANDERING_WISPS);
		this.basicItem(ItemRegistry.RECORD_WATERLOGGED);
		this.basicItem(ItemRegistry.MYSTERIOUS_RECORD);
		this.basicItem(ItemRegistry.AMATE_MAP);
		this.basicItem(ItemRegistry.FILLED_AMATE_MAP);
	}

	public ItemModelBuilder basicItem(DeferredItem<Item> item) {
		return this.basicItem(item.get());
	}

	public ItemModelBuilder otherTextureItem(DeferredItem<Item> item, ResourceLocation texture) {
		return this.getBuilder(item.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), "item/" + texture.getPath()));
	}
}

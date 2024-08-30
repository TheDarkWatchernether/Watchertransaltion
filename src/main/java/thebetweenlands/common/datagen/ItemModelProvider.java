package thebetweenlands.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.registries.ItemRegistry;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
	public ItemModelProvider(PackOutput output, ExistingFileHelper helper) {
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

		this.basicItem(ItemRegistry.BUBBLER_CRAB);
		this.basicItem(ItemRegistry.SILT_CRAB);
		this.toolItem(ItemRegistry.BONE_AXE);
		this.toolItem(ItemRegistry.NET);
		this.basicItem(ItemRegistry.TINY_SLUDGE_WORM);
		this.otherTextureItem(ItemRegistry.TINY_SLUDGE_WORM_HELPER, this.modLoc("tiny_sludge_worm"));
		this.toolItem(ItemRegistry.WIGHTS_BANE);
		this.toolItem(ItemRegistry.SLUDGE_SLICER);
		this.toolItem(ItemRegistry.CRITTER_CRUNCHER);
		this.toolItem(ItemRegistry.HAG_HACKER);
		this.toolItem(ItemRegistry.SWIFT_PICK);
		this.toolItem(ItemRegistry.VOODOO_DOLL);

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

		this.basicItem(ItemRegistry.GROUND_LEAF);
		this.basicItem(ItemRegistry.GROUND_CATTAIL);
		this.basicItem(ItemRegistry.GROUND_SWAMP_GRASS);
		this.basicItem(ItemRegistry.GROUND_SHOOTS);
		this.basicItem(ItemRegistry.GROUND_ARROW_ARUM);
		this.basicItem(ItemRegistry.GROUND_BUTTON_BUSH);
		this.basicItem(ItemRegistry.GROUND_MARSH_HIBUSCUS);
		this.basicItem(ItemRegistry.GROUND_PICKERELWEED);
		this.basicItem(ItemRegistry.GROUND_SOFT_RUSH);
		this.basicItem(ItemRegistry.GROUND_MARSH_MALLOW);
		this.basicItem(ItemRegistry.GROUND_MILKWEED);
		this.basicItem(ItemRegistry.GROUND_BLUE_IRIS);
		this.basicItem(ItemRegistry.GROUND_COPPER_IRIS);
		this.basicItem(ItemRegistry.GROUND_BLUE_EYED_GRASS);
		this.basicItem(ItemRegistry.GROUND_BONESET);
		this.basicItem(ItemRegistry.GROUND_BOTTLE_BRUSH_GRASS);
		this.basicItem(ItemRegistry.GROUND_WEEDWOOD_BARK);
		this.basicItem(ItemRegistry.GROUND_DRIED_SWAMP_REED);
		this.basicItem(ItemRegistry.GROUND_ALGAE);
		this.basicItem(ItemRegistry.GROUND_ANGLER_TOOTH);
		this.basicItem(ItemRegistry.GROUND_BLACKHAT_MUSHROOM);
		this.basicItem(ItemRegistry.GROUND_CRIMSON_SNAIL_SHELL);
		this.basicItem(ItemRegistry.GROUND_BOG_BEAN);
		this.basicItem(ItemRegistry.GROUND_BROOMSEDGE);
		this.basicItem(ItemRegistry.GROUND_BULB_CAPPED_MUSHROOM);
		this.basicItem(ItemRegistry.GROUND_CARDINAL_FLOWER);
		this.basicItem(ItemRegistry.GROUND_CAVE_GRASS);
		this.basicItem(ItemRegistry.GROUND_CAVE_MOSS);
		this.basicItem(ItemRegistry.GROUND_CRIMSON_MIDDLE_GEM);
		this.basicItem(ItemRegistry.GROUND_DEEP_WATER_CORAL);
		this.basicItem(ItemRegistry.GROUND_FLATHEAD_MUSHROOM);
		this.basicItem(ItemRegistry.GROUND_GOLDEN_CLUB);
		this.basicItem(ItemRegistry.GROUND_GREEN_MIDDLE_GEM);
		this.basicItem(ItemRegistry.GROUND_HANGER);
		this.basicItem(ItemRegistry.GROUND_LICHEN);
		this.basicItem(ItemRegistry.GROUND_MARSH_MARIGOLD);
		this.basicItem(ItemRegistry.GROUND_MIRE_CORAL);
		this.basicItem(ItemRegistry.GROUND_OCHRE_SNAIL_SHELL);
		this.basicItem(ItemRegistry.GROUND_MOSS);
		this.basicItem(ItemRegistry.GROUND_NETTLE);
		this.basicItem(ItemRegistry.GROUND_PHRAGMITES);
		this.basicItem(ItemRegistry.GROUND_SLUDGECREEP);
		this.basicItem(ItemRegistry.GROUND_SUNDEW);
		this.basicItem(ItemRegistry.GROUND_SWAMP_KELP);
		this.basicItem(ItemRegistry.GROUND_ROOTS);
		this.basicItem(ItemRegistry.GROUND_AQUA_MIDDLE_GEM);
		this.basicItem(ItemRegistry.GROUND_PITCHER_PLANT);
		this.basicItem(ItemRegistry.GROUND_WATER_WEEDS);
		this.basicItem(ItemRegistry.GROUND_VENUS_FLY_TRAP);
		this.basicItem(ItemRegistry.GROUND_VOLARPAD);
		this.basicItem(ItemRegistry.GROUND_THORNS);
		this.basicItem(ItemRegistry.GROUND_POISON_IVY);
		this.basicItem(ItemRegistry.GROUND_BLADDERWORT_FLOWER);
		this.basicItem(ItemRegistry.GROUND_BLADDERWORT_STALK);
		this.basicItem(ItemRegistry.GROUND_EDGE_SHROOM);
		this.basicItem(ItemRegistry.GROUND_EDGE_MOSS);
		this.basicItem(ItemRegistry.GROUND_EDGE_LEAF);
		this.basicItem(ItemRegistry.GROUND_ROTBULB);
		this.basicItem(ItemRegistry.GROUND_PALE_GRASS);
		this.basicItem(ItemRegistry.GROUND_STRING_ROOTS);
		this.basicItem(ItemRegistry.GROUND_CRYPTWEED);
		this.basicItem(ItemRegistry.GROUND_BETWEENSTONE_PEBBLE);
		this.basicItem(ItemRegistry.LEAF);
		this.basicItem(ItemRegistry.ALGAE_CLUMP);
		this.basicItem(ItemRegistry.ARROW_ARUM_LEAF);
		this.basicItem(ItemRegistry.BLUE_EYED_GRASS_FLOWERS);
		this.basicItem(ItemRegistry.BLUE_IRIS_PETAL);
		this.basicItem(ItemRegistry.MIRE_CORAL_STALK);
		this.basicItem(ItemRegistry.DEEP_WATER_CORAL_STALK);
		this.basicItem(ItemRegistry.BOG_BEAN_FLOWER_DROP);
		this.basicItem(ItemRegistry.BONESET_FLOWERS);
		this.basicItem(ItemRegistry.BOTTLE_BRUSH_GRASS_BLADES);
		this.basicItem(ItemRegistry.BROOMSEDGE_LEAVES);
		this.basicItem(ItemRegistry.BUTTON_BUSH_FLOWERS);
		this.basicItem(ItemRegistry.CARDINAL_FLOWER_PETALS);
		this.basicItem(ItemRegistry.CATTAIL_HEAD);
		this.basicItem(ItemRegistry.CAVE_GRASS_BLADES);
		this.basicItem(ItemRegistry.COPPER_IRIS_PETALS);
		this.basicItem(ItemRegistry.GOLDEN_CLUB_FLOWERS);
		this.basicItem(ItemRegistry.LICHEN_CLUMP);
		this.basicItem(ItemRegistry.MARSH_HIBISCUS_FLOWER);
		this.basicItem(ItemRegistry.MARSH_MALLOW_FLOWER);
		this.basicItem(ItemRegistry.MARSH_MARIGOLD_FLOWER_DROP);
		this.basicItem(ItemRegistry.NETTLE_LEAF);
		this.basicItem(ItemRegistry.PHRAGMITE_STEMS);
		this.basicItem(ItemRegistry.PICKERELWEED_FLOWER);
		this.basicItem(ItemRegistry.SHOOT_LEAVES);
		this.basicItem(ItemRegistry.SLUDGECREEP_LEAVES);
		this.basicItem(ItemRegistry.SOFT_RUSH_LEAVES);
		this.basicItem(ItemRegistry.SUNDEW_HEAD);
		this.basicItem(ItemRegistry.SWAMP_GRASS_BLADES);
		this.basicItem(ItemRegistry.CAVE_MOSS_CLUMP);
		this.basicItem(ItemRegistry.MOSS_CLUMP);
		this.basicItem(ItemRegistry.MILKWEED_FLOWER);
		this.basicItem(ItemRegistry.HANGER_DROP);
		this.basicItem(ItemRegistry.PITCHER_PLANT_TRAP);
		this.basicItem(ItemRegistry.WATER_WEEDS_DROP);
		this.basicItem(ItemRegistry.VENUS_FLY_TRAP_HEAD);
		this.basicItem(ItemRegistry.VOLARPAD_LEAF);
		this.basicItem(ItemRegistry.THORN_BRANCH);
		this.basicItem(ItemRegistry.POISON_IVY_LEAF);
		this.basicItem(ItemRegistry.BLADDERWORT_STALK_DROP);
		this.basicItem(ItemRegistry.BLADDERWORT_FLOWER_DROP);
		this.basicItem(ItemRegistry.EDGE_SHROOM_GILLS);
		this.basicItem(ItemRegistry.EDGE_MOSS_CLUMP);
		this.basicItem(ItemRegistry.EDGE_LEAF_DROP);
		this.basicItem(ItemRegistry.ROTBULB_STALK);
		this.basicItem(ItemRegistry.PALE_GRASS_BLADES);
		this.basicItem(ItemRegistry.STRING_ROOT_FIBERS);
		this.basicItem(ItemRegistry.CRYPTWEED_BLADES);

		var anadia = this.getBuilder(ItemRegistry.ANADIA.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/anadia_head_1"))
			.texture("layer0", TheBetweenlands.prefix("item/anadia_body_1"))
			.texture("layer0", TheBetweenlands.prefix("item/anadia_tail_1"));

		for (int head = 0; head < 3; head++) {
			for (int body = 0; body < 3; body++) {
				for (int tail = 0; tail < 3; tail++) {
					var anadiaModel = this.getBuilder(ItemRegistry.ANADIA.getId().toString() + head + body + tail)
						.parent(new ModelFile.UncheckedModelFile("item/generated"))
						.texture("layer0", TheBetweenlands.prefix("item/anadia_head_" + (head + 1)))
						.texture("layer1", TheBetweenlands.prefix("item/anadia_body_" + (body + 1)))
						.texture("layer2", TheBetweenlands.prefix("item/anadia_tail_" + (tail + 1)));
					anadia.override()
						.predicate(TheBetweenlands.prefix("head"), head)
						.predicate(TheBetweenlands.prefix("body"), body)
						.predicate(TheBetweenlands.prefix("tail"), tail)
						.model(anadiaModel).end();
				}
			}
		}

		this.getBuilder(ItemRegistry.ASPECTRUS_FRUIT.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/aspectrus_fruit"))
			.texture("layer1", TheBetweenlands.prefix("item/aspectrus_fruit_overlay"));

		this.basicItem(ItemRegistry.GREEN_DENTROTHYST_VIAL);
		this.basicItem(ItemRegistry.DIRTY_DENTROTHYST_VIAL);
		this.basicItem(ItemRegistry.ORANGE_DENTROTHYST_VIAL);

		this.getBuilder(ItemRegistry.GREEN_ELIXIR.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/vial_liquid"))
			.texture("layer1", TheBetweenlands.prefix("item/green_dentrothyst_vial"))
			.texture("layer2", TheBetweenlands.prefix("item/vial_liquid_glint"));

		this.getBuilder(ItemRegistry.ORANGE_ELIXIR.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/vial_liquid"))
			.texture("layer1", TheBetweenlands.prefix("item/orange_dentrothyst_vial"))
			.texture("layer2", TheBetweenlands.prefix("item/vial_liquid_glint"));

		this.getBuilder(ItemRegistry.GREEN_ASPECT_VIAL.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/aspect_liquid"))
			.texture("layer1", TheBetweenlands.prefix("item/green_dentrothyst_vial"));

		this.getBuilder(ItemRegistry.ORANGE_ASPECT_VIAL.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", TheBetweenlands.prefix("item/aspect_liquid"))
			.texture("layer1", TheBetweenlands.prefix("item/orange_dentrothyst_vial"));
	}

	public ItemModelBuilder basicItem(DeferredItem<? extends Item> item) {
		return this.basicItem(item.get());
	}

	public ItemModelBuilder toolItem(DeferredItem<? extends Item> item) {
		return this.getBuilder(item.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getId().getNamespace(), "item/" + item.getId().getPath()));
	}

	public ItemModelBuilder otherTextureItem(DeferredItem<? extends Item> item, ResourceLocation texture) {
		return this.getBuilder(item.getId().toString())
			.parent(new ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), "item/" + texture.getPath()));
	}
}

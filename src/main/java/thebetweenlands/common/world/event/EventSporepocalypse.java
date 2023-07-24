package thebetweenlands.common.world.event;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import thebetweenlands.api.storage.LocalRegion;
import thebetweenlands.api.storage.StorageUUID;
import thebetweenlands.common.lib.ModInfo;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.world.WorldProviderBetweenlands;
import thebetweenlands.common.world.gen.biome.decorator.SurfaceType;
import thebetweenlands.common.world.storage.BetweenlandsWorldStorage;
import thebetweenlands.common.world.storage.SporeHiveManager;
import thebetweenlands.common.world.storage.location.LocationSporeHive;

public class EventSporepocalypse extends TimedEnvironmentEvent {
	public static final ResourceLocation ID = new ResourceLocation(ModInfo.ID, "spores");

	public EventSporepocalypse(BLEnvironmentEventRegistry registry) {
		super(registry);
	}

	@Override
	public ResourceLocation getEventName() {
		return ID;
	}

	@Override
	public int getOffTime(Random rnd) {
		return rnd.nextInt(50000) + 60000;
	}
	@Override
	public int getOnTime(Random rnd) {
		return rnd.nextInt(5000) + 8000;
	}

	@Override
	public void update(World world) {
		super.update(world);

		if(this.isActive() && world.provider instanceof WorldProviderBetweenlands && !world.isRemote && world instanceof WorldServer) {
			WorldServer worldServer = (WorldServer) world;

			SporeHiveManager sporeHiveManager = BetweenlandsWorldStorage.forWorld(world).getSporeHiveManager();

			Iterator<Chunk> chunks = worldServer.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator());
			while(chunks.hasNext()) {
				Chunk chunk = chunks.next();

				int rx = world.rand.nextInt(16);
				int rz = world.rand.nextInt(16);

				BlockPos pos = new BlockPos(chunk.x * 16 + rx, chunk.getHeightValue(rx, rz), chunk.z * 16 + rz);

				if(world.isAirBlock(pos) && SurfaceType.MIXED_GROUND.matches(world, pos.down()) && sporeHiveManager.occupyIfFree(pos)) {
					this.spawnSporeHive(world, pos);
				}
			}
		}
	}

	private void spawnSporeHive(World world, BlockPos pos) {
		world.setBlockState(pos, BlockRegistry.MOULD_HORN.getDefaultState());

		BetweenlandsWorldStorage worldStorage = BetweenlandsWorldStorage.forWorld(world);

		LocationSporeHive hive = new LocationSporeHive(worldStorage, new StorageUUID(UUID.randomUUID()), LocalRegion.getFromBlockPos(pos), pos);
		hive.addBounds(new AxisAlignedBB(pos).grow(8 + world.rand.nextInt(17), 4, 8 + world.rand.nextInt(17)));
		hive.setSeed(MathHelper.getCoordinateRandom(pos.getX(), pos.getY(), pos.getZ()));

		worldStorage.getLocalStorageHandler().addLocalStorage(hive);
	}

	@Override
	public ResourceLocation[] getVisionTextures() {
		// TODO
		return null;
	}

	@Override
	public SoundEvent getChimesSound() {
		// TODO
		return null;
	}
}
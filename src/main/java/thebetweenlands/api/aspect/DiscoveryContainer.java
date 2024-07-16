package thebetweenlands.api.aspect;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import thebetweenlands.api.item.DiscoveryProvider;
import thebetweenlands.common.herblore.aspect.AspectManager;

import java.util.*;

public class DiscoveryContainer<T> {
	private final Map<AspectItem, List<AspectType>> discoveredStaticAspects = new HashMap<>();
	private final DiscoveryProvider<T> provider;
	private final T providerObj;

	public DiscoveryContainer(DiscoveryProvider<T> provider, T providerObj) {
		this.provider = provider;
		this.providerObj = providerObj;
	}

	private DiscoveryContainer() {
		this.provider = null;
		this.providerObj = null;
	}

	/**
	 * Returns a new empty discovery container
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static DiscoveryContainer<?> empty() {
		return new DiscoveryContainer();
	}

	/**
	 * Reads a discovery container from an NBT
	 *
	 * @param tag
	 * @return
	 */
	public static DiscoveryContainer<?> readFromNBT(CompoundTag tag, HolderLookup.Provider registries) {
		DiscoveryContainer<?> container = empty();
		container.updateFromNBT(tag, registries, false);
		return container;
	}

	/**
	 * Returns how many aspects of the specified item are already discovered
	 * @param item
	 * @return
	 */
	private int getDiscoveryCount(AspectItem item) {
		return !this.discoveredStaticAspects.containsKey(item) ? 0 : this.discoveredStaticAspects.get(item).size();
	}

	private DiscoveryContainer<T> saveContainer() {
		if(this.provider != null && this.providerObj != null)
			this.provider.saveContainer(this.providerObj, this);
		return this;
	}

	/**
	 * Discovers a new aspect of an item and returns the discovery result
	 * @param manager
	 * @param aspectItem
	 * @return
	 */
	public AspectDiscovery discover(AspectManager manager, AspectItem aspectItem) {
		List<Aspect> staticAspects = manager.getStaticAspects(aspectItem);
		if(staticAspects.isEmpty()) {
			return new AspectDiscovery(AspectDiscovery.DiscoveryResult.NONE, null, false);
		}
		int discoveryCount = this.getDiscoveryCount(aspectItem) + 1;
		if(discoveryCount > staticAspects.size()) {
			return new AspectDiscovery(AspectDiscovery.DiscoveryResult.END, null, false);
		}
		Aspect undiscovered = this.getUndiscoveredAspect(staticAspects, this.discoveredStaticAspects.get(aspectItem));
		this.addDiscovery(aspectItem, undiscovered.type);
		if(discoveryCount == staticAspects.size()) {
			this.saveContainer();
			return new AspectDiscovery(AspectDiscovery.DiscoveryResult.LAST, undiscovered, true);
		} else {
			this.saveContainer();
			return new AspectDiscovery(AspectDiscovery.DiscoveryResult.NEW, undiscovered, true);
		}
	}

	public boolean haveDiscoveredAll(AspectManager manager) {
		for(Map.Entry<AspectItem, List<Aspect>> e : manager.getMatchedAspects().entrySet()) {
			List<Aspect> staticAspects = manager.getStaticAspects(e.getKey());
			if (getDiscoveryCount(e.getKey()) < staticAspects.size())
				return false;
		}
		return true;
	}

	/**
	 * Discovers all aspects of all aspect items
	 * @param manager
	 */
	public void discoverAll(AspectManager manager) {
		for(Map.Entry<AspectItem, List<Aspect>> e : manager.getMatchedAspects().entrySet()) {
			for(Aspect a : e.getValue())
				this.addDiscovery(e.getKey(), a.type);
		}
		this.saveContainer();
	}

	/**
	 * Removes all discoveries of the specified item
	 * @param item
	 */
	public void resetDiscovery(AspectItem item) {
		this.discoveredStaticAspects.remove(item);
		this.saveContainer();
	}

	/**
	 * Resets all aspect discoveries of all aspect items
	 */
	public void resetAllDiscovery() {
		this.discoveredStaticAspects.clear();
		this.saveContainer();
	}

	/**
	 * Adds a single aspect discovery
	 * @param item
	 * @param discovered
	 */
	public void addDiscovery(AspectItem item, AspectType discovered) {
		List<AspectType> discoveredAspects = this.discoveredStaticAspects.get(item);
		if(discoveredAspects == null) {
			this.discoveredStaticAspects.put(item, discoveredAspects = new ArrayList<AspectType>());
		}
		if(!discoveredAspects.contains(discovered))
			discoveredAspects.add(discovered);
		this.saveContainer();
	}

	private Aspect getUndiscoveredAspect(List<Aspect> all, List<AspectType> discovered) {
		if(discovered == null) {
			return all.size() == 0 ? null : all.get(0);
		}
		for(Aspect a : all) {
			if(!discovered.contains(a.type))
				return a;
		}
		return null;
	}

	/**
	 * Writes this discovery container to an NBT
	 * @param tag
	 */
	public CompoundTag writeToNBT(CompoundTag tag, HolderLookup.Provider registries) {
		ListTag discoveryList = new ListTag();
		Iterator<Map.Entry<AspectItem, List<AspectType>>> discoveryIT = this.discoveredStaticAspects.entrySet().iterator();
		while(discoveryIT.hasNext()) {
			Map.Entry<AspectItem, List<AspectType>> e = discoveryIT.next();
			if(e.getKey() == null || e.getValue() == null || e.getValue().isEmpty()) {
				discoveryIT.remove();
				continue;
			}
			CompoundTag discoveryEntry = new CompoundTag();
			AspectManager.writeAspectItemToNbt(e.getKey(), discoveryEntry, registries);
			ListTag aspectListCompound = new ListTag();
			for(AspectType type : e.getValue()) {
				aspectListCompound.add(type.writeToNBT(new CompoundTag()));
			}
			discoveryEntry.put("aspects", aspectListCompound);
			discoveryList.add(discoveryEntry);
		}
		tag.put("discoveries", discoveryList);
		return tag;
	}

	/**
	 * Updates this discovery container from an NBT
	 * @param tag
	 * @return
	 */
	public DiscoveryContainer<T> updateFromNBT(CompoundTag tag, HolderLookup.Provider registries, boolean save) {
		this.discoveredStaticAspects.clear();
		ListTag discoveryList = tag.getList("discoveries", Tag.TAG_COMPOUND);
		for (int i = 0; i < discoveryList.size(); i++) {
			CompoundTag discoveryEntry = discoveryList.getCompound(i);
			AspectItem item = AspectManager.readAspectItemFromNBT(discoveryEntry, registries);
			List<AspectType> aspectTypeList = new ArrayList<>();
			ListTag aspectListCompound = discoveryEntry.getList("aspects", Tag.TAG_COMPOUND);
			for (int c = 0; c < aspectListCompound.size(); c++) {
				CompoundTag aspectTypeCompound = aspectListCompound.getCompound(c);
				aspectTypeList.add(AspectType.readFromNBT(aspectTypeCompound));
			}
			this.discoveredStaticAspects.put(item, aspectTypeList);
		}
		if (save)
			this.saveContainer();
		return this;
	}

	/**
	 * Merges the discoveries of another container into this container
	 * @param other
	 * @return
	 */
	public DiscoveryContainer<T> mergeDiscoveries(DiscoveryContainer<?> other) {
		boolean changed = false;
		for (Map.Entry<AspectItem, List<AspectType>> entry : other.discoveredStaticAspects.entrySet()) {
			AspectItem otherItem = entry.getKey();
			List<AspectType> otherTypes = entry.getValue();
			if (!this.discoveredStaticAspects.containsKey(otherItem)) {
				this.discoveredStaticAspects.put(otherItem, otherTypes);
				changed = true;
			} else {
				List<AspectType> aspectTypes = this.discoveredStaticAspects.get(otherItem);
				for (AspectType otherType : otherTypes) {
					if (!aspectTypes.contains(otherType)) {
						aspectTypes.add(otherType);
						changed = true;
					}
				}
			}
		}
		if(changed)
			this.saveContainer();
		return this;
	}

	/**
	 * Returns the list of all the discovered aspects of the specified item.
	 * @param manager
	 * @param item
	 * @return
	 */
	public List<Aspect> getDiscoveredStaticAspects(AspectManager manager, AspectItem item) {
		List<Aspect> discoveredStaticAspects = new ArrayList<>();
		if(this.discoveredStaticAspects.containsKey(item)) {
			List<AspectType> discoveredAspects = this.discoveredStaticAspects.get(item);
			List<Aspect> staticAspects = manager.getStaticAspects(item);
			for(Aspect a : staticAspects) {
				if(discoveredAspects.contains(a.type))
					discoveredStaticAspects.add(a);
			}
		}
		return discoveredStaticAspects;
	}

	public static class AspectDiscovery {
		public final DiscoveryResult result;
		public final boolean successful;
		public final Aspect discovered;

		private AspectDiscovery(DiscoveryResult result, Aspect discovered, boolean successful) {
			this.result = result;
			this.discovered = discovered;
			this.successful = successful;
		}

		public enum DiscoveryResult {
			NONE, NEW, LAST, END
		}
	}

	/**
	 * Returns whether a player has a discovery provider to write to
	 * @param player
	 * @return
	 */
	public static boolean hasDiscoveryProvider(Player player) {
		Inventory inventory = player.getInventory();
		for(int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);
			if(!stack.isEmpty() && stack.getItem() instanceof DiscoveryProvider)
				return true;
		}
		return false;
	}

	/**
	 * Returns a list of all writable discovery containers in the inventory of a player
	 * @param player
	 * @return
	 */
	public static List<DiscoveryContainer<?>> getWritableDiscoveryContainers(Player player) {
		List<DiscoveryContainer<?>> containerList = new ArrayList<DiscoveryContainer<?>>();
		Inventory inventory = player.getInventory();
		for(int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);
			if(!stack.isEmpty() && stack.getItem() instanceof DiscoveryProvider<?>) {
				@SuppressWarnings("unchecked")
				DiscoveryProvider<ItemStack> provider = (DiscoveryProvider<ItemStack>) stack.getItem();
				DiscoveryContainer<?> container = provider.getContainer(stack);
				if(container != null)
					containerList.add(container);
			}
		}
		return containerList;
	}

	/**
	 * Merges all discovery containers in the inventory of a player into one discovery container.
	 * Mostly used to get the combined knowledge of the player.
	 * @param player
	 * @return
	 */
	public static DiscoveryContainer<?> getMergedDiscoveryContainer(Player player) {
		List<DiscoveryContainer<?>> containerList = getWritableDiscoveryContainers(player);
		DiscoveryContainer<?> merged = DiscoveryContainer.empty();
		for(DiscoveryContainer<?> container : containerList) {
			if(container != null)
				merged.mergeDiscoveries(container);
		}
		return merged;
	}

	/**
	 * Adds a discovered aspect to all discovery containers of the player
	 * @param player
	 * @param item
	 * @param type
	 */
	public static void addDiscoveryToContainers(Player player, AspectItem item, AspectType type) {
		List<DiscoveryContainer<?>> discoveryContainers = getWritableDiscoveryContainers(player);
		for(DiscoveryContainer<?> container : discoveryContainers)
			container.addDiscovery(item, type);
	}
}
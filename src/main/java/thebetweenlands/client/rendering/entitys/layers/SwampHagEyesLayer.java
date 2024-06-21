package thebetweenlands.client.rendering.entitys.layers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.client.rendering.model.entity.ModelSwampHag;
import thebetweenlands.common.entitys.BetweenlandsEntity;

public class SwampHagEyesLayer<T extends BetweenlandsEntity, M extends ModelSwampHag<T>> extends EyesLayer<T, M> {

	private static final ResourceLocation SWAMP_HAG_EYES_TEXTURE = new ResourceLocation(TheBetweenlands.ID, "textures/entity/swamp_hag_eyes.png");
	private static final RenderType SWAMP_HAG_EYES = RenderType.eyes(new ResourceLocation(TheBetweenlands.ID, "textures/entity/swamp_hag_eyes.png"));
	
	public SwampHagEyesLayer(RenderLayerParent<T, M> p_116981_) {
		super(p_116981_);
	}
	
	@Override
	public RenderType renderType() {
		return SWAMP_HAG_EYES;
	}

}
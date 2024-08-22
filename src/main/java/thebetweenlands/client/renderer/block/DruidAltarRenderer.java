package thebetweenlands.client.renderer.block;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.block.DruidAltarModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.block.CenserBlock;
import thebetweenlands.common.block.DruidAltarBlock;
import thebetweenlands.common.block.entity.DruidAltarBlockEntity;

public class DruidAltarRenderer implements BlockEntityRenderer<DruidAltarBlockEntity> {

	private final DruidAltarModel altar;
	private final DruidAltarModel stones;
	private final ResourceLocation ACTIVE = TheBetweenlands.prefix("textures/entity/block/druid_altar_active.png");
	private final ResourceLocation ACTIVEGLOW = TheBetweenlands.prefix("textures/entity/block/druid_altar_active_glow.png");
	private final ResourceLocation NORMAL = TheBetweenlands.prefix("textures/entity/block/druid_altar.png");
	private final ResourceLocation NORMALGLOW = TheBetweenlands.prefix("textures/entity/block/druid_altar_glow.png");
	private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);

	public DruidAltarRenderer(BlockEntityRendererProvider.Context context) {
		this.altar = new DruidAltarModel(context.bakeLayer(BLModelLayers.DRUID_ALTAR));
		this.stones = new DruidAltarModel(context.bakeLayer(BLModelLayers.DRUID_STONES));
	}

	@Override
	public void render(DruidAltarBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource source, int light, int overlay) {

		stack.pushPose();
		stack.translate(0.5F, 1.0F, 0.5F);
		stack.mulPose(Axis.XP.rotationDegrees(180.0F));
		stack.translate(0.0F, 1.0F, 0.0F);
		stack.scale(-1.0F, 1.0F, 1.0F);
		//altar
		stack.pushPose();
		this.altar.renderToBuffer(stack, source.getBuffer(RenderType.entityCutoutNoCull(entity.getBlockState().getValue(DruidAltarBlock.ACTIVE) ? ACTIVE : NORMAL)), light, overlay);
		this.altar.renderToBuffer(stack, source.getBuffer(RenderType.entityCutoutNoCull(entity.getBlockState().getValue(DruidAltarBlock.ACTIVE) ? ACTIVEGLOW : NORMALGLOW)), LightTexture.FULL_BRIGHT, overlay);
		stack.popPose();

		//stones
		float renderRotation = entity.rotation + (entity.rotation - entity.prevRotation) * partialTicks;
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(renderRotation));
		stack.translate(0.0F, -1.5F, 0.0F);
		this.stones.renderToBuffer(stack, source.getBuffer(RenderType.entityCutoutNoCull(entity.getBlockState().getValue(DruidAltarBlock.ACTIVE) ? ACTIVE : NORMAL)), light, overlay);
		this.stones.renderToBuffer(stack, source.getBuffer(RenderType.entityCutoutNoCull(entity.getBlockState().getValue(DruidAltarBlock.ACTIVE) ? ACTIVEGLOW : NORMALGLOW)), LightTexture.FULL_BRIGHT, overlay);
		stack.popPose();

		//Animate the 4 talisman pieces
		double yOff = 1.2D;
		if (entity.getBlockState().getValue(DruidAltarBlock.ACTIVE) && entity.craftingProgress != 0) {
			yOff = Math.min(entity.renderYOffset + (entity.renderYOffset - entity.prevRenderYOffset) * partialTicks, DruidAltarBlockEntity.FINAL_HEIGHT + 1.0D);

			stack.pushPose();
			stack.translate(0.5D, 3.1D, 0.5D);
			stack.mulPose(Axis.YP.rotation(renderRotation * 2.0F));
			float shineScale = (float) (0.04f * Math.pow(1.0D - (DruidAltarBlockEntity.FINAL_HEIGHT + 1.0D - yOff) / DruidAltarBlockEntity.FINAL_HEIGHT, 12));
			stack.scale(shineScale, shineScale, shineScale);
			this.renderShine(stack, source.getBuffer(RenderType.dragonRays()), (float) Math.sin(Math.toRadians(renderRotation)) / 2.0f - 0.2f, (int) (80 * Math.pow(1.0D - (DruidAltarBlockEntity.FINAL_HEIGHT + 1.0D - yOff) / DruidAltarBlockEntity.FINAL_HEIGHT, 12)));
			stack.popPose();
		}
		for (int xi = 0; xi < 2; xi++) {
			for (int zi = 0; zi < 2; zi++) {
				ItemStack item = entity.getItem(zi * 2 + xi + 1);
				if (item.isEmpty()) {
					continue;
				}
				float xOff = xi == 0 ? -0.18f : 1.18f;
				float zOff = zi == 0 ? -0.18f : 1.18f;
				stack.pushPose();
				stack.translate(xOff, 1, zOff);
				this.renderCone(stack, source.getBuffer(RenderType.dragonRays()), 5);
				stack.popPose();
				Vector3d midVec = new Vector3d();
				midVec.x = 0.5F;
				midVec.z = 0.5F;
				Vector3d diffVec = new Vector3d();
				diffVec.x = xOff - midVec.x;
				diffVec.z = zOff - midVec.z;
				double rProgress = 1.0D - Math.pow(1.0D - (DruidAltarBlockEntity.FINAL_HEIGHT + 1.0D - yOff) / DruidAltarBlockEntity.FINAL_HEIGHT, 6);
				diffVec.x *= rProgress;
				diffVec.z *= rProgress;
				midVec.x += diffVec.x;
				midVec.z += diffVec.z;
				stack.pushPose();
				stack.translate(midVec.x, yOff, midVec.z);
				stack.scale(0.3f, 0.3f, 0.3f);
				stack.mulPose(Axis.YP.rotation(-renderRotation * 2.0f));
				Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemDisplayContext.FIXED, light, overlay, stack, source, null, 0);
				stack.popPose();
			}
		}

		//Render swamp talisman
		ItemStack itemTalisman = entity.getItem(0);
		if (!itemTalisman.isEmpty()) {
			stack.pushPose();
			stack.translate(0.5D, 3.1D, 0.5D);
			stack.mulPose(Axis.YP.rotation(-renderRotation * 2.0F));
			stack.scale(0.04F, 0.04F, 0.04F);
			this.renderShine(stack, source.getBuffer(RenderType.dragonRays()), (float) Math.sin(Math.toRadians(renderRotation)) / 2.0F - 0.2F, 80);
			stack.popPose();
			stack.pushPose();
			stack.translate(0.5D, 3.1D, 0.5D);
			stack.scale(0.3f, 0.3f, 0.3f);
			stack.mulPose(Axis.YP.rotation(-renderRotation * 2.0F));
			Minecraft.getInstance().getItemRenderer().renderStatic(itemTalisman, ItemDisplayContext.FIXED, light, overlay, stack, source, null, 0);
			stack.popPose();
		}

		stack.popPose();
	}

	private void renderShine(PoseStack poseStack, VertexConsumer buffer, float rotation, int iterations) {
		float f2 = 0.0f;
		if (rotation > 0.8F) {
			f2 = (rotation - 0.8F) / 0.2F;
		}
		int i = FastColor.ARGB32.colorFromFloat(1.0F - f2, 1.0F, 1.0F, 1.0F);
		RandomSource randomsource = RandomSource.create(432L);
		Vector3f vector3f = new Vector3f();
		Vector3f vector3f1 = new Vector3f();
		Vector3f vector3f2 = new Vector3f();
		Vector3f vector3f3 = new Vector3f();
		Quaternionf quaternionf = new Quaternionf();

		for (int l = 0; l < iterations; l++) {
			quaternionf.rotationXYZ(
					randomsource.nextFloat() * (float) (Math.PI * 2),
					randomsource.nextFloat() * (float) (Math.PI * 2),
					randomsource.nextFloat() * (float) (Math.PI * 2))
				.rotateXYZ(
					randomsource.nextFloat() * (float) (Math.PI * 2),
					randomsource.nextFloat() * (float) (Math.PI * 2),
					randomsource.nextFloat() * (float) (Math.PI * 2) + rotation * (float) (Math.PI / 2));
			poseStack.mulPose(quaternionf);
			float pos1 = randomsource.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
			float pos2 = randomsource.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
			vector3f1.set(-HALF_SQRT_3 * pos2, pos1, -0.5F * pos2);
			vector3f2.set(HALF_SQRT_3 * pos2, pos1, -0.5F * pos2);
			vector3f3.set(0.0F, pos1, pos2);
			PoseStack.Pose posestack$pose = poseStack.last();
			buffer.addVertex(posestack$pose, vector3f).setColor(i);
			buffer.addVertex(posestack$pose, vector3f1).setColor(16711935);
			buffer.addVertex(posestack$pose, vector3f2).setColor(16711935);
			buffer.addVertex(posestack$pose, vector3f).setColor(i);
			buffer.addVertex(posestack$pose, vector3f2).setColor(16711935);
			buffer.addVertex(posestack$pose, vector3f3).setColor(16711935);
			buffer.addVertex(posestack$pose, vector3f).setColor(i);
			buffer.addVertex(posestack$pose, vector3f3).setColor(16711935);
			buffer.addVertex(posestack$pose, vector3f1).setColor(16711935);
		}

		poseStack.popPose();
	}

	private void renderCone(PoseStack stack, VertexConsumer consumer, int faces) {
		stack.pushPose();
		float step = 360.0f / (float) faces;

		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
		RenderSystem.enableCull();
		RenderSystem.depthMask(false);

		for (float i = 0; i < 360.0f; i += step) {
			float lr = 0.1F;
			float ur = 0.3F;
			float height = 0.2F;
			float sin = Mth.sin((float) Math.toRadians(i));
			float cos = Mth.cos((float) Math.toRadians(i));
			float sin2 = Mth.sin((float) Math.toRadians(i + step));
			float cos2 = Mth.cos((float) Math.toRadians(i + step));

			consumer.addVertex(sin * lr, 0, cos * lr).setColor(255, 255, 255, 0);
			consumer.addVertex(sin2 * lr, 0, cos2 * lr).setColor(255, 255, 255, 0);

			consumer.addVertex(sin2 * ur, height, cos2 * ur).setColor(0, 0, 255, 60);
			consumer.addVertex(sin * ur, height, cos * ur).setColor(0, 0, 255, 60);

			consumer.addVertex(sin * ur, height, cos * ur).setColor(0, 0, 255, 60);
			consumer.addVertex(sin2 * ur, height, cos2 * ur).setColor(0, 0, 255, 60);

			consumer.addVertex(sin2 * lr, 0, cos2 * lr).setColor(255, 255, 255, 0);
			consumer.addVertex(sin * lr, 0, cos * lr).setColor(255, 255, 255, 0);
		}

		RenderSystem.depthMask(true);
		RenderSystem.disableCull();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		//Lighting.setupForFlatItems();
		stack.popPose();
	}
}
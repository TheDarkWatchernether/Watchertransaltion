package thebetweenlands.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import javax.imageio.ImageIO;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;

public class RenderUtils {
	private static int frameCounter = 0;
	private static int renderTickCounter = 0;
	public static boolean framebufferSupported;
	public static FboMode framebufferType;

	public static int getRenderTickCounter() {
		return renderTickCounter;
	}

	public static int getFrameCounter() {
		return frameCounter;
	}

	public final void initType() {
		GLCapabilities capabilities = GL.getCapabilities();
		framebufferSupported = capabilities.OpenGL14 && (capabilities.GL_ARB_framebuffer_object || capabilities.GL_EXT_framebuffer_object || capabilities.OpenGL30);
		if (capabilities.OpenGL30) {
			framebufferType = FboMode.BASE;
		} else if (capabilities.GL_ARB_framebuffer_object) {
			framebufferType = FboMode.ARB;
		} else if (capabilities.GL_EXT_framebuffer_object) {
			framebufferType = FboMode.EXT;
		}
	}

	public static void onRenderTick(RenderLevelStageEvent event) {
		if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
			frameCounter++;
		}
	}

	public static void onClientTick(ClientTickEvent.Post event) {
		renderTickCounter++;
	}

	@SuppressWarnings("unchecked")
	public static <T extends RenderLayer<?, ?>> T getRenderLayer(LivingEntityRenderer<?, ?> renderer, Class<T> cls, boolean subclasses) {
		try {
			List<? extends  RenderLayer<?, ?>> layers = renderer.layers;
			for(RenderLayer<?, ?> layer : layers) {
				if(subclasses ? cls.isInstance(layer) : cls == layer.getClass()) {
					return (T) layer;
				}
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return null;
	}

	public static boolean doesRendererHaveLayer(LivingEntityRenderer<?, ?> renderer, Class<? extends RenderLayer<?, ?>> cls, boolean subclasses) {
		return getRenderLayer(renderer, cls, subclasses) != null;
	}

	/**
	 * Saves the texture of an FBO to the specified PNG file.
	 *
	 * @param file
	 * @param fbo
	 */
	public static void saveFboToFile(File file, RenderTarget fbo) {
		// I dislike the hacky hack fix, but you can't suppress the try-with-resources warning and -Werror won't leave you alone if you don't remove it.
		FramebufferStack.State state = null;
		try {
			state = FramebufferStack.push();
			
			fbo.bindWrite(false);

			GL11.glReadBuffer(GL11.GL_FRONT);
			int width = fbo.viewWidth;
			int height= fbo.viewHeight;
			int bpp = 4;
			ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
			GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

			String format = "PNG";
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					int i = (x + (width * y)) * bpp;
					int r = buffer.get(i) & 0xFF;
					int g = buffer.get(i + 1) & 0xFF;
					int b = buffer.get(i + 2) & 0xFF;
					int a = buffer.get(i + 3) & 0xFF;
					image.setRGB(x, height - (y + 1), (a << 24) | (r << 16) | (g << 8) | b);
				}
			}

			try {
				ImageIO.write(image, format, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if(state != null) {
				state.close();
			}
		}
	}

	enum FboMode {
		BASE,
		ARB,
		EXT
	}
}
import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScaleEffect implements Effect {

	@Override
	public BufferedImage applyEffect(BufferedImage img) {
		BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = gray.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return gray;
	}
}

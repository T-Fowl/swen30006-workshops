import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDocument {

	public BufferedImage image;
	public Effect[] transforms;
	public String outputFile;

	public ImageDocument(BufferedImage image) {
		this.image = image;
	}

	public boolean addTransform(Effect effect) {
		image = effect.applyEffect(image);
		return true;
	}

	public boolean addTransformations(Effect[] transformations) {
		for (Effect transformation : transformations) {
			if (!addTransform(transformation)) {
				return false;
			}
		}
		return true;
	}

	public void renderToImage(String outFile) {
		this.outputFile = outFile;
		try {

			for (String suffix : ImageIO.getReaderFileSuffixes()) {
				if (outFile.endsWith("." + suffix)) {
					ImageIO.write(image, suffix, new File(outFile));
					return;
				}
			}

			throw new IllegalArgumentException("Unknown file extension: " + outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

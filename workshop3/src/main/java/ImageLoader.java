import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
	private String fileName;

	public ImageLoader(String fileName) {
		this.fileName = fileName;
	}

	public ImageDocument loadImage() {
		try {
			BufferedImage buff = ImageIO.read(new File(fileName));
			return new ImageDocument(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void writeImage(Image img, String outputFile) {
	}
}

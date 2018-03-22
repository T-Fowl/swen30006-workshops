public class ImageShop {

	private ImageDocument image;

	private Effect[] processTransforms(String[] args) {
		return null;
	}

	private void processImage(String image, Effect[] transforms) {

	}

	public static void main(String[] args) {
		ImageLoader loader = new ImageLoader("./workshop3/input.jpg");
		ImageDocument image = loader.loadImage();

		EffectLibrary library = new EffectLibrary();
		library.initialise();

		Effect effect = library.getEffect("grayScale");
		image.addTransform(effect);

		image.renderToImage("./workshop3/output.jpg");
	}
}

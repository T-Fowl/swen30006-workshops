public class ImageShop {

	private ImageDocument image;

	private Effect[] processTransforms(String[] args) {
		return null;
	}

	private void processImage(String image, Effect[] transforms) {

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: ImageShop <input> <output>");
			System.exit(0);
		}

		ImageLoader loader = new ImageLoader(args[0]);
		ImageDocument image = loader.loadImage();

		EffectLibrary library = new EffectLibrary();
		library.initialise();

		Effect effect = library.getEffect("grayScale");
		image.addTransform(effect);

		image.renderToImage(args[1]);
	}
}

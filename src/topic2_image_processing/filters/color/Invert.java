package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Pravi "negativ" od date slike. Svaku komponentu (r, g, b) menja njenom suprotnom vrednoscu.
 */
public class Invert extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		double r = input.getRed();
		double g = input.getGreen();
		double b = input.getBlue();

		return new Color(1-r, 1-g, 1-b, input.getOpacity());
	}

}

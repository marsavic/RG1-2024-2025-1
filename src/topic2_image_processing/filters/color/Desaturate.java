package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Umanjuje zasicenost bojama slike, prema parametru datom u konstruktoru filtera.
 */
public class Desaturate extends ColorFilter {
	final double saturationFactor;
	
	
	public Desaturate(double saturation) {
		this.saturationFactor = saturation;
	}


	@Override
	public Color processColor(Color input) {
		return Color.hsb(
				input.getHue(),
				input.getSaturation() * saturationFactor,
				input.getBrightness(),
				input.getOpacity()
				);
	}
	
}	

package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Pretvara sliku u jednobojnu, zadrzavajuci samo originalnu osvetljenost svakog piksela. Hue i saturation se
 * postavljaju na vrednosti zadate u parametrima konstruktora.
 */
public class Colorize extends ColorFilter {
	final double hue, saturation;
	
	
	public Colorize(double hue, double saturation) {
		this.hue = hue;
		this.saturation = saturation;
	}


	@Override
	public Color processColor(Color input) {
		return Color.hsb(hue, saturation, input.getBrightness());
	}
	
}

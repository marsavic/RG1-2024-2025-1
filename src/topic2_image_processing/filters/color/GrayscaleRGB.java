package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Filter koji sliku pretvara u "crno-belu" sliku, odnosno sliku iscrtanu samo nijansama sive. Radi tako sto racuna
 * prosecnu vrednost tri komponente (r, g, b), i vraca boju koja odgovara nijansi sive svetline jednake tom proseku.
 */
public class GrayscaleRGB extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		double r = input.getRed();
		double g = input.getGreen();
		double b = input.getBlue();
		
		double s = (r + g + b) / 3;
		
		return new Color(s, s, s, input.getOpacity());
	}
	
}	

package topic2_image_processing.filters.color;

import javafx.scene.paint.Color;
import topic2_image_processing.filters.ColorFilter;

/**
 * Primenjuje "sepia" efekat, koji sve boje pretvara u nijasnu zuto-braon boje iste osvetljenosti i srednje saturacije.
 */
public class Sepia extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		return Color.hsb(
				input.getHue(),
				input.getSaturation(),
				input.getBrightness(),
				input.getOpacity()
				);
	}
	
}

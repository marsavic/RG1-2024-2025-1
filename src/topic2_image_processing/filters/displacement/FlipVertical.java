package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;

/**
 * Obrce sliku po y-osi.
 */
public class FlipVertical extends DisplacementFilter {
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		// Vracamo poziciju koja je osno simetricna u odnosu na srednju horizontalu.
		return new Vector(dst.x, dim.y - 1 - dst.y);
	}
	
}

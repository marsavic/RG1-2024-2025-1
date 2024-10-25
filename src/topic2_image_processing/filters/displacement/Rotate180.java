package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;

/**
 * Rotira sliku za 180 stepeni.
 */
public class Rotate180 extends DisplacementFilter {

	@Override
	public Vector source(Vector dst, Vector dim) {
		// Vracamo poziciju koja je centralno simetricna u odosu na centar slike.
		return new Vector(dim.x - 1 - dst.x, dim.y - 1 - dst.y);
	}
	
}

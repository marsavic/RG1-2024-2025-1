package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


public class Lens extends DisplacementFilter {
	final double f;
	
	
	public Lens(double f) {
		this.f = f;
	}


	@Override
	public Vector source(Vector dst, Vector dim) {
		Vector s = dst.div(dim).mul(2).sub(new Vector(1, 1));
		double r = s.norm();                                  // Udaljenost od centra, normalizovana tako da je radius 1.
		double phi = s.angle();                               // Ugao koji zaklapa sa x-osom
		double l = Math.pow(r, f);                            // Udaljenost src-a od centra je funkcija udaljenosti dst-a od centra		
		Vector p = Vector.polar(l, phi);                      // Trazena pozicija izrazena u normalizovanim koordinatama	
		return p.add(new Vector(1, 1)).div(2).mul(dim);       // Vracamo se u koordinatni sistem slike 
	}
	
}

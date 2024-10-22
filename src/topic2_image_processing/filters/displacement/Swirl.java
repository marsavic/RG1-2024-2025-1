package topic2_image_processing.filters.displacement;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


public class Swirl extends DisplacementFilter {
	final double f, a;
	
	
	public Swirl(double f, double a) {
		this.f = f;
		this.a = a;
	}


	@Override
	public Vector source(Vector dst, Vector dim) {
		Vector s = dst.div(dim).mul(2).sub(new Vector(1, 1)); // Pretvaramo poziciju u normalizovane koordinate. Vektor s se nalazi u [-1,1]x[-1,1]. 
		double r = s.norm();                                  // Udaljenost od centra, vrednost iz [0,1].
		double phi = s.angle() + a * Math.cos(f*r*Math.PI*2); // s.angle() je ugao koji vektor s zaklapa sa x-osom. Na to dodajemo pomeraj prema navedenoj funkciji.
		Vector p = Vector.polar(r, phi);                      // Trazenu poziciju dobijamo sa izracunatim novim uglom, a istom udaljenosti od centra.	
		return p.add(new Vector(1, 1)).div(2).mul(dim);       // Vracamo se u koordinatni sistem slike.
	}
	
}

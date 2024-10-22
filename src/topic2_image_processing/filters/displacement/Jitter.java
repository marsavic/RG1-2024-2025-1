package topic2_image_processing.filters.displacement;

import java.util.Random;

import mars.geometry.Vector;
import topic2_image_processing.filters.DisplacementFilter;


/**
 * Svaki piksel rezultujuce slike se dobija kopiranjem nasumicno odabranog piksela iz njegove okoline.
 * Maksimalna udaljenost sa koje moze da se kopira je zadata kao parametar konstruktora.
 */
public class Jitter extends DisplacementFilter {
	final double r;
	Random random = new Random();
	
	
	public Jitter(double r) {
		this.r = r;
	}
	
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		// Source pozicija nam je slucajno generisana pozicija unutar kruga poluprecnika r sa centrom u dst.
		
		return dst;
	}
	
}

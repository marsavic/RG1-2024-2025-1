package topic3_rasterization;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

import java.util.function.Predicate;


public class Antialiasing implements Drawing {

	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetInteger(min = 1)
	int subdivision = 3; // Svaki piksel će biti podeljen na subdivision*subdivision subpixela. 
	                     // Sto je subdivision veci, to je antialias bolji, ali sporiji.

	
	CameraSimple camera = new CameraSimple();

	
	
	/**
	 * Crta antialiasovanu sliku implicitno zadatog oblika.
	 * 
	 * @param inside Funkcija: Vector -> Boolean. Vraca da li je zadata pozicija unutar oblika. Vektor (0, 0) je u sredini slike. 
	 * @param sizeX Sirina slike.
	 * @param sizeY Visina slike.
	 * @param subdivision Svaki piksel se deli na subdivison^2 subpiksela koji se koriste za antialiasing.
	 * @return Antialiasovana slika zadatog oblika.
	 */
	Image antialiasedShape(Predicate<Vector> inside, int sizeX, int sizeY, int subdivision) {
		WritableImage image = new WritableImage(sizeX, sizeY);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				// Pixel (x, y)
				
				int count      = 0; // Koliko subpiksela je unutar oblika.
				int countTotal = 0; // Ukupan broj testiranih subpiksela.
				
				for (int sy = 0; sy < subdivision; sy++) {
					for (int sx = 0; sx < subdivision; sx++) {
						// Subpiksel (sx, sy) piksela (x, y). 
						
						// Vektor p je polozaj centra subpiksela.
						Vector p = new Vector(
								x - sizeX / 2.0 + (sx + 0.5) / subdivision,
								sizeY / 2.0 - y + (sy + 0.5) / subdivision
						);
					
						if (inside.test(p)) { // Ako je posmatrani subpixel u obliku, povecavamo brojac.
							count++;
						}
						countTotal++;
					}
				}
				
				double k = 1.0 * count / countTotal; // Udeo subpixela koji su u obliku.				
				pw.setColor(x, y, Color.gray(1.0, k));
			}
		}
		
		return image;
	}


	@Override
	public void init(View view) {
		view.setImageSmoothing(false);
	}
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		DrawingUtils.clear(view, Color.gray(0.125));

		// Poluravan x + 0.173*y >= 0
//		Image image = antialiasedShape(p -> p.x + 0.173 * p.y >= 0, 120, 120, subdivision);

		// Kruznica poluprecnika 50 i debljine 2.
		Image image = antialiasedShape(p -> Math.abs(p.norm() - 50) < 1, 120, 120, subdivision);

		// Grafik sinusoide
//		Image image = antialiasedShape(p -> Math.abs(p.y - 50 * Math.sin(p.x / 50)) <= 1, 300, 120, subdivision);

		// [*] Perspective checkerboard
//		double d = 283;
//		Image image = antialiasedShape(p -> (Numeric.mod(p.x * (d / (d / 2 - p.y)), 100) < 50) ^ (Numeric.mod(d * (d / (d / 2 - p.y) - 1), 100) < 50), (int) d, (int) d, subdivision);
		
//		 [*] Kinder Bueno
//		Image image = antialiasedShape(p -> (Math.cos(2 * Math.PI * p.x / 80) * Math.cos(2 * Math.PI * p.y / 80))  > Math.sin(time + (p.x + p.y) / 100), 200, 200, subdivision);

		view.drawImageCentered(Vector.ZERO, image);
	}
	
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}

	
}

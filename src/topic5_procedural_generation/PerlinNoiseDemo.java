package topic5_procedural_generation;


import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDoubleLogarithmic;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.random.fixed.continuous.PerlinNoise;

import java.util.function.DoubleUnaryOperator;

public class PerlinNoiseDemo implements Drawing {

	int size = 400;
	
	@GadgetAnimation
	double z = 0;
	
	@GadgetDoubleLogarithmic(min = 1, max = 100)
	double zoom = 6.0;
	
	
	@GadgetInteger(min = 0, max = 6)
	int functionIndex = 0;
	
	
	// Niz funkcija koje mozemo isprobavati nad dobijenim perlin noise vrednostima.
	// Svaka funkcija kao parametar prima broj koji je iz intervala [-1, 1], a mora vratiti
	// broj iz intervala [0, 1]
	DoubleUnaryOperator[] functions = {
			k -> (k + 1) / 2,                  // Samo linearno preslikano na interval [0, 1]
			k -> k < 0 ? 0 : 1,                // Threshold funkcija
			k -> (int) ((k + 1) * 5) / 10.0,   // Posterizacija - zaokruživanje na svega nekoliko vrednosti.
			k -> Math.abs(k),                  // Plazma efekat - apsolutna vrednost
			k -> 1 - Math.abs(k),              // Inverz apsolutne vrednosti
			k -> (Math.sin(k*20) + 1) / 2,     // Sinus
			k -> Math.abs(Math.sin(k*20))      // Apsolutna vrednost sinusa
	};
	
	
	public Image image() {
		PerlinNoise pn = new PerlinNoise(6238957209870546723l);
		
		WritableImage image = new WritableImage(size, size);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = x / image.getWidth();              // Normalizujemo na interval [0, 1)
				double dy = y / image.getHeight();
				
				double k = pn.getValue(dx*zoom, dy*zoom, z);   // Vrednost iz [-1, 1]
				pw.setColor(x, y, Color.gray(functions[functionIndex].applyAsDouble(k)));
			}
		}
		
		return image;
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		Image image = image();
		view.drawImageCentered(Vector.ZERO, image);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
		
}

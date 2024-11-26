package topic5_procedural_generation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetDoubleLogarithmic;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.random.RNG;
import mars.random.fixed.continuous.PerlinNoise;


public class Terrain implements Drawing {
	
	int size = 500;
	
	@GadgetDoubleLogarithmic(min = 0.01, max = 100)
	double zoom = 3;
	
	@GadgetDouble(min = 0, max = 5)
	double z = 0.5;
	
	@GadgetInteger(min = 0, max = 10)
	int nLevels = 8;
	

	double factor = 2;
	
	Color c0 = Color.hsb(210, 0.7, 0.8); // duboka voda
	double h0 = -0.5;
	Color c1 = Color.hsb(210, 0.7, 0.9); // voda
	double h1 =  0.0;
	Color c2 = Color.hsb( 50, 0.5, 1.0); // pesak
	double h2 =  0.1;
	Color c3 = Color.hsb(110, 0.6, 0.8); // trava
	double h3 =  0.4;
	Color c4 = Color.hsb( 30, 0.6, 0.5); // planina
	double h4 =  0.8;
	Color c5 = Color.hsb(  0, 0.0, 1.0); // sneg
	
	
	PerlinNoise[] pn;
	
	{
		setup();
	}
	
	
	void setup() {
		pn = new PerlinNoise[10];
		RNG rng = new RNG();
		for (int i = 0; i < pn.length; i++) {
			pn[i] = new PerlinNoise(rng.nextLong());
		}
	}
	
	
	Color colorForHeight(double h) {
		if (h < h0) return c0;
		if (h < h1) return c1;
		if (h < h2) return c2;
		if (h < h3) return c3;
		if (h < h4) return c4;
		return c5;
	}
	
	
	public Image generateMap() {
		WritableImage image = new WritableImage(size, size);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = x / image.getWidth(); // Normalizujemo na interval [0, 1)
				double dy = y / image.getHeight();
				
				dx *= zoom;
				dy *= zoom;
				
				// Postizemo vise nivoa detaljnosti tako sto Perlin Noise koristimo vise puta
				// skaliran razlicitim faktorima.
				
				double amplitude = 1.0;
				double frequency = 1.0;
				double h = 0.0;
				
				for (int l = 0; l < nLevels; l++) {
					h += amplitude * pn[l].getValue(frequency * dx, frequency * dy, z);
					amplitude /= factor;
					frequency *= factor;
				}
				
				pw.setColor(x, y, colorForHeight(h));
			}
		}
		
		return image;
	}
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		view.drawImageCentered(Vector.ZERO, generateMap());
	}
	
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKeyPress(KeyCode.ENTER)) {
			setup();
		}
	}
	
	
	
	public static void main(String[] args) {
		Options options = Options.redrawOnEvents();
		options.drawingSize = new Vector(666, 666);
		DrawingApplication.launch(options);
	}
	
}

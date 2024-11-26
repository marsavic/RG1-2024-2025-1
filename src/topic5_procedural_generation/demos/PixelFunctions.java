package topic5_procedural_generation.demos;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.random.fixed.continuous.PerlinNoise;
import mars.utils.Numeric;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


// Sve slike su generisane samo na osnovu funkcije koja za dati polozaj piksela vraca boju tog piksela.

// Ovaj program je samo za demonstraciju, ne morate se truditi da razumete detalje implementacije.


@FunctionalInterface
interface PixelColorFunction {
	int colorAt(int x, int y);
}


public class PixelFunctions implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;

	@GadgetInteger(min = 0, max = 6)
	int index = 0;

	
	int w = 480;
	int h = 480;
	
	
	
	List<PixelColorFunction> functions = Arrays.asList(
		this::fXOR,
		this::fWavesStanding,
		this::fWavesCircular,
		this::fRainbow,
		this::fFantasy,
		this::fIce,
		this::fFire
	);
	
	
	
	int fXOR(int x, int y) {
		int x_ = (int) (256.0 * x / w); 
		int y_ = (int) (256.0 * y / h); 
		int z = x_ ^ y_;
		double v = z / 256.0;
		return colorCode(v, 0.5 * v*v, 0.5 * v*v);
	}
	
	
	int fWavesStanding(int x, int y) {
		double v = Numeric.cosT(x/240.0) * Numeric.cosT(y/240.0) * Numeric.cosT(time / 4);
		v = (v + 1) / 2; // [-1, 1] -> [0, 1]
		return colorCode(0.5 * v, v, 0.3 * v*v);	
	}

	
	int fWavesCircular(int x, int y) {
		Vector p = new Vector(2.0 * x / w - 1, 2.0 * y / h - 1);
		double v = Numeric.cosT(2*p.norm() - time) * Numeric.cosT(p.angle() + 0.375);
		v = (v + 1) / 2;    // [-1, 1] -> [0, 1]
		v = 0.2 + v * 0.8;  // [0, 1] -> [0.2, 1]		
		return colorCode(0.5 * v*v, 0.7 * v, v);
	}

	
	final PerlinNoise pn0 = new PerlinNoise( 2039491571936435253l);
	final PerlinNoise pn1 = new PerlinNoise(-1943758375034528585l);
	final PerlinNoise pn2 = new PerlinNoise( 5987717928374283406l);
	
	
	int fRainbow(int x, int y) {
		final double s = 200;
		
		double cr = (pn0.getValue(x/s, y/s, time) + 1) / 2;
		double cg = (pn1.getValue(x/s, y/s, time) + 1) / 2;
		double cb = (pn2.getValue(x/s, y/s, time) + 1) / 2;
		
		return colorCode(cr, cg, cb);
	}
	

	int fFantasy(int x, int y) {
		final double s = 200;
		
		double v1 = (pn0.getValue(x/s, y/s, 0.7 * time) + 1) / 2;
		double v0 = (pn1.getValue(x/s, y/s, 1.0 * time) + 1) / 2;
		double v2 = (pn2.getValue(x/s, y/s, 1.3 * time) + 1) / 2;
		
		double m = Math.max(v0, Math.max(v1, v2));
		double cr = m*m;
		double cb = 0.7 * m*m*m;
		double cg = 0.5 * m*m*m;
		
		return colorCode(cr, cg, cb);
	}

	
	int fIce(int x, int y) {
		final double c = 100;
		final double d = 100;
		final double k = 7;
		
		double dx = pn0.getValue(x/c, y/c, 0.1 * time);
		double dy = pn1.getValue(x/c, y/c, 0.1 * time / 6);
		double v  = pn2.getValue(x/d + k*dx, y/d + k*dy, 0.3 * time);
		
		v = (v + 1) / 2;
		
		double cb = 0.20 + 0.80 * v;
		double cg = 0.15 + 0.85 * v*v;
		double cr = 0.10 + 0.90 * v*v*v;
		
		return colorCode(cr, cg, cb);
	}
	

	int fFire(int x, int y) {
		final double c = 43;
		final double d = 107;
		final double k = 0.5;
		final double ySpeed = 2.3;
		final double jiggleFreq = 2.1;
		
		double dx = pn0.getValue(x/c, y/c + ySpeed * time, jiggleFreq * time);
		double dy = pn1.getValue(x/c, y/c + ySpeed * time, jiggleFreq * time);
		double v  = pn2.getValue(x/d + k*dx, y/d + k*dy + ySpeed * time, 0.9 * time);
		
		v = (v + 1) / 2;
		v *= 1.0 * y/h;
		double cr = v;
		double cg = v*v*v;
		double cb = cg*cg;

		return colorCode(cr, cg, cb);
	}
	

	
	PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
	
	
	int colorCode(double r, double g, double b) {
		return (0xFF << 24) | ((int) (255 * r) << 16) | ((int) (255 * g) << 8) | (int) (255 * b);
	}

	
	int[] intArray = new int[w * h];
	
	void drawPixelsToImage(WritableImage img, PixelColorFunction f) {
		IntStream.range(0, h).parallel().forEach(y -> {
			int o = y*w;
			for (int x = 0; x < w; x++) {
				intArray[o+x] = f.colorAt(x, y);
			}
		});
		img.getPixelWriter().setPixels(0, 0, w, h, pixelFormat, intArray, 0, w);
	}

	
	WritableImage img = new WritableImage(w, h);
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.BLACK);

		drawPixelsToImage(img, functions.get(index));
		view.drawImageCentered(Vector.ZERO, img);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}

}

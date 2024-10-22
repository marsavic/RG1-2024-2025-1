package topic2_image_processing.filters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ConvolutionFilter extends Filter {
	double[][] kernel;
	int wk, hk;
	
	
	public ConvolutionFilter(double[][] kernel) {
		if (kernel.length % 2 != 1 || kernel[0].length % 2 != 1) {
			throw new IllegalArgumentException("Both the width and the height of the kernel must be odd.");
		}
		this.kernel = kernel.clone();
		
		wk = kernel[0].length;
		hk = kernel.length;
	}
	
	
	double clamp(double v) {
		if (v < 0) return 0;
		if (v > 1) return 1;
		return v;
	}
	
	
	@Override
	public Image process(Image input) {
		int w = (int) input.getWidth();
		int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		// Za svaki piksel u output slici
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				double r = 0;
				double g = 0;
				double b = 0;
				
				// Za svaki element kernela
				for (int dy = 0; dy < hk; dy++) {
					for (int dx = 0; dx < wk; dx++) {
						
						int px = Math.floorMod(x - (dx - wk/2), w);
						int py = Math.floorMod(y - (dy - hk/2), h);
						
						Color c = pr.getColor(px, py);
						
						r += kernel[dy][dx] * c.getRed();
						g += kernel[dy][dx] * c.getGreen();
						b += kernel[dy][dx] * c.getBlue();						
					}
				}

				Color outputColor = new Color(clamp(r), clamp(g), clamp(b), 1);
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}

	
	// Sum = 1
	
	// Efekat zamucenja. Nije lepo zamucenje, ali se moze implementirati tako da bude jako brzo.
	// Najcesce se primenjuje nekoliko puta za redom, i rezultat dobijen na taj nacin priblizno
	// odgovara Gaussian blur-u (koji je znatno lepsi).
	public static final double[][] BOX_BLUR_3x3 = new double[][] {
		{ 1.0/9, 1.0/9, 1.0/9 },
		{ 1.0/9, 1.0/9, 1.0/9 },
		{ 1.0/9, 1.0/9, 1.0/9 }
	};
	
	// Malo lepse zamucenje od box blura.
	public static final double[][] BLUR_3x3 = new double[][] {
		{ 1.0/16, 2.0/16, 1.0/16 },
		{ 2.0/16, 4.0/16, 2.0/16 },
		{ 1.0/16, 2.0/16, 1.0/16 }
	};
	
	// Postize umereni efekat zamucenja
	public static final double[][] BLUR_5x5 = new double[][] {
		{  1.0/256,  4.0/256,  6.0/256,  4.0/256,  1.0/256},
		{  4.0/256, 16.0/256, 24.0/256, 16.0/256,  4.0/256},
		{  6.0/256, 24.0/256, 36.0/256, 24.0/256,  6.0/256},
		{  4.0/256, 16.0/256, 24.0/256, 16.0/256,  4.0/256},
		{  1.0/256,  4.0/256,  6.0/256,  4.0/256,  1.0/256},
	};
	
	// Izoštrava sliku, cineci prelaze izmedju kontrastnih oblasti izrazenijim.
	public static final double[][] SHARPEN = new double[][] {
		{  0, -1,  0},
		{ -1,  5, -1},
		{  0, -1,  0},
	};
	
	
	// Sum = 0
	
	// "Detektuje ivice", ostavljajuci vidljivim samo prelaze izmedju kontrastnih oblasti.
	public static final double[][] DETECT_EDGES = new double[][] {
		{ -1, -1, -1},
		{ -1,  8, -1},
		{ -1, -1, -1},
	};

}

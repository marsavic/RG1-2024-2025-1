package topic1_colors_and_bitmaps;


import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

public class ColorsAndBitmaps implements Drawing {

	public Image imgSolidColor() {
		// Svi pikseli su ljubicasti.
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.PURPLE);
			}
		}
		
		return image;
	}

	
	public Image imgLinearGradient() {
		// Linearni gradijent po x-osi od crne do plave.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(0, 0, x / image.getWidth(), 1));
			}
		}
		
		return image;
	}
	
	
	public Image imgLinearGradient2() {
		// Linearni gradijent crvene po x-osi i zelene po y-osi.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(x / image.getWidth(), y / image.getHeight(), 0, 1));
			}
		}
		
		return image;
	}
	
	

	public Image imgRadialGradient() {
		// Radijalni gradijent sive.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                // Udaljenost po y-osi od centra (od -1 do 1).
				double s = Math.sqrt(dx*dx + dy*dy);          // Udaljenost od centra.
				if (s > 1) {
					s = 1.0;
				}
				pw.setColor(x, y, Color.gray(1-s));           // Isto sto i new Color(1-s, 1-s, 1-s, 1).
			}
		}
		
		return image;
	}


	public Image imgRadialGradientOpacity() {
		// Radijalni gradijent providnosti.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                // Udaljenost po y-osi od centra (od -1 do 1).
				double s = Math.sqrt(dx*dx + dy*dy);          // Udaljenost od centra.
				if (s > 1) {
					s = 1.0;
				}
				pw.setColor(x, y, new Color(1, 1, 1, 1-s));
			}
		}
		
		return image;
	}

	
	public Image imgWave() {
		// Intenzitet boje je talasne funkcije po x osi. 
		
		WritableImage image = new WritableImage(500, 200);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double s = Math.cos(2 * Math.PI * (x + 50) / 100) / 2 + 0.5; 
				pw.setColor(x, y, Color.gray(s));
			}
		}
		
		return image;
	}
	
	
	public Image imgWaves() {
		// Crvena i zelena komponenta su talasne funkcije po x odnosno po y, plava je uvek 1. 
		
		WritableImage image = new WritableImage(500, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double r = Math.cos(2 * Math.PI * x / 100) / 2 + 0.5; 
				double g = Math.cos(2 * Math.PI * y / 100) / 2 + 0.5;
				double b = 1;
				pw.setColor(x, y, new Color(r, g, b, 1));
			}
		}
		
		return image;
	}

	
	public Image imgDiagonals() {
		// Crvena i zelena komponenta su funkcija dijagonalnih udaljenosti (x+y, odnosno x-y osa).
		// Kompononte boja se "pale" i "gase" periodicno duz tih osa. Plava je uvek 0.5.

		WritableImage image = new WritableImage(500, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				float r = (x + y) % 200 > 100 ? 0 : 1; 
				float g = (x - y + 400) % 200 > 100 ? 0 : 1;
				float b = 0.5f;
				pw.setColor(x, y, new Color(r, g, b, 1));
			}
		}
		
		return image;
	}

	
	public Image imgFixedHue() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(0, x/image.getWidth(), y/image.getHeight()));
			}
		}
		
		return image;
	}

	
	public Image imgFixedSaturation() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(360*x/image.getWidth(), 1, y/image.getHeight()));
			}
		}
		
		return image;
	}
	
	
	public Image imgFixedBrightness() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(360*x/image.getWidth(), y/image.getHeight(), 1));
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk1() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)

				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, 1, 1));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk2() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)
				
				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, 1, d));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgDisk3() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)
				
				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, d, 1));
				}
			}
		}
		
		return image;
	}
	
	
	public Image imgRainbow() {
		int w = 500;
		int h = 500;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		double r0 = 0.5;
		double r1 = 0.75;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				
				Color c;
				
				if (dy > 0) {
					c = Color.hsb(120, 0.6, 0.7*(dy/2+0.5));
				} else {
					if (d >= r0 && d <= r1) {
						double k = (d - r0) / (r1 - r0);
						c = Color.hsb(360*(1-k) - 30, 0.7, 1);
					} else {
						c = Color.hsb(210, 1+dy, 1);
					}
				}
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}
	
	
	public Image imgTablecloth() {
		// Stolnjak u hipsterskim kafanama
		
		int w = 410;
		int h = 410;
		
		int d = 10;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color c;
				if ((x + y) % 2 == 0) {
					c = x % (2*d) < d ? Color.RED : Color.WHITE;
				} else {
					c = y % (2*d) < d ? Color.RED : Color.WHITE;
				}
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}
	
	
	public Image imgPlot() {
		// Grafik sinusoide
		
		int w = 400;
		int h = 500;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		
		for (int x = 0; x < w; x++) {
			double dx = (2.0 * x / w) - 1;                              // dx je u [-1, 1)
			double dy = Math.sin(dx*Math.PI);                           // dy je u [-1, 1]
			int y = (int) Math.round((h-1) / 2.0 - dy * (h-1) / 2.0);   // Obrcemo y osu!
					
			pw.setColor(x, y, Color.WHITE);
		}
		
		return image;
	}

	
	// ============================================================================================
	
	
	Image[] images;
	

	@Override
	public void init(View view) {
		images = new Image[] {
			imgSolidColor(),
			imgLinearGradient(),
			imgLinearGradient2(),
			imgRadialGradient(),
			imgRadialGradientOpacity(),
			
			imgFixedHue(),
			imgFixedSaturation(),
			imgFixedBrightness(),
			imgDisk1(),
			imgDisk2(),
			imgDisk3(),
			imgRainbow(),

			imgWave(),
			imgWaves(),
			imgDiagonals(),
			imgTablecloth(),
			
			imgPlot()
		};
		
		view.setImageSmoothing(false);
	}
	
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1);
	
	@GadgetInteger(min = 0, max = 16)
	int imageIndex = 0;


	CameraSimple camera = new CameraSimple();
	
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(Vector.ZERO, images[imageIndex]);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}


	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}
	
}

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
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class ColorsRGB implements Drawing {
	
	@GadgetColorPicker
	Color colorBackground = Color.gray(0.125);
	
	@GadgetInteger(min = 1, max = 400)
	int size = 400;
	
	@GadgetDouble
	double blue = 0.5;
	
	
	
	public Image image() {
		// Linearni gradijent crvene po x-osi i zelene po y-osi.
		
		WritableImage image = new WritableImage(size, size);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = x / image.getWidth();            // Normalizujemo na interval [0, 1). Mozemo i podeliti sa (image.getWidth() - 1), da bi rezultat bio u intervalu [0, 1].
				double dy = y / image.getHeight();
				pw.setColor(x, y, new Color(dx, dy, blue, 1));
			}
		}
		
		return image;
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(Vector.ZERO, image());
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}

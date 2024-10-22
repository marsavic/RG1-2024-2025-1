package topic2_image_processing;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetImageChooser;
import mars.geometry.Vector;
import topic2_image_processing.filters.misc.Sobel;



public class DemoSobel implements Drawing {
	
	@GadgetImageChooser
	Image originalImage;
	
	@GadgetDouble(min = 0, max = 1)
	double angle = 0;
	
	@GadgetBoolean
	Boolean applyFilter = false;
	
	
	
	@Override
	public void init(View view) {
		originalImage = new Image("images/couple.png");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Vector[][] gradient = Sobel.gradient(originalImage);
		Image filteredImage = Sobel.imgEmboss(gradient, angle);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : originalImage);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(Options.redrawOnEvents());
	}
}

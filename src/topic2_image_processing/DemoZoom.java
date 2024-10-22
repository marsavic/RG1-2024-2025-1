package topic2_image_processing;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetImageChooser;
import mars.geometry.Vector;
import topic2_image_processing.filters.Filter;
import topic2_image_processing.filters.displacement.Zoom;


public class DemoZoom implements Drawing {
	
	@GadgetImageChooser
	Image originalImage;
	
	@GadgetDouble(min = 0, max = 4)
	double k = 1;


	
	@Override
	public void init(View view) {
		originalImage = new Image("images/monalisa.png");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		Filter filter = new Zoom(k);
		Image filteredImage = filter.process(originalImage);
		view.drawImageCentered(Vector.ZERO, filteredImage);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(Options.redrawOnEvents());
	}
}

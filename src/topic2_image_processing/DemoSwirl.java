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
import topic2_image_processing.filters.displacement.Swirl;


public class DemoSwirl implements Drawing {
	
	@GadgetImageChooser
	Image originalImage;
	
	@GadgetDouble(min = 0, max = 16)
	double f = 6;

	@GadgetDouble(min = -1, max = 1)
	double a = 0.012;


	
	@Override
	public void init(View view) {
		originalImage = new Image("images/Mona Lisa.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		Filter filter = new Swirl(f, a);
		Image filteredImage = filter.process(originalImage);
		view.drawImageCentered(Vector.ZERO, filteredImage);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(Options.redrawOnEvents());
	}
}

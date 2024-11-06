package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class SmileyFace implements Drawing {

	@GadgetDouble(min = 0, max = 300)
	double r = 200;

	@GadgetDouble(min = 0, max = 200)
	double xEye = 50;

	@GadgetDouble(min = 0, max = 200)
	double yEye = 50;
	
	@GadgetDouble(min = 0, max = 200)
	double rEye = 10;
	
	@GadgetDouble(min = 0, max = 300)
	double rMouth = 100;
	
	@GadgetDouble(min = 0, max = 0.5)
	double phiMouth = 1/3.0;
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		// glava
		view.setFill(Color.hsb(60, 0.8, 1.0));
		view.fillCircleCentered(Vector.ZERO, r);
		
		// oci
		view.setFill(Color.BLACK);
		view.fillCircleCentered(new Vector( xEye, yEye), rEye);
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);
		
		// usta
		view.setLineWidth(16);
		view.strokeArcCentered(Vector.ZERO, new Vector(rMouth), 0.75 - phiMouth / 2, phiMouth);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}

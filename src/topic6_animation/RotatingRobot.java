package topic6_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;


public class RotatingRobot implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	double l1 = 150.0;                // Duzine duzi
	double l2 =  80.0;
	double l3 =  45.0;
	

	@GadgetDouble(min = -2, max = 2)
	double omega1 =  0.27;            // Ugaone brzine u obrtima po sekundi

	@GadgetDouble(min = -2, max = 2)
	double omega2 = -0.51;

	@GadgetDouble(min = -2, max = 2)
	double omega3 =  0.88;

	
	@GadgetVector
	Vector p0 = Vector.ZERO;          // Centar pocetnog kruga
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0.0, 0.2));
		
		double theta1 = omega1 * time;   // Uglovi na kojima se nalaze krugovi u trenutku time
		double theta2 = omega2 * time;
		double theta3 = omega3 * time;

		Vector p1 = p0.add(Vector.polar(l1, theta1));  // Polozaj svakog od krugova
		Vector p2 = p1.add(Vector.polar(l2, theta2));
		Vector p3 = p2.add(Vector.polar(l3, theta3));
		
		double r = 10;
	
		view.setLineWidth(5);
		view.setStroke(Color.hsb(0, 0, 1));
		view.strokeLine(p0, p1);
		view.strokeLine(p1, p2);
		view.strokeLine(p2, p3);

		view.setFill(Color.hsb(0, 0.0, 0.5));
		view.fillCircleCentered(p0, r);

		view.setFill(Color.hsb(0, 0.5, 0.9));
		view.fillCircleCentered(p1, r);

		view.setFill(Color.hsb(120, 0.5, 0.9));
		view.fillCircleCentered(p2, r);

		view.setFill(Color.hsb(240, 0.5, 0.9));
		view.fillCircleCentered(p3, r);
	}


	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}


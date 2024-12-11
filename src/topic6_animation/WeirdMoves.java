package topic6_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class WeirdMoves implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;

	@GadgetDouble(min = -1, max = 1)
	double omega = 0.2;                 // Ugaona brzina [turn/s]
	
	@GadgetDouble(min = 0, max = 300)
	double r = 200.0;

	@GadgetInteger
	int n = 5;
	
	

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));
		
		
		double theta = omega * time;          // Ugao temena 0 
		
		Vector[] p = new Vector[n];
		Vector[] q = new Vector[n];
		for (int i = 0; i < n; i++) {
			double phi = theta + 1.0 * i / n;
			p[i] = Vector.polar(r, phi);
			q[i] = Vector.polar(r, -phi);
		}
		
		view.setFill(Color.hsb(240, 0.7, 0.8));
		view.fillPolygon(p);
		view.fillPolygon(q);
	}		
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}

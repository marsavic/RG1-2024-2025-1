package topic6_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;


public class ProjectileMotion implements Drawing {
	
	@GadgetAnimation(min = 0, max = 5)
	double t = 0.0;
	
	@GadgetVector
	Vector p0 = new Vector(-250, -150);    // Polozaj u trenutku t = 0.

	@GadgetVector
	Vector v0 = new Vector(150, 400);      // Brzina u trenutku t = 0.
	
	@GadgetVector
	Vector a = new Vector(0, -200);        // Ubrzanje (konstantno)
	
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Vector p = a.mul(t*t/2).add(v0.mul(t)).add(p0);     // Polozaj u trenutku t
		Vector v = v0.add(a.mul(t));                        // Brzina u trenutku t
		
		view.setStroke(Color.CHARTREUSE);
		DrawingUtils.drawArrow(view, p, v);
		
		view.setStroke(Color.DARKSALMON);
		DrawingUtils.drawArrow(view, p, a);
		
		view.setFill(Color.GHOSTWHITE);
		view.fillCircleCentered(p, 6);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}

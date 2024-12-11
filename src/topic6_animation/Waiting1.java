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
import mars.utils.Numeric;


public class Waiting1 implements Drawing {
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetInteger
	int n = 9;
	
	@GadgetDouble(min = 0, max = 100)
	double rDist = 30.0;
	
	@GadgetDouble(min = 0, max = 20)
	double rCirc = 8.0;
	
	@GadgetDouble(min = -0.5, max = 0.5)
	double rotationalSpeed = -0.07;
	
	@GadgetDouble(min = -0.5, max = 0.5)
	double stepTime = 0.08;
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));
		
		for (int i = 0; i < n; i++) {
			Vector c = Vector.polar(rDist, 1.0*i/n + rotationalSpeed*time);
			
			double k = 1 - Numeric.mod(time / stepTime - i, n) / n;
			view.setFill(Color.gray(1, k));
			view.fillCircleCentered(c, rCirc);
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 300);
	}
	
}

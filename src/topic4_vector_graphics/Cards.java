package topic4_vector_graphics;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.random.RNG;


public class Cards implements Drawing {

	@GadgetInteger(min = 1, max = 16)
	int nCards = 6;

	Vector size = new Vector(150, 200);
	Vector a = new Vector(20, 20);
		
	double r = 48;
	
	Vector d = new Vector(16, 16);

	
	void drawCard(View view) {
	}
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(Transformation.translation(new Vector(-120, -120)));
		DrawingUtils.clear(view, Color.hsb(120, 0.5, 0.2));
	
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
	
}

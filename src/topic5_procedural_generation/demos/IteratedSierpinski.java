package topic5_procedural_generation.demos;

// mars

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.random.RNG;


public class IteratedSierpinski implements Drawing {
	
	Vector[] vertices;
	
	@GadgetInteger
	int nVertices = 3;
	
	@GadgetVector
	Vector c = new Vector(0.5, 0);
	
	@GadgetInteger(min = 0, max = 1000000)
	int nIterations = 50000;
	
	
	
	public void draw(View view) {
		view.setTransformation(Transformation.scaling(300));
		
		DrawingUtils.clear(view, Color.gray(0.125));
		view.setFill(Color.gray(1.0, 0.5));
		
		vertices = new Vector[nVertices];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = Vector.polar(1, 1.0 * i / vertices.length);
		}
		
		RNG rng = new RNG(-2307298370289059238L);
		Vector p = vertices[0];
		for (int iter = 0; iter < nIterations; iter++) {
			Vector q = vertices[rng.nextInt(vertices.length)];
			p = (q.sub(p)).mulComplex(c).add(p);
			view.fillCircleCentered(p, 0.001);
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(720, 720);
	}
	
}

package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.utils.Graphics;


public class Transformations implements Drawing {
	
	@GadgetBoolean
	boolean applyTransformation = true;
	
	@GadgetInteger(min = 0, max = 13)
	int index = 0;
	
	
	Transformation[] transformations = {
			Transformation.identity(),
			Transformation.translation(new Vector(120, 70)),
			Transformation.rotation(0.1),
			Transformation.scaling(1.5),
			Transformation.scaling(0.8, 1.2),
			Transformation.shearing(0, 0.5),
			Transformation.shearing(0.2, 0.5),
			Transformation.scaling(-1, 1),
			new Transformation().rotate(0.1).translate(new Vector(120, 70)),
			new Transformation().translate(new Vector(120, 70)).rotate(0.1),
			new Transformation().scale(2.0/3.0, 3.0/2.0).rotate(0.25),
			new Transformation().rotate(0.25).scale(2.0/3.0, 3.0/2.0),
			new Transformation().translate(new Vector(60, 90)).scale(1.5),
			new Transformation().scale(1.5).translate(new Vector(60, 90)),
	};
	
	String[] descriptions = {
			"Transformation.identity()",
			"Transformation.translation(new Vector(120, 70))",
			"Transformation.rotation(0.1)",
			"Transformation.scaling(1.5)",
			"Transformation.scaling(0.8, 1.2)",
			"Transformation.shearing(0, 0.5)",
			"Transformation.shearing(0.2, 0.5)",
			"Transformation.scaling(-1, 1)",
			"new Transformation().rotate(0.1).translate(new Vector(120, 70))",
			"new Transformation().translate(new Vector(120, 70)).rotate(0.1)",
			"new Transformation().scale(2.0/3.0, 3.0/2.0).rotate(0.25)",
			"new Transformation().rotate(0.25).scale(2.0/3.0, 3.0/2.0)",
			"new Transformation().translate(new Vector(60, 90)).scale(1.5)",
			"new Transformation().scale(1.5).translate(new Vector(60, 90))",
	};
	
	
	Color color = Color.MEDIUMORCHID;
	
	
	void drawObject(View view, Transformation t) {
		view.stateStore();
		
		view.setTransformation(t);

		DrawingUtils.drawGrid(view, 100, 10, Color.gray(1, 0.125));

		Vector[] polygon = {
				new Vector(  0,   0),
				new Vector(  0, 100),
				new Vector(100, 100),
				new Vector(100,  80),
				new Vector( 20,  80),
				new Vector( 20,  60),
				new Vector( 60,  60),
				new Vector( 60,  40),
				new Vector( 20,  40),
				new Vector( 20,   0),
		};

		
		view.setFill(Graphics.scaleOpacity(color, 0.25));
		view.fillPolygon(polygon);
		
		view.setStroke(color);
		view.strokePolygon(polygon);
		
		view.stateRestore();
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Transformation t = applyTransformation ? transformations[index] : Transformation.identity();
		drawObject(view, t);
		
		DrawingUtils.drawInfoText(view, transformations[index].toStringAsMatrixExtended().split("\n"));
		DrawingUtils.drawInfoText(view, descriptions[index]);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(720, 720);
	}
	
}

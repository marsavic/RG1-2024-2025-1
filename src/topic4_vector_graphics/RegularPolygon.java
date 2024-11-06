package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class RegularPolygon implements Drawing {
	@GadgetInteger
	int n = 6;

	@GadgetDouble(min = 0, max = 300)
	double r = 200.0;                          // Poluprecnik poligona (rastojanje od centra do temena)

	@GadgetDouble(min = 0, max = 1)
	double alpha = 0.0;                        // Ugao pocetnog temena (u okretima)
	

	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));

		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}

package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class Building implements Drawing {
	
	@GadgetInteger(min = 1, max = 10)
	int n = 2;                           // Broj vrsta

	@GadgetInteger(min = 1, max = 10)
	int m = 3;                           // Broj kolona
	
	
	Vector w = new Vector(30, 20);  // Dimenzije prozora
	Vector g = new Vector(10, 10);  // Razmak izmedju prozora

	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}

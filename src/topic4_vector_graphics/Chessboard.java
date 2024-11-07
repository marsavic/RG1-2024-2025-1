package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class Chessboard implements Drawing {

	@GadgetInteger(min = 1, max = 20)
	int n = 8;                           // Broj vrsta

	@GadgetInteger(min = 1, max = 20)
	int m = 8;                           // Broj kolona

	@GadgetDouble(min = 0, max = 100)
	double d = 50.0;                     // Duzina stranice jednog polja
	
	
	@GadgetColorPicker
	Color c1 = Color.hsb(30, 0.4, 0.4);  // Tamnija boja
	
	@GadgetColorPicker
	Color c2 = Color.hsb(30, 0.4, 0.6);  // Svetlija boja
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.1));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				// Svetlija i tamnija polja naizmenično
				if ((i + j) % 2 == 0) {
					view.setFill(c1);
				} else {
					view.setFill(c2);					
				}
				
				// d*(j, i) - d*(m/2, n/2) = d*(j - m/2, i - n/2)
				view.fillRect(new Vector(d).mul(new Vector(j - m/2.0, i - n/2.0)), new Vector(d));
			}
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}

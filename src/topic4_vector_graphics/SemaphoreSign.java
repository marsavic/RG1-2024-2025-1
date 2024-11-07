package topic4_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class SemaphoreSign implements Drawing {
	
	private double a = 200;
	private Vector[] corners = new Vector[] {
			new Vector(a, 0),
			new Vector(0, a),
			new Vector(-a, 0),
			new Vector(0, -a),
	};
	
	private double r = 32; // Poluprecnik svetla
	private double d = 12; // Vertikalni razmak izmedju svetla

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.25));
		
		// Kvadrat
		view.setFill(Color.hsb(60, 0.7, 0.96));
		view.fillPolygon(corners);
		
		// Ivica kvadrata
		view.setLineWidth(20);
		view.setStroke(Color.BLACK);
		view.strokePolygon(corners);
				
		// Pravougaonik
		view.setFill(Color.BLACK);
		view.fillRectCentered(Vector.ZERO, new Vector(r+d, 3*r + 2*d));
		
		// Crveno svetlo
		view.setFill(Color.hsb(0, 0.7, 0.96));
		view.fillCircleCentered(new Vector(0, 2*r+d), r);

		// Žuto svetlo
		view.setFill(Color.hsb(60, 0.7, 0.96));
		view.fillCircleCentered(Vector.ZERO, r);
		
		// Zeleno svetlo
		view.setFill(Color.hsb(120, 0.7, 0.96));
		view.fillCircleCentered(new Vector(0, -2*r-d), r);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}

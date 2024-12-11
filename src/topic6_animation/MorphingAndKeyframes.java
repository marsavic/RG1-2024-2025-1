package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;


public class MorphingAndKeyframes implements Drawing {

	final Vector fieldP = new Vector(-400, -300);    // Donje levo teme pravougaonika u kome se svi poligoni nalaze.
	final Vector fieldD = new Vector( 800,  600);    // Dijagonala pravougaonika u kome se svi poligoni nalaz.
	
	final int maxNVertices = 100;                    // Maksimalan broj temena poligona.
	final int maxNKeyframes = 100;                   // Maksimalan broj keyframe-ova.

	final double stateDuration = 1.0;                // Trajanje jednog stanja (vreme izmedju dva keyframe-a).
	
	Vector[][] polygons;                             // polygons[k][i] = i-ti cvor k-tog poligona
	double[] hues;                                   // hues[k] = hue k-tog poligona

	

	@GadgetAnimation(start = true)
	double time = 0.0;

	@GadgetBoolean
	boolean showKeyframes = true;

	@GadgetInteger(min = 3, max = maxNVertices)
	int nVertices = 3;
	
	@GadgetInteger(min = 1, max = maxNKeyframes)
	int nStates = 3;
	

	
	double smootherstep(double x) {
		// 6x^5 - 15x^4 + 10x^3
		return x * x * x * (x * (x * 6 - 15) + 10);
	}

	
	@Override
	public void init(View view) {
		// Generisemo poligone na slucajan nacin unutar zadatih granica.
		
		polygons = new Vector[maxNKeyframes][maxNVertices];
		hues = new double[maxNKeyframes];

		for (int k = 0; k < maxNKeyframes; k++) {
			
			for (int i = 0; i < maxNVertices; i++) {
				polygons[k][i] = Vector.randomInBox(fieldP, fieldD);
			}
			hues[k] = 360 * Math.random();
		}		
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));
	
		if (showKeyframes) {
			view.setLineDashes(4);                          // Isprekidana linija, crtice i razmaci su duzine 4.
			view.setLineCap(StrokeLineCap.BUTT);            // Krajevi linija su ravni.
			view.setLineJoin(StrokeLineJoin.ROUND);         // Spojevi linija su zaobljeni.
			view.setLineWidth(1);
			view.setStroke(Color.hsb(0, 0, 1, 0.2));
			for (int k = 0; k < nStates; k++) {
				
				view.strokePolygon(nVertices, polygons[k]);
				
			}			
		}
		view.setLineDashes(null); 
		
		
		
		
		int k0 = (int)(time / stateDuration) % nStates;
		int k1 = (k0 + 1) % nStates;
		
		double t = (time % stateDuration) / stateDuration;
		
		
		
		
		t = smootherstep(t);
		
		Vector[] polygon = new Vector[nVertices];
		for (int i = 0; i < nVertices; i++) {
			polygon[i] = Vector.lerp(polygons[k0][i], polygons[k1][i], t);
		}
		
		Color c0 = Color.hsb(hues[k0], 0.7, 0.7, 0.7); 
		Color c1 = Color.hsb(hues[k1], 0.7, 0.7, 0.7);
		
		view.setFill(c0.interpolate(c1, t));
		view.fillPolygon(polygon);
		
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}

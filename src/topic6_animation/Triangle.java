package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class Triangle implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;

	@GadgetVector
	Vector p0 = new Vector(-200, -200);

	@GadgetVector
	Vector p1 = new Vector(300, -100);
	
	@GadgetVector
	Vector p2 = new Vector(100, 200);
	
	@GadgetDouble(min = 0, max = 1000)
	double speed = 300;                                 // Brzina kretanja loptice (u pikselima po sekundi).
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setLineJoin(StrokeLineJoin.ROUND);         // Spojevi linija su zaobljeni.
		view.setStroke(Color.DODGERBLUE);
		view.setLineWidth(4);
		view.strokePolygon(p0, p1, p2);
		
		double l0 = p0.distanceTo(p1);                  // Duzina stranice 0
		double l1 = p1.distanceTo(p2);                  // Duzina stranice 1
		double l2 = p2.distanceTo(p0);                  // Duzina stranice 2
		double l = l0 + l1 + l2;                        // Obim trougla
		
		// Vremena normalizujemo tako da je vreme obilazenja trougla jednako 1.0.
		
		double dt0 = l0 / l;                            // Normalizovano trajanje boravka na stranici 0
		double dt1 = l1 / l;                            // Normalizovano trajanje boravka na stranici 1
		double dt2 = l2 / l;                            // Normalizovano trajanje boravka na stranici 2
		                                                // Ukupno je dt0 + dt1 + dt2 = 1, sto je normalizovano vreme obilaska trougla.
		
		double t0 = 0;                                  // Momenat prolaska kroz teme 0
		double t1 = t0 + dt0;                           // Momenat prolaska kroz teme 1
		double t2 = t1 + dt1;                           // Momenat prolaska kroz teme 2
		
		// Racunamo parametar t iz [0, 1), koji nam govori gde smo u trouglu. Kada je 0 onda smo u tacki p0.
		// time * speed                     = ukupno predjena razdaljina
		// time * speed / l                 = koliko puta smo obisli trougao (npr. 3.46 puta)
		// Numeric.mod(time * speed / l, 1) = gde smo trenutno u trouglu (npr. Numeric.mod(3.46, 1) = 0.46)
		// Numeric.mod(x, m) je kao x % m samo sto uvek vraca broj iz [0, m), tj. "lepo" radi i za negativne vrednosti.
		
		double t = Numeric.mod(time * speed / l, 1); 
		
		Vector q0, q1;    // Pocetna i krajnja tacka stranice na kojoj se nalazimo
		double k;         // Parametar interpolacije za trenutnu stranicu iz [0, 1). 0 - na pocetoj tacki smo, 1 - na krajnjoj tacki smo
		
		if (t < t1) { q0 = p0; q1 = p1; k = (t - t0) / dt0; } else   // Ako smo na stranici 0
		if (t < t2) { q0 = p1; q1 = p2; k = (t - t1) / dt1; } else   // Ako smo na stranici 1
		            { q0 = p2; q1 = p0; k = (t - t2) / dt2; }        // Ako smo na stranici 2
		
		Vector p = Vector.lerp(q0, q1, k); // Racunamo poziciju interpolacijom izmedju pocetne i krajnje tacke.
		
		view.setFill(Color.ORANGERED);
		view.fillCircleCentered(p, 16);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}

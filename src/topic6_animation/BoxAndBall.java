package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;


public class BoxAndBall implements Drawing {
	@GadgetAnimation
	double time = 0.0;
	
	double dt0 = 1.0;            // Trajanje stanja 0 - Podizanje poklopca
	double dt1 = 2.0;            // Trajanje stanja 1 - Ubacivanje lopte
	double dt2 = 1.0;            // Trajanje stanja 2 - Spustanje poklopca
	
	// Vremena keyfremova (trenutke prelaza izmedju stanja) racunamo sabiranjem trajanja stanja
	
	double time0 = 0.0;          //                        Vreme pocetka stanja 0
	double time1 = time0 + dt0;  // Vreme kraja stanja 0 = Vreme pocetka stanja 1
	double time2 = time1 + dt1;  // Vreme kraja stanja 1 = Vreme pocetka stanja 2
	double time3 = time2 + dt2;  // Vreme kraja stanja 2
	
	
	
	/* Sluzi da kretanje ucini manje "robotskim". Kretanje pocinje i zavrsava se lagano. */
	double smoothStep(double x) {
		return x * x * (3 - 2 * x);
	}
	
	
	/* Vraca t za loptu u zavisnosti od vremena */
	double tBall(double time) {
		if (time < time1) return 0;
		if (time < time2) return smoothStep((time - time1) / dt1);
		return 1;
	}
	
	
	/* Vraca t za kutiju u zavisnosti od vremena */
	double tBox(double time) {
		if (time < time0) return 0;
		if (time < time1) return smoothStep((time - time0) / dt0);
		if (time < time2) return 1;
		if (time < time3) return smoothStep(1 - (time - time2) / dt2);
		return 0;
	}
	
	
	/* Crta loptu za njeno t */
	void drawBall(View view, double t) {
		Vector p = Vector.lerp(new Vector(0, 150), new Vector(0, -50), t);
		
		view.setFill(Color.hsb(0, 0.6, 1.0));
		view.fillCircleCentered(p, 40);
	}
	
	
	/* Crta kutiju za njeno t */
	void drawBox(View view, double t) {
		double phi = 0.30 * t;
		
		Vector a = new Vector( 50,    0);
		Vector b = new Vector( 50, -100);
		Vector c = new Vector(-50, -100);
		Vector d = new Vector(-50,    0);
		Vector e = d.add(Vector.polar(100, phi));
		
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.setLineWidth(4);
		
		view.setFill  (Color.hsb(120, 0.5, 0.6));
		view.setStroke(Color.hsb(120, 0.5, 1.0));
		
		view.fillPolygon   (a, b, c, d);
		view.strokePolyline(a, b, c, d, e);
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.125));
		
		drawBall(view, tBall(time));
		drawBox (view, tBox (time));
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}

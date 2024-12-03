package topic6_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.utils.Numeric;

import java.util.function.DoubleUnaryOperator;


public class AnimationCurves implements Drawing {

	@GadgetAnimation(min = 0, max = 1)
	double time = 0.0;

	@GadgetInteger(min = 0, max = 7)
	int index = 0;


	Vector p0 = new Vector(-300, -200);
	Vector p1 = new Vector( 100,  200);
	double r = 20;
	
	Vector cBox = new Vector(-250, 150);
	Vector rBox = new Vector(  50,  50);
	
	int n = 3;
	int rPoly = 50;
	Vector cPoly = new Vector(250,  50);
	
	Vector cColorsCircle = new Vector( 50, -150);
	double rColorsCircle = 40;
	
	
	
	void drawScene(View view, double t) {
//      Tri nacina za interpolaciju vektora, svi daju (skoro) isti rezultat:
//			p0.mul(1-t).add(p1.mul(t))
//			p0.add(p1.sub(p0).mul(t))
//			Vector.lerp(p0, p1, t)

		// Pomeranje
		view.setFill(Color.CHOCOLATE);
		Vector p = Vector.lerp(p0, p1, t);
		view.fillCircleCentered(p, r);
		
		// Sirenje
		view.setFill(Color.VIOLET);
		view.fillRectCentered(cBox, rBox.mul(t));
		
		// Rotacija
		view.setFill(Color.DEEPSKYBLUE);
		Vector[] poly = new Vector[n];
		for (int i = 0; i < n; i++) {
			poly[i] = Vector.polar(rPoly, t + 1.0 * i / n).add(cPoly);
		}
		view.fillPolygon(poly);
		
		// Promena boje
		Color c0 = Color.ORANGERED;
		Color c1 = Color.LAWNGREEN;
		Color c = c0.interpolate(c1, t);
		view.setFill(c);
		view.fillCircleCentered(cColorsCircle, rColorsCircle);
	}


	
	double fLinear(double x) {
		return x;
	}
	
	double fEaseInOutQuadratic(double x) { // SmoothStep
		return x * x * (3 - 2*x);
	}
	
	double fEaseInOutQubic(double x) { // SmootherStep
		return x * x * x * (x * (x * 6 - 15) + 10);
	}
	
	double fEaseInQuadratic(double x) {
		return x*x;
	}
	
	double fEaseOutQuadratic(double x) {
		return 1-(1-x)*(1-x);
	}
	
	double fEaseOutBack(double x) {
		return 1-((x=1-x) * x * (2.70158 * x - 1.70158));
	}
	
	double fEaseOutBounce(double x) {
		if        (x < 1.00 / 2.75) {
			return 7.5625 * x * x;
		} else if (x < 2.00 / 2.75) {
			return 7.5625 * (x -= (1.5   / 2.75)) * x + 0.75;
		} else if (x < 2.50 / 2.75) {
			return 7.5625 * (x -= (2.25  / 2.75)) * x + 0.9375;
		} else {
			return 7.5625 * (x -= (2.625 / 2.75)) * x + 0.984375;
		}
	}
	
	double fEaseOutElastic(double x) {
		double f1 = Numeric.cosT(2*x);
		double f2 = Math.pow(2, 10*(-x));
		double minf2 = 0.000976563;
		double sclf2 = 0.999023;
		f2 = (f2 - minf2) / sclf2;
		return 1 - f1*f2;
	}
	
	
	
	DoubleUnaryOperator[] functions = new DoubleUnaryOperator[] {
			this::fLinear,
			this::fEaseInOutQuadratic,
			this::fEaseInOutQubic,
			this::fEaseInQuadratic,
			this::fEaseOutQuadratic,
			this::fEaseOutBack,
			this::fEaseOutBounce,
			this::fEaseOutElastic,
	};

	String[] descriptions = new String[] {
			"Linear",
			"EaseInOut quadratic (smoothstep)",
			"EaseInOut qubic (smootherstep)",
			"EaseIn quadratic",
			"EaseOut quadratic",
			"EaseOut back",
			"EaseOut bounce",
			"EaseOut elastic",
	};
	
			
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.125));
		drawScene(view, functions[index].applyAsDouble(time));
		DrawingUtils.drawInfoText(view, descriptions[index]);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
	
}

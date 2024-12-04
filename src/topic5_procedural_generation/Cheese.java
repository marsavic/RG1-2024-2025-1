package topic5_procedural_generation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Box;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;


public class Cheese implements Drawing {
	
	Box box = Box.createCentered(Vector.ZERO, new Vector(500, 500));
	
	double border = 4.0;
	double gap = 6.0;
	
	List<Hole> holes;
	
	
	@Override
	public void init(View view) {
		generateHoles();
	}
	
	
	double getMaxRadius(Vector c) {
		
	}
	
	
	Vector getCenter() {
		return Vector.randomInBox(box);
	}
	
	
	void generateHoles() {
		
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(50, 0.9, 0.9));
		
	}

	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKeyPress(KeyCode.ENTER)) {
			generateHoles();
		}
	}
	
	
	private static final class Hole {
		
		public static final double R_MIN = 8.0;
		public static final double R_MAX = 120.0;
		
		Vector c;
		double r;
		
		public Hole(Vector c, double r) {
			this.c = c;
			this.r = r;
		}	
	}	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}	
}

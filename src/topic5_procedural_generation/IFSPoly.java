package topic5_procedural_generation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;


public class IFSPoly implements Drawing {
	
	@GadgetInteger(min = 0)
	int nLevels = 5;
	
	@GadgetInteger(min = 1)
	int n = 5;
	
	double r = 100;
	
	
	
	private void drawSymbol(View view, int level) {
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineWidth(10);
		
		double k = Math.pow(0.8, level);
		view.setStroke(Color.hsb(0, 0.9, k * 0.9));
		
		for (int i = 0; i < n; i++) {
			int j = i + n/2;
			
			Vector pi = Vector.polar(r, 0.25 + 1.0 * i / n);			
			Vector pj = Vector.polar(r, 0.25 + 1.0 * j / n);
			
			view.strokeLine(pi, pj);
		}
		
		view.strokeCircle(Vector.ZERO, r);
	}
	
	
	private void drawIFS(View view, int level) {
		if (level == nLevels) {
			return;
		}

		for (int i = 0; i < n; i++) {
			view.stateStore();
			
			Vector s = Vector.polar(200, 0.25 + 1.0 * i / n);
			view.addTransformation(Transformation.scaling(-0.5).translate(s));
			
			drawIFS(view, level + 1);
			
			view.stateRestore();
		}
		
		drawSymbol(view, level);
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0));
		drawIFS(view, 0);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class HelloGraphics implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0;
	
	Font font = new Font(100);
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(Transformation.identity()
				.scale(0.1 * (Numeric.sinT(0.17 * time) + 3))
				.translate(new Vector(200 * Numeric.sinT(time * 0.21), 0))
				.rotate(0.17 * time + Numeric.sinT(time * 0.13))
		);
		DrawingUtils.clear(view, Color.BLACK);
		view.setFont(font);
		view.setTextAlign(TextAlignment.CENTER);
		view.setFill(Color.hsb(time * 23, 0.8, 1));
		view.fillText("Grafikaaaaaa!", Vector.ZERO);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(720, 720);
	}
	
}

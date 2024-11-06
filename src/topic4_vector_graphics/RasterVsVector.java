package topic4_vector_graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;


public class RasterVsVector implements Drawing {

	static {
		System.setProperty("prism.fontSizeLimit", "0.1");
	}

	@GadgetBoolean
	boolean interpolation = false;

	CameraSimple camera = new CameraSimple();
	
	Image image = img();

	
	Image img() {
		WritableImage image = new WritableImage(200, 200);
		PixelWriter pw = image.getPixelWriter();
		
		for (int j = 0; j < image.getHeight(); j++) {
			for (int i = 0; i < image.getWidth(); i++) {
				int x = i - 100;
				int y = j - 100;
				Color c = (x >= -60 && y >= -60 && x+y < 0) ? Color.BLACK : Color.SPRINGGREEN;
				pw.setColor(i, j, c);
			}
		}
		
		return image;
	}

	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setImageSmoothing(interpolation);
		view.drawImageCentered(new Vector(-200, 0), image);
		
		view.setFill(Color.HOTPINK);
		view.fillRectCentered(new Vector(200, 0), new Vector(100, 100));
		view.setFill(Color.BLACK);
		view.fillPolygon(new Vector(140, 60), new Vector(260, 60), new Vector(140, -60));

		view.setFill(Color.WHITE);
		view.fillText("Raster graphics", new Vector(-280, -120));
		view.fillText("Vector graphics", new Vector( 120, -120));
		
		DrawingUtils.drawGrid(view, 100, 5, Color.hsb(0, 0, 1, 0.125));
	}

	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}

	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 400);
	}

}

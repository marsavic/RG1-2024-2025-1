package topic2_image_processing.filters.binary;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import topic2_image_processing.filters.BinaryFilter;

/**
 * Stavlja drugu zadatu sliku preko prve, tako da je neprovidnost druge slike broj zadat u parametru konstruktora. 
 */
public class Overlap extends BinaryFilter {
	double opacity;
	

	public Overlap(double opacity) {
		this.opacity = opacity;
	}


	@Override
	public Image process(Image input1, Image input2) {
		final int w = (int) input1.getWidth();
		final int h = (int) input1.getHeight();
		
		if (input2.getWidth() != w || input2.getHeight() != h) {
			throw new IllegalArgumentException("Input images must have the same size.");
		}

		WritableImage output = new WritableImage(w, h);

		PixelReader pr1 = input1.getPixelReader();
		PixelReader pr2 = input2.getPixelReader();
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color inputColor1 = pr1.getColor(x, y);
				Color inputColor2 = pr2.getColor(x, y);
				
				Color outputColor = new Color(
						(1 - opacity) * inputColor1.getRed()     + opacity * inputColor2.getRed()    ,
						(1 - opacity) * inputColor1.getGreen()   + opacity * inputColor2.getGreen()  ,
						(1 - opacity) * inputColor1.getBlue()    + opacity * inputColor2.getBlue()   ,
						1
				);
				
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}
	
}

package topic2_image_processing.filters.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import topic2_image_processing.filters.Filter;

/**
 * Pravi efekat utapanja slike u crnu pozadinu tako sto cini da slika postaje tamnija sto je piksel dalji od centra.
 */
public class Vignette extends Filter {

	@Override
	public Image process(Image input) {
		final int w = (int) input.getWidth();
		final int h = (int) input.getHeight();
		
		WritableImage output = new WritableImage(w, h);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {

				Color inputColor = pr.getColor(x, y);
						
				pw.setColor(x, y, inputColor);
			}
		}
		
		return output;
	}
	
}

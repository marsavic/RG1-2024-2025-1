package topic2_image_processing.filters;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * ColorFilter je filter koji nezavisno postavlja boju svakog piksela samo na osnovu njegove originalne vrednosti.
 * Zbog toga, sve sto je potrebno da bi se definisao ColorFilter jeste funkcija koja transformise jednu boju u
 * drugu. Ta funkcija se prilikom primene filtera primenjuje na svaki piksel pojedinacno.
 */
public abstract class ColorFilter extends Filter {

	public abstract Color processColor(Color input);
	
	
	@Override
	public Image process(Image input) {
		final int w = (int) input.getWidth();
		final int h = (int) input.getHeight();

		WritableImage output = new WritableImage(w, h);
		
		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {                          // Prvo petlja po y, pa onda po x, radi efikasnosti. 
			for (int x = 0; x < w; x++) {
				Color inputColor = pr.getColor(x, y);
				Color outputColor = processColor(inputColor);
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}
	
}

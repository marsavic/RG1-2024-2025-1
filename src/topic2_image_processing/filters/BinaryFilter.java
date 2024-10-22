package topic2_image_processing.filters;

import javafx.scene.image.Image;


/**
 * BinaryFilter je funkcija transformacije slike. Za dve zadate ulazne slike primenom filtera dobijamo izlaznu sliku. 
 */
public abstract class BinaryFilter {
	public abstract Image process(Image input1, Image input2);
}

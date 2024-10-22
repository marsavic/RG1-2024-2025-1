package topic2_image_processing.filters;

import javafx.scene.image.Image;


/**
 * Filter koji je ekvivalentan kombinaciji filtera zadatih u konstruktoru, primenjenih navedenim redom.
 */
public class CombinedFilter extends Filter {
	Filter[] filters;
	
	
	public CombinedFilter(Filter... filters) {
		this.filters = filters;
	}
	
	@Override
	public Image process(Image input) {
		Image img = input;
		for (Filter filter : filters) {
			img = filter.process(img);
		}		
		return img;
	}
	
}

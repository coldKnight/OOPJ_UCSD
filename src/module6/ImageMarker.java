package module6;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;

/**
 * This marker displays an image at its location.
 */
public abstract class ImageMarker extends AbstractMarker {

	PImage img;

	public ImageMarker(Location location, PImage img) {
		super(location);
		this.img = img;
	}
	
	public void draw(PGraphics pg, float x, float y) {
		// For starter code just drawMaker(...)
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);
			}
		}
	}

//	@Override
//	public void draw(PGraphics pg, float x, float y) {
//		pg.pushStyle();
//		pg.imageMode(PConstants.CORNER);
//		// The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
//		pg.image(img, x - 11, y - 37);
//		pg.popStyle();
//	}

	@Override
	protected boolean isInside(float checkX, float checkY, float x, float y) {
		return checkX > x && checkX < x + img.width && checkY > y && checkY < y + img.height;
	}
	
protected boolean clicked = false;
	
	public ImageMarker(Location location) {
		super(location);
	}
	
	public ImageMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}
	
	// Getter method for clicked field
	public boolean getClicked() {
		return clicked;
	}
	
	// Setter method for clicked field
	public void setClicked(boolean state) {
		clicked = state;
	}


	public abstract void drawMarker(PGraphics pg, float x, float y);
	public abstract void showTitle(PGraphics pg, float x, float y);
}

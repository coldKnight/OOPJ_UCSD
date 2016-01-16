package module6;

import java.util.List;
import java.math.*;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static int TRI_SIZE = 5;
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(148, 0, 211);
		pg.ellipse(x, y, 5, 5);
		
		
		//pg.pushStyle();
//		pg.imageMode(PConstants.CORNER);
		// The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
//		pg.image(img, x - 11, y - 37);
		
		//pg.popStyle();
		
//		String name = getName() + ", " + getCity() + ", " + getCountry() + ", ";
//		
//		pg.fill(255, 255, 255);
//		pg.textSize(12);
//		pg.rectMode(PConstants.CORNER);
//		pg.rect(x, y-TRI_SIZE-39, pg.textWidth(name)+ 6, 39);
//		pg.fill(0, 0, 0);
//		pg.textAlign(PConstants.LEFT, PConstants.TOP);
//		pg.text(name, x+3, y-TRI_SIZE-33);
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		
		pg.pushStyle();
		
		pg.stroke(200, 0, 0, 200);
		String name = "Airport- " + getName() + ", City- " + getCity() + ", " + getCountry();
		String Meta = "Code- " + getCode() + ", Altitude- " + getAltitude();
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-39, pg.textWidth(name)+5, 20);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-35);
		
		pg.fill(255, 255, 255);
		pg.rect(x, y-15, pg.textWidth(Meta)+5, 20);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(Meta, x+3, y-11);
		
		pg.strokeWeight(15);
		//pg.stroke(200, 0, 0, 200);
		pg.noFill();
		float s = 5;
		float startPoint = (float) (Math.PI*0.95);
		float stopPoint = (float) (Math.PI*0.05);
		pg.arc(x, y, s, s, -startPoint, -stopPoint);
		pg.arc(x, y, s, s, stopPoint,startPoint);
		
		pg.popStyle();
	}
	
	private String getCode() {
		// TODO Auto-generated method stub
		return (String) getProperty("code");
	}

	private String getAltitude() {
		// TODO Auto-generated method stub
		return (String) getProperty("altitude");
	}

	private String getName() {
		// TODO Auto-generated method stub
		return (String) getProperty("name");
	}

	private String getCountry() {
		// TODO Auto-generated method stub
		return (String) getProperty("country");
	}

	public String getCity() {
		return (String) getProperty("city");	
		
	}
}

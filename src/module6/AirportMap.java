package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PImage;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	
	List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
	List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
	HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
	
	private AirportMarker lastClicked;
	
	public void setup() {
		// setting up PAppler
		size(1000,650, OPENGL);
		PImage AirportImage = loadImage("airport_marker.png");
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 950, 600);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		//List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		
		// create markers from features
		for(PointFeature feature : features) {
			
			AirportMarker m = new AirportMarker(feature);
	
			//m.setRadius(5);
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
			//System.out.println(feature.getProperties());
		
		}
		
		
		// parse route data
		
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			//System.out.println(route.getProperties());
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		    
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
		}
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		map.addMarkers(airportList);
	}
	
	public void draw() {
		background(0);
		map.draw();
		
	}
	
	/*=================================================================================*/
	
	@Override
	public void mouseClicked()
	{
		if (lastClicked != null) {
			unhideMarkers();
			lastClicked.setSelected(false);
			lastClicked = null;
			
		}
		else if (lastClicked == null) 
		{	
			//lastClicked.setSelected(true);
			checkAirportsForClick();
		}
	}
	
	private void checkAirportsForClick()
	{
		if (lastClicked != null) return;
		
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker marker : airportList) {
			//EarthquakeMarker marker = (EarthquakeMarker)m;
			if (!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = (AirportMarker)marker;
				marker.setSelected(true);
				// Hide all the other earthquakes and hide
				for (Marker mhide : airportList) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}				
				}
				
				String name = (String)lastClicked.getProperty("name");
				String city = (String)lastClicked.getProperty("city");
				String country = (String)lastClicked.getProperty("country");
				String location = name.concat(city).concat(country);
				System.out.println(lastClicked.getProperties());
			}
		}
	}
	
	// loop over and unhide all markers
	private void unhideMarkers() {
		for(Marker marker : airportList) {
			marker.setHidden(false);
		}
	}	
	
	
	
	
	/*=================================================================================*/

}

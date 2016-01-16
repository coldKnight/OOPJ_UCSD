package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet {

	public void setup()
	{
		size(400, 400);
		background(200, 200, 200);
	}
	
	public void draw()
	{
		fill(255, 255, 0);
		ellipse(200, 200, 390, 390);
		
		fill(0,0,0);
		ellipse(120, 150, 50, 70);
		ellipse(280, 150, 50, 70);
		
		noFill();
		arc(200, 250, 150, 150, 0, PI);
		
	}
}

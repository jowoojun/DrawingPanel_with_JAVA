package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle {
	private Rectangle rectangle;
	private Point startP;
	
	public GERectangle(){
		rectangle = new Rectangle();
	}
	
	public void initDraw(Point startP){
		this.startP = startP;
	}
	
	public void setCoordinate(Point currentP){
		rectangle.setBounds(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}

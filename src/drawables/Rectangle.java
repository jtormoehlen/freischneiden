package drawables;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Geometry {

	private Point a;
	private Point b;
	
	public Rectangle(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void paint(Graphics g) {
		Point dX = new Point(b.x, a.y);
		Point dY = new Point(a.x, b.y);
		
		drawRec(a, dX, g);
		drawRec(a, dY, g);
		drawRec(dX, b, g);
		drawRec(dY, b, g);		
	}
	
	public void drawRec(Point a, Point b, Graphics g) {
		g.setColor(Color.red);
		new Line(a, b).paint(g);
		g.setColor(Color.black);
	}
}

package drawables;

import java.awt.Graphics;

public class Line extends Geometry {

	private Point a;
	private Point b;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
	}
}

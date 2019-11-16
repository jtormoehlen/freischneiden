package drawables;

import java.awt.Graphics;

public class Point extends Geometry {

	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public Point add(Point b) {
		return new Point(this.x + b.x, this.y + b.y);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawRect((int)x, (int)y, 1, 1);
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}

}

package model.geometry;

@SuppressWarnings("serial")
public class Rectangle extends java.awt.Rectangle {

	public Point a, b;
	
	public Rectangle(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public boolean contains(Point p) {
		if((p.x >= a.x && p.y >= a.y) && (p.x <= b.x && p.y <= b.y)) {
			return true;
		} else {
			return false;
		}
	}
}

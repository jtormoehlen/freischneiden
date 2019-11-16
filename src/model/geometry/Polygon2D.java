package model.geometry;

import java.awt.Polygon;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Polygon2D extends Polygon {

	private LinkedList<Point> pointList;

	public Polygon2D() {
		super();
		pointList = new LinkedList<>();
	}

	public void add(Point p) {
		this.addPoint(p.x, p.y);
		pointList.add(p);
	}

	public boolean contains(Point p) {
		return super.contains(p.x, p.y);
	}

	public Point getFirst() {
		return pointList.getFirst();
	}

	public Point getLast() {
		return pointList.getLast();
	}

	public int getSize() {
		return this.npoints;
	}
}

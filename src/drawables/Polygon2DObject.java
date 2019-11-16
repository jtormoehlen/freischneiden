package drawables;

import java.awt.Polygon;

public class Polygon2DObject extends Polygon {

	public void add(Point p) {
		super.addPoint(p.getX(), p.getY());
	}
}

package drawables;

import java.awt.Polygon;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class Polygon2DObject extends Polygon {

	public void add(Point p) {
		super.addPoint(p.getX(), p.getY());
	}
}

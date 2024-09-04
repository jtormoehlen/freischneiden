package drawables;

import java.awt.Graphics;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
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

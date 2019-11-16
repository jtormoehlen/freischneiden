package drawables;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Vector2DDrawable extends Geometry {

	private Vector2DObject vec2D;
	private Point a;
	private Point b;
	final int H = 14;
	final int B = (int)(14.0 / 1.6);
	
	private double initAngle;
	
	public Vector2DDrawable(Point a, Point b) {
		vec2D = new Vector2DObject(a, b);
		this.a = a;
		this.b = b;
//		initAngle = Math.atan(() / (b.x - a.x - H));
	}
	
	@Override
	public void paint(Graphics g) {
		drawLine(g);
		drawArrow(g);
		
	}
	
	public void drawLine(Graphics g) {
		new Line(a, b).paint(g);
	}
	
	public void drawArrow(Graphics g) {
		double dX = b.x - H;
		System.out.println(Math.cos(vec2D.angle()));
		double dY_P = b.y + (B / 2.0d);
		double dY_N = b.y - (B / 2.0d);
				
		Point p1 = b;
		Point p2 = new Point(dX, dY_P);
		Point p3 = new Point(dX, dY_N);
//		Point p3 = new Point((vec2D.abs() * Math.cos(Math.toDegrees(vec2D.angle())) + (double)H), (vec2D.abs() * Math.sin(Math.toDegrees(vec2D.angle())) - (double)(B / 2.0d)));
		
//		System.out.println(vecObject.angle());
//		System.out.println(vecObject.abs());
		
		double r = Math.sqrt(((b.x - H) * (b.x - H)) + ((B / 2) * (B / 2)));
//		double dX = r * Math.cos(0);
		
		Polygon2DObject p2D = new Polygon2DObject();
		p2D.add(p1);
		p2D.add(p2);
		p2D.add(p3);
		
		double theta = Math.toDegrees(vec2D.angle());
		Graphics2D g2D = (Graphics2D)g;
		
		System.out.println(theta);
		
		AffineTransform oldG2D = g2D.getTransform();
		g2D.rotate(-theta, b.x, b.y);
		g2D.drawPolygon(p2D);
		g2D.fillPolygon(p2D);
		g2D.setTransform(oldG2D);
	}
}

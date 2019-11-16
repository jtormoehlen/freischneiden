package drawables;

public class Vector2DObject {

	private Point a;
	private Point b;
	
	public Vector2DObject(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Vector2DObject(Point a, double abs, double dir) {
		
		this(a, a.add(target(abs, dir)));
	}
	
	public static Point target(double abs, double dir) {
		double dX = Math.cos(dir) * abs;
		double dY = Math.sin(dir) * abs;
		
		return new Point(dX, dY);
	}
	
	public double angle() {
		Vector2DObject tmp = new Vector2DObject(new Point(0, 0), new Point(1, 0));
		double u = this.dotP(tmp);
		double v = this.abs() * tmp.abs();
		
		if(b.y > a.y) return (2 * Math.PI) - Math.acos(u / v);

		return Math.acos(u / v);
	}
	
	private double dotP(Vector2DObject vec2D) {
		double a_x = b.x - a.x;
		double a_y = b.y - a.y;
		
		double b_x = vec2D.getB().x - vec2D.getA().x;
		double b_y = vec2D.getB().y - vec2D.getB().y;
		
		return (a_x * b_x) + (a_y * b_y);
	}

	public double abs() {
		double dX = b.x - a.x;
		double dY = b.y - a.y;
		return Math.sqrt((dX * dX) + (dY * dY));
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

}

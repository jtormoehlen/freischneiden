package model.geometry;

public class Vector2D {

    private double x_1, x_2, y_1, y_2;
    private double dir;
    private double abs;

    /* VectorMatrix */
    public double dx;
    public double dy;

    /* VectorMatrix */
    public Vector2D(Vector2D vec) {
        dx = vec.getX_2() - vec.getX_1();
        dy = vec.getY_2() - vec.getY_1();
    }

    /* VectorMatrix */
    public Vector2D(Point p) {
        dx = p.x;
        dy = p.y;
    }
    
    public Vector2D(double x_1, double x_2, double y_1, double y_2) {
        this.x_1 = x_1;
        this.x_2 = x_2;
        this.y_1 = y_1;
        this.y_2 = y_2;

        dir = dir();
        abs = abs();
    }

    public Vector2D(Point p, Point q) {
        x_1 = p.x;
        x_2 = q.x;
        y_1 = p.y;
        y_2 = q.y;
    }

    /* VectorMatrix */
    public int getX() {
        return (int) dx;
    }

    /* VectorMatrix */
    public int getY() {
        return (int) dy;
    }

    /* VectorMatrix */
    public void add(Vector2D vec) {
        this.dx += vec.dx;
        this.dy += vec.dy;
    }

    /* VectorMatrix */
    public void sub(Vector2D vec) {
        this.dx -= vec.dx;
        this.dy -= vec.dy;
    }

    public void moveTo(Point p) {
        double dx, dy;

        dx = x_2 - x_1;
        dy = y_2 - y_1;

        x_1 = p.x;
        y_1 = p.y;

        x_2 = x_1 + dx;
        y_2 = y_1 + dy;
    }

    public double dir() {
        double rx = x_2 - x_1;
        double a = Math.acos(rx / abs());

        if (y_2 >= y_1) {
            return (Math.PI * 2) - a;
        } else {
            return a;
        }
    }

    public double abs() {
        double dx = (x_2 - x_1) * (x_2 - x_1);
        double dy = (y_2 - y_1) * (y_2 - y_1);

        return Math.sqrt(dx + dy);
    }

    public int getX_1() {
        return (int) x_1;
    }

    public int getX_2() {
        return (int) x_2;
    }

    public int getY_1() {
        return (int) y_1;
    }

    public int getY_2() {
        return (int) y_2;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x_1=" + x_1 +
                ", x_2=" + x_2 +
                ", y_1=" + y_1 +
                ", y_2=" + y_2 +
                ", dir=" + dir +
                ", abs=" + abs +
                ", dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}

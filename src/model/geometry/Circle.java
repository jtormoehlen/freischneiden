package model.geometry;

/**
 * Created by Johannes on 14.06.2016.
 */
public class Circle extends java.awt.geom.Ellipse2D.Double {

    public Circle(double radius) {
        super(0, 0, radius, radius);
    }

    public void translate(int x, int y) {
        this.x = x - this.getWidth() / 2;
        this.y = y - this.getHeight() / 2;
    }
}

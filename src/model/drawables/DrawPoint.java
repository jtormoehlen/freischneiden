package model.drawables;


import model.geometry.Point;

import java.awt.*;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class DrawPoint extends DrawGeometry {

    private Point point;

    public DrawPoint(Point point) {
        this.point = point;
    }

    @Override
    public void paint(Graphics g) {
        Color currColor = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(point.x, point.y, 1, 1);
        g.setColor(currColor);
    }

    @Override
    public String toData() {
        return "DrawPoint{" +
                "point=" + point +
                '}';
    }
}

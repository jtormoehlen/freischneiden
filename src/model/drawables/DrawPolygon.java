package model.drawables;

import model.geometry.Polygon2D;

import java.awt.*;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class DrawPolygon extends DrawGeometry {

    Polygon2D polygon2D;

    public DrawPolygon(Polygon2D polygon2D) {
        this.polygon2D = polygon2D;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2D = initDraw(g);
        Color currColor = g2D.getColor();
        g2D.setColor(Color.RED);
        g2D.drawPolygon(polygon2D);
        g2D.setColor(currColor);
    }

    private Graphics2D initDraw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2D;
    }

    @Override
    public String toData() {
        return "DrawPolygon{" +
                "polygon2D=" + polygon2D +
                '}';
    }
}

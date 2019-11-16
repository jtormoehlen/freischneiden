package model.drawables;

import java.awt.*;

import model.geometry.Point;
import model.geometry.Rectangle;
import model.geometry.Vector2D;
import view.DrawPanel;

public class Earth extends Item {

    public final double EARTH_ACCELERATION = 9.81d;
    private Dimension d;

    private Point a;
    private Point b;

    private Color color;

    private String name;

    private Rectangle frame;
    private boolean showFrame;

    private float alpha;

    public Earth(Dimension d) {
        this.d = d;
        a = new Point(0, 450);   //(0.75 * d.getHeight())
        b = new Point(d.width, a.y + 20);
        name = "e";
        color = Color.BLUE;
        frame = new Rectangle(a, b);
        showFrame = false;
        alpha = 1.0f;
    }

    @Override
    public void paint(Graphics g) {

        paintImage(g);

        if (showFrame) {
            new DrawRectangle(frame, color).paint(g);
        }

    }

    private void paintImage(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();

        AlphaComposite alphaComp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2D.setComposite(alphaComp);

        drawGround(g2D);
        g2D.drawRect(a.x, a.y, b.x, 1);

        g2D.dispose();
    }

    private void drawGround(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < d.getWidth(); i++) {
            if (i % 10 == 0) {
                g.drawLine(i, a.y, i - 10, a.y + 20);
            }
        }
    }

    @Override
    public String toData() {
        return "Earth{" +
                "EARTH_ACCELERATION=" + EARTH_ACCELERATION +
                ", d=" + d +
                ", a=" + a +
                ", b=" + b +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", frame=" + frame +
                ", showFrame=" + showFrame +
                '}';
    }

    @Override
    public Point getCenter() {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    @Override
    public void setShowFrame(boolean showFrame) {
        this.showFrame = showFrame;
    }

    @Override
    public Rectangle getFrame() {
        return frame;
    }

    @Override
    public String getName() {
        return "e";
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void addY(int y) {
        a.y += y;
        b.y += y;
    }

    public void addX(int x) {

    }
}

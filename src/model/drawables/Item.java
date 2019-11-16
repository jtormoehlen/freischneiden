package model.drawables;

import controller.ImageHandler;
import model.geometry.Point;
import model.geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class Item extends DrawGeometry {

    public Point a;
    public Point b;

    private BufferedImage img;
    private Color color;

    private String name;

    private Rectangle frame;
    private boolean showFrame;

    private float alpha;

    public Item() { /* for Earth */ }

    public Item(Point a, String s, Color color, String name) {
        this.a = a;
        this.name = name;
        img = new ImageHandler(s).getImg();
        this.color = color;
        b = new Point(a.x + img.getWidth(), a.y + img.getHeight());
        frame = new Rectangle(a, b);
        alpha = 1.0f;
    }

    @Override
    public void paint(Graphics g) {

        paintImage(g);

        if(showFrame) {
            new DrawRectangle(frame, color).paint(g);
        }
    }

    private void paintImage(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();

        AlphaComposite alphaComp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2D.setComposite(alphaComp);
        g2D.drawImage(img, a.x, a.y, null);

        g2D.dispose();
    }

    @Override
    public String toData() {
        return "Item{" +
                "a=" + a +
                ", b=" + b +
                ", img=" + img +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", frame=" + frame +
                ", showFrame=" + showFrame +
                '}';
    }

    public void setShowFrame(boolean showFrame) {
        this.showFrame = showFrame;
    }

    public Point getCenter() {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    public Point getNorth() {
        return new Point((a.x + b.x) / 2, a.y);
    }

    public Point getSouth() {
        return new Point(b.x - (img.getWidth() / 2), a.y + img.getHeight());
    }

    public Point getWest() {
        return new Point(a.x, a.y + (img.getHeight() / 2));
    }

    public Point getEast() {
        return new Point(b.x, b.y - (img.getHeight() / 2));
    }

    public Point getLowerNorth() {
        return new Point((a.x + b.x) / 2, a.y + (img.getHeight() / 4));
    }

    public Rectangle getFrame() {
        return frame;
    }

    public String getName() {
        return name;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void addY(int y) {
        a.y += y;
        b.y += y;
    }

    public void addX(int x) {
        a.x += x;
        b.x += x;
    }

    public Color getColor() {
        return color;
    }
}

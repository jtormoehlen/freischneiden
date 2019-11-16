package model.drawables;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.geometry.Point;
import model.geometry.Polygon2D;
import model.geometry.Vector2D;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;

public class DrawVector extends DrawGeometry {

    private Vector2D vec;
    private Color color;
    private String name;
    private Polygon2D head;

    private BufferedImage b;

    public DrawVector() { }

    public DrawVector(Vector2D vec, Color color, String name) {
        this.vec = vec;
        this.color = color;

        setName(name);
    }

    @Override
    public void paint(Graphics g) {
        Color tmp = g.getColor();
        g.setColor(color);

        Graphics2D g2D = initDraw(g);

            drawBody(g2D);
            drawHead(g2D);
            drawLatexFont(g2D);

        g2D.dispose();
        g.setColor(tmp);
    }

    private void createLatex() {
        String math = "\\vec {F} \\textsubscript{ " + name + "}";

        TeXFormula fomule = new TeXFormula(math);
        fomule.setColor(color);
        TeXIcon ti = fomule.createTeXIcon(
                TeXConstants.STYLE_DISPLAY, 20);

        b = new BufferedImage(ti.getIconWidth(), ti
                .getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
    }

    private Graphics2D initDraw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2D;
    }

    private void drawBody(Graphics2D g2D) {

        g2D.drawLine(vec.getX_1(), vec.getY_1(), vec.getX_2(), vec.getY_2());
    }

    private void drawHead(Graphics2D g2D) {

        double phi;
        double s;
        double xAlt, yAlt;
        double xNeu, yNeu;
        double xSpitze, ySpitze;

        Point von = new Point(vec.getX_1(), vec.getY_1());
        Point nach = new Point(vec.getX_2(), vec.getY_2());
        s = 10;
        phi = Math.atan(((double) nach.y - (double) von.y) / ((double) nach.x - (double) von.x));
        if (nach.x < von.x)
            phi = phi + Math.PI;

        head = new Polygon2D();
        head.addPoint(nach.x, nach.y);
        // x_rot = x*cos(phi) - y*sin(phi)
        // y_rot = x*sin(phi) + y*cos(phi)
        xSpitze = (double) nach.x;
        ySpitze = (double) nach.y;

        // erster Punkt
        xAlt = (double) nach.x - s - xSpitze;
        yAlt = (double) nach.y + s / 2.5 - ySpitze;
        xNeu = xAlt * Math.cos(phi) - yAlt * Math.sin(phi) + xSpitze;
        yNeu = xAlt * Math.sin(phi) + yAlt * Math.cos(phi) + ySpitze;
        head.addPoint((int) xNeu, (int) yNeu);

        // zweiter Punkt
        yAlt = (double) nach.y - s / 2.5 - ySpitze;
        xNeu = xAlt * Math.cos(phi) - yAlt * Math.sin(phi) + xSpitze;
        yNeu = xAlt * Math.sin(phi) + yAlt * Math.cos(phi) + ySpitze;
        head.addPoint((int) xNeu, (int) yNeu);

        g2D.drawPolygon(head);
        g2D.fillPolygon(head);
    }

    private void drawLatexFont(Graphics2D g2D) {
        double angle = Math.toDegrees(vec.dir());

        if (angle >= 180 && angle <= 360) {
            g2D.drawImage(b, vec.getX_2(), vec.getY_2(), null);
        } else {
            g2D.drawImage(b, vec.getX_2(), vec.getY_2() - b.getHeight(), null);
        }
    }
    public Vector2D getVec() {
        return vec;
    }

    public Polygon2D getHead() {
        return head;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        createLatex();
    }

    @Override
    public String toData() {
        return "DrawVector{" +
                "vec=" + vec +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", head=" + head +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrawVector that = (DrawVector) o;

        if (vec != null ? !vec.equals(that.vec) : that.vec != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        return b != null ? b.equals(that.b) : that.b == null;

    }

    @Override
    public int hashCode() {
        int result = vec != null ? vec.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}

package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 * Created by Johannes on 14.06.2016.
 */
public abstract class ZoomPanel extends JPanel {

    public ZoomListener getZoomListener() {
        final ZoomListener listener = new ZoomListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        addMouseWheelListener(listener);
        return listener;
    }

    public ZoomPanel() {
        super();
    }

    public abstract AbstractMagnifier getMagnifier();

    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        final AbstractMagnifier magnifier = getMagnifier();
        final Shape shape = magnifier.getShape();

        if (shape != null) {
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setClip(shape);
            final Rectangle b = shape.getBounds();
            g2.clearRect(b.x, b.y, b.width, b.height);
            final AffineTransform transform = new AffineTransform();
            final double centerX = b.getCenterX();
            final double centerY = b.getCenterY();
            transform.translate(centerX, centerY);
            final double scale = Math.pow(magnifier.step, magnifier.zoom);
            transform.scale(scale, scale);
            transform.translate(-centerX, -centerY);
            g2.setTransform(transform);

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            super.paint(g2);

            g2.setTransform(new AffineTransform());
            g2.setClip(g.getClip());
            g2.setStroke(magnifier.getStroke());
            g2.draw(shape);
            g2.dispose();
        }
    }

    protected abstract class AbstractMagnifier {
        private double step = 1.1;
        private int zoom = 1;
        private Point point;
        private Stroke stroke = new BasicStroke(1f);

        public double getStep() {
            return this.step;
        }

        public void setStep(final double step) {
            this.step = step;
        }

        public int getZoom() {
            return this.zoom;
        }

        public void setZoom(final int zoom) {
            this.zoom = zoom;
        }

        public Stroke getStroke() {
            return this.stroke;
        }

        public void setStroke(final Stroke stroke) {
            this.stroke = stroke;
        }

        public Point getPoint() {
            return this.point;
        }

        public void setPoint(final Point point) {
            this.point = point;
        }

        public abstract Shape getShape();

        public void defaultValue() {
            step = 1.1;
            zoom = 0;
            stroke = new BasicStroke(1f);
        }
    }

    private class ZoomListener implements MouseInputListener, MouseWheelListener {
        private void movePoint(final MouseEvent e) {
            getMagnifier().setPoint(e.getPoint());
            repaint();
        }

        @Override
        public void mousePressed(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                movePoint(e);
                setInvisibleCursor();
            }
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                getMagnifier().setPoint(null);
                repaint();
                setCursor(Cursor.getDefaultCursor());
                getMagnifier().setZoom(1);
//                getMagnifier().defaultValue();
            }
        }

        @Override
        public void mouseDragged(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                movePoint(e);
                setInvisibleCursor();
            }
        }

        @Override
        public void mouseWheelMoved(final MouseWheelEvent e) {
            if (getMagnifier().getPoint() != null) {
                final int rotation = getMagnifier().getZoom() - e.getWheelRotation();
                if(rotation > 0 && rotation < 18) {
                    getMagnifier().setZoom(rotation);
                }
                repaint();
            }
        }

        @Override
        public void mouseClicked(final MouseEvent e) {
        }

        @Override
        public void mouseEntered(final MouseEvent e) {
        }

        @Override
        public void mouseExited(final MouseEvent e) {
        }

        @Override
        public void mouseMoved(final MouseEvent e) {
        }

        private void setInvisibleCursor() {
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    cursorImg, new Point(0, 0), "blank cursor");

            setCursor(blankCursor);
        }
    }
}

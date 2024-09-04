package listener;

import controller.Container;
import controller.DrawOperation;
import controller.ViewPanelController;
import listener.tools.Boundary;
import model.drawables.*;
import model.geometry.Point;
import model.geometry.Polygon2D;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class CutListener extends MouseInputAdapter {

    private DrawOperation drawOperation;
    private Polygon2D polygon2D;
    private Polygon2D polygonTmp;
    private LinkedList<DrawPoint> drawPoints;
    private DrawGeometry drawGeometry;
    private Container container;

    public CutListener(DrawOperation drawOperation, Container container) {
        this.drawOperation = drawOperation;
        this.container = container;
        polygon2D = new Polygon2D();
        drawPoints = new LinkedList<>();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point next = new Point(e.getX(), e.getY());

        polygon2D.add(next);

        if (polygon2D.getSize() % 5 == 0) {
            DrawPoint drawPoint = new DrawPoint(next);
            drawPoints.add(drawPoint);
            drawOperation.drawGeometry(drawPoint);
            polygonTmp = polygon2D;
        }
        drawOperation.redrawGeometry();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        DrawPolygon drawPolygon = new DrawPolygon(polygon2D);
        drawOperation.tempDrawGeometry(drawPolygon);
        polygon2D = new Polygon2D();

        removePoints();

        drawGeometry = Boundary.giveOut(polygonTmp, drawOperation.getDrawables());

        if (!drawGeometry.getClass().equals(Dummy.class)) {
            if (drawGeometry.getClass().equals(Item.class)) {
                ((Item) drawGeometry).setShowFrame(true);
            } else if (drawGeometry.getClass().equals(Earth.class)) {
                ((Earth) drawGeometry).setShowFrame(true);
            }

            if(container.getCuttedGeometry() != null) {
                for(DrawGeometry drawGeometry : drawOperation.getDrawables()) {
                    drawGeometry.setAlpha(1.0f);
                }
            }

            container.setCuttedGeometry(drawGeometry);
            ((ViewPanelController) drawOperation).getListenerController().updateCutPanel();
            drawOperation.clearTempDrawGeometry();
            drawOperation.redrawGeometry();
        }
    }

    private void removePoints() {
        while (!drawPoints.isEmpty()) {
            drawOperation.clearDrawGeometry(drawPoints.element());
            drawPoints.remove();
        }
    }
}

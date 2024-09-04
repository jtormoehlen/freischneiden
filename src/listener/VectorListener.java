package listener;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputAdapter;

import controller.*;
import controller.Container;
import model.drawables.DrawVector;
import model.drawables.Item;
import model.geometry.Point;
import model.geometry.Vector2D;

import listener.tools.Boundary;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class VectorListener extends MouseInputAdapter {

    private DrawOperation drawOperation;
    private Point start;
    private LinkedList<Vector2D> vecDrawables;
    private Container container;

//    private String fromTo;
//    private String toFrom;

    public VectorListener(DrawOperation drawOperation, Container container) {
        this.drawOperation = drawOperation;
        this.container = container;
        vecDrawables = new LinkedList<>();
//        fromTo = "";
//        toFrom = "";
    }

    public VectorListener(OptionPanelController optionPanelController) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (start == null) {

            start = new Point(e.getX(), e.getY());

//            Point p = new Point(vecDrawables.getLast().getX_1(), vecDrawables.getLast().getY_1());
//            if (Boundary.isNear(p, start, 20)) {
//                start = p;
//            }

            if(nearSpot() != null) {
                start = nearSpot();
            }

        } else {
            drawOperation.clearTempDrawGeometry();

            Point end = new Point(e.getX(), e.getY());
            Vector2D tmp = new Vector2D(start.x, end.x, start.y, end.y);
            vecDrawables.add(tmp);
            DrawVector vec = new DrawVector(tmp, container.getCuttedGeometry().getColor(), "");

            drawOperation.drawGeometry(vec);
            container.setCurrentVector(vec);
            ((ViewPanelController) drawOperation).getListenerController().updateVectorPanel();
            ((ViewPanelController) drawOperation).getOptionPanel().getDataPanel().getVecFrom().setEnabled(true);

            start = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (start != null) {
            Point end = new Point(e.getX(), e.getY());

            DrawVector vec = new DrawVector(new Vector2D(start.x, end.x, start.y, end.y), container.getCuttedGeometry().getColor(),"");

            drawOperation.tempDrawGeometry(vec);
            container.setCurrentVector(vec);
            ((ViewPanelController) drawOperation).getListenerController().updateVectorPanel();
        }
    }

    private Point nearSpot() {
        Point spot = null;

        if(Boundary.isNear(((Item) container.getCuttedGeometry()).getNorth(), start, 10)) {
            spot = ((Item) container.getCuttedGeometry()).getNorth();
        } else if(Boundary.isNear(((Item) container.getCuttedGeometry()).getEast(), start, 10)) {
            spot = ((Item) container.getCuttedGeometry()).getEast();
        } else if(Boundary.isNear(((Item) container.getCuttedGeometry()).getSouth(), start, 10)) {
            spot = ((Item) container.getCuttedGeometry()).getSouth();
        }else if(Boundary.isNear(((Item) container.getCuttedGeometry()).getWest(), start, 10)) {
            spot = ((Item) container.getCuttedGeometry()).getWest();
        } else if(Boundary.isNear(((Item) container.getCuttedGeometry()).getCenter(), start, 10)) {
            spot = ((Item) container.getCuttedGeometry()).getCenter();
        }

        return spot;
    }
}

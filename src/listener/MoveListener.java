package listener;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.event.MouseInputAdapter;

import controller.Container;
import controller.DrawOperation;
import controller.ViewPanelController;
import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.drawables.Earth;
import model.drawables.Item;
import model.geometry.Point;
import listener.tools.Boundary;

public class MoveListener extends MouseInputAdapter {

    private DrawOperation drawOperation;
    private ArrayList<DrawGeometry> drawables;
    private DrawGeometry geometry;
    private String name;
    private Container container;

    private HashMap<String, LinkedList<DrawVector>> drawVecs;

    public MoveListener(DrawOperation drawOperation, Container container) {
        this.drawOperation = drawOperation;
        this.container = container;
        this.drawables = drawOperation.getDrawables();
        name = "";
    }

    public void updateDrawVecs(HashMap<String, LinkedList<DrawVector>> drawVecs) {
        this.drawVecs = drawVecs;
    }

    private void relation() {
        if (name.length() >= 3) {
            name = container.getCuttedGeometry().getName() + "_";
        } else if (name.length() == 1) {
            name += "_";
        } else {
            name = container.getCuttedGeometry().getName() + "_";
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point at = new Point(e.getX(), e.getY());

        relation();

        geometry = Boundary.giveAt(at, drawables);
        name += geometry.getName();

        if (!container.getCuttedGeometry().equals(geometry))
            geometry.setAlpha(0.25f);

        if(container.getCurrentVector() != null) {
            drawOperation.clearDrawGeometry(container.getCurrentVector());
        }

        container.setCurrentGeometry(geometry);
        ((ViewPanelController) drawOperation).getListenerController().updateObjectPanel();
        ((ViewPanelController) drawOperation).getListenerController().updateRelationPanel(name);

        if (drawVecs.containsKey(name)) {
            drawVecs.get(name).stream().filter(v -> !drawables.contains(v)).forEach(v -> drawOperation.drawGeometry(v));
        }

        drawOperation.redrawGeometry();
    }
}

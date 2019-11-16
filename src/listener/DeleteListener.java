package listener;

import controller.DrawOperation;
import controller.ViewPanelController;
import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.geometry.Point;
import listener.tools.Boundary;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Johannes on 26.05.2016.
 */
public class DeleteListener extends MouseInputAdapter {

    private DrawOperation drawOperation;

    public DeleteListener(DrawOperation drawOperation) {
        this.drawOperation = drawOperation;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DrawGeometry geometry = Boundary.giveAt(new Point(e.getX(), e.getY()), drawOperation.getDrawables());

        if(geometry.getClass().equals(DrawVector.class)) {
            drawOperation.clearDrawGeometry(geometry);
        }

        drawOperation.redrawGeometry();
        ((ViewPanelController) drawOperation).getOptionPanel().getDataPanel().getVecFrom().setEnabled(false);
    }
}

package listener;

import java.awt.Color;
import java.util.ArrayList;

import controller.DrawOperation;
import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.geometry.Point;
import model.geometry.Vector2D;
import listener.tools.Resultant;

public class BalanceListener {

    private DrawOperation drawOperation;
    private ArrayList<Vector2D> drawables;
    private ArrayList<Vector2D> matVecs;
    private Point start;

    public BalanceListener(DrawOperation drawOperation, ArrayList<DrawGeometry> drawables) {
        this.drawOperation = drawOperation;
        this.drawables = new ArrayList<Vector2D>();

        vecDrawables(drawables);
        calcAverage();
        transformDrawables();
        drawNewVecs();
        drawResVec();
    }

    private void transformDrawables() {
        Vector2D mat;
        matVecs = new ArrayList<>();

        for (int i = 0; i < drawables.size(); i++) {
            mat = new Vector2D(drawables.get(i));
            matVecs.add(mat);
        }
    }

    private void calcAverage() {
        int dx = 0;
        int dy = 0;

        int size = drawables.size();

        for(int i = 0; i < size; i++) {
            dx += drawables.get(i).getX_1();
            dy += drawables.get(i).getY_1();
        }

        if(size != 0) {
            dx /= size;
            dy /= size;
        }

        start = new Point(dx, dy);

    }

    private void vecDrawables(ArrayList<DrawGeometry> drawables) {
        for (int i = 0; i < drawables.size(); i++) {
            if (drawables.get(i).getClass().equals(DrawVector.class)) {
                DrawVector tmp = (DrawVector) drawables.get(i);
                this.drawables.add(tmp.getVec());
            }
        }

    }

    private void drawResVec() {
        DrawVector vec = new DrawVector(Resultant.calcResVec(start, matVecs), Color.red, "R");
        DrawVector antiVec = new DrawVector(Resultant.calcAntiResVec(start, matVecs), Color.green, "-R");
        drawOperation.drawGeometry(vec);
        drawOperation.drawGeometry(antiVec);
    }

    private void drawNewVecs() {
        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).moveTo(start);
        }

        drawOperation.redrawGeometry();
    }
}

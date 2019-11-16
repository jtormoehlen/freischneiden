package view;

import model.drawables.DrawGeometry;
import model.scenes.DrawScene;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

/**
 * Created by Johannes on 30.06.2016.
 */
public class PreviewPanel extends JPanel {

    private ArrayList<DrawGeometry> drawables;
    private int scene;

    public PreviewPanel() {
        scene = 0;
        drawables = new ArrayList<>();
    }

    public void paintSample(int scene) {
        this.scene = scene;
        DrawScene tmp = new DrawScene();
        tmp.drawSample(scene, new Dimension(this.getWidth(), this.getHeight()));
        drawables = tmp.getDrawables();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            for (DrawGeometry q : drawables) {
                Graphics2D g2D = (Graphics2D) g.create();
                g2D.scale(0.5, 0.5);
//                g2D.translate(this.getWidth() / 2, this.getHeight() / 2);
                q.paint(g2D);
                g2D.dispose();
            }
        } catch (NullPointerException e) {
            System.out.println("Object not found!");
        }
    }

    public int getScene() {
        return scene;
    }
}

package model.scenes;

import controller.DrawOperation;
import controller.ViewPanelController;
import listener.tools.Resultant;
import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.drawables.Earth;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Johannes Tormoehlen on 30.06.2016.
 *
 * @author jtormoehlen
 */
public abstract class Scene {
    protected DrawOperation drawOperation;
    protected ArrayList<DrawGeometry> drawables;
    protected Earth earth;
    protected HashMap<String, LinkedList<DrawVector>> drawVecs;
    protected int width;
    protected int height;
    private String[] modelList;

    protected void updateModelLists() {
        ((ViewPanelController) drawOperation).getOptionPanel().getDataPanel().getVecFrom().setModel(new DefaultComboBoxModel<>(modelList));
        ((ViewPanelController) drawOperation).getOptionPanel().getDataPanel().getVecTo().setModel(new DefaultComboBoxModel<>(modelList));
    }

    protected void updateModels() {
        int width = 600;
        int height = 800;
        try {
            width = ((ViewPanelController) drawOperation).getOptionPanel().getDrawPanel().getWidth();
            height = ((ViewPanelController) drawOperation).getOptionPanel().getDrawPanel().getHeight();
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }

        for (DrawGeometry g : drawables) {
            g.addY(((height - 600) / 2));
            g.addX((width - 800) / 2);
        }
    }

    protected void draw() {
        modelList = new String[drawables.size() + 1];
        modelList[0] = "-Auswählen-";
        for (int i = 0; i < drawables.size(); i++) {
            drawOperation.drawGeometry(drawables.get(i));
            modelList[i + 1] = Resultant.translate(drawables.get(i).getName().charAt(0));
        }
    }

    public ArrayList<DrawGeometry> getDrawables() {
        return drawables;
    }

    public HashMap<String, LinkedList<DrawVector>> getDrawVecs() {
        return drawVecs;
    }
}

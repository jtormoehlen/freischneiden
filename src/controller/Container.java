package controller;

import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.drawables.Dummy;

/**
 * Created by Johannes Tormoehlen on 09.06.2016.
 *
 * @author jtormoehlen
 */
public class Container {

    private DrawGeometry cuttedGeometry;
    private DrawGeometry currentGeometry;

    private DrawVector currentVector;

    private int currentScene;

    /* EMPTY Container is EMPTY */
    public Container() {
        cuttedGeometry = new Dummy();
        currentGeometry = new Dummy();
        currentVector = new DrawVector();
        currentScene = 0;
    }

    public DrawGeometry getCuttedGeometry() {
        return cuttedGeometry;
    }

    public void setCuttedGeometry(DrawGeometry cuttedGeometry) {
        this.cuttedGeometry = cuttedGeometry;
    }

    public DrawGeometry getCurrentGeometry() {
        return currentGeometry;
    }

    public void setCurrentGeometry(DrawGeometry currentGeometry) {
        this.currentGeometry = currentGeometry;
    }

    public DrawVector getCurrentVector() {
        return currentVector;
    }

    public void setCurrentVector(DrawVector currentVector) {
        this.currentVector = currentVector;
    }

    public int getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }
}

package model.drawables;

import java.awt.*;

/**
 * Created by Johannes Tormoehlen on 14.06.2016.
 *
 * @author jtormoehlen
 */
public class Dummy extends DrawGeometry {

    /* Dummy class to prevent NullPointerException */
    @Override
    public void paint(Graphics g) {

    }

    @Override
    public String toData() {
        return "";
    }
}

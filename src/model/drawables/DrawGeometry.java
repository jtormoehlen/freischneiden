package model.drawables;

import java.awt.*;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public abstract class DrawGeometry {

	public DrawGeometry() { }

	public abstract void paint(Graphics g);

	public abstract String toData();

	public String getName() {
		return " ";
	}

	public void setAlpha(float alpha) { }

	public void addY(int y) { }

	public void addX(int x) { }

	public Color getColor() {
		return Color.BLACK;
	}
}

package model.drawables;

import java.awt.*;

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

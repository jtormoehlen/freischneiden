package model.drawables;

import java.awt.*;

import model.geometry.Rectangle;

public class DrawRectangle extends DrawGeometry {

	private Rectangle rec;
	private Color color;

	public DrawRectangle(Rectangle rec, Color color) {
		this.rec = rec;
		this.color = color;
	}
	
	@Override
	public void paint(Graphics g) {
		Color tmp = g.getColor();
		g.setColor(color);
		g.drawRect(rec.a.x, rec.a.y, rec.b.x - rec.a.x, rec.b.y - rec.a.y);
		g.setColor(tmp);
	}

	@Override
	public String toData() {
		return "DrawRectangle{" +
				"rec=" + rec +
				", color=" + color +
				'}';
	}
}

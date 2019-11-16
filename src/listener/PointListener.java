package listener;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import controller.DrawableGeometryProcessing;
import drawables.Point;

public class PointListener extends MouseInputAdapter {
	
	private DrawableGeometryProcessing d;
	
	public PointListener(DrawableGeometryProcessing d) {
		this.d = d;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		d.processDrawableObject(p);
	}
}

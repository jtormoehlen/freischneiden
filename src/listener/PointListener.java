package listener;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import controller.DrawableGeometryProcessing;
import drawables.Point;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
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

package listener;

import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

import javax.swing.event.MouseInputAdapter;

import controller.DrawableGeometryProcessing;

import drawables.Box;
import drawables.Point;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class BoxListener extends MouseInputAdapter {

	private DrawableGeometryProcessing d;
	private Point start;

	public BoxListener(DrawableGeometryProcessing d) {
		this.d = d;
	}

	public void mouseClicked(MouseEvent e) {
		start = new Point(e.getX(), e.getY());
		Box b = new Box(start, 20);
		d.processDrawableObject(b);
	}
	
}

package drawables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class Box extends Geometry {

	private BufferedImage image;
	private Point a;
	private double mass;
	private boolean vecEnabled;
	
	final double EARTH_ACCELERATION = 9.81d;

	public Box(Point a, double mass) {
		this.a = a;
		this.mass = mass;
		this.vecEnabled = false;
		
		try {
			image = ImageIO.read(new File("holzkiste.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paint(Graphics g) {
		drawImage(g);
		drawFrame(a, g);
		
		if(vecEnabled) {
			Point inner = new Point(a.getX() + (image.getWidth() / 2), a.getY() + (image.getHeight() / 2));
			Point outer = new Point(a.getX() + (image.getWidth() / 2), a.getY() + (image.getHeight() / 2) + (int)(mass * EARTH_ACCELERATION));
			drawVector(inner, outer, g);
		}
	}
	
	public void drawFrame(Point a, Graphics g) {
		Point tmp = new Point(a.getX() + image.getWidth(), a.getY() + image.getHeight());
		new Rectangle(a, tmp).paint(g);
	}
	
	public void drawImage(Graphics g) {
		g.drawImage(image, a.getX(), a.getY(), null);
	}
	
	public void drawVector(Point a, Point b, Graphics g) {
		new Vector2DDrawable(a, b).paint(g);
	}

	public void setVecEnabled(boolean vecEnabled) {
		this.vecEnabled = vecEnabled;
	}

}

package listener.tools;

import model.drawables.*;
import model.geometry.Point;
import model.geometry.Polygon2D;
import model.geometry.Rectangle;

import java.util.ArrayList;

/**
 * Created by Johannes on 14.05.2016.
 */
public class Boundary {
	
	public static boolean contains(Item b, Point p) {
		Rectangle rec = b.getFrame();
		return rec.contains(p);
	}

    public static boolean contains(DrawVector d, Point p) {
        try {
            Polygon2D head = d.getHead();
            return head.contains(p);
        } catch(NullPointerException ex) {
            System.out.println("Polygon2D in Dawvector doesn't contain Point. -> Returned FALSE value instead.");
            return false;
        }
    }

    public static boolean contains(Earth e, Point p) {
        Rectangle rec = e.getFrame();
        return rec.contains(p);
    }

    public static boolean isNear(Point start, Point p, int tol) {
        boolean upperX, lowerX, upperY, lowerY;

        upperX = p.x <= start.x + tol;
        lowerX = p.x >= start.x - tol;
        upperY = p.y <= start.y + tol;
        lowerY = p.y >= start.y - tol;

        if(upperX && lowerX && upperY && lowerY) {
            return true;
        } else {
            return false;
        }
    }

    public static DrawGeometry giveOut(Polygon2D out, ArrayList<DrawGeometry> drawables) {
        DrawGeometry geometry = new Dummy();

        for(DrawGeometry g : drawables) {
            if(g.getClass().equals(Item.class)) {
                Item item = (Item) g;
                if(out.contains(((Item) g).getCenter())) {
                    geometry = item;
                    break;
                }
            }

            if(g.getClass().equals(Earth.class)) {
                Earth earth = (Earth) g;
                if(out.contains(((Earth) g).getCenter())) {
                    geometry = earth;
                    break;
                }
            }
        }

        return geometry;
    }

    public static DrawGeometry giveAt(Point at, ArrayList<DrawGeometry> drawables) {
        DrawGeometry geometry = new Dummy();

        for(DrawGeometry g : drawables) {
            if(g.getClass().equals(DrawVector.class)) {
                DrawVector drawVector = (DrawVector) g;
                if(contains(drawVector, at)) {
                    geometry = drawVector;
                    break;
                }
            }

            if(g.getClass().equals(Item.class)) {
                Item item = (Item) g;
                if(contains(item, at)) {
                    geometry = item;
                    break;
                }
            }

            if(g.getClass().equals(Earth.class)) {
                Earth earth = (Earth) g;
                if(contains(earth, at)) {
                    geometry = earth;
                    break;
                }
            }
        }

        return geometry;
    }
}

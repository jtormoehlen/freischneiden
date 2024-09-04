package controller;

import java.util.ArrayList;

import model.drawables.DrawGeometry;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public interface DrawOperation {

	void drawGeometry(DrawGeometry drawable);
	
	void redrawGeometry();

	void clearDrawGeometry(DrawGeometry drawable);
	
	void tempDrawGeometry(DrawGeometry drawable);
	
	void clearTempDrawGeometry();
	
	ArrayList<DrawGeometry> getDrawables();

	void clearDrawables();
}

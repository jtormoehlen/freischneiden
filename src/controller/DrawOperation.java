package controller;

import java.util.ArrayList;

import model.drawables.DrawGeometry;

public interface DrawOperation {

	void drawGeometry(DrawGeometry drawable);
	
	void redrawGeometry();

	void clearDrawGeometry(DrawGeometry drawable);
	
	void tempDrawGeometry(DrawGeometry drawable);
	
	void clearTempDrawGeometry();
	
	ArrayList<DrawGeometry> getDrawables();

	void clearDrawables();
}

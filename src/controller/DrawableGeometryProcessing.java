package controller;

import drawables.Geometry;

/**
 * Created by Johannes Tormoehlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public interface DrawableGeometryProcessing {
	
		void processDrawableObject(Geometry drawableGeometry);

		void setTemporaryDrawableObject(Geometry drawableGeometry);

		void clearTemporaryDrawableObject();

}

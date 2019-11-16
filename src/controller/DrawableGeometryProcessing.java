package controller;

import drawables.Geometry;

public interface DrawableGeometryProcessing {
	
		void processDrawableObject(Geometry drawableGeometry);

		void setTemporaryDrawableObject(Geometry drawableGeometry);

		void clearTemporaryDrawableObject();

}

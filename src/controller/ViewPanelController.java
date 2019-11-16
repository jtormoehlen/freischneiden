package controller;

import java.util.ArrayList;

import model.drawables.DrawGeometry;
import view.OptionPanel;

public class ViewPanelController implements DrawOperation {

	final static int WIDTH = 800;
	final static int HEIGHT = 600;

	private ArrayList<DrawGeometry> drawables;
	private DrawGeometry drawGeometry;
	private OptionPanel optionPanel;
	private ListenerController listenerController;

	public ViewPanelController() {
		drawables = new ArrayList<>();
		optionPanel = new OptionPanel(WIDTH, HEIGHT, drawables);
		listenerController = new ListenerController(this);
	}

	@Override
	public void drawGeometry(DrawGeometry drawable) {
		drawables.add(drawable);
		optionPanel.getDrawPanel().repaint();
		optionPanel.getStackSize().setText(drawables.size() + "");
	}

	@Override
	public void tempDrawGeometry(DrawGeometry drawable) {
		if(drawable != null) {
			this.clearTempDrawGeometry();
		}
		
		drawGeometry = drawable;
		this.drawGeometry(drawGeometry);
	}

	@Override
	public void clearTempDrawGeometry() {
		drawables.remove(drawGeometry);
		optionPanel.getDrawPanel().repaint();
		drawGeometry = null;
	}
	
	@Override
	public void redrawGeometry() {
		optionPanel.getDrawPanel().repaint();
	}

	@Override
	public void clearDrawGeometry(DrawGeometry drawable) {
		drawables.remove(drawable);
	}

	public ArrayList<DrawGeometry> getDrawables() {
		return drawables;
	}

	@Override
	public void clearDrawables() {
		getDrawables().clear();
	}

	public OptionPanel getOptionPanel() {
		return optionPanel;
	}

	public ListenerController getListenerController() {
		return listenerController;
	}
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.MouseInputListener;

import drawables.Box;
import drawables.Geometry;
import drawables.Point;
import listener.BoxListener;
import listener.PointListener;
import listener.VectorListener;
import view.OptionPanel;

public class OptionPanelController implements DrawableGeometryProcessing {

	private OptionPanel optionPanel;

	private ArrayList<Geometry> drawableGeometryModel;
	private Geometry temporaryGeometry;

	private PointListener pointListener;
	private BoxListener boxListener;
	private VectorListener vectorListener;
	
	private ArrayList<Box> boxList;
	private Box box;
	
	private boolean toggle;
	
	public OptionPanelController() {

		boxList = new ArrayList<Box>();
		toggle = false;
		
		drawableGeometryModel = new ArrayList<Geometry>();


		String[] objectArray = { "Punkt", "Vektor", "Kiste" };
//		optionPanel.getComboBox().setModel(new DefaultComboBoxModel<String>(objectArray));

		pointListener = new PointListener(this);
		boxListener = new BoxListener(this);
		vectorListener = new VectorListener(this);

		ActionListener c = new ActionListener() {

			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				JComboBox<String> cb = (JComboBox<String>) e.getSource();

				if (cb.getSelectedItem().equals("Punkt")) {
					changeMouseInputListenerTo(pointListener);
				} else if (cb.getSelectedItem().equals("Kiste")) {
					changeMouseInputListenerTo(boxListener);
				} else if (cb.getSelectedItem().equals(("Vektor"))) {
					changeMouseInputListenerTo(vectorListener);
				}

			}

			private void changeMouseInputListenerTo(MouseInputListener newListener) {

				MouseListener[] currentInputListeners = optionPanel.getDrawPanel().getMouseListeners();

				for (MouseListener curListener : currentInputListeners) {
					optionPanel.getDrawPanel().removeMouseListener(curListener);
				}

				MouseMotionListener[] currentMotionListeners = optionPanel.getDrawPanel().getMouseMotionListeners();
				for (MouseMotionListener curListener : currentMotionListeners) {
					optionPanel.getDrawPanel().removeMouseMotionListener(curListener);
				}

				optionPanel.getDrawPanel().addMouseListener(newListener);
				optionPanel.getDrawPanel().addMouseMotionListener(newListener);
			}

		};

//		optionPanel.getComboBox().addActionListener(c);
//
//		optionPanel.getComboBox().setSelectedItem("Punkt");

		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawableGeometryModel.clear();
				optionPanel.getDrawPanel().repaint();
			}
		};

//		optionPanel.getButton().addActionListener(a);

		ActionListener b = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				toggle = !toggle;
				System.out.println(toggle);
			}

		};

//		optionPanel.getVecButton().addActionListener(b);
	}

	public OptionPanel getView() {
		return optionPanel;
	}

	public void processDrawableObject(Geometry drawableGeometry) {
		drawableGeometryModel.add(drawableGeometry);
		if(drawableGeometry.getClass().equals("class drawables.Box") && (toggle == true)) {
			box = (Box) drawableGeometry;
			box.setVecEnabled(true);
			boxList.add((Box) drawableGeometry);
		}
		optionPanel.getDrawPanel().repaint();
	}

	public void clearTemporaryDrawableObject() {
		drawableGeometryModel.remove(temporaryGeometry);
		optionPanel.getDrawPanel().repaint();
		temporaryGeometry = null;
	}

	public void setTemporaryDrawableObject(Geometry drawableGeometry) {

		if (temporaryGeometry != null) {
			this.clearTemporaryDrawableObject();
		}

		temporaryGeometry = drawableGeometry;
		this.processDrawableObject(temporaryGeometry);
	}
}

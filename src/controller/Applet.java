package controller;

import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

public class Applet {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Applet: Freischneiden");
		ViewPanelController controller = new ViewPanelController();

//		setLookAndFeelDefault();

		frame.add(controller.getOptionPanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void setLookAndFeelDefault() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}

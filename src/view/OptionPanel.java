package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

import model.drawables.DrawGeometry;

/**
 * Created by Johannes Tormoehlen on 30.06.2016.
 *
 * @author jtormoehlen
 */
@SuppressWarnings("serial")
public class OptionPanel extends JPanel{
	
	private DrawPanel drawPanel;
	private DataPanel dataPanel;

	private JTextField opStatus;
	private JTextField stackSize;
	private JTextField cursorPos;

	private JPanel options;

	private JMenuItem newFile;
	private JMenuItem overview;
	private JMenuItem exit;
	private JMenuItem info;

	private JMenuItem no11;
	private JMenuItem no12;
	private JMenuItem no21;
	private JMenuItem no31;
	private JMenuItem no41;
	private JMenuItem no51;

	private JMenuItem mark;
	private JMenuItem delete;
	private JMenuItem drawForceVector;
	private JMenuItem deleteDrawable;
	private JMenuItem zoom;

	
	public OptionPanel(int width, int height, ArrayList<DrawGeometry> drawables) {
		drawPanel = new DrawPanel(width, height, drawables, false);
		dataPanel = new DataPanel(200, 600, drawables);

		this.setLayout(new BorderLayout());
		this.add(dataPanel, BorderLayout.EAST);
		this.add(drawPanel, BorderLayout.CENTER);

		buildMenu();
		buildOptions();
	}

	private void buildOptions() {
		options = new JPanel();
		this.add(options, BorderLayout.SOUTH);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		options.setBorder(loweredbevel);


		// Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		// TitledBorder title = BorderFactory.createTitledBorder(
		// 		loweredbevel, "Operation");
		// title.setTitlePosition(TitledBorder.ABOVE_TOP);

		opStatus = new JTextField("");
		opStatus.setEditable(false);
		opStatus.setPreferredSize(new Dimension(200, 40));
		opStatus.setBorder(new BorderUIResource.TitledBorderUIResource("Aktuelle Operation"));
		options.add(opStatus);

		stackSize = new JTextField("");
		stackSize.setEditable(false);
		stackSize.setPreferredSize(new Dimension(200, 40));
		stackSize.setBorder(new BorderUIResource.TitledBorderUIResource("Stapel Größe"));
		options.add(stackSize);

		cursorPos = new JTextField("");
		cursorPos.setEditable(false);
		cursorPos.setPreferredSize(new Dimension(200, 40));
		cursorPos.setBorder(new BorderUIResource.TitledBorderUIResource("Maus Position"));
		options.add(cursorPos);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		this.add(menuBar, BorderLayout.NORTH);

		JMenu file = new JMenu("Datei");
		menuBar.add(file);

		newFile = new JMenuItem("Neu");
		file.add(newFile);

		overview = new JMenuItem("Auswahl");
		file.add(overview);

		file.addSeparator();
		exit = new JMenuItem("Beenden");
		file.add(exit);

		JMenu look = new JMenu("Ansicht");
		menuBar.add(look);

		JMenu scenes = new JMenu("Szenen");
		look.add(scenes);

		JMenu oneForce = new JMenu("Tutorial: 1 Kraft");
		scenes.add(oneForce);

		JMenu twoForce = new JMenu("Start: 2 Kräfte");
		scenes.add(twoForce);

		JMenu threeForce = new JMenu("Profi: 3 Kräfte");
		scenes.add(threeForce);

		JMenu threePlusForce = new JMenu("Experte: 3+ Kräfte");
		scenes.add(threePlusForce);

		no11 = new JMenuItem("Magnet und Nagel");
		no12 = new JMenuItem("Baum und Ei");
		no21 = new JMenuItem("Erde und Ball");
		no31 = new JMenuItem("Zwei Personen und Kiste");
		no41 = new JMenuItem("Erde, Tisch und Vase");
		no51 = new JMenuItem("Frei");
		oneForce.add(no11);
		oneForce.add(no12);
		twoForce.add(no21);
		threeForce.add(no31);
		threePlusForce.add(no41);
		scenes.add(no51);

		JMenu edit = new JMenu("Bearbeiten");
		menuBar.add(edit);

		mark = new JMenuItem("Markieren/Umkringeln");
		edit.add(mark);

		delete = new JMenuItem("Auswählen/Entfernen");
		edit.add(delete);

		drawForceVector = new JMenuItem("Zeichnen");
		edit.add(drawForceVector);

		deleteDrawable = new JMenuItem("Löschen");
		edit.add(deleteDrawable);

		zoom = new JMenuItem("Lupe");
		edit.add(zoom);

		JMenu help = new JMenu("Hilfe");
		menuBar.add(help);

		info = new JMenuItem("Info");
		help.add(info);
	}

	public void updateOptions() {
		boolean flag = drawPanel.getSceneFlag();

		// if(flag) {
		// 	setComponent(options, false);
		// } else {
		// 	setComponent(options, true);
		// }
	}

	private void setComponent(JPanel menu, boolean flag) {
		for(Component c : menu.getComponents()) {
			c.setEnabled(flag);
		}
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public DataPanel getDataPanel() {
		return dataPanel;
	}

	public JMenuItem getNewFile() {
		return newFile;
	}

	public JMenuItem getOverview() {
		return overview;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public JMenuItem getInfo() {
		return info;
	}

	public JMenuItem getNo11() {
		return no11;
	}

	public JMenuItem getNo12() {
		return no12;
	}

	public JMenuItem getNo21() {
		return no21;
	}

	public JMenuItem getNo31() {
		return no31;
	}

	public JMenuItem getNo41() {
		return no41;
	}

	public JMenuItem getNo51() {
		return no51;
	}

	public JMenuItem getMark() {
		return mark;
	}

	public JMenuItem getDelete() {
		return delete;
	}

	public JMenuItem getDrawForceVector() {
		return drawForceVector;
	}

	public JMenuItem getDeleteDrawable() {
		return deleteDrawable;
	}

	public JMenuItem getZoom() {
		return zoom;
	}

	public JTextField getOpStatus() {
		return opStatus;
	}

	public JTextField getStackSize() {
		return stackSize;
	}

	public JTextField getCursorPos() {
		return cursorPos;
	}
}

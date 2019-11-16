package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import model.drawables.DrawGeometry;
import model.drawables.DrawVector;
import model.geometry.Vector2D;
import model.scenes.DrawScene;
import listener.*;
import listener.tools.Resultant;
import view.OptionPanel;
import view.PreviewPanel;

public class ListenerController {

    private ViewPanelController viewPanelController;
    private OptionPanel optionPanel;

    private VectorListener vecListener;
    private MoveListener moveListener;
    private CutListener cutListener;
    private DeleteListener deleteListener;

    private DrawScene scenes;

    private Container container;

    public ListenerController(ViewPanelController viewPanelController) {

        container = new Container();

        this.viewPanelController = viewPanelController;
        optionPanel = viewPanelController.getOptionPanel();
        optionPanel.getDrawPanel().setSceneFlag(true);
        optionPanel.getDrawPanel().updateMenu();
        optionPanel.updateOptions();

        vecListener = new VectorListener(viewPanelController, container);
        moveListener = new MoveListener(viewPanelController, container);
        cutListener = new CutListener(viewPanelController, container);
        deleteListener = new DeleteListener(viewPanelController);

        scenes = new DrawScene(viewPanelController);

        ActionListener drawListener = new ActionListener() {

            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e) {

                changeMouseInputListenerTo(null);

                if(e.getSource() == optionPanel.getMark()) {
                    changeMouseInputListenerTo(cutListener);
                    optionPanel.getOpStatus().setText(optionPanel.getMark().getText());
                } else if(e.getSource() == optionPanel.getDelete()) {
                    changeMouseInputListenerTo(moveListener);
                    optionPanel.getOpStatus().setText(optionPanel.getDelete().getText());
                } else if(e.getSource() == optionPanel.getDeleteDrawable()) {
                    changeMouseInputListenerTo(deleteListener);
                    optionPanel.getOpStatus().setText(optionPanel.getDeleteDrawable().getText());
                } else if(e.getSource() == optionPanel.getDrawForceVector()) {
                    changeMouseInputListenerTo(vecListener);
                    optionPanel.getOpStatus().setText(optionPanel.getDrawForceVector().getText());
                } else if(e.getSource() == optionPanel.getZoom()) {
                    optionPanel.getDrawPanel().getZoomListener();
                    optionPanel.getOpStatus().setText(optionPanel.getZoom().getText());
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

        ActionListener radioListener = e -> {

            int currentScene = 0;

            if (e.getSource() == optionPanel.getNo11()) {
                currentScene = 1;
            } else if(e.getSource() == optionPanel.getNo12()) {
                currentScene = 9;
            } else if (e.getSource() == optionPanel.getNo21()) {
                currentScene = 2;
            } else if (e.getSource() == optionPanel.getNo31()) {
                currentScene = 3;
            } else if(e.getSource() == optionPanel.getNo41()) {
                currentScene = 4;
            } else if (e.getSource() == optionPanel.getNo51()) {
                currentScene = 0;
            }

            changeSceneListenerTo(currentScene);
            container.setCurrentScene(currentScene);
        };

        ActionListener newListener = e -> {
            changeSceneListenerTo(container.getCurrentScene());
            reset();
        };

        ActionListener overviewListener = e -> {
            optionPanel.getDrawPanel().setSceneFlag(true);
            optionPanel.getDrawPanel().updateMenu();
            reset();
        };

        ActionListener exitListener = e -> {
            int result = JOptionPane.showConfirmDialog(null, "Wollen Sie das Programm wirklich beenden?", "Programm beenden", JOptionPane.YES_NO_OPTION);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    System.exit(0);

                case JOptionPane.NO_OPTION:
            }
        };

        ActionListener infoListener = e -> {
            JFrame info = new JFrame();
            info.setVisible(true);
            info.setSize(400, 300);
        };

        ComponentListener resizeListener = new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                if(!optionPanel.getDrawPanel().getSceneFlag()) {
                    changeSceneListenerTo(container.getCurrentScene());
                } else {
                    optionPanel.getDrawPanel().buildLayout();
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) { }

            @Override
            public void componentShown(ComponentEvent e) { }

            @Override
            public void componentHidden(ComponentEvent e) { }
        };

        ActionListener vecFromListener = e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            container.getCurrentVector().setName((String)cb.getSelectedItem() + " ");
            viewPanelController.redrawGeometry();

            optionPanel.getDataPanel().getVecTo().setEnabled(true);
            optionPanel.getDataPanel().getVecFrom().setEnabled(false);
            updateVectorPanel();
        };

        ActionListener vecToListener = e -> {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            String name = container.getCurrentVector().getName();
            for(int i = 0; i < name.length(); i++) {
                if(name.charAt(i) == ' ') {
                    name += "auf " + cb.getSelectedItem();
                    break;
                }
            }
            container.getCurrentVector().setName(name);
            viewPanelController.redrawGeometry();

            optionPanel.getDataPanel().getSolution().setEnabled(true);
            optionPanel.getDataPanel().getVecTo().setEnabled(false);
            updateVectorPanel();
        };

        ActionListener solutionListener = e -> {
            String name = container.getCurrentVector().getName();

            String[] parts = name.split(" ");

            String from = Resultant.coding(parts[0]);
            String to = Resultant.coding(parts[2]);
            name = to + "_" + from;

            if(scenes.getDrawVecs().containsKey(name)) {
                for(DrawGeometry g : scenes.getDrawVecs().get(name)) {
                    if(!viewPanelController.getDrawables().contains(g)) {
                        viewPanelController.drawGeometry(g);
                    }
                }
            }

            viewPanelController.clearDrawGeometry(container.getCurrentVector());
            viewPanelController.redrawGeometry();

            optionPanel.getDataPanel().getSolution().setEnabled(false);
        };

        MouseMotionListener componentGrid = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) { }

            @Override
            public void mouseMoved(MouseEvent e) {
                optionPanel.getCursorPos().setText("X:" + e.getX() + " Y:" + e.getY());
            }
        };

        MouseListener selectionListener =  new MouseListener() {
            Border raisedbevel = BorderFactory.createRaisedBevelBorder();
            Border redline = BorderFactory.createLineBorder(Color.RED);

            @Override
            public void mouseClicked(MouseEvent e) {
                int currentScene = ((PreviewPanel) e.getSource()).getScene() % 100;
                container.setCurrentScene(currentScene);
                changeSceneListenerTo(currentScene);
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {

                Border compound = BorderFactory.createCompoundBorder(
                        redline, raisedbevel);
                ((PreviewPanel) e.getSource()).setBorder(compound);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((PreviewPanel) e.getSource()).setBorder(raisedbevel);
            }
        };

        for(Component c : optionPanel.getDrawPanel().getMenu().getComponents()) {
            c.addMouseListener(selectionListener);
        }

        optionPanel.getDrawPanel().addMouseMotionListener(componentGrid);

        optionPanel.getDataPanel().getSolution().addActionListener(solutionListener);

        optionPanel.getDataPanel().getVecFrom().addActionListener(vecFromListener);
        optionPanel.getDataPanel().getVecTo().addActionListener(vecToListener);


        optionPanel.getDrawPanel().addComponentListener(resizeListener);

        optionPanel.getInfo().addActionListener(infoListener);
        optionPanel.getExit().addActionListener(exitListener);
        optionPanel.getNewFile().addActionListener(newListener);
        optionPanel.getOverview().addActionListener(overviewListener);

        optionPanel.getNo11().addActionListener(radioListener);
        optionPanel.getNo12().addActionListener(radioListener);
        optionPanel.getNo21().addActionListener(radioListener);
        optionPanel.getNo31().addActionListener(radioListener);
        optionPanel.getNo41().addActionListener(radioListener);
        optionPanel.getNo51().addActionListener(radioListener);

        optionPanel.getMark().addActionListener(drawListener);
        optionPanel.getDelete().addActionListener(drawListener);
        optionPanel.getDeleteDrawable().addActionListener(drawListener);
        optionPanel.getDrawForceVector().addActionListener(drawListener);
        optionPanel.getZoom().addActionListener(drawListener);
    }

    private void changeSceneListenerTo(int c) {
        scenes.drawScene(c, optionPanel.getDrawPanel().getSize());
        optionPanel.getDrawPanel().setSceneFlag(false);
        optionPanel.getDrawPanel().updateMenu();
        optionPanel.updateOptions();
        moveListener.updateDrawVecs(scenes.getDrawVecs());
    }

    public void updateRelationPanel(String s) {
        optionPanel.getDataPanel().getCurrRelation().setText(Resultant.translate(s.charAt(2)) + "->" + Resultant.translate(s.charAt(0)));
    }

    public void updateObjectPanel() {
        optionPanel.getDataPanel().getCurrGeometry().setText(Resultant.translate(container.getCurrentGeometry().getName().charAt(0)));
    }

    public void updateCutPanel() {
        optionPanel.getDataPanel().getCutGeometry().setText(Resultant.translate(container.getCuttedGeometry().getName().charAt(0)));
    }

    public void updateVectorPanel() {
        Vector2D tmp = container.getCurrentVector().getVec();
        optionPanel.getDataPanel().getCurrVector().setText("Name: " + container.getCurrentVector().getName() + "\n" + "Richtung(rel. Erde): " + (int)Math.toDegrees(tmp.dir()) + "°\n" + "Betrag: " + (int)tmp.abs() + "px");
    }

    private void reset() {
        viewPanelController.clearDrawables();
        viewPanelController.redrawGeometry();
    }
}

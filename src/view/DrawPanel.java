package view;


import model.drawables.DrawGeometry;

import model.geometry.Circle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Johannes Tormoehlen on 20.05.2016.
 *
 * @author jtormoehlen
 */
@SuppressWarnings("serial")
public class DrawPanel extends ZoomPanel {

    public static int width;
    public static int height;

    private ArrayList<DrawGeometry> drawables;

    private boolean sceneFlag;

    private JPanel menu;

    private Color defaultFore;
    private Color defaultBack;

    public DrawPanel(int width, int height, ArrayList<DrawGeometry> drawables, boolean sceneFlag) {
        this.drawables = drawables;
        this.sceneFlag = sceneFlag;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        defaultFore = getForeground();
        defaultBack = getBackground();
        buildMenu();
    }

    public void buildLayout() {
        menu.setPreferredSize(new Dimension(width, height));
        menu.setLayout(new GridLayout(0, 4));
    }

    private void buildMenu() {
        menu = new JPanel();

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();

        PreviewPanel previewPanelOne = new PreviewPanel();
        previewPanelOne.setBorder(raisedbevel);
        this.add(previewPanelOne);
        previewPanelOne.paintSample(104);
        menu.add(previewPanelOne);

        PreviewPanel previewPanelTwo = new PreviewPanel();
        previewPanelTwo.setBorder(raisedbevel);
        this.add(previewPanelTwo);
        previewPanelTwo.paintSample(101);
        menu.add(previewPanelTwo);

        PreviewPanel previewPanelThree = new PreviewPanel();
        previewPanelThree.setBorder(raisedbevel);
        this.add(previewPanelThree);
        previewPanelThree.paintSample(102);
        menu.add(previewPanelThree);

        PreviewPanel previewPanelFour = new PreviewPanel();
        previewPanelFour.setBorder(raisedbevel);
        this.add(previewPanelFour);
        previewPanelFour.paintSample(103);
        menu.add(previewPanelFour);

        PreviewPanel previewPanelSix = new PreviewPanel();
        previewPanelSix.setBorder(raisedbevel);
        this.add(previewPanelSix);
        previewPanelSix.paintSample(108);
        menu.add(previewPanelSix);

        PreviewPanel previewPanelFive = new PreviewPanel();
        previewPanelFive.setBorder(raisedbevel);
        this.add(previewPanelFive);
        previewPanelFive.paintSample(109);
        menu.add(previewPanelFive);

        PreviewPanel previewPanelEight = new PreviewPanel();
        previewPanelEight.setBorder(raisedbevel);
        this.add(previewPanelEight);
        previewPanelEight.paintSample(112);
        menu.add(previewPanelEight);

        PreviewPanel previewPanelSeven = new PreviewPanel();
        previewPanelSeven.setBorder(raisedbevel);
        this.add(previewPanelSeven);
        previewPanelSeven.paintSample(114);
        menu.add(previewPanelSeven);

        PreviewPanel previewPanelNine = new PreviewPanel();
        previewPanelNine.setBorder(raisedbevel);
        this.add(previewPanelNine);
        previewPanelNine.paintSample(107);
        menu.add(previewPanelNine);

        PreviewPanel previewPanelTen = new PreviewPanel();
        previewPanelTen.setBorder(raisedbevel);
        this.add(previewPanelTen);
        previewPanelTen.paintSample(110);
        menu.add(previewPanelTen);

        PreviewPanel previewPanelEleven = new PreviewPanel();
        previewPanelEleven.setBorder(raisedbevel);
        this.add(previewPanelEleven);
        previewPanelEleven.paintSample(111);
        menu.add(previewPanelEleven);

        PreviewPanel previewPanelTwelve = new PreviewPanel();
        previewPanelTwelve.setBorder(raisedbevel);
        this.add(previewPanelTwelve);
        previewPanelTwelve.paintSample(115);
        menu.add(previewPanelTwelve);

        this.add(menu);
    }

    public void updateMenu() {
        if (sceneFlag) {
            menu.setVisible(true);
            setForeground(defaultFore);
            setBackground(defaultBack);
        } else {
            menu.setVisible(false);
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        updateMenu();
        super.paintComponent(g);

        try {
            for (DrawGeometry q : drawables) {
                q.paint(g);
            }
        } catch (NullPointerException e) {
            System.out.println("Object not found!");
        }

        g.setColor(Color.black);
    }

    private final AbstractMagnifier magnifier = new AbstractMagnifier() {
        {
            setStroke(new BasicStroke(3f));
        }

        @Override
        public Shape getShape() {
            final java.awt.Point point = getPoint();
            if (point != null) {
                Circle circle = new Circle(250);
                circle.translate(point.x, point.y);
                return circle;
            }
            return null;
        }
    };

    @Override
    public AbstractMagnifier getMagnifier() {
        return this.magnifier;
    }

    public boolean getSceneFlag() {
        return sceneFlag;
    }

    public void setSceneFlag(boolean sceneFlag) {
        this.sceneFlag = sceneFlag;
    }

    public JPanel getMenu() {
        return menu;
    }
}

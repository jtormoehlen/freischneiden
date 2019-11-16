package view;

import model.drawables.DrawGeometry;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Johannes on 20.05.2016.
 */
public class DataPanel extends JPanel {

    private ArrayList<DrawGeometry> drawables;
    private JTextField currGeometry;
    private JTextField currRelation;
    private JTextField cutGeometry;
    private JTextArea currVector;
    private JComboBox<String> vecFrom;
    private JComboBox<String> vecTo;
    private JButton solution;

    private int width;
    private int height;

    public DataPanel(int width, int height, ArrayList<DrawGeometry> drawables) {
        this.drawables = drawables;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        this.setBorder(loweredbevel);

        buildText();
    }

    private void buildText() {
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title;

        cutGeometry = new JTextField("");
        cutGeometry.setEditable(false);
        cutGeometry.setPreferredSize(new Dimension(width - 20, 40));
        title = BorderFactory.createTitledBorder(
                loweredetched, "Freischneiden");
        cutGeometry.setBorder(title);
        this.add(cutGeometry);

        currGeometry = new JTextField("");
        currGeometry.setEditable(false);
        currGeometry.setPreferredSize(new Dimension(width - 20, 40));
        title = BorderFactory.createTitledBorder(
                loweredetched, "Geometrie");
        currGeometry.setBorder(title);
        this.add(currGeometry);

        currRelation = new JTextField("");
        currRelation.setEditable(false);
        currRelation.setPreferredSize(new Dimension(width - 20, 40));
        title = BorderFactory.createTitledBorder(
                loweredetched, "Beziehung");
        currRelation.setBorder(title);
        this.add(currRelation);

        currVector = new JTextArea("");
        currVector.setEditable(false);
        currVector.setPreferredSize(new Dimension(width - 20, 80));
        currVector.setBackground(this.getBackground());
        currVector.setLineWrap(true);
        currVector.setWrapStyleWord(true);
        title = BorderFactory.createTitledBorder(
                loweredetched, "Vektor");
        currVector.setBorder(title);
        this.add(currVector);

        vecFrom = new JComboBox<>();
        vecFrom.setPreferredSize(new Dimension(width - 20, 45));
        vecFrom.setEnabled(false);
        title = BorderFactory.createTitledBorder(
                loweredetched, "Kraft von");
        vecFrom.setBorder(title);
        this.add(vecFrom);

        vecTo = new JComboBox<>();
        vecTo.setPreferredSize(new Dimension(width - 20, 45));
        vecTo.setEnabled(false);
        title = BorderFactory.createTitledBorder(
                loweredetched, "Kraft auf");
        vecTo.setBorder(title);
        this.add(vecTo);

        solution = new JButton("Lösungsvektor");
        solution.setPreferredSize(new Dimension(width - 24, 45));
        solution.setEnabled(false);
        this.add(solution);

        title.setTitleJustification(TitledBorder.CENTER);
    }

    public JTextField getCurrGeometry() {
        return currGeometry;
    }

    public JTextField getCurrRelation() {
        return currRelation;
    }

    public JTextField getCutGeometry() {
        return cutGeometry;
    }

    public JTextArea getCurrVector() {
        return currVector;
    }

    public JComboBox<String> getVecFrom() {
        return vecFrom;
    }

    public JComboBox<String> getVecTo() {
        return vecTo;
    }

    public JButton getSolution() {
        return solution;
    }
}

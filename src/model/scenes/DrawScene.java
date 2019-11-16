package model.scenes;

import controller.DrawOperation;

import model.drawables.DrawVector;
import model.drawables.Earth;
import model.drawables.Item;
import model.geometry.Point;
import model.geometry.Vector2D;
import listener.tools.Resultant;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Johannes Tormöhlen on 24.05.2016.
 *
 * @author jtormoehlen
 */
public class DrawScene extends Scene {

    public DrawScene() { }

    public DrawScene(DrawOperation drawOperation) {
        this.drawOperation = drawOperation;
    }

    public void drawSample(int scene, Dimension d) {
        drawables = new ArrayList<>();
        width = (int) d.getWidth();
        height = (int) d.getHeight();

        earth = new Earth(d);
        drawables.add(earth);

        switch(scene) {

            case 104:
                drawTV();
                break;

            case 101:
                drawables.remove(earth);
                drawMN();
                break;

            case 102:
                drawB();
                break;

            case 103:
                drawPB();
                break;

            case 107:
                drawables.remove(earth);
                drawPD();
                break;

            case 108:
                drawTB();
                break;

            case 109:
                drawTE();
                break;

            case 110:
                drawK();
                break;

            case 111:
                drawGL();
                break;

            case 112:
                drawables.remove(earth);
                drawDN();
                break;

            case 114:
                drawables.remove(earth);
                drawBF();
                break;

            case 115:
                drawPM();
                break;
        }

        updateModels();
    }

    public void drawScene(int scene, Dimension d) {
        drawOperation.clearDrawables();

        drawables = new ArrayList<>();
        drawVecs = new HashMap<>();
        width = (int) d.getWidth();
        height = (int) d.getHeight();

        earth = new Earth(d);
        drawables.add(earth);

        switch (scene) {
            case 3:
                drawPersonsAndBox();
                break;

            case 4:
                drawTableAndVase();
                break;

            case 1:
                drawables.remove(earth);
                drawMagnetAndNail();
                break;

            case 2:
                drawBall();
                break;

            case 5:
                drawPersonsAndDogHor();
                break;

            case 6:
                drawPersonsAndDogVer();
                break;

            case 7:
                drawables.remove(earth);
                drawPersonAndDog();
                break;

            case 8:
                drawTableAndBook();
                break;

            case 9:
                drawTreeAndEgg();
                break;

            case 10:
                drawGroundAndBox();
                break;

            case 11:
                drawGroundAndLight();
                break;

            case 12:
                drawables.remove(earth);
                drawPositiveAndNegative();
                break;

            case 13:
                drawTreeAndApple();
                break;

            case 14:
                drawables.remove(earth);
                drawButtonAndFinger();
                break;

            case 15:
                drawPersonAndMagnet();
                break;

            case 0:
                drawables.remove(earth);
                break;
        }

        draw();
        updateModelLists();
    }

    private void drawPD() {
        Item woman = new Item(new Point(356, 196), "frau_hund", Color.RED, "l");
        Item dog = new Item(new Point(200, 328), "hund2", Color.GREEN, "j");

        drawables.add(woman);
        drawables.add(dog);
    }

    private void drawTV() {
        Item vase = new Item(new Point(356, 196), "vase", Color.RED, "v");
        Item table = new Item(new Point(200, 328), "tisch", Color.GREEN, "t");

        drawables.add(vase);
        drawables.add(table);
    }

    private void drawMN() {
        Item nail = new Item(new Point(387, 352), "nagel", Color.MAGENTA, "n");
        Item magnet = new Item(new Point(368, 82), "magnet", Color.CYAN, "m");

        drawables.add(nail);
        drawables.add(magnet);
    }

    private void drawTE() {
        Item egg = new Item(new Point(420, 275), "ei", Color.MAGENTA, "f");
        Item tree = new Item(new Point(200, 78), "baum", Color.CYAN, "g");

        drawables.add(egg);
        drawables.add(tree);
    }

    private void drawPB() {
        Item actorLeft = new Item(new Point(253, 198), "mensch_l", Color.GREEN, "l");
        Item actorRight = new Item(new Point(415, 198), "mensch_r", Color.CYAN, "r");
        Item box = new Item(new Point(339, 243), "kiste", Color.RED, "k");

        drawables.add(actorLeft);
        drawables.add(actorRight);
        drawables.add(box);
    }

    private void drawB() {
        Item ball = new Item(new Point(325, 322), "ball", Color.RED, "b");

        drawables.add(ball);
    }

    private void drawK() {
        Item box = new Item(new Point(200, 200), "kiste2", Color.GREEN, "k");

        drawables.add(box);
    }

    private void drawTB() {
        Item book = new Item(new Point(356, 196), "buch", Color.RED, "o");
        Item table = new Item(new Point(200, 328), "tisch_alt", Color.GREEN, "t");

        drawables.add(book);
        drawables.add(table);
    }

    private void drawBF() {
        Item finger = new Item(new Point(253, 198), "finger", Color.GREEN, "i");
        Item feder = new Item(new Point(415, 198), "feder", Color.CYAN, "d");
        Item button = new Item(new Point(339, 243), "taste", Color.RED, "p");

        drawables.add(finger);
        drawables.add(feder);
        drawables.add(button);
    }

    private void drawDN() {
        Item dipol = new Item(new Point(200, 200), "dipol", Color.BLUE, "c");
        Item negative = new Item(new Point(300, 300), "negativ", Color.GREEN, "h");

        drawables.add(dipol);
        drawables.add(negative);
    }

    private void drawGL() {
        Item light = new Item(new Point(200, 200), "lampe", Color.BLUE, "p");
        Item ceiling = new Item(new Point(0, 0), "decke", Color.RED, "q");

        drawables.add(light);
        drawables.add(ceiling);
    }

    private void drawPM() {
        Item human = new Item(new Point(100, 100), "mensch_magnet", Color.CYAN, "l");
        Item magnetLeft = new Item(new Point(100, 200), "magnet_links", Color.MAGENTA, "m");
        Item ceiling = new Item(new Point(0, 0), "decke", Color.RED, "q");
        Item magnetRight = new Item(new Point(100, 300), "magnet_rechts", Color.YELLOW, "p");

        drawables.add(human);
        drawables.add(magnetLeft);
        drawables.add(ceiling);
        drawables.add(magnetRight);
    }

    private void drawPersonsAndDogHor() {
//        Item womanMan = new Item(new Point(200, 200), "mann_frau_hund", Color.GREEN, )
    }

    private void drawPersonsAndDogVer() {

    }

    private void drawPersonAndMagnet() {
        Item human = new Item(new Point(100, 100), "mensch_magnet", Color.CYAN, "l");
        Item magnetLeft = new Item(new Point(100, 200), "magnet_links", Color.MAGENTA, "m");
        Item ceiling = new Item(new Point(0, 0), "decke", Color.RED, "q");
        Item magnetRight = new Item(new Point(100, 300), "magnet_rechts", Color.YELLOW, "p");

        drawables.add(human);
        drawables.add(magnetLeft);
        drawables.add(ceiling);
        drawables.add(magnetRight);
        updateModels();
    }

    private void drawPersonAndDog() {
        Item woman = new Item(new Point(356, 196), "frau_hund", Color.RED, "l");
        Item dog = new Item(new Point(200, 328), "hund2", Color.GREEN, "j");

        drawables.add(woman);
        drawables.add(dog);
        updateModels();
    }

    private void drawTableAndBook() {
        Item book = new Item(new Point(356, 196), "buch", Color.RED, "o");
        Item table = new Item(new Point(200, 328), "tisch_alt", Color.GREEN, "t");

        drawables.add(book);
        drawables.add(table);
        updateModels();
    }

    private void drawTreeAndEgg() {
        Item egg = new Item(new Point(420, 275), "ei", Color.MAGENTA, "f");
        Item tree = new Item(new Point(200, 78), "baum", Color.CYAN, "g");

        drawables.add(egg);
        drawables.add(tree);
        updateModels();

        Vector2D fallEgg = new Vector2D(egg.getSouth(), new Point(egg.getSouth().x, egg.getSouth().y + 20));

        LinkedList<DrawVector> list = new LinkedList<>();
        list.add(new DrawVector(fallEgg, Color.MAGENTA, "Erde auf Ei"));

        drawVecs.put("f_e", list);
    }

    private void drawGroundAndBox() {
        Item box = new Item(new Point(200, 200), "kiste2", Color.GREEN, "k");

        drawables.add(box);
        updateModels();
    }

    private void drawGroundAndLight() {
        Item light = new Item(new Point(200, 200), "lampe", Color.BLUE, "p");
        Item ceiling = new Item(new Point(100, 100), "decke", Color.RED, "q");

        drawables.add(light);
        drawables.add(ceiling);
        updateModels();
    }

    private void drawPositiveAndNegative() {
        Item dipol = new Item(new Point(200, 200), "dipol", Color.BLUE, "c");
        Item negative = new Item(new Point(300, 300), "negativ", Color.GREEN, "h");

        drawables.add(dipol);
        drawables.add(negative);
        updateModels();
    }

    private void drawTreeAndApple() {

    }

    private void drawButtonAndFinger() {
        Item finger = new Item(new Point(253, 198), "finger", Color.GREEN, "i");
        Item feder = new Item(new Point(415, 198), "feder", Color.CYAN, "d");
        Item button = new Item(new Point(339, 243), "taste", Color.RED, "p");

        drawables.add(finger);
        drawables.add(feder);
        drawables.add(button);
        updateModels();
    }

    private void drawPersonsAndBox() {
        Item actorLeft = new Item(new Point(253, 198), "mensch_l", Color.GREEN, "l");
        Item actorRight = new Item(new Point(415, 198), "mensch_r", Color.CYAN, "r");
        Item box = new Item(new Point(339, 243), "kiste", Color.RED, "k");

        drawables.add(actorLeft);
        drawables.add(actorRight);
        drawables.add(box);
        updateModels();

        Vector2D rightHand = new Vector2D(box.getEast(), actorRight.getLowerNorth());
        Vector2D leftHand = new Vector2D(box.getWest(), actorLeft.getLowerNorth());

        ArrayList<Vector2D> tmpList = new ArrayList<>();
        tmpList.add(new Vector2D(leftHand));
        tmpList.add(new Vector2D(rightHand));
        Vector2D gravity = Resultant.calcAntiResVec(box.getCenter(), tmpList);

        LinkedList<DrawVector> earthList = new LinkedList<DrawVector>();
        earthList.add(new DrawVector(gravity, Color.RED, "Erde auf Kiste"));

        LinkedList<DrawVector> leftList = new LinkedList<DrawVector>();
        leftList.add(new DrawVector(leftHand, Color.RED, "lPerson auf Kiste"));

        LinkedList<DrawVector> rightList = new LinkedList<DrawVector>();
        rightList.add(new DrawVector(rightHand, Color.RED, "rPerson auf Kiste"));

        drawVecs.put("k_e", earthList);
        drawVecs.put("k_l", leftList);
        drawVecs.put("k_r", rightList);
    }

    private void drawTableAndVase() {
        Item vase = new Item(new Point(356, 196), "vase", Color.RED, "v");
        Item table = new Item(new Point(200, 328), "tisch", Color.GREEN, "t");

        drawables.add(vase);
        drawables.add(table);
        updateModels();

        Vector2D gVase = new Vector2D(vase.getCenter(), new Point(vase.getCenter().x, (int) (vase.getCenter().y + (earth.EARTH_ACCELERATION * 2.5))));
        Vector2D bVase = new Vector2D(vase.getSouth(), new Point(vase.getSouth().x, vase.getSouth().y - 30));

        Vector2D vTable = Resultant.calcAntiVec(table.getNorth(), new Vector2D(bVase));
        Vector2D gTable = new Vector2D(table.getCenter(), new Point(table.getCenter().x, (int) (table.getCenter().y + (earth.EARTH_ACCELERATION * 5))));
        Vector2D bTablel = new Vector2D(new Point(table.getSouth().x - 173, table.getSouth().y), new Point(table.getSouth().x - 173, table.getSouth().y - 100));
        Vector2D bTabler = new Vector2D(new Point(table.getSouth().x + 173, table.getSouth().y), new Point(table.getSouth().x + 173, table.getSouth().y - 100));

        Vector2D tBodenl = Resultant.calcAntiVec(new Point(table.getSouth().x - 173, table.getSouth().y), new Vector2D(bTablel));
        Vector2D tBodenr = Resultant.calcAntiVec(new Point(table.getSouth().x + 173, table.getSouth().y), new Vector2D(bTabler));

        LinkedList<DrawVector> list = new LinkedList<>();
        list.add(new DrawVector(gVase, Color.RED, "Erde auf Vase"));
        drawVecs.put("v_e", list);

        list = new LinkedList<>();
        list.add(new DrawVector(bVase, Color.RED, "Tisch auf Vase"));
        drawVecs.put("v_t", list);

        list = new LinkedList<>();
        list.add(new DrawVector(vTable, Color.GREEN, "Vase auf Tisch"));
        drawVecs.put("t_v", list);

        list = new LinkedList<>();
        list.add(new DrawVector(gTable, Color.GREEN, "Erde auf Tisch"));
        list.add(new DrawVector(bTablel, Color.GREEN, "Boden auf Tisch"));
        list.add(new DrawVector(bTabler, Color.GREEN, "Boden auf Tisch"));
        drawVecs.put("t_e", list);

        list = new LinkedList<>();
        list.add(new DrawVector(tBodenl, Color.BLUE, "Tisch auf Boden"));
        list.add(new DrawVector(tBodenr, Color.BLUE, "Tisch auf Boden"));
        drawVecs.put("e_t", list);
    }

    private void drawMagnetAndNail() {
        Item nail = new Item(new Point(387, 352), "nagel", Color.MAGENTA, "n");
        Item magnet = new Item(new Point(368, 82), "magnet", Color.CYAN, "m");

        drawables.add(nail);
        drawables.add(magnet);
        updateModels();

//        Item nail = new Item(new Point((width / 2) + 20, (height / 2) + 100), "nagel", Color.MAGENTA, "n");
//        Item magnet = new Item(new Point((width / 2), (height / 2) - 250), "magnet", Color.CYAN, "m");

        Vector2D elecmag = new Vector2D(nail.getNorth(), new Point(nail.getNorth().x, nail.getNorth().y - 100));

        LinkedList<DrawVector> list = new LinkedList<>();
        list.add(new DrawVector(elecmag, Color.MAGENTA, "Magnet auf Nagel"));

        drawVecs.put("n_m", list);
    }

    private void drawBall() {
        Item ball = new Item(new Point(325, 322), "ball", Color.RED, "b");

        drawables.add(ball);
        updateModels();

        Vector2D gravity = new Vector2D(ball.getCenter(), new Point(ball.getCenter().x, (int) (ball.getCenter().y + (earth.EARTH_ACCELERATION * 2.5))));

        Vector2D tmpRel = new Vector2D(gravity);

        Vector2D ground = new Vector2D(new Point(ball.getSouth().x, ball.getSouth().y), new Point(ball.getSouth().x, ball.getSouth().y - 30));
        Vector2D antiTmp = Resultant.calcAntiVec(ball.getCenter(), tmpRel);


        LinkedList<DrawVector> list = new LinkedList<>();
        list.add(new DrawVector(gravity, Color.RED, "Erde auf Ball"));
        list.add(new DrawVector(ground, Color.RED, "Boden auf Ball"));

        drawVecs.put("b_e", list);
//        drawVecs.put("b_e", antiTmp);
    }

}

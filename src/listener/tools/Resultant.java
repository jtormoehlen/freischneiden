package listener.tools;

import java.util.ArrayList;

import model.geometry.Point;
import model.geometry.Vector2D;

/**
 * Created by Johannes Tormoehlen on 12.05.2016.
 *
 * @author jtormoehlen
 */
public class Resultant {

    public static Vector2D calcResVec(Point start, ArrayList<Vector2D> matVecs) {
        Vector2D mat = new Vector2D(start);

        matVecs.forEach((vec) -> mat.add(vec));

        return new Vector2D(start.x, mat.dx, start.y, mat.dy);

    }

    public static Vector2D calcAntiResVec(Point start, ArrayList<Vector2D> matVecs) {
        Vector2D antiMat = new Vector2D(start);

        matVecs.forEach((vec) -> antiMat.sub(vec));

        return new Vector2D(start.x, antiMat.dx, start.y, antiMat.dy);
    }

    public static Vector2D calcAntiVec(Point start, Vector2D vec) {
        ArrayList<Vector2D> list = new ArrayList<>();
        list.add(vec);

        Vector2D result = calcAntiResVec(start, list);

        return result;
    }

    public static String translate(char c) {

        String result = "";

        try {
            switch (c) {
                case 'a':
                    result = "Taste";
                    break;
                case 'b':
                    result = "Ball";
                    break;
                case 'c':
                    result = "Dipol";
                    break;
                case 'd':
                    result = "Feder";
                    break;
                case 'e':
                    result = "Erde";
                    break;
                case 'f':
                    result = "Ei";
                    break;
                case 'g':
                    result = "Baum";
                    break;
                case 'h':
                    result = "Negativ";
                    break;
                case 'i':
                    result = "Finger";
                    break;
                case 'j':
                    result = "Hund";
                    break;
                case 'k':
                    result = "Kiste";
                    break;
                case 'l':
                    result = "lPerson";
                    break;
                case 'm':
                    result = "Magnet";
                    break;
                case 'n':
                    result = "Nagel";
                    break;
                case 'o':
                    result = "Buch";
                    break;
                case 'p':
                    result = "Lampe";
                    break;
                case 'q':
                    result = "Decke";
                    break;
                case 'r':
                    result = "rPerson";
                    break;
                case 't':
                    result = "Tisch";
                    break;
                case 'v':
                    result = "Vase";
                    break;
            }
        } catch(NullPointerException ex) {
            System.out.println("Failed to translate DrawGeometry name. -> Returned EMPTY value instead");
        }

        return result;
    }

    public static String coding(String s) {
        String result = "";

        try {
            switch (s) {
                case "Lampe":
                    result = "p";
                    break;
                case "Decke":
                    result = "q";
                    break;
                case "Negativ":
                    result = "h";
                    break;
                case "Feder":
                    result = "d";
                    break;
                case "Erde":
                    result = "e";
                    break;
                case "Ei":
                    result = "f";
                    break;
                case "Finger":
                    result = "i";
                    break;
                case "Baum":
                    result = "g";
                    break;
                case "Ball":
                    result = "b";
                    break;
                case "Buch":
                    result = "o";
                    break;
                case "Vase":
                    result = "v";
                    break;
                case "Tisch":
                    result = "t";
                    break;
                case "lPerson":
                    result = "l";
                    break;
                case "rPerson":
                    result = "r";
                    break;
                case "Kiste":
                    result = "k";
                    break;
                case "Magnet":
                    result = "m";
                    break;
                case "Nagel":
                    result = "n";
                    break;
                case "Taste":
                    result = "a";
                    break;
                case "Dipol":
                    result = "c";
                    break;
            }
        } catch(NullPointerException ex) {
            System.out.println("Failed to coding DrawGeometry name. -> Returned EMPTY value instead");
        }

        return result;
    }
}

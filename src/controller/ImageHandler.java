package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by Johannes Tormoehlen on 14.06.2016.
 *
 * @author jtormoehlen
 */
public class ImageHandler {

	private BufferedImage img;
	
	public ImageHandler(String fileName) {

		try {
			img = ImageIO.read(new File("res/" + fileName + ".png"));
		} catch (IOException e) {
			System.out.println("File not found!");
		}
	}

	public BufferedImage getImg() {
		return img;
	}
}

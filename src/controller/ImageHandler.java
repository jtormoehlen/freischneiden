package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

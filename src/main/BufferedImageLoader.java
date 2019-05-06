package main;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

		private BufferedImage image;
		
		public BufferedImage loadImage(String path) {
			try {
				image = ImageIO.read(getClass().getResource(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
}

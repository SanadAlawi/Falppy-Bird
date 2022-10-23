package game;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class SpriteSheetProvider {
	private final static Image OBSTACLE_IMAGE = new Image("/obstacle/PipeStyle1.png");
	
	public static Image getObstacleSubImage(Image image, int x, int y, double width, double height) {
		
		BufferedImage subImage= SwingFXUtils.fromFXImage(image, null).getSubimage(x, y, (int)width, (int)height);
		
		return SwingFXUtils.toFXImage(subImage, null);
	}
}

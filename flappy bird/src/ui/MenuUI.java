package ui;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class MenuUI extends UIStructure{

	public MenuUI(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super(gameWindow, graphicsContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UIHeader() {
		double x = (gameWindow.getGameWindowWidth() / 2) - ((gameWindow.getTileSize() * 2) / 2);
		double y = gameWindow.getGameWindowHeight() / 3;
		
		Image MenuImageHeader = new Image("/player/bird1.png");
		double MenuImageHeaderWidth = MenuImageHeader.getWidth() / 4;
		double MenuImageHeaderHeight = MenuImageHeader.getHeight();
		graphicsContext.drawImage(MenuImageHeader, 0, 0, MenuImageHeaderWidth, MenuImageHeaderHeight, x, y, gameWindow.getTileSize() * 2, gameWindow.getTileSize() * 2);
		
		x += gameWindow.getTileSize() * 2.5;
		y += (gameWindow.getTileSize() * 2) / 1.5;
		
		graphicsContext.fillText("Flappy Bird", x, y);
	}

	@Override
	public void options() {
		graphicsContext.setFont(new Font("Arial", 30));
		String optionText = "Play";
		double optionTextWidth = getTextWidth(optionText, "Arial", 30);
		int distanceBetweenOptions = gameWindow.getTileSize();
		
		double x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		double y = (gameWindow.getGameWindowHeight() / 2) + distanceBetweenOptions;
		
		if(currentOption == 0) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);
		
		optionText = "Quit";
		optionTextWidth = getTextWidth(optionText, "Arial", 30);
		distanceBetweenOptions = gameWindow.getTileSize();
		
		x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		y += distanceBetweenOptions;
		
		if(currentOption == 1) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);
		
	}

}

package ui;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PauseUI extends UIStructure{

	public PauseUI(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super(gameWindow, graphicsContext);
		
	}

	@Override
	public void UIHeader() {
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.setFont(new Font("Arial", 80));
		String text = "Pause";
		double textWidth = getTextWidth(text, "Arial", 80);
		graphicsContext.fillText(text, (gameWindow.getGameWindowWidth() / 2) - (textWidth / 2), gameWindow.getGameWindowHeight() / 2);
		
	}

	@Override
	public void options() {
		graphicsContext.setFont(new Font("Arial", 30));
		String optionText = "Continue";
		double optionTextWidth = getTextWidth(optionText, "Arial", 30);
		int distanceBetweenOptions = gameWindow.getTileSize();
		
		double x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		double y = (gameWindow.getGameWindowHeight() / 2) + distanceBetweenOptions;
		
		if(currentOption == 0) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);
		
		optionText = "Try again";
		optionTextWidth = getTextWidth(optionText, "Arial", 30);
		distanceBetweenOptions = gameWindow.getTileSize();
		
		x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		y += distanceBetweenOptions;
		
		if(currentOption == 1) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);
		
		optionText = "Quit";
		optionTextWidth = getTextWidth(optionText, "Arial", 30);
		distanceBetweenOptions = gameWindow.getTileSize();
		
		x = (gameWindow.getGameWindowWidth() / 2) - (optionTextWidth / 2);
		y += distanceBetweenOptions;
		
		if(currentOption == 2) graphicsContext.fillText(">", x - distanceBetweenOptions, y);
		graphicsContext.fillText(optionText, x, y);
		
	}

}

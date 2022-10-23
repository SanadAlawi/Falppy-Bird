package ui;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class UIStructure {
	
	protected GameWindow gameWindow;
	protected GraphicsContext graphicsContext;
	
	protected int currentOption = 0;
	
	
	public UIStructure(GameWindow gameWindow, GraphicsContext graphicsContext) {
		super();
		this.gameWindow = gameWindow;
		this.graphicsContext = graphicsContext;
	}

	public int getTextWidth(String text, String fontType, int fontSize) {
		Text stringText = new Text(text);
		stringText.setFont(new Font(fontType, fontSize));
		return (int) stringText.getLayoutBounds().getWidth();
	}
	
	public void show() {
		UIHeader();
		options();
	}
	
	public abstract void UIHeader();
	public abstract void options();

	public int getCurrentOption() {
		return currentOption;
	}

	public void setCurrentOption(int currentOption) {
		this.currentOption = currentOption;
	}
	
	
}

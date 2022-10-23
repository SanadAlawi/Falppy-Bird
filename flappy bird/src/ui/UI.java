package ui;

import constants.State;
import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI {
	private GameWindow gameWindow;
	
	private GameOverUI gameOverUI;
	private  MenuUI menuUI;
	private PauseUI pauseUI;
	
	private Font arialFont_30 = null;
	private Font arialFont_15 = null;

	public UI(GameWindow gameWindow) {
		super();
		this.gameWindow = gameWindow;
		gameOverUI = new GameOverUI(this.gameWindow, this.gameWindow.getGraphicsContext());
		menuUI = new MenuUI(gameWindow, this.gameWindow.getGraphicsContext());
		pauseUI = new PauseUI(gameWindow, this.gameWindow.getGraphicsContext());
		arialFont_30 = new Font("Arial", 30);
		arialFont_15 = new Font("Arial", 15);
	}
	
	public void draw(GraphicsContext graphicsContext) {
		switch (gameWindow.getGameState()) {
		
			case State.PLAY_STATE: playUi(graphicsContext); break;
				
			case State.PAUSE_STATE: pauseUI.show(); break;
				
			case State.GAME_OVER_STATE: gameOverUI.show(); break;
				
			case State.MENU_STATE: menuUI.show(); break;

		}
	}


	private void playUi(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.setFont(arialFont_30);
		graphicsContext.fillText(String.format("Score: %s", gameWindow.getPlayer().getScore()), gameWindow.getTileSize(), gameWindow.getTileSize());
		
		graphicsContext.setFont(arialFont_15);
		graphicsContext.fillText("Space to jump", gameWindow.getTileSize(), gameWindow.getTileSize() * 2);
		
	}

	public GameOverUI getGameOverUI() {
		return gameOverUI;
	}

	public void setGameOverUI(GameOverUI gameOverUI) {
		this.gameOverUI = gameOverUI;
	}

	public MenuUI getMenuUI() {
		return menuUI;
	}

	public void setMenuUI(MenuUI menuUI) {
		this.menuUI = menuUI;
	}

	public PauseUI getPauseUI() {
		return pauseUI;
	}

	public void setPauseUI(PauseUI pauseUI) {
		this.pauseUI = pauseUI;
	}
	
	
}

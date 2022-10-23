package game;

import constants.State;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyHandler {
	private boolean spacePressed = false;
	private GameWindow gameWindow;
	
	public KeyHandler(GameWindow gameWindow, Scene scene) {
		this.gameWindow = gameWindow;
		
		scene.setOnKeyPressed(e -> {
			
			KeyCode keyPressed = e.getCode();
			int gameState = gameWindow.getGameState();
			
			switch (gameState) {
			case State.PLAY_STATE: playStateInput(keyPressed); break;
			case State.PAUSE_STATE: pauseStateInput(keyPressed); break;
			case State.GAME_OVER_STATE: gameOverStateInput(keyPressed); break;
			case State.MENU_STATE: menuStateInput(keyPressed); break;
			}
			
		});
		
		scene.setOnKeyReleased(e -> {
			KeyCode keyReleased = e.getCode();
			
			switch (keyReleased) {
			case SPACE:
				spacePressed = false;
				break;
				
			}
			
		});
		
		
	}
	

	private void menuStateInput(KeyCode keyPressed) {
		switch (keyPressed) {	
		case UP:
			gameWindow.getUi().getMenuUI().setCurrentOption(gameWindow.getUi().getMenuUI().getCurrentOption() - 1);
			if(gameWindow.getUi().getMenuUI().getCurrentOption() < 0) gameWindow.getUi().getMenuUI().setCurrentOption(1);
			break;
			
		case DOWN:
			gameWindow.getUi().getMenuUI().setCurrentOption(gameWindow.getUi().getMenuUI().getCurrentOption() + 1);
			if(gameWindow.getUi().getMenuUI().getCurrentOption() > 1) gameWindow.getUi().getMenuUI().setCurrentOption(0);
			break;
			
		case ENTER:
			if(gameWindow.getUi().getMenuUI().getCurrentOption() == 0) gameWindow.reloadGame();
			else System.exit(0);
			
			break;
		}
		
	}


	private void gameOverStateInput(KeyCode keyPressed) {
		switch (keyPressed) {	
		case UP:
			gameWindow.getUi().getGameOverUI().setCurrentOption(gameWindow.getUi().getGameOverUI().getCurrentOption() - 1);
			if(gameWindow.getUi().getGameOverUI().getCurrentOption() < 0) gameWindow.getUi().getGameOverUI().setCurrentOption(2);
			break;

		case DOWN:
			gameWindow.getUi().getGameOverUI().setCurrentOption(gameWindow.getUi().getGameOverUI().getCurrentOption() + 1);
			if(gameWindow.getUi().getGameOverUI().getCurrentOption() > 2) gameWindow.getUi().getGameOverUI().setCurrentOption(0);
			break;
			
		case ENTER:
			if(gameWindow.getUi().getGameOverUI().getCurrentOption() == 0)  gameWindow.setGameState(State.MENU_STATE);
			else if(gameWindow.getUi().getGameOverUI().getCurrentOption() == 1) gameWindow.reloadGame();
			else System.exit(0);		
			break;
		}
		
	}


	private void pauseStateInput(KeyCode keyPressed) {
		switch (keyPressed) {	
		case UP:
			gameWindow.getUi().getPauseUI().setCurrentOption(gameWindow.getUi().getPauseUI().getCurrentOption() - 1);
			if(gameWindow.getUi().getPauseUI().getCurrentOption() < 0) gameWindow.getUi().getPauseUI().setCurrentOption(2);
			break;

		case DOWN:
			gameWindow.getUi().getPauseUI().setCurrentOption(gameWindow.getUi().getPauseUI().getCurrentOption() + 1);
			if(gameWindow.getUi().getPauseUI().getCurrentOption() > 2) gameWindow.getUi().getPauseUI().setCurrentOption(0);
			break;
		
		case SPACE:	
		case ENTER:
			if(gameWindow.getUi().getPauseUI().getCurrentOption() == 0)  gameWindow.setGameState(State.PLAY_STATE);
			else if(gameWindow.getUi().getPauseUI().getCurrentOption() == 1) gameWindow.reloadGame();
			else System.exit(0);		
			break;
		}
		
	}


	private void playStateInput(KeyCode keyPressed) {
		switch (keyPressed) {	
		case SPACE:
			spacePressed = true;
			break;

		case P:
			gameWindow.setGameState(State.PAUSE_STATE);
			break;
			
		}
		
	}


	public boolean isSpacePressed() {
		return spacePressed;
	}

	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}
	
}

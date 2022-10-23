package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
	/*
	 * Parameter one (row): how many row
	 * Parameter two (column): how many column
	 * Parameter three (scale): window scale
	 * 
	 * Window Width = tileSize * row
	 * Window Height = tileSize * column
	 * 
	 * By default tileSize = 16
	 * tileSize can increase by scale --> tileSize = tileSize * scale
	 */
	
	private GameWindow gameWindow = new GameWindow(25, 15, 3); 

	@Override
	public void start(Stage stage) throws Exception {
		gameWindow.open(stage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}

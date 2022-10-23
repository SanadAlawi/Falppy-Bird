package game;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.Document;

import constants.Counter;
import constants.SoundType;
import constants.State;
import entity.Obstacle;
import entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import sound.Sound;
import ui.UI;

public class GameWindow {
	private int frame = 0;
	private int tileSize = 16;
	private int GameWindowWidth = 0;
	private int GameWindowHeight = 0;
	private Canvas canvas;
	private GraphicsContext graphicsContext;

	private int scale = 0;
	private double gameSpeed = Counter.GAME_SPEED;
	private int hue = 0;
	private int gameState = State.MENU_STATE;

	private Timeline timeline;
	private Scene scene;
	private KeyHandler keyHandler;
	private Player player;
	private Queue<Obstacle> obstacles;
	private CollisionChecker collisionChecker;
	private Sound sounds;
	private UI ui;

	public GameWindow(int canvasColumn, int canvasRow, int scale) {
		super();
		this.scale = scale;
		this.tileSize *= this.scale;
		this.GameWindowWidth = canvasColumn * this.tileSize;
		this.GameWindowHeight = canvasRow * this.tileSize;

		setUpCanvasStaff();
	}

	private void setUpCanvasStaff() {
		this.canvas = new Canvas(this.GameWindowWidth, this.GameWindowHeight);
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run())); // Our fuction that run the game
																						// loop
		this.timeline.setCycleCount(Timeline.INDEFINITE);

		this.scene = new Scene(new Group(this.canvas));
		this.keyHandler = new KeyHandler(this, scene);

		this.player = new Player(this, tileSize * 5, GameWindowHeight / 2, 7, keyHandler, "bird3", 4, 1);
		this.obstacles = new LinkedList<Obstacle>();
		this.collisionChecker = new CollisionChecker(this);
		this.ui = new UI(this);
		this.sounds = new Sound(2);

	}

	// OPEN THE WINDOW AND START GAME LOOP FUNCTION
	public void open(Stage stage) {
		stage.setScene(scene);
		stage.show();
		timeline.play();
	}

	Image background = new Image("/background/Background2.png");
	double x = 0;

	// GAME LOOP FUNCTION
	private void run() {
		graphicsContext.clearRect(0, 0, GameWindowWidth, GameWindowHeight);
		drawMovingBackground();

		switch (gameState) {
		case State.PLAY_STATE: handlePlayStateStaff(); break;
		
		case State.MENU_STATE: ui.draw(graphicsContext); break;
		
		case State.GAME_OVER_STATE: handleGameOverStaff(); break;
		}
	}


	private void handlePlayStateStaff() {
		// Drawing obstacles and updated their position
		handleObstacles();
		
		// Draw player and updated it's position
		if (gameState != State.PAUSE_STATE) player.update();
		player.draw(graphicsContext);

		// UI drawing, Like score or any text
		ui.draw(graphicsContext);
		
		frame++;
		hue++;
		
	}
	

	private void handleGameOverStaff() {
		for (Obstacle obstacle : obstacles) obstacle.draw(graphicsContext);
		player.draw(graphicsContext);
		ui.draw(graphicsContext);
		
	}

	private void drawMovingBackground() {
		for (int i = 0; i < 2; i++)
			graphicsContext.drawImage(background, x + i * GameWindowWidth, 0, GameWindowWidth, GameWindowHeight);

		x -= gameSpeed;
		if (x + 1 * GameWindowWidth < 0)
			x = 0;

	}
	
	private void handleObstacles() {
		// Draw obstacles
		for (Obstacle obstacle : obstacles) {
			if (gameState != State.PAUSE_STATE)
				obstacle.update();
			obstacle.draw(graphicsContext);

			// Get a point if player get throw obstacle with sound effect
			if (!obstacle.isCountedScore() && obstacle.getxPosition() + obstacle.getWidth() < player.getxPosition()) {
				player.addScore();
				obstacle.setCountedScore(true);

				sounds.setSound(SoundType.GET_POINT);
				sounds.start();
			}
		}

		// Create new obstacle every 170 frame
		if (frame == 170) {
			obstacles.add(new Obstacle(this, GameWindowWidth, 0, gameSpeed));
			frame = 0;
		}
		// Remove one obstacle if obstacles number more that 40 obstacle
		if (obstacles.size() > 40)
			obstacles.poll();

	}

	public void reloadGame() {
		player.setScore(0);
		player.setyPosition(GameWindowHeight / 2);

		obstacles.clear();
		gameSpeed = Counter.GAME_SPEED;
		gameState = State.PLAY_STATE;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getGameWindowWidth() {
		return GameWindowWidth;
	}

	public void setGameWindowWidth(int gameWindowWidth) {
		GameWindowWidth = gameWindowWidth;
	}

	public int getGameWindowHeight() {
		return GameWindowHeight;
	}

	public void setGameWindowHeight(int gameWindowHeight) {
		GameWindowHeight = gameWindowHeight;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public CollisionChecker getCollisionChecker() {
		return collisionChecker;
	}

	public void setCollisionChecker(CollisionChecker collisionChecker) {
		this.collisionChecker = collisionChecker;
	}

	public Sound getSounds() {
		return sounds;
	}

	public void setSounds(Sound sounds) {
		this.sounds = sounds;
	}

	public int getHue() {
		return hue;
	}

	public void setHue(int hue) {
		this.hue = hue;
	}

	public Queue<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(Queue<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public double getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(double gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

}

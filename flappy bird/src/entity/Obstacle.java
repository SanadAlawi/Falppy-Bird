package entity;

import java.util.Random;

import game.Animation;
import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Entity{
     
	
	private double topObstacleHeight = 0;
	private double bottomObstacleHeight = 0;
	private Rectangle boxColliderBottom = null;
	private boolean countedScore = false;
	
	
	public Obstacle(GameWindow gameWindow, double xPosition, double yPosition, double speed) {
		super(gameWindow, xPosition, yPosition, speed);
		this.topObstacleHeight = new Random().nextInt(gameWindow.getGameWindowHeight() - gameWindow.getTileSize());
		this.bottomObstacleHeight = gameWindow.getGameWindowHeight() - this.topObstacleHeight - gameWindow.getTileSize() * 5;
//		this.bottomObstacleHeight = (Math.random() * gameWindow.getGameWindowHeight() / 3) + 20;
		this.spriteSheet = new Animation(4, 2);
		this.spriteSheet.setSubSpriteSheet(new Image("/obstacle/PipeStyle1.png"), 0, 1);
		
		boxCollider = new Rectangle(xPosition, yPosition, width, this.topObstacleHeight);
		boxColliderBottom = new Rectangle(xPosition, gameWindow.getGameWindowHeight() - bottomObstacleHeight, width, this.bottomObstacleHeight);
	}

	@Override
	public void update() {
		updateBoxCollider();
		updateBoxColliderBottom();
		
		xPosition -= speed;	
	}

	private void updateBoxColliderBottom() {
		boxColliderBottom.setX(xPosition);
		boxColliderBottom.setY(gameWindow.getGameWindowHeight() - boxColliderBottom.getHeight());
		
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {		
		graphicsContext.drawImage(spriteSheet.getSpriteSheet(), xPosition, 0, width, topObstacleHeight);
		graphicsContext.drawImage(spriteSheet.getSpriteSheet(), xPosition, gameWindow.getGameWindowHeight() - bottomObstacleHeight, width, bottomObstacleHeight);
		
	}

	public double getTopObstacleHeight() {
		return topObstacleHeight;
	}

	public void setTopObstacleHeight(double topObstacleHeight) {
		this.topObstacleHeight = topObstacleHeight;
	}

	public double getBottomObstacleHeight() {
		return bottomObstacleHeight;
	}

	public void setBottomObstacleHeight(double bottomObstacleHeight) {
		this.bottomObstacleHeight = bottomObstacleHeight;
	}

	public Rectangle getBoxColliderBottom() {
		return boxColliderBottom;
	}

	public void setBoxColliderBottom(Rectangle boxColliderBottom) {
		this.boxColliderBottom = boxColliderBottom;
	}

	public boolean isCountedScore() {
		return countedScore;
	}

	public void setCountedScore(boolean countedScore) {
		this.countedScore = countedScore;
	}
	
	

}

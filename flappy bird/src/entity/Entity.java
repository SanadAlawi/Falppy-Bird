package entity;

import game.Animation;
import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
	
	protected GameWindow gameWindow;
	protected double xPosition = 0;
	protected double yPosition = 0;
	protected double speed;
	protected double width;
	protected double height;
	protected Animation spriteSheet;
	
	protected Rectangle boxCollider;
	protected int boxColliderOffsetX = 0;
	protected int boxColliderOffsetY = 0;
	
	
	public Entity(GameWindow gameWindow, double xPosition, double yPosition, double speed) {
		super();
		this.gameWindow = gameWindow;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.speed = speed;
		this.width = gameWindow.getTileSize();
		this.height = gameWindow.getTileSize();
		this.boxCollider = new Rectangle(xPosition, yPosition, width, height);
	}
	
	public abstract void update();
	public abstract void draw(GraphicsContext graphicsContext);
	
	public void updateBoxCollider() {
		boxCollider.setX(xPosition + boxColliderOffsetX);
		boxCollider.setY(yPosition + boxColliderOffsetY);
	}
	
	public double getxPosition() {
		return xPosition;
	}
	
	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}
	public double getyPosition() {
		return yPosition;
	}
	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Rectangle getBoxCollider() {
		return boxCollider;
	}

	public void setBoxCollider(Rectangle boxCollider) {
		this.boxCollider = boxCollider;
	}

	public Animation getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Animation spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	
	
	
}

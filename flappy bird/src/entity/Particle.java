package entity;

import game.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle extends Entity{
	
	private int size = 0;
	private String color = null;
	private int ySpeed = 0;

	public Particle(GameWindow gameWindow, double xPosition, double yPosition, double speed) {
		super(gameWindow, xPosition, yPosition, speed);
		
		this.size = (int)(Math.random() * gameWindow.getTileSize() / 4) + gameWindow.getTileSize() / 4;
		this.color = "hsl("+ gameWindow.getHue() +", 100%, 50%)";
		this.ySpeed = (int)((Math.random() * 6) - 3);
		while(ySpeed == 0) this.ySpeed = (int)((Math.random() * 6) - 3);
//		if(ySpeed == 0) this.ySpeed = (int)((Math.random() * 6) - 3);
	}

	@Override
	public void update() {
		xPosition -= speed; 
		yPosition += ySpeed;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.valueOf(color));
		graphicsContext.fillOval(xPosition, yPosition, size, size);
	}

}

package entity;

import java.util.LinkedList;
import java.util.Queue;

import constants.Counter;
import constants.SoundType;
import constants.State;
import game.Animation;
import game.GameWindow;
import game.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Entity{
	private final double SCREEN_X_POSITION;

	private KeyHandler keyHandler;
	
	private double yVelocity = 0;
	private double gravity = 0.5;
	private double jump = 1;
	private int score = 0;
	private Queue<Particle> particles = null;
	
	private int xFrame = 3;
	private int xFrameCounter = 0;
	
	
	public Player(GameWindow gameWindow, double xPosition,double yPosition, double speed, KeyHandler keyHandler, String spriteSheetType, int xFrame, int yFrame) {
		super(gameWindow, xPosition, yPosition, speed);
		
		SCREEN_X_POSITION = xPosition;
		this.keyHandler = keyHandler;
		this.spriteSheet = new Animation(String.format("/player/%s.png", spriteSheetType), xFrame, yFrame);
		this.width = gameWindow.getTileSize();
		this.height = gameWindow.getTileSize();
		
		this.boxCollider = new Rectangle(xPosition, yPosition, this.width, this.height);
		this.particles = new LinkedList<Particle>();
	}

	@Override
	public void update() {
		updateBoxCollider();
			
		boolean collisionHappen = gameWindow.getCollisionChecker().playerCollision(this);
		if(collisionHappen) obstacleCollision();
		
		if(yPosition > gameWindow.getGameWindowHeight() - boxCollider.getHeight()) {
			birdInBottom();
		}else if(yPosition < 0) {
			birdInRoof();
		}else{
			putGravityToBird();
		}
		
		if(keyHandler.isSpacePressed()) {
			jump();
//			particles.add(new Particle(gameWindow, SCREEN_X_POSITION, yPosition + (height / 2), gameWindow.getGameSpeed()));
		}
		
		particles.add(new Particle(gameWindow, SCREEN_X_POSITION, yPosition + (height / 2), gameWindow.getGameSpeed()));
	}
	
	@Override
	public void draw(GraphicsContext graphicsContext) {
		drawParticles();
		
		graphicsContext.drawImage(spriteSheet.getSpriteSheet(), spriteSheet.getSpriteSheetWidth() * xFrame, 0, spriteSheet.getSpriteSheetWidth(), spriteSheet.getSpriteSheetHeight(), SCREEN_X_POSITION, yPosition, width, height);
		
		xFrameCounter++;
		if(xFrameCounter == Counter.SPRITSHEET_COUNTER_CHANGE) {
			xFrameCounter = 0;
			xFrame++;
			if(xFrame >= spriteSheet.getxSpriteSheetFrame()) xFrame = 0;	
		}
		
	}

	private void drawParticles() {
		for(Particle particle : particles) {
			if(gameWindow.getGameState() != State.PAUSE_STATE) particle.update();
			particle.draw(gameWindow.getGraphicsContext());
		}
		
		if(particles.size() > 200) particles.poll();
	}

	private void obstacleCollision() {
		gameWindow.getSounds().setSound(SoundType.BIRD_COLLISION);
		gameWindow.getSounds().start();
		
		gameWindow.setGameState(State.GAME_OVER_STATE);
	}

	private void jump() {
		yVelocity -= jump;
		
	}

	private void putGravityToBird() {
		yVelocity += gravity; 
		yVelocity *= 0.9;
		yPosition += yVelocity;
		
	}

	private void birdInRoof() {
		yPosition = 0;
		yVelocity = 0;
		
	}

	private void birdInBottom() {
		yPosition = gameWindow.getGameWindowHeight() - boxCollider.getHeight();
		yVelocity = 0;
		
	}
  
	
	public void addScore() {
		this.score += 1;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}

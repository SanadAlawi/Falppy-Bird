package game;

import entity.Entity;
import entity.Obstacle;
import entity.Player;
import javafx.scene.shape.Rectangle;

public class CollisionChecker {
	private GameWindow gameWindow;

	public CollisionChecker(GameWindow gameWindow) {
		super();
		this.gameWindow = gameWindow;
	}
	
	public boolean playerCollision(Player player) {
		for (Obstacle obstacle : gameWindow.getObstacles()) {
			if ( overlaps(player.getBoxCollider(), obstacle.getBoxCollider()) || overlaps(player.getBoxCollider(), obstacle.getBoxColliderBottom()))
				return true;
		}

		return false;
	}
	
	public boolean playerCornerCollisionInX(Player player, Obstacle obstacle) {
		return (player.getxPosition() >= obstacle.getxPosition() && player.getxPosition() <= obstacle.getxPosition() + obstacle.getWidth()) || 
			   (player.getxPosition() + player.getWidth() >= obstacle.getxPosition() && player.getxPosition() + player.getWidth() <= obstacle.getxPosition() + obstacle.getWidth());
	}
	public boolean playerCornerCollisionInY(Player player, Obstacle obstacle) {
		double obstacleBottomPositionY = gameWindow.getGameWindowHeight() - obstacle.getBottomObstacleHeight();
		return ((player.getyPosition() >= obstacle.getyPosition() &&
				player.getyPosition() <= obstacle.getyPosition() + obstacle.getTopObstacleHeight()) ||
				(player.getyPosition() >= obstacleBottomPositionY && 
				player.getyPosition() <= obstacleBottomPositionY + obstacle.getBottomObstacleHeight()));
	}

	public boolean overlaps(Rectangle entity, Rectangle object) {
		return object.getX() < entity.getX() + entity.getWidth() && object.getX() + object.getWidth() > entity.getX()
				&& object.getY() < entity.getY() + entity.getHeight()
				&& object.getY() + object.getHeight() > entity.getY();
	}
}

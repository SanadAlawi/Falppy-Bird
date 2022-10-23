package game;

import javafx.scene.image.Image;

public class Animation {
	private Image spriteSheet;
	private double spriteSheetWidth;
	private double spriteSheetHeight;
	private int xSpriteSheetFrame;
	private int ySpriteSheetFrame;
	
	public Animation(String spriteSheetType, int xspriteSheetFrame, int ySpriteSheetFrame) {
		super();
		this.spriteSheet = new Image(spriteSheetType);
		this.xSpriteSheetFrame = xspriteSheetFrame;
		this.ySpriteSheetFrame = ySpriteSheetFrame;
		this.spriteSheetWidth = this.spriteSheet.getWidth() / this.xSpriteSheetFrame;
		this.spriteSheetHeight = this.spriteSheet.getHeight() / this.ySpriteSheetFrame;
	}
	
	public Animation(int xspriteSheetFrame, int ySpriteSheetFrame) {
		super();
		this.xSpriteSheetFrame = xspriteSheetFrame;
		this.ySpriteSheetFrame = ySpriteSheetFrame;
//		this.spriteSheetWidth = this.spriteSheet.getWidth() / this.xSpriteSheetFrame;
//		this.spriteSheetHeight = this.spriteSheet.getHeight() / this.ySpriteSheetFrame;
	}

	public Image getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(String spriteSheetPath) {
		this.spriteSheet = new Image(String.format("/player/%s.png", spriteSheetPath));
	}

	public double getSpriteSheetWidth() {
		return spriteSheetWidth;
	}

	public void setSpriteSheetWidth(double spriteSheetWidth) {
		this.spriteSheetWidth = spriteSheetWidth;
	}

	public double getSpriteSheetHeight() {
		return spriteSheetHeight;
	}

	public void setSpriteSheetHeight(double spriteSheetHeight) {
		this.spriteSheetHeight = spriteSheetHeight;
	}

	public int getxSpriteSheetFrame() {
		return xSpriteSheetFrame;
	}

	public void setxSpriteSheetFrame(int xSpriteSheetFrame) {
		this.xSpriteSheetFrame = xSpriteSheetFrame;
	}

	public int getySpriteSheetFrame() {
		return ySpriteSheetFrame;
	}

	public void setySpriteSheetFrame(int ySpriteSheetFrame) {
		this.ySpriteSheetFrame = ySpriteSheetFrame;
	}

	public void setSpriteSheet(Image spriteSheet) {
		this.spriteSheet = spriteSheet;
	}


	public void setSubSpriteSheet(Image image, int x, int y) {
		this.spriteSheetWidth = image.getWidth() / xSpriteSheetFrame;
		this.spriteSheetHeight = image.getHeight() / ySpriteSheetFrame;
		spriteSheet = SpriteSheetProvider.getObstacleSubImage(image, (int)(x * spriteSheetWidth), (int)(y * spriteSheetHeight), spriteSheetWidth, spriteSheetHeight);
	}
	
	
}

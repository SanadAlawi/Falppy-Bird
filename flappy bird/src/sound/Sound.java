package sound;

import constants.SoundType;
import game.SetUp;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	private Media [] soundsMedia;
	private MediaPlayer mediaPlayer;
	
	public Sound(int NumberOfSound) {
		super();
		this.soundsMedia = new Media[NumberOfSound];
		
		setUp();
	}

	private void setUp() {
		soundsMedia[SoundType.BIRD_COLLISION] = new Media(getClass().getResource("/sound/birdCollision.wav").toExternalForm());
		soundsMedia[SoundType.GET_POINT] = new Media(getClass().getResource("/sound/getPoint.wav").toExternalForm());
//		soundsMedia[SoundType.PADDLE_COLLISION] = new Media(getClass().getResource("/sound/paddleCollision.wav").toExternalForm());
	}
	
	public void setSound(int soundNumber) {
		mediaPlayer = new MediaPlayer(soundsMedia[soundNumber]);
	}
	
	public void start() {
		mediaPlayer.play();
	}
	public void stop() {
		mediaPlayer.stop();
	}
	
	
}

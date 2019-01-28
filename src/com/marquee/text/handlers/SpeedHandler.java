package com.marquee.text.handlers;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;

public class SpeedHandler implements EventHandler<ScrollEvent>{
	
	private TranslateTransition translateTransition;
	private int speed = 3000;
	
	public SpeedHandler(TranslateTransition translateTransition) {
		this.translateTransition = translateTransition;
	}
	
	@Override
	public void handle(ScrollEvent event) {
		if(event.getDeltaY() < 0) {
			speed += 1000;
		}else if(event.getDeltaY() > 0 && speed > 0){
			speed -= 1000;
		}
		System.out.println("Speed: " + speed);
		translateTransition.stop();
		translateTransition.setDuration(Duration.millis(speed));
		translateTransition.setFromX(0); translateTransition.play();
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}

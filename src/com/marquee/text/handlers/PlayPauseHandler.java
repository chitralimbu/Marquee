package com.marquee.text.handlers;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.animation.TranslateTransition;

public class PlayPauseHandler implements EventHandler<MouseEvent>{
	
	private TranslateTransition translateTransition;
	private boolean paused = false;

	public PlayPauseHandler(TranslateTransition translateTransition) {
		this.translateTransition = translateTransition;
	}
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(!paused) {
			translateTransition.pause();
			paused = true;
		}else {
			translateTransition.play();
			paused = false;
		}
	}

}


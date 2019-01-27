package com.marquee.text;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.marquee.text.handlers.PlayPauseHandler;

import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Font;

public class MarqueeText extends Application{
	
	private boolean paused = false;
	private int speed = 1000;
	
	@Override
	public void start(Stage stage) throws Exception {
		//TextFlow textFlow = new TextFlow();
		ContextMenu contextMenu = new ContextMenu();
		Text text = new Text();
		Text text2 = new Text();
		
		text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		text.setText("I'm using a TextFlow");
		text2.setText("Hi I am text 2");
		
		text.setFill(Color.BLUEVIOLET);
		text2.setFill(Color.BISQUE);
		
		text.setX(-text.getLayoutBounds().getWidth());
		text.setY(50);
		/* text.setFill(Color.BROWN); */
		
		
		/*
		 * textFlow.getChildren().addAll(text, text2); textFlow.setLayoutX(100);
		 * textFlow.setLayoutY(50);
		 * 
		 * 
		 * System.out.println(textFlow.getWidth());
		 */
		
		
		TranslateTransition translateTransition = new TranslateTransition();
		
		translateTransition.setDuration(Duration.millis(speed));
		
		translateTransition.setNode(text);
		
		translateTransition.setByX(text.getLayoutBounds().getWidth() + 600);
		
		translateTransition.setCycleCount(-1);
		
		translateTransition.setAutoReverse(false);
		
		translateTransition.play();
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				text.setFill(Color.DEEPPINK);
			}
			
		};
		
		PlayPauseHandler playPause = new PlayPauseHandler(translateTransition);
		
		EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
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
		};
		
		EventHandler<ScrollEvent> eventHandler3 = new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if(event.getDeltaY() < 0 && speed > 0) {
					speed -= 100;
				}else if(event.getDeltaY() > 0){
					speed += 100;
				}
				System.out.println("Speed: " + speed);
				translateTransition.stop();
				translateTransition.setDuration(Duration.millis(speed));
				translateTransition.setFromX(0); translateTransition.play();
			}
		};
		
		stage.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		stage.addEventFilter(MouseEvent.MOUSE_CLICKED, playPause);
		stage.addEventFilter(ScrollEvent.ANY, eventHandler3);
		
		Group root = new Group(text);
		
		Scene scene = new Scene(root, 600, 600);
		
		stage.setTitle("Sample application");
		stage.setAlwaysOnTop(true);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("Hello world");
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
}

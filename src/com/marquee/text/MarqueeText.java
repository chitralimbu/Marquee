package com.marquee.text;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
//import javafx.scene.control.ContextMenu;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.marquee.text.handlers.PlayPauseHandler;
import com.marquee.text.handlers.SpeedHandler;

import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Font;

public class MarqueeText extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		//TextFlow textFlow = new TextFlow();
		//ContextMenu contextMenu = new ContextMenu();
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
		SpeedHandler spHandler = new SpeedHandler(translateTransition);
		PlayPauseHandler playPause = new PlayPauseHandler(translateTransition);
		
		translateTransition.setDuration(Duration.millis(spHandler.getSpeed()));
		translateTransition.setNode(text);
		translateTransition.setByX(text.getLayoutBounds().getWidth() + 600);
		translateTransition.setCycleCount(-1);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
		
		//stage.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		stage.addEventFilter(MouseEvent.MOUSE_CLICKED, playPause);
		stage.addEventFilter(ScrollEvent.ANY, spHandler);
		
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

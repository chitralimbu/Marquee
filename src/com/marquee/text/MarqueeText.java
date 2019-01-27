package com.marquee.text;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.marquee.text.handlers.PlayPauseHandler;
import com.marquee.text.handlers.SpeedHandler;

import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Font;

public class MarqueeText extends Application{
	
	private Text text;
	private TranslateTransition translateTransition;
	private SpeedHandler spHandler;
	private PlayPauseHandler playPause;
	private Group root;
	private Scene scene;
	private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40);
	
	@Override
	public void start(Stage stage) throws Exception {
		//TextFlow textFlow = new TextFlow();
		text = new Text();
		text.setFont(font);
		text.setText("In our ProductController class, we only have the ProductRepository reference to access the Product domain object within the list method. But accessing ProductRepository directly from the ProductController is not the best practice, as it is always good to access the Persistence layer repository via a service object.");
		text.setFill(Color.BLACK);
		text.setX(-text.getLayoutBounds().getWidth());
		text.setY(text.getLayoutBounds().getHeight());
		/* text.setFill(Color.BROWN); */
		/*
		 * textFlow.getChildren().addAll(text, text2); textFlow.setLayoutX(100);
		 * textFlow.setLayoutY(50);
		 * 
		 * 
		 * System.out.println(textFlow.getWidth());
		 */
		translateTransition = new TranslateTransition();
		spHandler = new SpeedHandler(translateTransition);
		playPause = new PlayPauseHandler(translateTransition);
		
		translateTransition.setDuration(Duration.millis(spHandler.getSpeed()));
		translateTransition.setNode(text);
		translateTransition.setByX(primScreenBounds.getWidth() + text.getLayoutBounds().getWidth());
		translateTransition.setCycleCount(-1);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
		
		root = new Group(text);
		scene = new Scene(root, 600, 600);
		stage.addEventFilter(MouseEvent.MOUSE_CLICKED, playPause);
		stage.addEventFilter(ScrollEvent.ANY, spHandler);
		stage.setTitle("Sample application");
		stage.setX(0);
		stage.setY(primScreenBounds.getHeight() - text.getLayoutBounds().getHeight());
		stage.setHeight(text.getLayoutBounds().getHeight() + 15);
		stage.setWidth(primScreenBounds.getWidth());
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setAlwaysOnTop(true);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
}

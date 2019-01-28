package com.marquee.text;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.Interpolator;

import java.util.Arrays;
import java.util.List;

import com.marquee.text.handlers.PlayPauseHandler;
import com.marquee.text.handlers.SpeedHandler;
import javafx.scene.control.TextField;
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.collections.ObservableList; 
import javafx.geometry.Insets;
public class MarqueeText extends Application{
	
	private Text text;
	private TranslateTransition translateTransition;
	private SpeedHandler spHandler;
	private PlayPauseHandler playPause;
	private Group root;
	private Scene scene;
	private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	private Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
	private HBox hbox;
	private ObservableList<Node> list;
	private List<String> myObjects = Arrays.asList("Hello thereasdfasdfasdfasdfasdfasdfasdf", "Hi world", "Ticker tape");
	
	@Override
	public void start(Stage stage) throws Exception {
		//TextFlow textFlow = new TextFlow();
		BorderPane root = new BorderPane();
		hbox = new HBox();
		/* hbox.setSpacing(10); */
		list = hbox.getChildren();
		myObjects
			.stream()
			.forEach(text -> {
				Label label3 = new Label(text);
				label3.setFont(font);
				label3.setStyle("-fx-background-color: white;");
				label3.setPadding(new Insets(5,10,5,10));
				label3.setWrapText(false);
				list.add(label3);
			});
		hbox.setStyle("-fx-background-color: white;");
		root.setStyle("-fx-background-color: white;");
		root.setLayoutX(1500);
		root.setBottom(hbox);
		scene = new Scene(root, Color.WHITE);
		translateTransition = new TranslateTransition();
		spHandler = new SpeedHandler(translateTransition);
		playPause = new PlayPauseHandler(translateTransition);
		translateTransition.setInterpolator(Interpolator.LINEAR);
		translateTransition.setDuration(Duration.millis(spHandler.getSpeed()));
		translateTransition.setNode(hbox);
		translateTransition.setByX(-(primScreenBounds.getWidth() + calculateWidth()));
		translateTransition.setCycleCount(-1);
		translateTransition.setAutoReverse(false);
		translateTransition.play();	
		
		stage.addEventFilter(MouseEvent.MOUSE_CLICKED, playPause);
		stage.addEventFilter(ScrollEvent.ANY, spHandler); 
		stage.setTitle("Sample application");
		stage.setX(0);
		stage.setY(0/* primScreenBounds.getHeight() */);
		stage.setHeight(primScreenBounds.getHeight()/* calculateHeight() + 5 */);
		stage.setWidth(primScreenBounds.getWidth());
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setAlwaysOnTop(true);
		stage.setScene(scene);
		stage.show();
	}
	
	public double calculateWidth() {
		return myObjects.stream()
			.map(str -> {
				Text text = new Text();
				text.setText(str);
				text.setFont(font);
				return text;
			}).mapToDouble(txt -> txt.getLayoutBounds().getWidth())
			.sum();
	}
	
	public double calculateHeight() {
		Text text = new Text();
		text.setText("Hi");
		text.setFont(font);
		return text.getLayoutBounds().getHeight();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
}

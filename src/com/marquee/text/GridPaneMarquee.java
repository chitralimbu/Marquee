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

import java.nio.file.attribute.PosixFilePermission;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.collections.ObservableList; 
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
public class GridPaneMarquee extends Application{
	
	private Text text;
	private TranslateTransition translateTransition;
	private SpeedHandler spHandler;
	private PlayPauseHandler playPause;
	private Group root;
	private Scene scene;
	private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	private Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30);
	//private HBox hbox;
	private ObservableList<Node> list;
	private List<String> myObjects = Arrays.asList("Hello thereasdfasdfasdfasdfasdfasdfasdf", "Hi world", "Ticker tape");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("GridPane Experiment");

        Label button1 = new Label("Label asdfasdfasdf1");
        Label button2 = new Label("Label 2");
        Label button3 = new Label("Label 3");
        Label button4 = new Label("Label 4");
        Label button5 = new Label("Label 5");
        Label button6 = new Label("Label 6");
        button1.setMinWidth(100);
        button2.setMinWidth(100);
        button3.setMinWidth(100);
        button4.setMinWidth(100);
        button5.setMinWidth(100);
        button6.setMinWidth(100);
        GridPane gridPane = new GridPane();

        gridPane.add(button1, 1, 0);
        gridPane.add(button2, 2, 0);
        gridPane.add(button3, 3, 0);
        gridPane.add(button4, 4, 0);
        gridPane.add(button5, 5, 0);
        gridPane.add(button6, 6, 0);

        Scene scene = new Scene(gridPane, 240, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
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
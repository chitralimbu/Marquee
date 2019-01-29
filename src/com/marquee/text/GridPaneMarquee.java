package com.marquee.text;

import com.marquee.httpclient.ApacheHttpClient;
import com.marquee.model.ChangeObject;
import com.marquee.model.ParseXML;
import com.marquee.text.handlers.PlayPauseHandler;
import com.marquee.text.handlers.SpeedHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.transform.Translate;

public class GridPaneMarquee extends Application{
	private TranslateTransition translateTransition;
	private SpeedHandler spHandler;
	private PlayPauseHandler playPause;
	private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	private Font font = Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 45);
	private ApacheHttpClient ap = new ApacheHttpClient();
	private ParseXML pXML = new ParseXML(ap.allXML());
	private List<ChangeObject> allChanges;
	private BorderPane bp;
	private GridPane gridPane;
	private static final String GREENCOLOR = "#228B22";
	private static final String BLUECOLOR = "#009ACD";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("GridPane Experiment");
		bp = new BorderPane();
		allChanges = pXML.allObjects();
		gridPane = new GridPane();
		
		instantiateGrids();
		
		bp.setBottom(gridPane);
		Translate translate = new Translate();
		translate.setX(primScreenBounds.getWidth() + (30 * allChanges.size()));
		translate.setY(0);
		
		gridPane.getTransforms().addAll(translate);
		
		translateTransition = new TranslateTransition();
		spHandler = new SpeedHandler(translateTransition);
		playPause = new PlayPauseHandler(translateTransition);
		
		setTranslateTransitions();
		
		Scene scene = new Scene(bp, Color.WHITE);
		gridPane.setStyle("-fx-background-color: white;");
		bp.setStyle("-fx-background-color: white;");
		
		primaryStage.addEventFilter(MouseEvent.MOUSE_CLICKED, playPause);
		primaryStage.addEventFilter(ScrollEvent.ANY, spHandler);
        primaryStage.setHeight(calculateHeight() + 10);
        primaryStage.setWidth(primScreenBounds.getWidth());
        primaryStage.setX(0);
        primaryStage.setY(primScreenBounds.getHeight() - calculateHeight());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void setTranslateTransitions() {
		translateTransition.setNode(gridPane);
		translateTransition.setInterpolator(Interpolator.LINEAR);
		translateTransition.setDuration(Duration.millis(spHandler.getSpeed()));
		translateTransition.setByX(-(primScreenBounds.getWidth() + calculateTotalWidth() + (50 * allChanges.size())));
		translateTransition.setCycleCount(-1);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
	}
	
	public void instantiateGrids() {
		
		IntStream.range(0, allChanges.size())
		.forEach(i -> {
			Label label = new Label(allChanges.get(i).toString());
			label.setMinWidth(calculateWidth(allChanges.get(i).toString()) + 30);
			label.setPadding(new Insets(5,10,5,10));
			label.setFont(font);
			
			if(allChanges.get(i).getStart_date().isBefore(LocalDateTime.now())) {
				label.setStyle("-fx-background-color: " + GREENCOLOR + "; -fx-text-fill: white ; -fx-border-color: white;");
			}else if(i % 2 == 0) {
				label.setStyle("-fx-background-color: " + BLUECOLOR + "; -fx-text-fill: white ;");
			}
			gridPane.add(label, i, 0);
		});
	}
	
	public double calculateTotalWidth() {
		return allChanges.stream()
			.map(str -> {
				Text text = new Text();
				text.setText(str.toString());
				text.setFont(font);
				return text;
			}).mapToDouble(txt -> txt.getLayoutBounds().getWidth())
			.sum();
	}
	
	public double calculateWidth(String txt) {
		Text text = new Text();
		text.setFont(font);
		text.setText(txt);
		return text.getLayoutBounds().getWidth();
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

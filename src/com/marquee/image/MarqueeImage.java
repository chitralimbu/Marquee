package com.marquee.image;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class MarqueeImage extends Application {  
   @Override 
   public void start(Stage stage) throws FileNotFoundException {         
      //Creating an image 
      Image image = new Image(new FileInputStream(""));  
      
      //Setting the image view 
      ImageView imageView = new ImageView(image); 
      
      //Setting the position of the image 
      imageView.setX(-image.getWidth()); 
      imageView.setY(0); 
      
      //setting the fit height and width of the image view 
      imageView.setFitHeight(455); 
      imageView.setFitWidth(500); 
      
      //Setting the preserve ratio of the image view 
      imageView.setPreserveRatio(true);  
      
      TranslateTransition translateTransition = new TranslateTransition();
      
      translateTransition.setDuration(Duration.millis(2000));
      
      translateTransition.setNode(imageView);
      
      translateTransition.setByX(image.getWidth() + 600); 
      
      translateTransition.setCycleCount(-1);
      
      translateTransition.setAutoReverse(false);
      
      translateTransition.play();
      
      //Creating a Group object  
      Group root = new Group(imageView);  
      
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 500);  
      //Setting title to the Stage 
      stage.setTitle("Loading an image");  
      //Adding scene to the stage 
      stage.setScene(scene);
      Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
      stage.setX(0);
      stage.setY(primScreenBounds.getHeight() - image.getHeight());
      stage.initStyle(StageStyle.UNDECORATED);
      //Displaying the contents of the stage 
      stage.show(); 
   }  
   public static void main(String args[]) { 
      launch(args); 
   } 
}
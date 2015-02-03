package Game;

import Screens.SplashScreen;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	private static final int WIDTH = 500;
	private static final int HEIGHT = 600;
	
    private SplashScreen mySplash;

    public void start (Stage s) //playing the game sends player to the splash screen
    {
    	mySplash = new SplashScreen();
        setupView(s);
    }
    
    public void setupView(Stage s) //creates the splash screen and sets up the view
    {
    	Scene scene = mySplash.initialize(s, WIDTH, HEIGHT);
    	s.setTitle("Space Chase");
        s.setScene(scene);
        s.show();
    }

    public static void main (String[] args) 
    {
        launch(args);
    }
}


package Screens;

import Levels.LevelTwo;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BetweenLevelsScreen extends Screen
{
	private static final int NEXT_X_OFFSET = -150;
	private static final int LABEL_TEXT_SIZE = 65;
	
	public Scene initialize(Stage s, int width, int height)
	{
		//initializes variables that are inherited from abstract class
		myRoot = new Group();	
		myScene = new Scene(myRoot, width, height, Color.DARKGREY);
		myGame = new LevelTwo();
		
		//makes buttons and their functions
		Button nextButton = createButton("Move onto Level 2");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				myScene = myGame.initialize(s, width, height);
				setUpLoop(myGame);
				s.setScene(myScene);
			}
		});
		
		Button quitButton = createButton("Give up on mission");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				System.exit(1);
			}
		});
		
		setButton(nextButton, width/2 + NEXT_X_OFFSET, height/2);
		setButton(quitButton, width/2, height/2);
		createLabel("You beat level 1!", LABEL_TEXT_SIZE, Color.BLUE);
		
		return myScene;
	}
}

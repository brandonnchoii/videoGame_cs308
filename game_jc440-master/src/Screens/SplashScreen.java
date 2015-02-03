package Screens;

import java.util.ArrayList;

import Levels.LevelOne;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SplashScreen extends Screen
{
	private static final int LABEL_FONT_SIZE = 35;
	private static final int ST_X_OFFSET = -160;
	private static final int ST_Y_OFFSET = -150;
	private static final int Q_X_OFFSET = 35;
	private static final int Q_Y_OFFSET = -150;
	private static final int TEXT_X = 5;
	private static final int TEXT_Y = 100;
	
	public Scene initialize(Stage s, int width, int height)
	{
		//initializes variables that are inherited from abstract class
		myRoot = new Group();	
		myScene = new Scene(myRoot, width, height, Color.DARKGREY);
		myGame = new LevelOne();
		
		createLabel("Space Chase: Rescue Coach K!", LABEL_FONT_SIZE, Color.BLUE);
		
		//make the two buttons and their functions
		Button startB = createButton("Let's go save Coach K!");
		startB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				myScene = myGame.initialize(s, width, height);
				setUpLoop(myGame);
				s.setScene(myScene);
			}
		});
		
		Button quitB = createButton("Nah, I like UNC.");
		quitB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				System.exit(1);
			}
		});

		createText();
		
		setButton(startB, width/2 + ST_X_OFFSET,height + ST_Y_OFFSET );
		setButton(quitB, width/2 + Q_X_OFFSET, height + Q_Y_OFFSET );
		
		return myScene;
	}

	private void createText() {
		Text t = new Text();
		t.setText("You are Marshall Plumlee. "
				+ "Tenting season has just begun and you cannot wait \nuntil the upcoming UNC game."
				+ "However, during an alien invasion yesterday, evil \naliens kidnapped Mike Krzyzewski "
				+ "along with the rest of the team. \nYou soon find out that they were working with UNC's "
				+ "team in order to sabotage the \ngame. You cannot let the tenters and the team down. "
				+ "\nYou jump on the spaceship the athletics department has so kindly provided \nand you "
				+ "chase after them. \n\nYou are Duke's last hope!"
				+ "\n\n\n\n Instructions: Use arrow keys to move and space bar to shoot."
				+ "\n\n Level 1: Survive for 90 seconds!"
				+ "\n Level 2: Defeat the aliens and the UFO!"
				+ "\n The aliens are strong and the UFO even stronger.");
		addChildren(t);
		t.setLayoutX(TEXT_X);
		t.setLayoutY(TEXT_Y);
	}
}

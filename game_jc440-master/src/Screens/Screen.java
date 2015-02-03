package Screens;

import Levels.SpaceChase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Screen 
{
	private static final int NUM_FRAMES_PER_SECOND = 60;
	
	protected Scene myScene;
	protected Group myRoot;
	protected SpaceChase myGame;
	
	public abstract Scene initialize(Stage s, int w,int h);
	
	public void addChildren(Node n) //adds Node to Group
	{
		myRoot.getChildren().add(n);
	}
	
	public Button createButton(String text) //creates a Button and adds it to the Group
	{
		Button b = new Button(text);
		addChildren(b);
		return b;
	}
	
	public void createLabel(String s, int size, Color c) //creates label of the screen
	{
		Label l = new Label(s);
		l.setFont(new Font(size));
		l.setTextFill(c);
		addChildren(l);
	}
	
	public void setButton(Button b, double x, double y) //sets up Button location
	{
		b.setLayoutX(x);
		b.setLayoutY(y);
	}
	
	public void setUpLoop(SpaceChase s) //sets up the game loop
	{
		KeyFrame frame = s.start(NUM_FRAMES_PER_SECOND);
        Timeline animation = new Timeline();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.getKeyFrames().add(frame);
        s.assignAnimation(animation);
        animation.play();
	}
}

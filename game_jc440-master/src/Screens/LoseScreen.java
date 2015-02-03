package Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoseScreen extends Screen
{
	private static final int FONT_SIZE = 68;
	private static final int X_OFFSET = -70;
	
	public Scene initialize(Stage s, int w, int h) 
	{
		//initializes variables that are inherited from abstract class
		myRoot = new Group();
		myScene = new Scene(myRoot, w, h, Color.BLACK);
		
		createLabel("You LOSE. Boo.", FONT_SIZE, Color.RED);
		
		//make end button 
		Button end = createButton("Blech. Nice try.");
		end.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				System.exit(1);
			}
		});
		
		setButton(end, w/2 + X_OFFSET, h/2);
		
		return myScene;
	}

}

package Sprites;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Player extends Spaceship
{	
	private static final Image im = new Image ("images/rocket.jpg");
	private static final int PLAYER_HEALTH = 10;
	private static final int PLAYER_WIDTH = 45;
	private static final int PLAYER_HEIGHT = 45;
	private static final int X_OFFSET = -25;
	private static final int Y_OFFSET = -50;
	private static final int HACK_HEALTH = 9999;
	private static final int SHOOT_DIR = -1;
	private static final int PLAYER_SPEED = 15;
	
	public Player() //constructs player with a certain image and dimensions
	{
		super(PLAYER_HEALTH,im);
		setFitWidth(PLAYER_WIDTH);
		setFitHeight(PLAYER_HEIGHT);
	}
	
	public void setStartingPoint(Scene scene) //sets starting x and y coordinates
	{
		setLayoutX(scene.getWidth()/2 + X_OFFSET); 
		updateX(getLayoutX());
		setLayoutY(scene.getHeight() + Y_OFFSET);
		updateY(getLayoutY());
	}

	public void update(){}
	
	public void handleKeyInput (KeyEvent e)  //responds to user input's controls and cheat codes
	{
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.RIGHT) {
            setTranslateX(getTranslateX() + PLAYER_SPEED );
            updateX(PLAYER_SPEED );
        }
        else if (keyCode == KeyCode.LEFT) {
        	setTranslateX(getTranslateX() - PLAYER_SPEED );
        	updateX(-1*PLAYER_SPEED );
        }
        else if (keyCode == KeyCode.UP) {
        	setTranslateY(getTranslateY() - PLAYER_SPEED );
        	updateY(-1*PLAYER_SPEED );
        }
        else if (keyCode == KeyCode.DOWN) {
        	setTranslateY(getTranslateY() + PLAYER_SPEED );
        	updateY(PLAYER_SPEED );
        }
        else if (keyCode == KeyCode.SPACE) {
        	shoot(SHOOT_DIR);
        }
        
        //cheat codes here
        else if (keyCode == KeyCode.SHIFT){
        	setHP(HACK_HEALTH);
        }
        else if (keyCode == KeyCode.TAB){
        	setCheated();
        }
    }
}

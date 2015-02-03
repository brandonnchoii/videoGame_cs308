package Levels;

import java.util.ArrayList;
import java.util.Random;

import Game.GameTimer;
import Screens.BetweenLevelsScreen;
import Screens.LoseScreen;
import Sprites.Asteroid;
import Sprites.Bullet;
import Sprites.Debris;
import Sprites.Projectile;
import Sprites.Sprite;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelOne extends SpaceChase
{
	private static final int HEALTH_X_CRD = 400;
	private static final int HEALTH_Y_CRD = 580;
	private static final int TIME_X_CRD = 10;
	private static final int TIME_Y_CRD = 20;
	private static final int TIME_TEXT_FONT_SIZE = 20;
	private static final int ENEMY_Y_CRD = -5;
	private static final int ENEMY_PROBABILITY = 11;
	private static final int TIME = 90;
	
    private GameTimer timer;
    private Text timeDisplay;
	
	public Scene initialize(Stage s, int width, int height)
	{
		//initializes needed variables that are inherited from abstract class
		myRoot = new Group();
		myScene = new Scene(myRoot, width, height, Color.BLACK);
		list = new ArrayList<>();
		myBullets = new ArrayList<>();
		myStage = s;
		myHealthDisplay = new Text();
		
		//calls method to set up components of the game
		setUpHealthDisplay(myHealthDisplay, HEALTH_X_CRD, HEALTH_Y_CRD);
		setUpTimeDisplay();
		setUpResponses();
		setUpPlayer();
		
		return myScene;
	}
	
	public KeyFrame start (int frameRate) //initializes the GameTimer and updates the screen on every time interval
	{
		timer = new GameTimer(TIME, System.currentTimeMillis());
        return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites());
    }
	
	protected void updateSprites() //method that is called to update game screen and objects
	{
		//gets components to be updated (ie bullets, enemies)
		getBullets(myPlayer, myBullets);
		createEnemies();
		updateAndDisplayTime();
		updateHealthDisplay(myHealthDisplay, myPlayer);
		
		//updates all components and checks for collisions
		for (Bullet b: myBullets)
			b.update();
		
		for (Sprite s: list)
		{	
			if (!myBullets.isEmpty())
			{
				for (Bullet b: myBullets)
				{
					checkCollide(b, s);
				}
			}
			s.update(); 
			checkCollide(myPlayer, s);
			
		//checks conditions to determine whether or not to move on to the next level or lose screen
			if (checkWin())	
				nextScreen(new BetweenLevelsScreen());
			if (checkLose())
				nextScreen(new LoseScreen());
			if (myPlayer.didPlayerCheat())
				nextScreen(new BetweenLevelsScreen());	
		}	
	}
	
	protected void createEnemies() //creates either Debris or Asteroid based on probability and a random generator
	{
		Random generator = new Random();
		int num = generator.nextInt(1000);
		if (num <= ENEMY_PROBABILITY)
		{
			setUpEnemy(new Debris());
		}
		else if (num > ENEMY_PROBABILITY && num <= (2*ENEMY_PROBABILITY))
		{
			setUpEnemy(new Asteroid());
		}
	}
	
	private void setUpTimeDisplay() //sets up the time display 
	{
		timeDisplay = new Text();
		addChildren(timeDisplay);
		timeDisplay.setFont(new Font(TIME_TEXT_FONT_SIZE));
		timeDisplay.setFill(Color.RED);
		
		timeDisplay.setLayoutX(TIME_X_CRD);
		timeDisplay.setLayoutY(TIME_Y_CRD);
	}
	
	private void updateAndDisplayTime() //updates the time display
	{
		timer.update(System.currentTimeMillis());
		
		if (timer.getTime() != 0)
			timeDisplay.setText(timer.getTime() + "");
		else
		{
			timeDisplay.setText(timer.getTime() + ""); //if time is 0 and player is alive, then level 1 is won
			won = true;
		}
	}
	
	protected boolean checkWin() //checks winning condition for level 1
	{
		if (won)
			return true;
		else return false;
	}
	
	private void setUpEnemy(Projectile p) //sets up enemies before they fall
	{
		addChildren(p);
		p.setLayoutX(generateX());
		p.setLayoutY(ENEMY_Y_CRD); //all enemies start falling from off the screen locations
		list.add(p);
	}
	
	private int generateX() // used to generate a random x coordinate from where the enemy will fall
	{
		Random generator = new Random();
		int x = generator.nextInt((int) myScene.getWidth());
		return x;
	}
}

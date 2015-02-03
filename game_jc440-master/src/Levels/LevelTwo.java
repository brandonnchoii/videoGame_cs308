package Levels;

import java.util.ArrayList;

import Screens.LoseScreen;
import Screens.WinScreen;
import Sprites.Alien;
import Sprites.Bullet;
import Sprites.UFO;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelTwo extends SpaceChase
{
	private static final int HEALTH_X_CRD = 400;
	private static final int HEALTH_Y_CRD = 580;
	private static final int ENEMY_HEALTH_X_CRD = 400;
	private static final int ENEMY_HEALTH_Y_CRD = 25;
	private static final int ALIEN1_X_CRD = 1;
	private static final int ALIEN1_Y_CRD = 120;
	private static final int ALIEN2_X_CRD = 100;
	private static final int ALIEN2_Y_CRD = 170;
	private static final int ALIEN3_X_CRD = 200;
	private static final int ALIEN3_Y_CRD = 220;
	
	private ArrayList<Alien> aliens;
	private UFO boss;
	
	public Scene initialize(Stage s, int width, int height) 
	{
		//initializes needed variables that are inherited from abstract class
		myRoot = new Group();
		myScene = new Scene(myRoot, width, height, Color.BLACK);
		myBullets = new ArrayList<>();
		enemyBullets = new ArrayList<>();
		myHealthDisplay = new Text();
		enemyHealth = new Text();
		myStage = s;
		
		//calls method to set up components of the game
		setUpHealthDisplay(myHealthDisplay, HEALTH_X_CRD, HEALTH_Y_CRD);
		setUpHealthDisplay(enemyHealth, ENEMY_HEALTH_X_CRD, ENEMY_HEALTH_Y_CRD);
		setUpResponses();
		setUpPlayer();
		createEnemies();
		
		return myScene;
	}

	public KeyFrame start(int frameRate) //updates the screen on every time interval
	{
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites());
	}

	protected void updateSprites() //method that is called to update game screen and objects
	{
		//gets components to be updated (ie bullets, enemies)
		getBullets(myPlayer, myBullets);
		getBullets(boss, enemyBullets);
		updateHealthDisplay(myHealthDisplay, myPlayer);
		updateHealthDisplay(enemyHealth, boss);
		boss.update();
		
		//updates all components and checks for collisions
		for (Bullet b: myBullets)
			b.update();
		
		for (Bullet b: enemyBullets)
		{
			b.update();
			checkCollide(b, myPlayer);
		}
		
		for (Alien a: aliens)
		{
			if (!myBullets.isEmpty())
			{
				for (Bullet b: myBullets)
				{
					checkCollide(b, a);
					checkCollide(b, boss);
				}
			}
			checkCollide(myPlayer, a);
			a.update();
		}
		
		//checks conditions to determine whether or not game was won or lost and if the player cheated
		if(checkWin())
			nextScreen(new WinScreen());
		if (checkLose())
			nextScreen(new LoseScreen());
		if (myPlayer.didPlayerCheat()) 
			nextScreen(new WinScreen());
	}
	
	protected void createEnemies() //creates the boss UFO and the 3 aliens for level 2
	{
		aliens = new ArrayList<>();
		boss = new UFO();
		
		for (int i = 0; i < 3; i++)
		{
			Alien a = new Alien();
			aliens.add(a);
		}
		
		setUpAliens();
		setUpUFO();
	}
	
	protected boolean checkWin() //check winning conditions. all enemies must be dead for it to be true
	{
		if (boss.isDead() && aliens.get(0).isDead() && aliens.get(1).isDead() && aliens.get(2).isDead())
			return true;
		else return false;
	}
	
	private void setUpAliens() //sets up aliens' locations
	{
		makeAlien(0, ALIEN1_X_CRD, ALIEN1_Y_CRD);
		makeAlien(1, ALIEN2_X_CRD, ALIEN2_Y_CRD);
		makeAlien(2, ALIEN3_X_CRD, ALIEN3_Y_CRD);
	}
	
	private void makeAlien(int index, double x, double y) //makes the alien
	{
		aliens.get(index).setLayoutX(x);
		aliens.get(index).setStartX(x);
		aliens.get(index).setLayoutY(y);
		aliens.get(index).setStartY(y);
		addChildren(aliens.get(index));
	}
	
	private void setUpUFO() //sets up the boss
	{
		boss.setStartingPoint(myScene);
		addChildren(boss);
	}
}
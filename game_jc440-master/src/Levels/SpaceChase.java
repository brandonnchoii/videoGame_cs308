package Levels;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import Screens.Screen;
import Sprites.Alien;
import Sprites.Bullet;
import Sprites.Player;
import Sprites.Projectile;
import Sprites.Spaceship;
import Sprites.Sprite;
import sun.java2d.pipe.ShapeSpanIterator;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class SpaceChase 
{
	private static final int OFFSCREEN_LOC = 999;
	private static final int INITIAL_WIDTH = 500;
	private static final int INITIAL_HEIGHT = 600;
	private static final int HEALTH_FONT_SIZE = 20;
	
	protected Player myPlayer;
	protected Scene myScene;
    protected Group myRoot;
    protected Stage myStage;
    protected ArrayList<Projectile> list;
    protected ArrayList<Bullet> myBullets;
    protected ArrayList<Bullet> enemyBullets;
    protected boolean won = false;
    protected Text myHealthDisplay;
    protected Text enemyHealth; 
    private Timeline animation;
    
    //abstract methods that change depending on the level. implemented in the appropriate level classes
	public abstract Scene initialize(Stage s, int width, int height);
	public abstract KeyFrame start (int frameRate); 
	protected abstract void updateSprites(); 
	protected abstract void createEnemies();
	protected abstract boolean checkWin();
	
	public boolean checkLose() //checks if player is dead. if so, then game is over
	{
		if(myPlayer.isDead())
			return true;
		else return false;
	}
	
	public void assignAnimation(Timeline t) //receives animation so that play and stop are possible
	{
		animation = t;
	}
	
	public void setUpResponses() //sets up the inputs from player
	{
		myScene.setOnKeyPressed(e -> myPlayer.handleKeyInput(e));
	}
	
	public void setUpPlayer() //creates player's spaceship
	{
		myPlayer = new Player();
		addChildren(myPlayer);
		myPlayer.setStartingPoint(myScene); 
	}

	public void addChildren(Node n) //general method for adding to the main group
	{
		myRoot.getChildren().add(n);
	}
	
	public void removeChildren(Node n) //general method for removing from the main group
	{
		myRoot.getChildren().remove(n);
		
		if (n instanceof Sprite) //when Sprite is dead or needs to be removed, it is set to an far away location
		{
			((Sprite) n).setLayoutX(OFFSCREEN_LOC);
		}
	}
	
	public void getBullets(Spaceship ship, ArrayList<Bullet> bullets) //gets full list of bullets shot by spaceships
	{
		ArrayList<Bullet> bl = ship.getBulletList();
		if (bl.size() != 0 && !bullets.contains(bl.get(bl.size()-1)))
		{
			Bullet latest = bl.get(bl.size()-1);
			bullets.add(latest);
			setUpBullet(latest);
		}
	}
	
	public void setUpBullet(Bullet b) //sets up the location of the bullets on the screen
	{
		b.setLayoutX(b.getXc());	
		b.setLayoutY(b.getYc());
		addChildren(b);
	}
	
	public void nextScreen(Screen screen) //method for moving from screen to screen
	{
		animation.stop();
		Screen s = screen;
		myScene = s.initialize(myStage, INITIAL_WIDTH, INITIAL_HEIGHT);
        myStage.setScene(myScene);
        myStage.show();
	}
	
	public void setUpHealthDisplay(Text t, int x, int y) //creates health display for player or boss
	{
		addChildren(t);
		t.setFont(new Font(HEALTH_FONT_SIZE));
		t.setFill(Color.GREEN);
		t.setLayoutX(x);
		t.setLayoutY(y);
	}
	
	public void updateHealthDisplay(Text t, Sprite s) //updates health display with most recent health
	{
		t.setText("HP: " + s.getHP() + "");
	}
	
	public void updateHealth(Sprite s) //changes health if hit, if health is 0, then object is killed and removed
	{
		s.loseHealth();
		
		if (s.isDead())
			removeChildren(s);
	}
	
	public void checkCollide (Sprite one, Sprite two) //checks collision between any two Sprites
    {
		if (one.getBoundsInParent().intersects(two.getBoundsInParent()))
		{
			updateHealth(one);
			updateHealth(two);
		}
    }
}

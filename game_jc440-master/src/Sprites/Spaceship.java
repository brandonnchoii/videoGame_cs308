package Sprites;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class Spaceship extends Sprite 
{
	private static final int BULLET_OFFSET = 17;
	
	protected ArrayList<Bullet> bulletList;
	private boolean cheated;
	
	public Spaceship (int health, Image i) //constructs a general spaceship with a certain image and health
	{
		super (i,health);
		cheated = false;
		bulletList = new ArrayList<Bullet>();
	}
	
	public abstract void setStartingPoint(Scene scene); //abstract method for setting starting point
	
	public void setCheated() //makes cheated true if cheat key is pressed
	{
		cheated = true;
	}
	
	public boolean didPlayerCheat() //returns cheated boolean
	{
		return cheated;
	}
	
	public void shoot(int i) //shoots a bullet
	{
		Bullet shot = new Bullet(i);
		shot.setCoordinates(getXCrd() + BULLET_OFFSET, getYCrd());
		bulletList.add(shot);
	}
	
	public ArrayList<Bullet> getBulletList() //gets list of bullets fired
	{
		return bulletList;
	}
}

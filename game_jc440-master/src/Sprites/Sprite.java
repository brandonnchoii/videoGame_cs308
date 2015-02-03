package Sprites;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Sprite extends ImageView
{	
	private double xC;
	private double yC;
	protected int hp;
	
	public Sprite(Image im, int health) //constructs a Sprite with said image and health amount
	{
		hp = health;
		setImage(im);
		xC = 0.0;
		yC = 0.0;
	}
	
	private boolean dead = false;
	
	public abstract void update();
	
	public void kill() //kills Sprite
	{
		dead = true;
	}
	
	public boolean isDead() //returns dead boolean
	{
		return dead;
	}
	
	public void setHP(int i) //sets health manually
	{
		hp = i;
	}
	
	public int getHP() //returns current hp (health)
	{
		return hp;
	}
	
	public void loseHealth() //decrements health once hit, if health is 0, then kills Sprite
	{
		hp = hp - 1;
		
		if (hp == 0)
			kill();
	}
	
	public void updateX(double d) //updates x coordinate
	{
		xC = xC + d;
	}
	
	public double getXCrd() //returns x coordinate
	{
		return xC;
	}
	
	public void updateY(double d) //updates y coordinate
	{
		yC = yC + d;
	}
	
	public double getYCrd() //returns y coordinate
	{
		return yC;
	}
}

package Sprites;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Sprite
{
	private static final Image im = new Image("images/missle.jpg");
	private static final int BULLET_HEALTH = 1;
	private static final int BULLET_WIDTH = 5;
	private static final int BULLET_HEIGHT = 11;
	private static final int BULLET_SPEED = 8;
	
	private double xC;
	private double yC;
	private int motion;
	
	public Bullet(int i) //constructs bullet with a certain image and dimensions
	{
		super(im, BULLET_HEALTH);
		setFitWidth(BULLET_WIDTH);
		setFitHeight(BULLET_HEIGHT);
		motion = i * BULLET_SPEED; //can be negative depending on which direction it was fired
	}
	
	public void setCoordinates(double x, double y) //sets starting x and y coordinates
	{
		xC = x;
		yC = y;
	}
	
	public double getXc() //returns current x coordinate
	{
		return xC;
	}
	
	public double getYc() //returns current y coordinate
	{
		return yC;
	}

	public void update() //moves bullet across screen in direction fired
	{
		setTranslateY(getTranslateY() + motion);
	}	
}

package Sprites;

import java.util.Random;

import javafx.scene.image.Image;

public class Alien extends Sprite
{
	private static final Image im = new Image("images/alien.jpg");
	
	private static final int ALIEN_WIDTH = 55;
	private static final int ALIEN_HEIGHT = 55;
	private static final int ALIEN_HEALTH = 10;
	private static final int MIN_X = 10;
	private static final int MAX_X = 440;
	private static final int MAX_Y = 460;
	private static final int CHARGE_SPEED = 15;
	private static final int PROBABILITY = 20; //out of 1000
	
	private int MOVEMENT_DIRECTION = 1;
	private Random generator;
	
	public Alien() //constructs alien with alien image, 10 health points, and appropriate size
	{
		super(im, ALIEN_HEALTH);
		setFitWidth(ALIEN_WIDTH);
		setFitHeight(ALIEN_HEIGHT);
		generator = new Random();
	}
	
	public void setStartX(double x) //updates location with starting location
	{
		updateX(x);
	}
	
	public void setStartY(double y) //updates location with starting location
	{
		updateY(y);
	}
	
	public void update() //update method that creates automated movement
	{	
		if (getXCrd() == MIN_X)
			MOVEMENT_DIRECTION = 1;
		else if (getXCrd() == MAX_X)
			MOVEMENT_DIRECTION = -1;
		
		int number = generator.nextInt(1000); //probability for a charge movement
		if (number <= PROBABILITY)
			charge();
		else 
		{
			setTranslateX(getTranslateX() + MOVEMENT_DIRECTION);
			updateX(MOVEMENT_DIRECTION);
		}
	}
	
	public void charge() //moves forward if y coordinate is not beyond 460
	{
		if (getYCrd() <= MAX_Y)
		{
			setTranslateY(getTranslateY() + CHARGE_SPEED);
			updateY(CHARGE_SPEED);
		}
	}
}

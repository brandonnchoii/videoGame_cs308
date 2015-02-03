package Sprites;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class UFO extends Spaceship
{
	private static final Image im = new Image ("images/UFO.jpg");
	private static final int UFO_HEALTH = 30;
	private static final int UFO_WIDTH = 80;
	private static final int UFO_HEIGHT = 80;
	private static final int Y_OFFSET = -300;
	private static final int SHOOT_DIR = 1;
	private static final int MIN_X = 10;
	private static final int MAX_X = 440;
	private static final int PROBABILITY = 8; //out of 1000
	
	protected int movement = -1;
	private Random generator;
	
	public UFO() //constructs UFO with a certain image and dimensions
	{
		super(UFO_HEALTH, im);
		setFitWidth(UFO_WIDTH);
		setFitHeight(UFO_HEIGHT);
		generator = new Random();
	}
	
	public void setStartingPoint(Scene scene) //sets starting x and y coordinates
	{
		setLayoutX(scene.getWidth()/2); 
		updateX(getLayoutX());
		setLayoutY(scene.getHeight()/2 + Y_OFFSET);
		updateY(getLayoutY());
	}

	public void update() //automates movement and possible attacks
	{
		if (getXCrd() == MIN_X)
			movement = 1;
		else if (getXCrd() == MAX_X)
			movement = -1;
		
		setTranslateX(getTranslateX() + movement);
		updateX(movement);
		
		attack();
	}
	
	private void attack() //shooting based on probability from random generator
	{
		int number = generator.nextInt(1000);
		if (number <= PROBABILITY && !isDead())
		{
			shoot(SHOOT_DIR);
		}
	}
}

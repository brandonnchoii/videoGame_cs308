package Sprites;

import javafx.scene.image.Image;

public class Asteroid extends Projectile
{
	private static final Image im = new Image ("images/asteroids.jpg");
	
	private static final int AST_WIDTH = 35;
	private static final int AST_HEIGHT = 35;
	
	public Asteroid() //constructs asteroid with said image and dimensions
	{
		super(im);
		setFitWidth(AST_WIDTH);
		setFitHeight(AST_HEIGHT);
	}
}

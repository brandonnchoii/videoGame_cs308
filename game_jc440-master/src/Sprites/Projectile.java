package Sprites;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Projectile extends Sprite
{	
	private static final int PROJ_HEALTH = 1;
	private static final int PROJ_SPEED = 2;

	public Projectile(Image im) //constructs general projectile with a certain image
	{
		super(im, PROJ_HEALTH);
	}
	
	public void update() //moves projectile across screen 
	{
		this.setTranslateY(this.getTranslateY() + PROJ_SPEED);
	}
}

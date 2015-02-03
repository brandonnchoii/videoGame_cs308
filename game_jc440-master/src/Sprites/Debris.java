package Sprites;

import javafx.scene.image.Image;

public class Debris extends Projectile
{
	private static final Image im = new Image ("images/debris.jpg");
	
	private static final int DEB_WIDTH = 35;
	private static final int DEB_HEIGHT = 35;
	
	public Debris() //constructs debris with a certain image and dimensions
	{
		super(im);
		setFitWidth(DEB_WIDTH);
		setFitHeight(DEB_HEIGHT);
	}
}


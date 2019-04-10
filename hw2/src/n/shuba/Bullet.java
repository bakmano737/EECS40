package n.shuba;

import android.graphics.Rect;

public class Bullet {
	Rect shot;
	
	public Bullet(SpaceInvaders si)
	{
		//Set the initial position of bullet right above the invader
		shot = new Rect();
		shot.set(si.getPlayer().getCenterX() - 15 + si.getPlayer().getXOffset(), 
				 si.getPlayer().getCenterY() - 55, 
				 si.getPlayer().getCenterX() + 15 + si.getPlayer().getXOffset(), 
				 si.getPlayer().getCenterY() - 30);
	}
	
}

/* Originally, we were planning to have more methods here, but it turned 
 * out to be more convenient to do "bullet processing" in the SpaceInvaders class.
 * We realize that this is not so "object oriented", but we tried. Too much C, I guess. */

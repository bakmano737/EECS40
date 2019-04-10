package jn.pacman;

import java.util.Random;

import android.graphics.Bitmap;

/**
 * Clyde is the dumb ghost and is orange. His behavior is supposed to be
 * random, although wikipedia claims that he actually follows pacman until
 * he gets to close, when this happens he heads for the lower left corner.
 */
public class Clyde extends Ghost 
{
	Random rand;

	private int direction;
	private int goal;
	
	private long prevTime;

	public Clyde(Bitmap bmp, Player player, int x, int y, int speed)
	{
		super(player, x, y, speed);
		this.setBmp(bmp);
		rand = new Random();
		direction = LEFT;
		goal = DOWN;
	}

	/* (non-Javadoc)
	 * @see jn.pacman.Ghost#translate()
	 */
	@Override
	public void translate() 
	{
		super.translate();
		if(outOfGame) return;
		
		long currTime = System.currentTimeMillis();
		
		if(currTime > prevTime + 1000) 
		{
			goal = rand.nextInt(4) + 1;
			prevTime = currTime;
		}
		
		switch(direction)
		{
			case RIGHT:
				if (tile.getRight()!=null)
				{
					rect.offset(speed, 0);
					if(rect.right + speed >= tile.getRight().getRectangle().right) tile = tile.getRight();
				}
				else goal = rand.nextInt(4) + 1;
				break;
			case LEFT:
				if (tile.getLeft()!=null)
				{
					rect.offset(-speed, 0);
					if(rect.left - speed <= tile.getLeft().getRectangle().left) tile = tile.getLeft(); 
				}
				else goal = rand.nextInt(4) + 1;
				break;	
					
			case UP:
				if (tile.getUpper()!=null)
				{
					rect.offset(0, -speed);
					if(rect.top - speed <= tile.getUpper().getRectangle().top ) tile = tile.getUpper(); 
				}
				else goal = rand.nextInt(4) + 1;
				break;		
			case DOWN:
				if (tile.getLower()!=null)
				{
					rect.offset(0, speed);
					if( rect.bottom + speed >= tile.getLower().getRectangle().bottom ) tile = tile.getLower();  
				}
				else goal = rand.nextInt(4) + 1;
				break;
				
			default:
				if (tile.getLeft()!=null)
				{
					rect.offset(-speed, 0);
					if(rect.left - speed <= tile.getLeft().getRectangle().left) tile = tile.getLeft(); 
				}
				else goal = rand.nextInt(4) + 1;
				break;	
		}
		
		switch (goal)
		{
			case RIGHT:
				if (tile.getRight()!=null && rect.top >= tile.getRectangle().top
				&& rect.bottom <= tile.getRectangle().bottom) 
				direction = goal;
				break;	
			case LEFT:
				if (tile.getLeft()!=null && rect.top >= tile.getRectangle().top
					&& rect.bottom <= tile.getRectangle().bottom)
					direction = goal;
				break;	
			case UP:
				if (tile.getUpper()!=null && rect.right <= tile.getRectangle().right
					&& rect.left >= tile.getRectangle().left) 
					direction = goal;
				break;	
			case DOWN:
				if (tile.getLower()!=null && rect.right <= tile.getRectangle().right
					&& rect.left >= tile.getRectangle().left) 
					direction = goal;
				break;	
			
			default:
				if (tile.getLower()!=null && rect.right <= tile.getRectangle().right
				&& rect.left >= tile.getRectangle().left) 
				direction = goal;
				break;
		}
	}
}

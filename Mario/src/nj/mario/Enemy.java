package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy 
{
	// Position on the grid
	private int x;
	private int y;
	
	// Indicator for when to start moving
	private boolean start;
	
	// Indicator for drawing the squished icon
	private boolean dead;
	private int deadCounter;
	
	// Indicator of motion direction for drawing
	private boolean mRight;
	
	// Drawing rectangle
	private Rect rect;
	
	// Bitmap
	private Bitmap [] bmps;
	
	// Player to track
	private Player player;
	
	private long prevTime;
	
	/**
	 * Constructor - default values
	 */
	public Enemy()
	{
		x = 0;
		y = 0;
		start = false;
		rect = null;
		bmps = null;
	}
	
	/**
	 * Constructor - specified parameters
	 * @param x - x grid coordinate
	 * @param y - y grid coordinate
	 * @param bmp - bitmap
	 */
	public Enemy(int x, int y, Bitmap[] bmps, Player player)
	{
		this.x = x;
		this.y = y;
		this.rect = new Rect(Map.getGridBoard()[x][y].getRect());
		this.bmps = bmps;
		this.player = player;
		this.start = true;
		this.dead = false;
		this.deadCounter = 0;
	}
	
	/**
	 * draw - draw the enemy in his position
	 * 		  on the given canvas
	 * @param c - Canvas on which to draw
	 */
	public void draw(Canvas c)
	{
		if(this.start)
		{
			if(this.mRight)
			{
				if(this.dead)
				{
					c.drawBitmap(bmps[3], null, rect, null);
				}
				else
				{
					c.drawBitmap(bmps[1], null, rect, null);
				}
			}
			else
			{
				if(this.dead)
				{
					c.drawBitmap(bmps[2], null, rect, null);
				}
				else
				{
					c.drawBitmap(bmps[0], null, rect, null);
				}
			}
		}
	}
	
	/**
	 * translate - move the position by the given number of spaces
	 * @param dx
	 * @param dy
	 */
	public void translate( int dx, int dy )
	{
		// Update the position
		this.x += dx;
		this.y += dy;
		
		// Check and correct for boundary violations
		if(x<0) this.x = 0;
		else if(x>=Map.getWidth()) this.x = Map.getWidth()-1;
		if(y<0) this.y = 0;
		else if(y>=Map.getHeight()) this.y = Map.getHeight()-1;

		// Set the rectangle
		this.rect.set(Map.getGridBoard()[x][y].getRect());
	}
	
	/**
	 * isMoveLegal - is the given motion possible
	 * @param - dx - the x distance the player will be moved
	 * @param - dy - the y distance the player will be moved
	 * @return - boolean for validity of the motion
	 */
	private boolean isMoveLegal( int dx, int dy )
	{
		if(x+dx < 0) return false;
		if(y+dy < 0) return false;
		if(x+dx >= Map.getGridBoard().length) return false;
		if(y+dy >= Map.getGridBoard()[x+dx].length)
		{
			this.setDead(true);
			this.setStart(false);
			return false;
		}
		if(Map.getGridBoard()[x+dx][y+dy].isBlocked())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * update - update the enemy position 
	 */
	public void update()
	{
		long currTime = System.currentTimeMillis();
		if(this.start && currTime > prevTime + 500)
		{
			prevTime = currTime;
			
			// If the enemy is dead, draw the squished image
			// and do not update the position
			if(this.isDead())
			{
				
				// Make sure the dead image gets drawn
				// enough times for the user to see it
				deadCounter++;
				if( 5 <= deadCounter )
				{
					rect.setEmpty();
					setX(0);
					setY(0);
					this.setDead(false);
					this.setStart(false);
					this.deadCounter = 0;
				}
			}
			
			else
			{
				// Fall if possible
				if(isMoveLegal(0, 1))
				{
					translate(0, 1);
					return;
				}
				
				//Enemies move left until a blocked path:
				if(!mRight && isMoveLegal(-1, 0))
				{
					translate(-1, 0);
					mRight = false;
				}
				//If he encounters a wall, change direction:
				else if(isMoveLegal(1, 0))
				{
					translate(1, 0);
					mRight = true;
				}
				//Wall encountered here too, reverse again:
				else mRight = false;
			}
		}
		
		if(this.rect.top == this.getPlayer().getRect().bottom &&
			player.getRect().left < this.rect.right && 
			player.getRect().right > this.rect.left)
		{
			// Player has landed on the enemy
			// Enemy is dead
			if(!isDead())
			{
				this.setDead(true);
				Mario.incScore();	
				Mario.incScore();
			}
		}
		//else if(this.rect.left == this.getPlayer().getRect().right ||
			//	this.rect.right == this.getPlayer().getRect().left )
		else if(this.rect.intersect(player.getRect()))
		{
			if(!this.isDead())
			{
				MarioView.restartGame();
			}
		}
	}
	
	/** @return the x */
	public int getX() { return x; }

	/** @param x the x to set */
	public void setX(int x) { this.x = x; }

	/** @return the y */
	public int getY() { return y; }

	/** @param y the y to set */
	public void setY(int y) { this.y = y; }

	/** @return the start */
	public boolean isStart() { return start; }

	/** @param start the start to set */
	public void setStart(boolean start) { this.start = start; }

	/** @return the rect */
	public Rect getRect() { return rect; }

	/** @param rect the rect to set */
	public void setRect(Rect rect) { this.rect.set(rect); }

	/** @return the bmp */
	public Bitmap[] getBmps() { return bmps; }

	/** @param bmp the bmp to set */
	public void setBmp(Bitmap[] bmps) { this.bmps = bmps; }

	/** @return the player */
	public Player getPlayer() { return player; }

	/** @param player the player to set */
	public void setPlayer(Player player) { this.player = player; }

	/** @return the mRight */
	public boolean ismRight() { return mRight; }

	/** @param mRight the mRight to set */
	public void setmRight(boolean mRight) { this.mRight = mRight; }

	/** @return the dead */
	public boolean isDead() { return dead; }

	/** @param dead the dead to set */
	public void setDead(boolean dead) { this.dead = dead; }

}

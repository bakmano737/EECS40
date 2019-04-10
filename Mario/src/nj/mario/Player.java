package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player 
{
	//The player's position on the map,
	//in terms of map squares:
	private int x;
	private int y;
	
	//Bitmaps used to animate the player
	private Bitmap bmp1;
	
	// Variables for tracking jump position
	private static final int MAX_JUMPS = 4;
	private int jumpCounter;
	
	//Player rectangle to correspond with grid
	private Rect R;
	
	/** 
	 *  Constructor:
	 * 		@param bmp array of bitmaps to initialize player bmps 
	 */
	public Player(int w, int h, Bitmap[] bmps)
	{
		jumpCounter = 0;
		x=0;
		y = Map.getGridBoard()[0][Map.getHeight()-2].getY();
		bmp1 = bmps[0];		
		R = new Rect();
		R.set(Map.getGridBoard()[x][y].getRect());
	}
	
	/**
	 * Draw the player bitmap to the given canvas
	 * @param c - canvas on which to draw the bitmap
	 */
	public void draw(Canvas c) 
	{
		c.drawBitmap(bmp1, null, R, null);
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
		if(y+dy >= Map.getGridBoard()[x+dx].length) return false;
		if(Map.getGridBoard()[x+dx][y+dy].isBlocked())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * translate - Move the player the given number of grid spaces
	 * @param dx - number of grid spaces to move in the x direction
	 * @param dy - number of grid spaces to move in the y direction
	 */
	public synchronized boolean translate(int dx, int dy)
	{		
		x += dx;
		y += dy;	
		
		//Check left border
		if( x < 0 ) x = 0;
		//Check right border
		else if( x >= Map.getWidth()-1 ) 
		{
			x = Map.getWidth()-1;
			//Level won when edge is reached
			//and player collected at least 5 coins:
			if(Mario.getScore() >= 5)
			{
				Mario.passLevel();
			}
		}
		
		//Check top border
		if( y < 0 ) y = 0;
		//Check bottom border
		else if( y >= Map.getHeight()-1 ) 
		{
			y = Map.getHeight()-1;
			//Player fell into pit, restart:
			MarioView.restartGame();
		}

		if(Map.getGridBoard()[x][y].getType() == MapTile.COINS) 
		{
			Map.getGridBoard()[x][y].setType(MapTile.BACKGROUND);
			Mario.incScore();
		}
		
		R.set(Map.getGridBoard()[x][y].getRect());
		Map.getGridBoard()[x][y].setPlayer(true);
		return true;
	}

	/**
	 * update - called by Mario to tell player to update
	 * its state based on the given signals
	 * @param mLeft - signal for move left
	 * @param mRight - signal for move right
	 * @param jump - signal for jump
	 * @param duck - signal for duck
	 */ 
	public synchronized void update(boolean mLeft,	boolean mRight, 
									boolean jump,	boolean duck) 
	{
		//Check left and right moving signals and move the player on the
		//grid, if the movement is legal.
		//Check left signal
		if(mLeft  && !mRight)
		{
			if(this.isMoveLegal(-1, 0))
			{
				translate(-1, 0);
			}
		}
		//Check right signal
		else if(mRight && !mLeft)
		{
			if(this.isMoveLegal(1, 0))
			{
				translate(1, 0);
			}
		}
		
		//Handle the vertical movements
		//If the jump signal is given, we
		//move up if possible
		//Otherwise, we must make a legal
		//downward translation.
		if(jump)
		{
			if( jumpCounter < MAX_JUMPS )
			{
				if(this.isMoveLegal(0,  -1))
				{
					translate(0, -1);
					jumpCounter++;
				}
				else
				{
					if(Map.getGridBoard()[x][y-1].getType() == MapTile.BRICK) Map.getGridBoard()[x][y-1].setType(MapTile.BACKGROUND);
					else if(Map.getGridBoard()[x][y-1].getType() == MapTile.SURPRISE) 
					{
						Map.getGridBoard()[x][y-1].setType(MapTile.WALL);
						Map.getGridBoard()[x][y-2].setType(MapTile.COINS);
					}
					jumpCounter = MAX_JUMPS;
				}
			}
			else
			{
				if(this.isMoveLegal(0, 1))
				{
					translate(0, 1);
				}
				else
				{
					//Landed on a block from above
					//Stop jumping
					jumpCounter = 0;
					Mario.jump = false;
				}
			}
		}
		else
		{
			//If a downward translation is legal, it must be made.
			if(this.isMoveLegal(0, 1))
			{
				translate(0,1);
			}
		}
	}
	
	//Accession Methods and Set Methods
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x;}
	public void setY(int y) { this.y =y; }
	public Rect getRect() { return R; }
	public void setRect(Rect r) { this.R.set(r); }
}

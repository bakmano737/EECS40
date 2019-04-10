package n.shuba;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Alien
{
	// This class is for each individual alien the SpaceInvaders class contains a 2D array of these
	
	// Declare position variables
	private int centerX;
	private int centerY;
	// Declare size variables
	private static final int xSize = 30;
	private static final int ySize = 30;
	// Declare the Rectangle
	private Rect alienRect;
	// Declare the life variable
	private boolean alive;
	
	// Default Constructor
	public Alien()
	{
		centerX = 0;
		centerY = 0;
		alive = true;
		// Allocate the rectangle and set it based on its center position and size
		alienRect = new Rect();
		alienRect.set(centerX - xSize/2, 
					  centerY - ySize/2, 
					  centerX + xSize/2, 
					  centerY + ySize/2 );
		
	}
	
	// Detailed Constructor
	public Alien( int x, int y )
	{
		// User specified center positions and bitmap
		setCenterX(x);
		setCenterY(y);
		alive = true;
		// Allocate the rectangle and set it based on its center position and size
		alienRect = new Rect();
		alienRect.set(centerX - xSize/2, centerX - ySize/2, centerY + xSize/2, centerX + ySize/2 );
	}
	
	//Method to check if the alien has hit the wall
	public void hitRightWall( Canvas c )
	{
		if( (this.getAlienRect().right + Aliens.getSpeed() >= c.getWidth()) && alive )
		{
			//Alien is hitting the right wall!
			Aliens.hitRightWall(true);
		}
		else Aliens.hitRightWall(false);
	}
	
	//Method to check if the alien has hit the wall
	public void hitLeftWall( Canvas c )
	{
		if( (this.getAlienRect().left - Aliens.getSpeed()  <= 0) && alive )
		{
			//Alien is hitting the left wall!
			Aliens.hitLeftWall(true);
		}
		else Aliens.hitLeftWall(false);
	}
	
	// Is the alien alive?
	public boolean isAlienAlive() { return alive; }
	// Kill the alien
	public void killAlien()	{ alive = false; }
	
	//Set an alien's entire position in one move
	public void setAlienPosition( int x, int y )
	{
		setCenterX(x);
		setCenterY(y);
		if(alive) setRect(centerX - xSize/2, 
						  centerY - ySize/2, 
						  centerX + xSize/2, 
						  centerY + ySize/2 );
		else alienRect.setEmpty();
	}
	
	// Accessor Methods
	public int 		getCenterX() 		{ return centerX; 	}
	public int 		getCenterY() 		{ return centerY; 	}
	public Rect		getAlienRect()		{ return alienRect;	}
	// Static Size Accessor Methods
	public static int getXSize()		{ return xSize;	  	}
	public static int getYSize()		{ return ySize;   	}
	// Set Methods
	public void setCenterX( int x ) 	{ centerX = x; 		}
	public void setCenterY( int y )		{ centerY = y;		}
	public void setRect( int L, int T, int R, int B )
	{
		alienRect.set( L, T, R, B );
	}
}

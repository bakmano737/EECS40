package n.shuba;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Aliens 
{	
	// Declare position variables
	private int centerX;
	private int centerY;
	private int xOffset;
	private int yOffset;
	// Speed is declared as static so that 
	// Alien can access it without the Aliens object
	private static int speed;
	
	// Variable hit wall
	// These variables must be static so that
	// the Alien class can access them without
	// the Aliens object
	private static boolean hitRightWall;
	private static boolean hitLeftWall;
	
	// The Number of Aliens dead
	int numDead;
	
	// Alien Bitmap
	private static Bitmap alienBitmap;
	
	// Alien Array
	Alien[][] alienArray;
	
	// Default Constructor
	public Aliens()
	{
		centerX = 0;
		centerY = 0;
		xOffset = 0;
		yOffset = 0;
		speed = 3;
		numDead=0;
		alienBitmap = null;
		hitRightWall = false;
		hitLeftWall = false;
		
		// Allocate the alien array
		alienArray = new Alien[4][5];
		
		// Create the aliens to fill in the array
		for(int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 5; j++ )
			{
				alienArray[i][j] = new Alien();
			}
		}
			
	}
	
	// Copy constructor. Used to reset the game
	public Aliens( Aliens a )
	{
		centerX = a.getCenterX();
		centerY = a.getCenterY();
		xOffset = 0;
		yOffset = 0;
		
		//if the game was lost, speed is set back 
		//to the starting "difficulty"
		if(SpaceInvaders.loser) 
		{
			speed = 3; 
			SpaceInvaders.loser=false;
		}
		
		//else, the game was not lost
		//increase speed
		else speed *= 2;
		alienBitmap = a.getAlienBitmap();
		hitRightWall = false;
		hitLeftWall = false;
		
		alienArray = new Alien[4][5];
		for(int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 5; j++ )
			{
				alienArray[i][j] = new Alien();
			}
		}
	}
	
	// Position the individual aliens
	public void setAliens()
	{
		centerX=Alien.getXSize()/2 + this.getXOffset();
		centerY=Alien.getYSize()/2 + this.getYOffset();
		
		for(int i = 0; i < 4; i++ )
		{
			for(int j=0; j<5; j++)
			{
				alienArray[i][j].setAlienPosition(centerX, centerY);
				centerX += Alien.getXSize(); //increment X position
				
			}
			centerX=Alien.getXSize()/2 + this.getXOffset(); //reset the X position after inner loop is done
			centerY +=Alien.getYSize(); //increment Y position
		}
		centerY=Alien.getYSize()/2 + this.getYOffset(); //reset Y position
	}
	
	// Draw the aliens to the given canvas
	public void drawAliens( Canvas c )
	{
		this.setAliens();
		for(int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 5; j++ )
			{
				if(getAlienArray()[i][j].isAlienAlive()) //draw alien only if he is alive
				{ 
					c.drawBitmap(this.getAlienBitmap(), null, 
								 this.getAlienArray()[i][j].getAlienRect(), null);
				}
			}
		}
		
	}
	
	// Translate the whole group by changing centerX and centerY of the Aliens object
	public void translateAliens(Canvas c, SpaceInvaders si)
	{
		out: for(int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 5; j++ )
			{
				if(getAlienArray()[i][j].isAlienAlive()) //draw alien only if he is alive
				{
					
					if(Rect.intersects(getAlienArray()[i][j].getAlienRect(), si.getPlayer().getPlayerRect()))
					{
						// If the aliens reach the player, set gameOver to true
						SpaceInvaders.gameOver = true;
						SpaceInvaders.loser = true;
						// We don't want to set the offset after this, so we return instead of break
						return;
					}
					
					getAlienArray()[i][j].hitLeftWall(c);
					// Shift the aliens down if they hit the right wall
					if( hitRightWall )
					{
						// If we are in this block, then we are about to hit the right wall
						// Shift down
						setYOffset(getYOffset() + Alien.getYSize());
						// Reverse direction
						speed *= -1;
						// Reset the hitRightWall variable
						hitRightWall = false;
						break out;
					}
					getAlienArray()[i][j].hitRightWall(c);
					
					if( hitLeftWall && speed < 0)
					{
						// If we are in this block, then we are about to hit the left wall
						// Shift down
						setYOffset(getYOffset() + Alien.getYSize());
						// Reverse direction
						speed *= -1;
						// Reset the hitLeftWall variable
						hitLeftWall = false;
						break out;
					}
					
				}
			}
		}
		setXOffset( getXOffset() + speed );
	}
	
	// Accessor Methods
	public int getCenterX() 		{ return centerX; 		}
	public int getCenterY() 		{ return centerY; 		}
	public int getXOffset() 		{ return xOffset; 		}
	public int getYOffset() 		{ return yOffset; 		}
	public static int getSpeed()	{ return speed;			}
	public Alien[][]  getAlienArray() 	{ return alienArray;	}
	public Bitmap 	  getAlienBitmap()	{ return alienBitmap;  	}
	
	// Set Methods
	public void setCenterX( int x ) 	{ centerX = x; 		}
	public void setCenterY( int y )		{ centerY = y;		}
	public void setXOffset( int x )		{ xOffset = x;		}
	public void setYOffset( int y )		{ yOffset = y;		}
	public void setSpeed  ( int s )		{ speed   = s;		}
	public void setBitmap( Bitmap b )	{ alienBitmap = b; 	}
	
	// Hit the walls
	public static void hitLeftWall(Boolean b)  { hitLeftWall = b;  }
	public static void hitRightWall(Boolean b) { hitRightWall = b; } 
	
}

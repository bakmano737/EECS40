package n.shuba;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {

	// Declare position variables
	// Only one object of this class should exist
	// if extras are made by mistake this forces
	// the extra to behave the same as the original
	private static int centerX;
	private static int centerY;
	private static int xOffset;
	private static int yOffset;
	
	// Declare size constants
	private static final int xSize = 50;
	private static final int ySize = 50;
	
	// Declare the player Bitmap
	private static Bitmap playerBitmap;
	
	// Declare the Rectangle
	private static Rect playerRect;
	
	// Movement Variables
	private static Boolean movingLeft;
	private static Boolean movingRight;
	
	//Default Constructor
	public Player()
	{
		centerX = 0;
		centerY = 0;
		xOffset = 0;
		yOffset = 0;
		playerBitmap = null;
		movingLeft = false;
		movingRight = false;
		// Allocate the rectangle and set it based on its center position and size
		playerRect = new Rect();
		playerRect.set(centerX - xSize/2 + xOffset, centerY - ySize/2, centerX + xSize/2 + xOffset, centerY + ySize/2 );
	}
	
	//Detailed Constructor
	public Player( int x, int y, Bitmap b)
	{
		// User specified center positions and bitmap
		centerX = x;
		centerY = y;
		xOffset = 0;
		yOffset = 0;
		playerBitmap = b;
		// Allocate the rectangle and set it based on its center position and size
		playerRect = new Rect();
		playerRect.set(centerX - xSize/2 + xOffset, centerY, centerX + xSize/2 + xOffset, centerY + ySize );
	}
	
	//Set the player's entire position in one move
	public void setPlayerPosition( int x, int y, int dx, int dy, Bitmap b )
	{
		setCenterX(x);
		setCenterY(y);
		setXOffset(dx);
		setYOffset(dy);
		setBitmap(b);
		playerRect.set(centerX - xSize/2 + xOffset, centerY, centerX + xSize/2 + xOffset, centerY + ySize );
	}
	
	//Draw the player to the given canvas
	public void drawPlayer( Canvas c )
	{
		//Draw the bitmap
		c.drawBitmap(this.getPlayerBitmap(), null, this.getPlayerRect(), null);	
	}
	
	public void translatePlayer( Canvas c )
	{
		if(movingLeft && (this.getXOffset() >= -(this.getCenterX()-20)))
		{
			this.setXOffset(this.getXOffset() - 10);
		}
		else if(movingRight && (this.getXOffset() <= this.getCenterX()-20))
		{
			this.setXOffset(this.getXOffset() + 10);
		}
		else if(movingLeft && movingRight)
		{
			// If both keys are pressed, do not move!
			this.setXOffset(this.getXOffset());
		}

		this.setPlayerPosition( c.getWidth()/2, 
								c.getHeight() - Player.getPlayerYSize(), 
								this.getXOffset(), 
								this.getYOffset(), 
								this.getPlayerBitmap());
	}
	
	//Accessor Methods
	public int 		getCenterX() 		{ return centerX; 		}
	public int 		getCenterY() 		{ return centerY; 		}
	public int 		getXOffset() 		{ return xOffset; 		}
	public int 		getYOffset() 		{ return yOffset; 		}
	public Bitmap 	getPlayerBitmap()	{ return playerBitmap;  }
	public Rect		getPlayerRect()		{ return playerRect;	}
	//Size Values are static so we can access them with a static method if we need to obtain the size before creation
	public static int getPlayerXSize()	{ return xSize;	  		}
	public static int getPlayerYSize()	{ return ySize;   		}
	// Methods for Movement
	public Boolean getMovingLeft() 		{ return movingLeft;  	}
	public Boolean getMovingRight()		{ return movingRight; 	}
	
	//Set Methods
	public void setCenterX( int x ) 	  	{ centerX = x; 		}
	public void setCenterY( int y )		  	{ centerY = y;		}
	public void setXOffset( int x )		  	{ xOffset = x;		}
	public void setYOffset( int y )		  	{ yOffset = y; 		}
	public void setBitmap( Bitmap b )	  	{ playerBitmap = b; }
	public void setMovingLeft(  Boolean b ) { movingLeft = b;	}
	public void setMovingRight( Boolean b ) { movingRight = b;	}
	public void setRect( int L, int T, int R, int B )
	{
		playerRect.set( L, T, R, B );
	}
		
}

package nj.eecs40.tetris;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class TetrisPiece 
{
	// This class serves as the backbone for all 7 of the Tetris pieces or tetriminos.
	// Classes IPiece JPiece LPiece OPiece SPiece TPiece and ZPiece extend this class
	// and use its methods. Each class must define its own rotate method.
	
	// Static Fields Size, Speed, and Bitmap
	private static int size = 20;
	private static int speed;
	
	// Fields
	// Origin position on the grid
	protected int originX;
	protected int originY;
	
	//Origin for displaying next piece
	protected int dispOrigX;
	protected int dispOrigY;
	
	// Array of x coordinates relative to the grid
	protected int[] xCoords;
	protected int[] yCoords;
	
	// Bitmap of Piece Square
	protected Bitmap bmp;
	
	// Current Orientation. Most pieces have 4 orientations except I and O
	// The value of orientation can only be 1, 2, 3, 4. Maybe there is a better
	// way to declare this variable
	protected int orientation;
	// Orientation Constants: Refers to the position of the Head of the piece.
	// For I UP and DOWN are identical and LEFT and RIGHT are identical.
	// All four orientations are the same for O.
	// For J and L the head is the bent side
	// For T the head is the three adjacent pieces
	// For S and Z the origin is on the higher row.
	// Consult the readme file for more details.
	public static final int DISPLAY = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	
	// Direction Constants: refers to which way they rotate
	// Clockwise goes UP->LEFT->DOWN->RIGHT and is set by the up key
	// Counterclockwise (shortened to COUNTER) goes UP->RIGHT->DOWN->LEFT
	// and is set by the space bar.
	public static final int COUNTER = -1;
	public static final int CLOCKWISE = 1;
	
	// Array of Rectangles for drawing each square
	private Rect[] rectArray;
	// Array of Row #s of each square final position
	private int[] rowArray;

	//A bool to tell if the piece is stuck or not
	//Helps decide whether or not to draw next piece
	protected boolean settled; 
	
	// Generic Constructor
	public TetrisPiece()
	{
		speed = 0;
		bmp = null;
		originX = TetrisGrid.getCols()/2;
		originY = 3;
		orientation = UP;
		xCoords = new int[4];
		yCoords = new int[4];
		rectArray = new Rect[4];
		for( int i = 0; i < rectArray.length; i++ )
		{
			rectArray[i] = new Rect();
		}
		rowArray = new int[4];
	}
	
	/** drawPiece - Draw the all four pieces of */ 
	public void drawPiece(Canvas c)
	{
		
		for( int i = 0; i < getRectArray().length; i++ )
		{
			this.setCoords();
			c.drawBitmap(this.getBitmap(), null, this.getRect(i), null);
		}
	}
	
	public void drawDispPiece(Canvas c)
	{
		for( int i = 0; i < getRectArray().length; i++ )
		{
			c.drawBitmap(this.getBitmap(), null, this.getRect(i), null);
		}
	}
	
	/** rotate - rotate the piece based the given orientation which should 
	 *  be set to one of the four orientation constants RIGHT LEFT DOWN and UP.
	 *  The rotation will be based on the given direction which should be set to
	 *  one of the direction constants CLOCKWISE and COUNTER */
	public void rotate(int orientation, int direction) 
	{
		switch(orientation)
		{
			case UP:
			{
				if( direction == CLOCKWISE )
				{
					this.setOrientation(RIGHT);
					this.setCoords();
					return;
				}
				else if( direction == COUNTER )
				{
					this.setOrientation(LEFT);
					this.setCoords();
					return;
				}
			}
			case RIGHT:
			{
				if( direction == CLOCKWISE )
				{
					this.setOrientation(DOWN);
					this.setCoords();
					return;
					
				}
				else if( direction == COUNTER )
				{
					this.setOrientation(UP);
					this.setCoords();
					return;
				}				
			}
			case DOWN:
			{
				if( direction == CLOCKWISE )
				{
					this.setOrientation(LEFT);
					this.setCoords();
					return;
					
				}
				else if( direction == COUNTER )
				{
					this.setOrientation(RIGHT);
					this.setCoords();
					return;
				}
			}
			case LEFT:
			{
				if( direction == CLOCKWISE )
				{
					this.setOrientation(UP);
					this.setCoords();
					return;
					
				}
				else if( direction == COUNTER )
				{
					this.setOrientation(DOWN);
					this.setCoords();
					return;
				}
			}
			default:
			{
				System.out.println("Orientation Error!");
				return;
			}
		}
	}
	
	/** translate - Moves the piece by the given number of grid spaces
	 *  which are the dx and dy values */
	public void translate(int dx, int dy)
	{
		// Increase the grid position by the given number of spaces
		originX += dx;
		originY += dy;
		// Adjust the rectangles to the new grid position. 
		this.setCoords();
	}
	
	/** freezePiece - Called by the tetris piece when it hits the bottom of the screen
	 *  or intersects a frozen active grid square. The method grabs the bitmap from the
	 *  TetrisPiece and sets isEmpty for all of the grid pieces associated with it to false
	 */
	protected void freezePiece()
	{		
		// Set the bitmap and isEmpty for the gridPieces associated with the TetrisPiece
		for(int i = 0; i < this.xCoords.length; i++)
		{
			TetrisGrid.getGridBoard()[this.xCoords[i]][this.yCoords[i]].isEmpty = false;
			TetrisGrid.getGridBoard()[this.xCoords[i]][this.yCoords[i]].setBitmap(this.getBitmap());
			TetrisGrid.checkRow(this.yCoords[i]);
		}
		
		this.settled = true; //send signal to Tetris View that it's time to draw the next piece
	}
	
	/** setPosition - Set the value of originX and Origin to the given x and y */
	public void setPosition( int x, int y )
	{
		originX = x;
		originY = y;
	}
	
	/** Set the individual squares based on the values in xCoords and yCoords */
	public void setRects()
	{
		for(int i = 0; i < this.xCoords.length; i++)
		{
			this.rectArray[i].set(TetrisGrid.getGridBoard()[this.xCoords[i]][this.yCoords[i]].getRect());
		}
	}
	
	/** checkSides - returns true if the grid space to the left or right of the piece is occupied by
	 *  a frozen piece */
	public boolean checkSides()
	{
		// Iterate through the xCoords of the piece
		for( int i = 0; i < this.xCoords.length; i++ )
		{
			// Check that the grid space to the left (xCoords-1) is empty
			if( 0 <= xCoords[i]-1 &&
				!TetrisGrid.getGridBoard()[xCoords[i]-1][yCoords[i]].isEmpty ) 
			{
				return true;
			}
			// Check that the grid space to the right (xCoords+1) is empty
			if( TetrisGrid.getGridBoard().length > xCoords[i]+1 &&
				!TetrisGrid.getGridBoard()[xCoords[i]+1][yCoords[i]].isEmpty ) 
			{
				return true;
			}
		}
		return false;
	}
	
	/** checkBottom - returns true if the grid space below of any of the squares 
	 *  occupied by a settled piece */
	public boolean checkBottom()
	{
		// Iterate through the xCoords of the piece
		for( int i = 0; i < this.xCoords.length; i++ )
		{
			// Check that the grid space below (yCoords+1) is empty
			if( this.yCoords[i]+1 < TetrisGrid.getRows() 
				&& !TetrisGrid.getGridBoard()[xCoords[i]][yCoords[i]+1].isEmpty ) 
			{
				return true;
			}
		}
		return false;
	}
		
	// Set the squares to the given orientation
	public abstract void setCoords();
	
	// Accessor Methods
	
	// Rectangles
	public Rect[] 	getRectArray() 			{ return rectArray; 		}
	public Rect 	getRect( int index ) 	{ return rectArray[index]; 	}
	public int[]	getRowArray()			{ return rowArray;			}
	public int		getRow( int index )		{ return rowArray[index];	}
	
	// Positions
	public int		getOriginX()			{ return originX;			}
	public int		getOriginY()			{ return originY;			}
	public int		getOrientation()		{ return orientation;		}
	
	// Static Acces to Size and Speed
	public static int getSize()				{ return size;				}
	public static int getSpeed()			{ return speed;				}
	
	// Bitmap
	public Bitmap	getBitmap()				{ return bmp;				}
	
	//Is the piece stuck?
	public boolean isSettled() { return settled; }
	
	// Set Methods
	
	//size
	public static void setSize	(int s)		{ size = s;			}
	
	// Rectangles
	public void setRect( int index, Rect rect ) { rectArray[index] = rect; 	}
	public void	setRow( int index, int num ) 	{ rowArray[index] = num; 	}
	
	// Positions
	public void	setOriginX		( int x )	{ originX = x;		}
	public void	setOriginY		( int y )	{ originY = y;		}
	public void	setSpeed  		( int s )	{ speed = s;		}
	public void setOrientation	( int o )	{ orientation = o;  }		
	
	// Bitmap
	public void setBitmap ( Bitmap map )	{ bmp = map; 		}
	
}

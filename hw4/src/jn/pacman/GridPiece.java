package jn.pacman;
import android.graphics.Bitmap;
import android.graphics.Rect;

	/** IGridPiece is a subclass used to represent each square of the grid */
public class GridPiece 
{		
	// Rectangle for drawing
	private Rect R;
		
	// Bitmap to be obtained from the TetrisPiece
	private Bitmap activeBMP;
		
	private static int size;
	// Position variables for the rectangle. They will be used by TetrisPiece
	// and TetrisGrid, but only TetrisGrid should be allowed to change them.
	private int left;
	private int right;
	private int top;
	private int bottom;
	
	//Ordering in the Grid:
	private int x;
	private int y;
		
	// isEmpty tells the grid whether or not a piece has stopped there
	// this is necessary to check for full rows.
	// Other classes will need read and write access to isEmtpy
	// it's easier to make it available to the entire package 
	private boolean isEmpty;
	
	/** Constructor. Takes in coordinates of where to set the grid rectangle.
	 *  This depends on canvas size */
	public GridPiece(int xStart, int yStart, int x, int y, Bitmap b)
	{
		// Set isEmpty to true on creation
		setEmpty(true);
		
		this.x = x;
		this.y = y;
		
		// Since we create the space when it is empty the bitmap should be null.
		setBitmap(b);
			
		// Initialize the postion variables based on the given parameters
		left = xStart;
		top = yStart;
		right = xStart + getSize();
		bottom = yStart + getSize();
			
		// Create a rectangle and set it to the gridPiece's position
		R = new Rect();
		R.set(left, top, right, bottom);
	}
	
	//Get the Neighbors!
	/** Get right tile */
	public GridPiece getRight()
	{
		if( ((x+1) < PacmanGrid.getCols()) && (PacmanGrid.getGridBoard()[x+1][y].getBitmap() != PacmanGrid.getWall()) ) 
		{
			return PacmanGrid.getGridBoard()[x+1][y];
		}
		else return null;
	}
	
	/** Get left tile */
	public GridPiece getLeft()
	{
		if( ((x-1) < PacmanGrid.getCols()) && (x-1 >= 0) && (PacmanGrid.getGridBoard()[x-1][y].getBitmap() != PacmanGrid.getWall())) 
		{
			return PacmanGrid.getGridBoard()[x-1][y];
		}
		else return null;
	}
	
	/** Get upper tile */
	public GridPiece getUpper()
	{
		if(y-1 < PacmanGrid.getRows() && (y-1 >= 0)  && (PacmanGrid.getGridBoard()[x][y-1].getBitmap() != PacmanGrid.getWall())) 
		{
			return PacmanGrid.getGridBoard()[x][y-1];
		}
		else return null;
	}
	
	/** Get lower tile */
	public GridPiece getLower()
	{
		if(y+1 < PacmanGrid.getRows() && (PacmanGrid.getGridBoard()[x][y+1].getBitmap() != PacmanGrid.getWall()) ) 
		{
			return PacmanGrid.getGridBoard()[x][y+1];
		}
		else return null;
	}
		
	/** Set the bitmap to the bitmap of the give piece */
	public void setBitmap(Bitmap bmp)
	{
		this.activeBMP = bmp;
	}
		
	/** Get the bitmap of the given piece */
	public Bitmap getBitmap()
	{
		return activeBMP;
	}

	/**Get size of individual grid pieces */
	public static int getSize() { return size; }
	/**Set the size of individual grid pieces */
	public static void setSize(int size) { GridPiece.size = size; }

	public Rect getRectangle() { return R; }

	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() { return isEmpty; }

	/**
	 * @param isEmpty the isEmpty to set
	 */
	public void setEmpty(boolean isEmpty) { this.isEmpty = isEmpty; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public int getLcoord() { return left; 	}
	public int getUcoord() { return top; 	}
	public int getRcoord() { return right; 	}
	public int getDcoord() { return bottom; }
}
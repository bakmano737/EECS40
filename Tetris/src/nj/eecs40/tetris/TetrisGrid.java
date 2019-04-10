package nj.eecs40.tetris;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/** TetrisGrid class handles the 2D array of GridPieces and the array of rows.
 *  It implements methods to check if a row is full, clear a full row,
 *  drop down all pieces above a cleared row, and drawing all of the pieces */
public class TetrisGrid 
{
	// GridPiece class. This belongs to TetrisGrid because TetrisGrid should handle
	// all the squares.
	
	// TetrisGrid Fields
	
	// Grid Board
	private static GridPiece[][] GridBoard;
	
	// Array of booleans to have multiple rows full at once		
	private static Boolean[] isRowFull;
		
	// Number of Rows and Columns
	private static final int rows = 40;
	private static final int cols = 20;
	
	//Bitmap for testing grid layout
	public static Bitmap bmp;
		
	/** IGridPiece is a subclass used to represent each square of the grid */
	protected class GridPiece 
	{
		// GridPiece represents each square in the grid if a piece stops
		// in this piece, then the square needs to become 'active' and 
		// display the bitmap of the piece.
		
		// Rectangle for drawing
		private Rect R;
		
		// Bitmap to be obtained from the TetrisPiece
		private Bitmap activeBMP;
		
		// Position variables for the rectangle. They will be used by TetrisPiece
		// and TetrisGrid, but only TetrisGrid should be allowed to change them.
		private int left;
		private int right;
		private int top;
		private int bottom;
		
		// isEmpty tells the grid whether or not a piece has stopped there
		// this is necessary to check for full rows.
		// Other classes will need read and write access to isEmtpy
		// it's easier to make it available to the entire package 
		public boolean isEmpty;
	
		/** Constructor. Takes in coordinates of where to set the grid rectangle.
		 *  This depends on canvas size */
		public GridPiece(int xStart, int yStart, int XoffSet, int YoffSet)
		{
			// Set isEmpty to true on creation
			isEmpty = true;
			
			// Since we create the space when it is empty the bitmap should be null.
			activeBMP = TetrisGrid.bmp;
			
			// Initialize the postion variables based on the given parameters
			left = xStart + XoffSet;
			top = yStart + YoffSet;
			right = xStart + TetrisPiece.getSize() + XoffSet;
			bottom = yStart + TetrisPiece.getSize() + YoffSet;
			
			// Create a rectangle and set it to the gridPiece's position
			R = new Rect();
			R.set(left, top, right, bottom);
		}
		
		/** Copy constructor. To be used by dropRows */
		public GridPiece(GridPiece gridP)
		{
			this.R = gridP.R;
			this.isEmpty = gridP.isEmpty;
			this.activeBMP = gridP.activeBMP;
		}
		
		/** Get the rectangle */
		public Rect getRect() { return R; }
		
		/** Set the bitmap to the bitmap of the give piece */
		public void setBitmap(Bitmap bmp)
		{
			this.activeBMP = bmp;
		}
		
		/** Get the bitmap of the given piece */
		public Bitmap getBitmap()
		{
			return this.activeBMP;
		}
	}
	
	/** Constructor. Takes in number of rows and columns known
	 *  at the time that the TetrisGrid is initialized */
	public TetrisGrid(int w, int h, Bitmap b)
	{
		int pieceWidth = w/cols;
		int pieceHeight = h/rows;
		int XoffSet = 0;
		int YoffSet = 0;
		int size = 0;
		
		if(pieceWidth < pieceHeight)
		{
			size = pieceWidth;
			XoffSet = pieceHeight - size;
		}
		else
		{
			size = pieceHeight;
			YoffSet = pieceWidth - size;
		}
		
		TetrisPiece.setSize(size);
		
		GridBoard = new GridPiece[cols][rows];
		isRowFull = new Boolean[rows];
		
		bmp = b;
	
		for(int i=0; i < cols; i++)//x
		{
			for(int j=0; j < rows; j++)//y
			{
				GridBoard[i][j] = new GridPiece(i*size, j*size, XoffSet, YoffSet);
			}
		}
	}
	
	/** Check if the given row is full. Set the row to true is the isRowFull area 
	 *  and return true if it is */
	public static boolean checkRow( int j )
	{
		int sum = 0;
		for(int i = 0; i < cols; i++ )
		{
			if(!GridBoard[i][j].isEmpty) sum++;
		}
		if(sum == cols)
		{
			//If we got here, that means a row was filled up
			//Increment speed, clear and drop rows, and
			//add the number of rows cleared to the score
			isRowFull[j] = true;
			TetrisView.incSpeed();
			TetrisView.addScore(ClearAndDropRows(j));
			return true;
		}
		else return false;
	}
	
	/** Clears and drops subsequent rows. Returns number of rows dropped
	 *  to let higher functions know by how much to increase the score */
	private static int ClearAndDropRows(int k)
	{
		int numDropped = 1;
		boolean stillDropping = true;
		for(int i = 0; i < cols; i++ )
		{
			for(int j=k; j > 0; j--)
			{
				GridBoard[i][j].setBitmap(GridBoard[i][j-1].getBitmap()); 
				GridBoard[i][j].isEmpty = GridBoard[i][j-1].isEmpty;
				if(GridBoard[i][j].isEmpty) stillDropping = false;				
			}
			if(stillDropping) numDropped++;
		}
		return numDropped;
	}
	
	
	/** Draw the bitmap obtained from the piece that set the square's isEmpty to false
	 *  only if the isEmpty field is still false */
	public void drawGrid( Canvas c )
	{
		for(int i=0; i < cols; i++)
		{
			for(int j=0; j < rows; j++)
			{
				if((null != GridBoard[i][j].activeBMP) && (!GridBoard[i][j].isEmpty))
				{
					c.drawBitmap(GridBoard[i][j].activeBMP, null, GridBoard[i][j].R, null);
				}
			}
		}
		
	}
	
	public static int getRows() { return rows; }
	public static int getCols() { return cols; }
	public static GridPiece[][] getGridBoard() { return GridBoard; }
}

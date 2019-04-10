package jn.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/** PacmanGrid class handles the 2D array of GridPieces and the array of rows.
 *  */
public class PacmanGrid 
{	
	// Grid Board
	private static GridPiece[][] GridBoard;
		
	// Number of Rows and Columns
	private static final int rows = 21;
	private static final int cols = 19;
	
	//Bitmap for testing grid layout
	private static Bitmap Wall;
	private static Bitmap Road;
	private static Bitmap BigSeed;
	
	/** Constructor. Takes in number of rows and columns known
	 *  at the time that the TetrisGrid is initialized */
	public PacmanGrid(int w, int h)
	{
		int tileWidth = w/cols;
		int tileHeight = h/rows;
		int size = 0;
		
		Bitmap b = Road;
		
		if(tileWidth < tileHeight)
		{
			size = tileWidth;
		}
		else
		{
			size = tileHeight;
		}
		
		
		GridPiece.setSize(size);
		
		GridBoard = new GridPiece[cols][rows];
	
		for(int i=0; i < cols; i++)//x
		{
			for(int j=0; j < rows; j++)//y
			{
				if(0 == j || 0 == i || cols-1 == i || rows-1 == j) b = Wall;
				else b = Road;
				GridBoard[i][j] = new GridPiece(i*size, j*size, i, j, b);
			}
		}
		//Specifics for a map:
		customMap();
	}
	
	
	/** Draws tiles if they are walls */
	public void drawGrid( Canvas c )
	{
		for(int i=0; i < cols; i++)
		{
			for(int j=0; j < rows; j++)
			{
				if(null != GridBoard[i][j].getBitmap())
				{
					c.drawBitmap(GridBoard[i][j].getBitmap(), null, 
								 GridBoard[i][j].getRectangle(), null);
				}
			}
		}
		
	}
	
	/* Set bitmap methods: */
	public static void setWall(Bitmap b) 		{ Wall = b; }
	public static void setRoad(Bitmap b) 		{ Road = b; }
	public static void setBigSeed(Bitmap b) 	{ BigSeed = b; }
	
	/* Get bitmap methods: */
	public static Bitmap getWall() { return Wall; }
	public static Bitmap getRoad() { return Road; }
	public static Bitmap getBigSeed() 	{ return BigSeed; }
	
	public static int getRows() { return rows; }
	public static int getCols() { return cols; }
	public static GridPiece[][] getGridBoard() { return GridBoard; }
	
	/** Big, ugly method that no one should see.
	 *  Sets the unique to map walls and big, 
	 *  special seeds: */
	private void customMap()
	{
		//Center block:
		GridBoard[7][9].setBitmap(Wall);
		GridBoard[8][9].setBitmap(Wall);
		GridBoard[9][9].setBitmap(Wall);
		GridBoard[10][9].setBitmap(Wall);
		GridBoard[11][9].setBitmap(Wall);
		GridBoard[7][10].setBitmap(Wall);
		GridBoard[8][10].setBitmap(Wall);
		GridBoard[9][10].setBitmap(Wall);
		GridBoard[10][10].setBitmap(Wall);
		GridBoard[11][10].setBitmap(Wall);
		
		GridBoard[2][2].setBitmap(Wall);
		GridBoard[3][2].setBitmap(Wall);
		GridBoard[2][3].setBitmap(Wall);
		GridBoard[3][3].setBitmap(Wall);
		
		GridBoard[5][2].setBitmap(Wall);
		GridBoard[6][2].setBitmap(Wall);
		GridBoard[7][2].setBitmap(Wall);
		GridBoard[5][3].setBitmap(Wall);
		GridBoard[6][3].setBitmap(Wall);
		GridBoard[7][3].setBitmap(Wall);
		
		GridBoard[9][1].setBitmap(Wall);
		GridBoard[9][2].setBitmap(Wall);
		GridBoard[9][3].setBitmap(Wall);
		
		GridBoard[11][2].setBitmap(Wall);
		GridBoard[12][2].setBitmap(Wall);
		GridBoard[13][2].setBitmap(Wall);
		GridBoard[15][2].setBitmap(Wall);
		GridBoard[16][2].setBitmap(Wall);
		GridBoard[11][3].setBitmap(Wall);
		GridBoard[12][3].setBitmap(Wall);
		GridBoard[13][3].setBitmap(Wall);
		GridBoard[15][3].setBitmap(Wall);
		GridBoard[16][3].setBitmap(Wall);
		
		GridBoard[2][5].setBitmap(Wall);
		GridBoard[3][5].setBitmap(Wall);
		GridBoard[5][5].setBitmap(Wall);
		GridBoard[7][5].setBitmap(Wall);
		GridBoard[8][5].setBitmap(Wall);
		
		GridBoard[9][5].setBitmap(Wall);
		GridBoard[9][6].setBitmap(Wall);
		GridBoard[9][7].setBitmap(Wall);
		
		GridBoard[10][5].setBitmap(Wall);
		GridBoard[11][5].setBitmap(Wall);
		
		//Extending Walls for a tunnel-like thing:
		//Left:
		GridBoard[1][7].setBitmap(Wall);
		GridBoard[2][7].setBitmap(Wall);
		GridBoard[3][7].setBitmap(Wall);
		GridBoard[1][8].setBitmap(Wall);
		GridBoard[2][8].setBitmap(Wall);
		GridBoard[3][8].setBitmap(Wall);
		GridBoard[1][9].setBitmap(Wall);
		GridBoard[2][9].setBitmap(Wall);
		GridBoard[3][9].setBitmap(Wall);
		GridBoard[0][10].setBitmap(Road); 
		GridBoard[1][11].setBitmap(Wall);
		GridBoard[2][11].setBitmap(Wall);
		GridBoard[3][11].setBitmap(Wall);
		GridBoard[1][12].setBitmap(Wall);
		GridBoard[2][12].setBitmap(Wall);
		GridBoard[3][12].setBitmap(Wall);
		
		//Right Extension:
		GridBoard[15][7].setBitmap(Wall);
		GridBoard[16][7].setBitmap(Wall);
		GridBoard[17][7].setBitmap(Wall);
		GridBoard[15][8].setBitmap(Wall);
		GridBoard[16][8].setBitmap(Wall);
		GridBoard[17][8].setBitmap(Wall);
		GridBoard[15][9].setBitmap(Wall);
		GridBoard[16][9].setBitmap(Wall);
		GridBoard[17][9].setBitmap(Wall);
		GridBoard[18][10].setBitmap(Road); 
		GridBoard[15][11].setBitmap(Wall);
		GridBoard[16][11].setBitmap(Wall);
		GridBoard[17][11].setBitmap(Wall);
		GridBoard[15][12].setBitmap(Wall);
		GridBoard[16][12].setBitmap(Wall);
		GridBoard[17][12].setBitmap(Wall);

		GridBoard[13][5].setBitmap(Wall);
		GridBoard[13][6].setBitmap(Wall);
		GridBoard[13][7].setBitmap(Wall);
		GridBoard[12][7].setBitmap(Wall);
		GridBoard[11][7].setBitmap(Wall);
		GridBoard[13][8].setBitmap(Wall);
		GridBoard[13][9].setBitmap(Wall);
		
		GridBoard[15][5].setBitmap(Wall);
		GridBoard[16][5].setBitmap(Wall);
		
		GridBoard[5][6].setBitmap(Wall);
		GridBoard[5][7].setBitmap(Wall);
		GridBoard[6][7].setBitmap(Wall);
		GridBoard[7][7].setBitmap(Wall);
		GridBoard[5][8].setBitmap(Wall);
		GridBoard[5][9].setBitmap(Wall);
		
		//Halfway down:
		GridBoard[5][11].setBitmap(Wall);
		GridBoard[5][12].setBitmap(Wall);
		
		GridBoard[13][11].setBitmap(Wall);
		GridBoard[13][12].setBitmap(Wall);
		
		GridBoard[7][12].setBitmap(Wall);
		GridBoard[8][12].setBitmap(Wall);
		GridBoard[9][12].setBitmap(Wall);
		GridBoard[9][13].setBitmap(Wall);
		GridBoard[9][14].setBitmap(Wall);
		GridBoard[10][12].setBitmap(Wall);
		GridBoard[11][12].setBitmap(Wall);
		
		GridBoard[5][14].setBitmap(Wall);
		GridBoard[6][14].setBitmap(Wall);
		GridBoard[7][14].setBitmap(Wall);
		
		GridBoard[11][14].setBitmap(Wall);
		GridBoard[12][14].setBitmap(Wall);
		GridBoard[13][14].setBitmap(Wall);
		
		GridBoard[2][14].setBitmap(Wall);
		GridBoard[3][14].setBitmap(Wall);
		GridBoard[3][15].setBitmap(Wall);
		GridBoard[3][16].setBitmap(Wall);
		
		GridBoard[16][14].setBitmap(Wall);
		GridBoard[15][14].setBitmap(Wall);
		GridBoard[15][15].setBitmap(Wall);
		GridBoard[15][16].setBitmap(Wall);
		
		GridBoard[1][16].setBitmap(Wall);
		GridBoard[17][16].setBitmap(Wall);
		
		//3 lower walls:
		
		//Middle:
		GridBoard[7][16].setBitmap(Wall);
		GridBoard[8][16].setBitmap(Wall);
		GridBoard[9][16].setBitmap(Wall);
		GridBoard[9][17].setBitmap(Wall);
		GridBoard[9][18].setBitmap(Wall);
		GridBoard[10][16].setBitmap(Wall);
		GridBoard[11][16].setBitmap(Wall);
		
		//Left:
		GridBoard[2][18].setBitmap(Wall);
		GridBoard[3][18].setBitmap(Wall);
		GridBoard[4][18].setBitmap(Wall);
		GridBoard[5][18].setBitmap(Wall);
		GridBoard[5][17].setBitmap(Wall);
		GridBoard[5][16].setBitmap(Wall);
		GridBoard[6][18].setBitmap(Wall);
		GridBoard[7][18].setBitmap(Wall);
		
		//Right:
		GridBoard[11][18].setBitmap(Wall);
		GridBoard[12][18].setBitmap(Wall);
		GridBoard[13][18].setBitmap(Wall);
		GridBoard[13][17].setBitmap(Wall);
		GridBoard[13][16].setBitmap(Wall);
		GridBoard[14][18].setBitmap(Wall);
		GridBoard[15][18].setBitmap(Wall);
		GridBoard[16][18].setBitmap(Wall);
		
		//Big, special seeds:
		GridBoard[17][19].setBitmap(BigSeed);
		GridBoard[1][17].setBitmap(BigSeed);
		GridBoard[17][1].setBitmap(BigSeed);
		GridBoard[9][8].setBitmap(BigSeed);
	}
}


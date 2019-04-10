package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Map 
{
	// Grid Board
	private static MapTile[][] GridBoard;
		
	// Number of Rows and Columns
	
	// Should not be final, should be determined by size
	private static int height;
	private static final int width = 84;
	private static final int displayW = 22; 
	
	//The paint should be the same for all tiles,
	//so it's part of Map:
	private static Paint coins;
	
	private static Bitmap test;
	
	/** 
	 *  Constructor.
	 *  @param w - width of canvas 
	 *  @param h - height of canvas 
	 */
	public Map(int w, int h, int level)
	{
		int size;
		
		// Default tile type is background
		int tileType = MapTile.BACKGROUND;
		
		// Determine the size of the tiles 
		// from the given width
		size = w/displayW;
		MapTile.setSize(size);
		height = h/size;
		
		// Create the grid board array
		GridBoard = new MapTile[width][height];
	
		// Iterate through all the spaces of the
		// gridboard array to fill each with a
		// MapTile object setting specific tiles
		// to wall. Could this possibly be executed
		// in a complete version of customMap?
		for(int i=0; i < width; i++)
		{
			for(int j=0; j < height; j++)
			{
				if(j == height - 1 || i == width - 1) tileType = MapTile.WALL;
				else tileType = MapTile.BACKGROUND;
				
				GridBoard[i][j] = new MapTile(i, j, tileType);
			}
		}
		
		//Color for coins, same for all worlds:
		coins = new Paint();
		coins.setColor(Color.YELLOW);
		
		//Different map for each level:
		if(level == 1 ) setLevel1();
		else if(level == 2) setLevel2();
	}
	
	
	/** 
	 *  drawGrid - Draws tiles if they are walls 
	 *  @param c - Canvas on which the tiles are drawn 
	 */
	public synchronized void drawGrid( Canvas c, int x1, int x2 )
	{
		for(int i=0; i < width; i++)
		{
			for(int j=0; j < height; j++)
			{
				if((i>=0) && (i<width) && (j>=0) && (j<height))
				{
					GridBoard[i][j].getRect().offsetTo((i-x1)*MapTile.getSize(), j*MapTile.getSize());
					GridBoard[i][j].draw(c);
				}
			}
		}
 	}
	
	/** Big, ugly method that no one should see.
	 *  Sets the unique to map walls and special
	 *  blocks for level 1: */
	private void setLevel1()
	{
		//LEVEL 1: http://www.youtube.com/watch?v=YDKFia74mKs
		//height-11 is the top of world
		
		/* FIRST BRICKS: */
		GridBoard[4][height-5].setType(MapTile.BRICK);
		GridBoard[5][height-5].setType(MapTile.BRICK);
		GridBoard[6][height-5].setType(MapTile.BRICK);
		GridBoard[7][height-5].setType(MapTile.SURPRISE);
		GridBoard[8][height-5].setType(MapTile.SURPRISE);
		GridBoard[9][height-5].setType(MapTile.SURPRISE);
		GridBoard[10][height-5].setType(MapTile.SURPRISE);
		GridBoard[11][height-5].setType(MapTile.BRICK);
		GridBoard[12][height-5].setType(MapTile.BRICK);
		GridBoard[13][height-5].setType(MapTile.BRICK);
		
		//Upper first BRICK:
		GridBoard[7][height-8].setType(MapTile.BRICK);
		GridBoard[8][height-8].setType(MapTile.BRICK);
		GridBoard[9][height-8].setType(MapTile.BRICK);
		GridBoard[10][height-8].setType(MapTile.BRICK);
		
		//First upper wall:
		GridBoard[16][height-2].setType(MapTile.WALL);
		GridBoard[16][height-3].setType(MapTile.WALL);
		GridBoard[16][height-4].setType(MapTile.WALL);
		//Second:
		GridBoard[18][height-2].setType(MapTile.WALL);
		GridBoard[18][height-3].setType(MapTile.WALL);
		GridBoard[18][height-4].setType(MapTile.WALL);
		GridBoard[18][height-5].setType(MapTile.WALL);
		
		//Random Surprise:
		GridBoard[20][height-6].setType(MapTile.SURPRISE); 
		
		//Another wall:
		GridBoard[22][height-2].setType(MapTile.WALL);
		GridBoard[22][height-3].setType(MapTile.WALL);
		GridBoard[22][height-4].setType(MapTile.WALL);
		GridBoard[22][height-5].setType(MapTile.WALL);
		
		//Pitfall!
		GridBoard[26][height-1].setType(MapTile.BACKGROUND);
		GridBoard[27][height-1].setType(MapTile.BACKGROUND);
		
		GridBoard[28][height-5].setType(MapTile.BRICK);
		GridBoard[29][height-5].setType(MapTile.BRICK);
		GridBoard[30][height-5].setType(MapTile.BRICK);
		GridBoard[31][height-5].setType(MapTile.BRICK);
		
		GridBoard[29][height-8].setType(MapTile.BRICK);
		GridBoard[30][height-8].setType(MapTile.BRICK);
		//coins above:
		GridBoard[29][height-11].setType(MapTile.COINS);
		GridBoard[30][height-11].setType(MapTile.COINS);
		//more coins later:
		GridBoard[34][height-5].setType(MapTile.COINS);
		GridBoard[34][height-6].setType(MapTile.COINS);
		GridBoard[34][height-7].setType(MapTile.COINS);
		GridBoard[34][height-8].setType(MapTile.COINS);
		GridBoard[35][height-5].setType(MapTile.COINS);
		GridBoard[35][height-6].setType(MapTile.COINS);
		GridBoard[35][height-7].setType(MapTile.COINS);
		GridBoard[35][height-8].setType(MapTile.COINS);
		
		//A tunnel like thing:
		GridBoard[37][height-11].setType(MapTile.WALL);
		GridBoard[37][height-10].setType(MapTile.WALL);
		GridBoard[37][height-9].setType(MapTile.WALL);
		GridBoard[37][height-2].setType(MapTile.WALL);
		GridBoard[37][height-3].setType(MapTile.WALL);
		
		GridBoard[38][height-11].setType(MapTile.WALL);
		GridBoard[38][height-10].setType(MapTile.WALL);
		GridBoard[38][height-9].setType(MapTile.WALL);
		GridBoard[38][height-2].setType(MapTile.WALL);
		GridBoard[38][height-3].setType(MapTile.WALL);
		
		GridBoard[39][height-11].setType(MapTile.WALL);
		GridBoard[39][height-10].setType(MapTile.WALL);
		GridBoard[39][height-9].setType(MapTile.WALL);
		GridBoard[39][height-2].setType(MapTile.WALL);
		GridBoard[39][height-3].setType(MapTile.WALL);
		
		GridBoard[40][height-11].setType(MapTile.WALL);
		GridBoard[40][height-10].setType(MapTile.WALL);
		GridBoard[40][height-9].setType(MapTile.WALL);
		GridBoard[40][height-2].setType(MapTile.WALL);
		GridBoard[40][height-3].setType(MapTile.WALL);
		
		GridBoard[41][height-11].setType(MapTile.WALL);
		GridBoard[41][height-10].setType(MapTile.WALL);
		GridBoard[41][height-9].setType(MapTile.WALL);
		GridBoard[41][height-2].setType(MapTile.WALL);
		GridBoard[41][height-3].setType(MapTile.WALL);
		
		GridBoard[42][height-11].setType(MapTile.WALL);
		GridBoard[42][height-10].setType(MapTile.WALL);
		GridBoard[42][height-9].setType(MapTile.WALL);
		GridBoard[42][height-2].setType(MapTile.WALL);
		GridBoard[42][height-3].setType(MapTile.WALL);
		//End of tunnel
		
		//Coins
		GridBoard[47][height-4].setType(MapTile.COINS);
		GridBoard[47][height-3].setType(MapTile.COINS);
		GridBoard[48][height-4].setType(MapTile.COINS);
		GridBoard[48][height-3].setType(MapTile.COINS);
		
		GridBoard[50][height-4].setType(MapTile.COINS);
		GridBoard[50][height-3].setType(MapTile.COINS);
		GridBoard[51][height-4].setType(MapTile.COINS);
		GridBoard[51][height-3].setType(MapTile.COINS);
		
		//PITFALL!
		GridBoard[55][height-1].setType(MapTile.BACKGROUND);
		GridBoard[56][height-1].setType(MapTile.BACKGROUND);
		GridBoard[57][height-1].setType(MapTile.BACKGROUND);
		GridBoard[58][height-1].setType(MapTile.BACKGROUND);
		GridBoard[59][height-1].setType(MapTile.BACKGROUND);
		GridBoard[60][height-1].setType(MapTile.BACKGROUND);
		//a way to cross it:
		GridBoard[55][height-4].setType(MapTile.BRICK);
		GridBoard[56][height-4].setType(MapTile.BRICK);
		GridBoard[57][height-4].setType(MapTile.BRICK);
		GridBoard[58][height-4].setType(MapTile.BRICK);
		GridBoard[59][height-4].setType(MapTile.BRICK);
		GridBoard[60][height-4].setType(MapTile.BRICK);
		
		GridBoard[62][height-7].setType(MapTile.BRICK);
		GridBoard[63][height-7].setType(MapTile.BRICK);
		GridBoard[64][height-7].setType(MapTile.BRICK);
		GridBoard[65][height-7].setType(MapTile.BRICK);
		//TODO: possible "flowers"?
		GridBoard[67][height-7].setType(MapTile.WALL);
		GridBoard[68][height-7].setType(MapTile.WALL);
		GridBoard[69][height-7].setType(MapTile.WALL);
		GridBoard[70][height-7].setType(MapTile.WALL);
		//put coins here instead for now:
		GridBoard[71][height-8].setType(MapTile.COINS);
		GridBoard[71][height-9].setType(MapTile.COINS);
		
		//Ending surprises:
		GridBoard[73][height-4].setType(MapTile.SURPRISE);
		GridBoard[74][height-4].setType(MapTile.SURPRISE);
		GridBoard[75][height-4].setType(MapTile.SURPRISE);
		
		//End of level:
		GridBoard[width-1][height-2].setType(MapTile.BACKGROUND);
		GridBoard[width-1][height-3].setType(MapTile.BACKGROUND);
	}
	
	/** Big, ugly method that no one should see.
	 *  Sets the unique to map walls and special
	 *  blocks for level 2: */
	private void setLevel2()
	{
		//Walls to climb with coins on top:
		GridBoard[4][height-3].setType(MapTile.WALL);
		GridBoard[4][height-4].setType(MapTile.COINS);
		GridBoard[6][height-5].setType(MapTile.WALL);
		GridBoard[6][height-6].setType(MapTile.COINS);
		GridBoard[8][height-7].setType(MapTile.WALL);
		GridBoard[9][height-7].setType(MapTile.WALL);
		GridBoard[8][height-8].setType(MapTile.COINS);
		//Surprises to get:
		GridBoard[6][height-10].setType(MapTile.SURPRISE);
		GridBoard[8][height-10].setType(MapTile.SURPRISE);
		
		//PITFALL:
		GridBoard[11][height-1].setType(MapTile.BACKGROUND);
		GridBoard[12][height-1].setType(MapTile.BACKGROUND);
		
		GridBoard[15][height-4].setType(MapTile.BRICK);
		GridBoard[16][height-4].setType(MapTile.BRICK);
		GridBoard[17][height-4].setType(MapTile.BRICK);
		GridBoard[18][height-4].setType(MapTile.BRICK);
		GridBoard[19][height-4].setType(MapTile.SURPRISE);
		GridBoard[20][height-4].setType(MapTile.SURPRISE);
		GridBoard[21][height-4].setType(MapTile.SURPRISE);
		GridBoard[22][height-4].setType(MapTile.BRICK);
		
		//A new kind of pitfall:
		GridBoard[25][height-2].setType(MapTile.WALL);
		GridBoard[26][height-2].setType(MapTile.WALL);
		GridBoard[27][height-2].setType(MapTile.WALL);
		GridBoard[28][height-2].setType(MapTile.WALL);
		GridBoard[29][height-2].setType(MapTile.WALL);
		GridBoard[30][height-2].setType(MapTile.WALL);
		
		GridBoard[26][height-3].setType(MapTile.WALL);
		GridBoard[27][height-3].setType(MapTile.WALL);
		GridBoard[28][height-3].setType(MapTile.WALL);
		GridBoard[29][height-3].setType(MapTile.WALL);
		GridBoard[30][height-3].setType(MapTile.WALL);
		
		GridBoard[27][height-4].setType(MapTile.WALL);
		GridBoard[28][height-4].setType(MapTile.WALL);
		GridBoard[29][height-4].setType(MapTile.WALL);
		GridBoard[30][height-4].setType(MapTile.WALL);
		
		GridBoard[28][height-5].setType(MapTile.WALL);
		GridBoard[29][height-5].setType(MapTile.WALL);
		GridBoard[30][height-5].setType(MapTile.WALL);
		
		GridBoard[29][height-6].setType(MapTile.WALL);
		GridBoard[30][height-6].setType(MapTile.WALL);
		
		GridBoard[30][height-7].setType(MapTile.WALL);
		
		//the fall:
		for(int x=31; x<65; x++)
		{
			GridBoard[x][height-1].setType(MapTile.BACKGROUND);
		}
		//The runway is now a little higher:
		GridBoard[33][height-7].setType(MapTile.WALL);
		GridBoard[34][height-7].setType(MapTile.WALL);
		GridBoard[35][height-7].setType(MapTile.WALL);
		GridBoard[36][height-7].setType(MapTile.WALL);
		
		GridBoard[39][height-6].setType(MapTile.WALL);
		GridBoard[40][height-6].setType(MapTile.WALL);
		GridBoard[41][height-6].setType(MapTile.WALL);
		
		GridBoard[44][height-7].setType(MapTile.WALL);
		GridBoard[45][height-7].setType(MapTile.WALL);
		GridBoard[46][height-7].setType(MapTile.WALL);
		GridBoard[47][height-7].setType(MapTile.WALL);
		GridBoard[48][height-7].setType(MapTile.WALL);
		GridBoard[49][height-7].setType(MapTile.WALL);
		GridBoard[50][height-7].setType(MapTile.WALL);
		
		GridBoard[46][height-10].setType(MapTile.SURPRISE);
		GridBoard[47][height-10].setType(MapTile.WALL);
		GridBoard[48][height-10].setType(MapTile.WALL);
		GridBoard[49][height-10].setType(MapTile.WALL);
		
		GridBoard[54][height-4].setType(MapTile.WALL);
		GridBoard[55][height-4].setType(MapTile.WALL);
		GridBoard[56][height-4].setType(MapTile.WALL);
		
		GridBoard[58][height-2].setType(MapTile.WALL);
		GridBoard[59][height-2].setType(MapTile.WALL);
		GridBoard[60][height-2].setType(MapTile.WALL);
		GridBoard[61][height-2].setType(MapTile.WALL);
		GridBoard[62][height-2].setType(MapTile.WALL);
		
		GridBoard[60][height-6].setType(MapTile.SURPRISE);
		GridBoard[61][height-6].setType(MapTile.SURPRISE);
		
		//Last surprises:
		GridBoard[70][height-5].setType(MapTile.SURPRISE);
		GridBoard[71][height-5].setType(MapTile.SURPRISE);
		GridBoard[72][height-5].setType(MapTile.SURPRISE);
		
		GridBoard[70][height-8].setType(MapTile.COINS);
		GridBoard[71][height-8].setType(MapTile.COINS);
		GridBoard[72][height-8].setType(MapTile.COINS);
		
		//End of level:
		GridBoard[width-1][height-2].setType(MapTile.BACKGROUND);
		GridBoard[width-1][height-3].setType(MapTile.BACKGROUND);
		
	}
	
	// Accession methods
	public static int getWidth() { return width; }
	public static int getDWidth() { return displayW; }
	public static int getHeight() { return height; }
	public static Paint getCoin()		{ return coins;		 }
	public static MapTile[][] getGridBoard() { return GridBoard; }
	public static Bitmap getTest() { return test; }
	
	
}

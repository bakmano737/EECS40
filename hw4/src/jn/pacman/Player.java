package jn.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.KeyEvent;

public class Player 
{
	private int goal;
	
	//Player's current direction, tile, img, and rect:
	private int currDirection;
	private GridPiece tile;
	private static Bitmap playerImg;
	private Rect playerRect;
	
	//Different imgs for player's animation:
	private static Bitmap openR;
	private static Bitmap openL;
	private static Bitmap openU;
	private static Bitmap openD;
	private static Bitmap closed;
	
	private boolean isClosed;
	
	private int countSpecialMode;
	/** Constructor gets passed a GridPiece
	 * in which player starts */
	public Player(GridPiece p) 
	{
		tile = p;
		setDirection(0);
		goal=0;
		countSpecialMode = 100; //100 turns for special mode default
		
		//Pacman starts with open mouth to the right:
		isClosed = false;
		playerImg = openR;
		
		playerRect = new Rect();
		playerRect.set(tile.getRectangle().left + GridPiece.getSize()/5, tile.getRectangle().top + GridPiece.getSize()/5, 
				   tile.getRectangle().right - GridPiece.getSize()/5, tile.getRectangle().bottom - GridPiece.getSize()/5);
	}
	
	public void drawPlayer(Canvas c)
	{
		if(isClosed) playerImg = closed;
		c.drawBitmap(playerImg, null, playerRect, null);
	}
	
	public synchronized void translate() 
	{
		if(Pacman.isPacmanVicious())
		{
			countSpecialMode--;
			if(countSpecialMode==0)
			{
				Pacman.specialModeOver();
				countSpecialMode = 100; //reset counter
			}
		}
		
		//"Eat" the seed:
		if(tile.getBitmap() != null) 
		{
			if(tile.getBitmap() == PacmanGrid.getBigSeed()) Pacman.pacmanAteBigSeed();
			else Pacman.incSeeds();
			tile.setBitmap(null);
		}
		
		//change current direction to goal if there's no wall in the way
		switch(goal)
		{
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (tile.getRight()!=null && playerRect.top > tile.getRectangle().top
					&& playerRect.bottom < tile.getRectangle().bottom) 
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (tile.getLeft()!=null && playerRect.top > tile.getRectangle().top
					&& playerRect.bottom < tile.getRectangle().bottom)
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_UP:
				if (tile.getUpper()!=null && playerRect.right < tile.getRectangle().right
					&& playerRect.left > tile.getRectangle().left) 
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if (tile.getLower()!=null && playerRect.right < tile.getRectangle().right
					&& playerRect.left > tile.getRectangle().left) 
					setDirection(goal);
				break;	
		}
		
		//move player if possible
		switch(currDirection) 
		{
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				playerImg = openR;
				if (tile.getRight()!=null)
				{
					playerRect.offset(GridPiece.getSize()/5, 0);
					isClosed = !isClosed;
					if(playerRect.right + GridPiece.getSize()/5 >= tile.getRight().getRectangle().right) tile = tile.getRight();
				}
				
				//Specific condition for tunnel:
				else if(tile.getX() == 18 && tile.getY() == 10)
				{
					tile = PacmanGrid.getGridBoard()[0][10];
					playerRect.set(tile.getLcoord() + GridPiece.getSize()/5, tile.getUcoord() + GridPiece.getSize()/5, 
							   tile.getRcoord() - GridPiece.getSize()/5, tile.getDcoord() - GridPiece.getSize()/5);
				}
				break;	
			case KeyEvent.KEYCODE_DPAD_LEFT:
				playerImg = openL;
				if (tile.getLeft()!=null)
				{
					playerRect.offset(-GridPiece.getSize()/5, 0);
					isClosed = !isClosed;
					if(playerRect.left - GridPiece.getSize()/5 <= tile.getLeft().getRectangle().left) tile = tile.getLeft(); 
				}
				
				//Specific condition for tunnel:
				else if(tile.getX() == 0 && tile.getY() == 10)
				{
					tile = PacmanGrid.getGridBoard()[18][10];
					playerRect.set(tile.getLcoord() + GridPiece.getSize()/5, tile.getUcoord() + GridPiece.getSize()/5, 
							   tile.getRcoord() - GridPiece.getSize()/5, tile.getDcoord() - GridPiece.getSize()/5);
				}
				break;	
					
			case KeyEvent.KEYCODE_DPAD_UP:
				playerImg = openU;
				if (tile.getUpper()!=null)
				{
					playerRect.offset(0, -GridPiece.getSize()/5);
					isClosed = !isClosed;
					if(playerRect.top - GridPiece.getSize()/5 <= tile.getUpper().getRectangle().top ) tile = tile.getUpper(); 
				}
				break;		
			case KeyEvent.KEYCODE_DPAD_DOWN:
				playerImg = openD;
				if (tile.getLower()!=null)
				{
					playerRect.offset(0, GridPiece.getSize()/5);
					isClosed = !isClosed;
					if( playerRect.bottom + GridPiece.getSize()/5 >= tile.getLower().getRectangle().bottom ) tile = tile.getLower();  
				}
				break;	
		}
	}
	
	public void setGoal(int keyCode) { goal = keyCode;}
	
	//Set the different bitmaps:
	public static void setOpenR(Bitmap b)	{ openR = b;	}
	public static void setOpenL(Bitmap b)	{ openL = b;	}
	public static void setOpenD(Bitmap b)	{ openD = b;	}
	public static void setOpenU(Bitmap b)	{ openU = b;	}
	public static void setClosed(Bitmap b)	{ closed = b;	}
	
	//get open img for displaying lives:
	public static Bitmap getOpenR()   { return openR; } 

	public int getDirection() { return currDirection; }

	public void setDirection(int d) { this.currDirection = d; }

	public GridPiece getTile() {return tile; }

	public void setTile(GridPiece tile) { this.tile = tile;}
}
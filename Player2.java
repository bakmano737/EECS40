package jn.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.KeyEvent;

public class Player 
{
	private int goal;
	private int currDirection;
	private GridPiece tile;
	private static Bitmap playerImg;
	private Rect playerRect;
	
	/** Constructor gets passed a GridPiece
	 * in which player starts */
	public Player(GridPiece p) 
	{
		tile = p;
		setDirection(0);
		goal=0;
		playerRect = new Rect();
		playerRect.set(tile.getRectangle().left + GridPiece.getSize()/5, tile.getRectangle().top + GridPiece.getSize()/5, 
				   tile.getRectangle().right - GridPiece.getSize()/5, tile.getRectangle().bottom - GridPiece.getSize()/5);
	}
	
	public void drawPlayer(Canvas c)
	{
		c.drawBitmap(playerImg, null, playerRect, null);
	}
	
	public synchronized void translate() 
	{
		//"Eat" the seed:
		if(tile.getBitmap() != null) tile.setBitmap(null);
		
		//change current direction to goal if there's no wall in the way
		switch(goal) 
		{
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (tile.getRight()!=null ) 
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (tile.getLeft()!=null ) 
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_UP:
				if (tile.getUpper()!=null ) 
					setDirection(goal);
				break;	
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if (tile.getLower()!=null ) 
					setDirection(goal);
				break;	
		}
		
		//move player if possible
		switch(currDirection) 
		{
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (tile.getRight()!=null)
				{
					playerRect.offset(GridPiece.getSize()/5, 0);
					if( (playerRect.left) == (tile.getRectangle().right + GridPiece.getSize()/5)) tile = tile.getRight(); 
				}
				break;	
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (tile.getLeft()!=null)
				{
					playerRect.offset(-GridPiece.getSize()/5, 0);
					if( (playerRect.right) == (tile.getRectangle().left - GridPiece.getSize()/5)) tile = tile.getLeft(); 
				}
				break;	
					
			case KeyEvent.KEYCODE_DPAD_UP:
				if (tile.getUpper()!=null)
				{
					playerRect.offset(0, -GridPiece.getSize()/5);
					if( (playerRect.bottom) == (tile.getRectangle().top - GridPiece.getSize()/5)) tile = tile.getUpper(); 
				}
				break;		
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if (tile.getLower()!=null)
				{
					playerRect.offset(0, GridPiece.getSize()/5);
					if( (playerRect.top) == (tile.getRectangle().bottom + GridPiece.getSize()/5)) tile = tile.getLower(); 
				}
				break;	
		}
	}
	
	public void setGoal(int keyCode) { goal = keyCode;}
		
	public static void setBitmap(Bitmap b) { playerImg = b; }

	public int getDirection() { return currDirection; }

	public void setDirection(int d) { this.currDirection = d; }
}
package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class MapTile 
{
	//These are constants, hence they can
	//be public and remain safe:
	//Will be adding more types later for special boxes and such
	public static final int BACKGROUND = 0;
	public static final int WALL = 1;
	//little blocks that can be destroyed in the real game:
	public static final int BRICK = 2;
	//question mark boxes:
	public static final int SURPRISE = 3;
	//coins:
	public static final int COINS = 4;
	//enemy. If the player touches this he dies
	public static final int ENEMY = 5;
	
	//different bitmaps, same for all tiles:
	public static Bitmap Wall;
	public static Bitmap Brick;
	public static Bitmap Surprise;
	
	private static int size;
	
	// Rectangle for drawing
	private Rect R;
	private Bitmap activeBMP;

	
	//describes the type of the tile:
	private int type;
	//boolean indicating if an enemy covers this tile
	private boolean enemy;
	private boolean blocked;
	private boolean player;
		
	// Position variables for the rectangle
	private int left;
	private int right;
	private int top;
	private int bottom;
	
	// Index of grid position,
	// x is column and y is row
	private int x;
	private int y;
	
	/** 
	 * Constructor. Takes in coordinates of where to set the grid rectangle.
	 *  This depends on canvas size 
	 *  @param x - the x position
	 *  @param y - the y position
	 *  @param tileType - the type to set the tile
	 */
	public MapTile(int x, int y, int tileType)
	{
		this.setX(x);
		this.setY(y);
		type = tileType;
		
		if( tileType == WALL || tileType == BRICK || tileType == SURPRISE )
		{
			blocked = true;
			enemy = false;
		}
		else if( tileType == ENEMY )
		{
			blocked = false;
			enemy = true;
		}
		else
		{
			blocked = false;
			enemy = false;
		}
			
		// Initialize the position variables based on the given parameters
		left = x*size;
		top = y*size;
		right = x*size + size;
		bottom = y*size + size;
			
		// Create a rectangle and set it to the gridPiece's position
		R = new Rect();
		R.set(left, top, right, bottom);
	}
	
	/** 
	 * setType - set the type and any indicators
	 * based on the given type
	 * @param the type to set 
	 */
	public void setType(int t) 
	{ 
		type = t;
		
		if( type == WALL || type == BRICK || type == SURPRISE )
		{
			blocked = true;
			enemy = false;
		}
		else if( type == ENEMY )
		{
			blocked = false;
			enemy = true;
		}
		else
		{
			blocked = false;
			enemy = false;
		}
	}
	
	/**
	 *  draw - if the type is backgroud, draw nothing
	 *  	if the type is wall, draw the wall image
	 * @param c - the canvas on which to draw the image
	 */
	public void draw(Canvas c) 
	{
		switch(type)
		{
			//draw nothing for the black background:
			case BACKGROUND:
				break; 
				
			case WALL:
				c.drawBitmap(Wall, null, R, null);
				break;
			
			case BRICK:
				c.drawBitmap(Brick, null, R, null);
				break;
				
			case SURPRISE:
				c.drawBitmap(Surprise, null, R, null);
				break;
				
			case COINS:
				c.drawCircle(R.exactCenterX(), R.exactCenterY(), size/3, Map.getCoin());
				break;
		}
	}
	
	/** getType - get the type @return the type */
	public int getType() { return type; }
		
	/** setBitmap - set the bitmap @param the bitmap to set */
	public void setBitmap(Bitmap bmp) { this.activeBMP = bmp; }
		
	/** getBitmap - get the bitmap @return the bitmap of the tile */
	public Bitmap getBitmap() { return activeBMP; }

	/** getSize - get the size of the tile @return the size of the individual piece */
	public static int getSize() { return size; }
	
	/** setSize - set the size of the tile @param the size to set */
	public static void setSize(int s) { size = s; }
	
	/** getRect - get the tile Rect @return the rect */
	public Rect getRect() { return R; }

	/** getX - get the x position @return the x */
	public int getX() { return x; }

	/** setX - set the x position @param x the x to set */
	public void setX(int x) { this.x = x; }

	/** getY - get the y position @return the y */
	public int getY() { return y; }

	/** setY - set the y position @param y the y to set */
	public void setY(int y) { this.y = y; }
	
	/** Set the Wall bitmap */
	public static void setWall(Bitmap b) 	{ Wall = b; }
	/** Set the Brick bitmap */
	public static void setBrick(Bitmap b) 	{ Brick = b; }
	/** Set the Surprise bitmap */
	public static void setSurprise(Bitmap b){ Surprise = b; }

	/** isEnemy - check enemy field @return the enemy */
	public boolean isEnemy() { return enemy; }

	/** setEnemy - set the enemy indicator @param enemy the enemy to set */
	public void setEnemy(boolean enemy) { this.enemy = enemy; }

	/** isBlocked - check the blocked property @return blocked */
	public boolean isBlocked() { return blocked; }

	/** setBloxked - set the blocked property @param blocked the blocked to set */
	public void setBlocked(boolean blocked) { this.blocked = blocked; }

	/** isPlayer - check if the tile has the player @return the player */
	public boolean isPlayer() { return player; }

	/** setPlayer - set the player propety @param player the player to set */
	public void setPlayer(boolean player) { this.player = player; }

}

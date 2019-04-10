
package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Obstacle 
{
	// Obstacles are blocks that mario has to climb over
	// They each take up one grid space
	
	// Fields
	
	// Position
	private int x, y;
	
	// Bitmap
	private Bitmap bmp;
	
	// Rectangble in which to draw obstacle
	private Rect rect;
	
	/** 
	 *  Constructor
	 *  @param x - x position of Obstacle
	 *  @param y - y position of Obstacle
	 *  @param bmp - Bitmap of Obstacle to be 
	 *  	deecoded by MarioView
	 */
	public Obstacle(int x, int y, Bitmap bmp)
	{
		this.bmp = bmp;
		this.x = x;
		this.y = y;		
	}
	
	/** 
	 *  Constructor
	 *  	null values assigned to all fields
	 */
	public Obstacle()
	{
		this.bmp = null;
		this.x = 0;
		this.y = 0;
	}

	/**
	 * @return the x
	 */
	public int getX() { return x; }

	/**
	 * @param x the x to set
	 */
	public void setX(int x) { this.x = x; }

	/**
	 * @return the y
	 */
	public int getY() { return y; }

	/**
	 * @param y the y to set
	 */
	public void setY(int y) { this.y = y; }

	/**
	 * @return the bmp
	 */
	public Bitmap getBmp() { return bmp; }

	/**
	 * @return the rect
	 */
	public Rect getRect() { return rect; }

	/**
	 * @param rect the rect to set
	 */
	public void setRect(Rect rect) { this.rect = rect; }
}

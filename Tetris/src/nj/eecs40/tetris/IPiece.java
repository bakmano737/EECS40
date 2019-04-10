package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class IPiece extends TetrisPiece 
{

	public IPiece(Resources res) 
	{
		// Call the parent constructor
		super();
		// Set the unique bitmap using the given resources
		// IPiece will be cyan
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.cyan_square));
		// Initialize to the UP position
		this.setOrientation(DISPLAY);
		this.setCoords();
	}

	@Override
	public void setCoords() 
	{
		final int yBorder = TetrisGrid.getGridBoard()[0].length;
		final int xBorder = TetrisGrid.getGridBoard().length;
		switch(this.orientation)
		{
			case DISPLAY:
			{
				this.getRectArray()[0].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY, 
										   dispOrigX,
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[1].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX,
										   dispOrigY + 2*TetrisPiece.getSize());
				this.getRectArray()[2].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY + 2*TetrisPiece.getSize(),
										   dispOrigX,
										   dispOrigY + 3*TetrisPiece.getSize());
				this.getRectArray()[3].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY + 3*TetrisPiece.getSize(),
										   dispOrigX,
										   dispOrigY + 4*TetrisPiece.getSize());
				return;
			}
			
			case UP:
			{				
				// Check for hitting the bottom of the grid
				if( yBorder > this.originY+2 && 0 < this.originY-1 )
				{
					// Set the yCoords to their prescribed positions
					for( int j = 0; j < yCoords.length; j++) 
					{
						this.yCoords[j] = originY + (j - 1);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX )
					{						
						// Set the xCoords to the right border
						this.originX = xBorder - 1;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if ( 0 > this.originX )
					{
						// Set the xCoords to the left border
						this.originX = 0;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the grid coordinates of each rectangle
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
				}
				else
				{
					// Piece has reached the bottom. Put it in its place on the grid
					this.freezePiece();
					return;
				}
			}
			case RIGHT:
			{				
				// Border checks
				if( yBorder > this.originY && 0 < this.originY )
				{
					// Set the yCoords to their prescribed places
					for( int j = 0; j < yCoords.length; j++) 
					{
						this.yCoords[j] = originY;
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+2 )
					{
						this.originX = xBorder - 3;
						// Set the grid coordinates of each rectangle
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else if ( 0 > this.originX-1 )
					{
						this.originX = 1;
						// Set the grid coordinates of each rectangle
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the grid coordinates of each rectangle
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
				}
				else
				{
					// Piece has reached the bottom. Put it in its place on the grid
					this.freezePiece();
					return;
				}
			}
			// DOWN is the same as UP
			case DOWN:
			{				
				// Check for hitting the bottom of the grid
				if( yBorder > this.originY+2 && 0 < this.originY-1 )
				{
					// Set the yCoords to their prescribed places
					for( int j = 0; j < yCoords.length; j++)
					{
						this.yCoords[j] = originY + (j - 1);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX )
					{
						// Set the piece to the right edge
						this.originX = xBorder - 1;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if ( 0 > this.originX )
					{
						// Set the xCoords to the left wall
						this.originX = 1;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
					}
					else
					{
						// Set the grid coordinates of each rectangle
						for( int i = 0; i < xCoords.length; i++)
						{
							this.xCoords[i] = originX;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
				}
				else
				{
					// Piece has reached the bottom. Put it in its place on the grid
					this.freezePiece();
					return;
				}
			}
			// LEFT is the same as right */
			case LEFT:
			{				
				// Border checks
				if( yBorder > this.originY && 0 < this.originY )
				{
					// Set the yCoords to their prescribed places
					for( int j = 0; j < yCoords.length; j++)
					{
						this.yCoords[j] = originY;
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+2 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 3;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else if ( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to the left edge
						for( int i = 0; i < xCoords.length; i++) 
						{
							this.xCoords[i] = originX + (i - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
				}
				else
				{
					// Piece has reached the bottom. Put it in its place on the grid
					this.freezePiece();
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
}

package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class JPiece extends TetrisPiece 
{

	public JPiece(Resources res) 
	{
		// Use the parent constructor to initialize common variables
		super();
		// Set the unique bitmap using the given resources
		// JPiece will be green
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.green_square));
		// Initialize to the UP position
		this.setOrientation(DISPLAY);
		this.setCoords();
	}
	
	@Override
	// Take in the new orientation and position the squares accordingly
	public void setCoords()
	{
		final int yBorder = TetrisGrid.getGridBoard()[0].length;
		final int xBorder = TetrisGrid.getGridBoard().length;
		switch(orientation)
		{
			case DISPLAY:
			{
				this.getRectArray()[0].set(dispOrigX, 
										   dispOrigY - TetrisPiece.getSize(),
										   dispOrigX + TetrisPiece.getSize(), 
										   dispOrigY);
				this.getRectArray()[1].set(dispOrigX,
										   dispOrigY, dispOrigX + TetrisPiece.getSize(),
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[2].set(dispOrigX,
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX + TetrisPiece.getSize(), 
										   dispOrigY + 2*TetrisPiece.getSize());
				this.getRectArray()[3].set(dispOrigX - TetrisPiece.getSize(), 
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX,
										   dispOrigY + 2*TetrisPiece.getSize());
				return;
			}
			
			case UP:
			{				
				// Border Checks
				if( yBorder > this.originY+1 && 0 < this.originY-1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++)
					{
						this.yCoords[j] = this.originY + (j - j/3 - 1);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						this.originX = xBorder-1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 3);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-1 )
					{
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - (i / 3);
						}
						this.setRects();
						this.setOrientation(orientation);
					}
					else
					{
						// Set the xCoords and yCoords
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - (i / 3);
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
				if( yBorder > this.originY && 0 < this.originY-1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY - j/3;
					}
					
					// Check for hitting or exceeding the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right border
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - ((i - i/3) - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}					
					// Check for hitting the left wall
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left border
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - ((i - i/3) - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
					}
					else
					{
						// Set the xCoords and yCoords
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - ((i - i/3) - 1);
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
			case DOWN:
			{				
				// Border checks
				if( yBorder > this.originY+1 && 0 < this.originY-1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY - (j - j/3 - 1);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the left border
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + i/3;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX )
					{
						// Set the xCoords to the left border
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + i/3;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords and yCoords
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + i/3;
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
			case LEFT:
			{				
				// Border checks
				if( yBorder > this.originY+1 && 0 < this.originY )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + j/3;
					}
					
					// Check for hitting right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the left border
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i - i/3 - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting left wall
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left border
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i - i/3 - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords and yCoords
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i - i/3 - 1);
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

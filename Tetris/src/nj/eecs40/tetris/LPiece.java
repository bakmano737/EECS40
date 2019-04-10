package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class LPiece extends TetrisPiece 
{

	public LPiece(Resources res) 
	{
		// Use the parent constructor to initialize common variables
		super();
		// Set the unique bitmap using the given resources
		// LPiece will be orange
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.orange_square));
		// Initialize to the UP position
		this.setOrientation(DISPLAY);
		this.setCoords();
	}
	
	@Override
	public void setCoords()
	{
		final int yBorder = TetrisGrid.getGridBoard()[0].length;
		final int xBorder = TetrisGrid.getGridBoard().length;
		switch(orientation)
		{
			case DISPLAY:
			{
				this.getRectArray()[0].set(dispOrigX - TetrisPiece.getSize(), 
										   dispOrigY - TetrisPiece.getSize(),
										   dispOrigX, 
										   dispOrigY);
				this.getRectArray()[1].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY, dispOrigX,
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[2].set(dispOrigX - TetrisPiece.getSize(),
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX, 
										   dispOrigY + 2*TetrisPiece.getSize());
				this.getRectArray()[3].set(dispOrigX, 
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX + TetrisPiece.getSize(),
										   dispOrigY + 2*TetrisPiece.getSize());
				return;
			}
			
			case UP:
			{				
				// Border Checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++)
					{
						this.yCoords[j] = this.originY + (j - j/3 - 1);
					}
					
					// Check the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the piece to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + i/3;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}					
					// Check the left wall
					else if( 0 > this.originX )
					{
						// Set the piece to the left edge
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
			case RIGHT:
			{				
				// Border Checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to the prescribed places
					for(int j = 0; j < this.yCoords.length; j++)
					{
						this.yCoords[j] = this.originY + j/3;
					}
					
					// Check the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - (i - i/3 - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check the left wall
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - (i - i/3 - 1);
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
							this.xCoords[i] = this.originX - (i - i/3 - 1);
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
				// Border Checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed coords
					for(int j = 0; j < this.yCoords.length; j++)
					{
						this.yCoords[j] = this.originY - (j - j/3 - 1);
					}
					
					// Check the right wall
					if( xBorder <= this.originX )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - i/3;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check the left wall
					else if(0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - i/3;
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to their prescribed coords
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX - i/3;
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
				
				// Border Checks
				if( yBorder > this.originY )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY - j/3;
					}
					// Check the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i - i/3 - 1);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check the left wall
					else if(0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
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

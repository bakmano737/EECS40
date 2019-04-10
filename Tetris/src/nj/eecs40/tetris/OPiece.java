package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class OPiece extends TetrisPiece 
{

	public OPiece(Resources res) 
	{
		// Use the parent constructor to initialize common variables
		super();
		// Set the unique bitmap using the given resources
		// OPiece will be blue
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.blue_square));
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
										   dispOrigY, dispOrigX, 
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[1].set(dispOrigX, dispOrigY, 
										   dispOrigX + TetrisPiece.getSize(),
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
				// Set the xCoords and yCoords
				
				// Border checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j / 2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to their prescribed places
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
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
				// Check for hitting the bottom wall
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j / 2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to their prescribed places
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
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
				// Check for hitting the bottom wall
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j / 2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to their prescribed places
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
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
				// Check for hitting the bottom wall
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j / 2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else
					{
						// Set the xCoords to their prescribed places
						for(int i = 0; i < this.xCoords.length; i++)
						{
							this.xCoords[i] = this.originX + (i % 2);
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

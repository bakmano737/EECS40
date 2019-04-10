package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class ZPiece extends TetrisPiece 
{

	public ZPiece(Resources res) 
	{
		// Use the parent constructor to initialize common variables
		super();
		// Set the unique bitmap using the given resources
		// ZPiece will be yellow
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.yellow_square));
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
										   dispOrigY, 
										   dispOrigX, 
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[1].set(dispOrigX, dispOrigY, 
										   dispOrigX + TetrisPiece.getSize(),
										   dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[2].set(dispOrigX, 
						   				   dispOrigY + TetrisPiece.getSize(), 
						   				   dispOrigX + TetrisPiece.getSize(), 
						   				   dispOrigY + 2*TetrisPiece.getSize());
				this.getRectArray()[3].set(dispOrigX + TetrisPiece.getSize(), 
										   dispOrigY + TetrisPiece.getSize(),
										   dispOrigX + 2*TetrisPiece.getSize(),
										   dispOrigY + 2*TetrisPiece.getSize());
				return;
			}			
			case UP:
			{
				// Border checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j/2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
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
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
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
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + ((j-1) - (j/2));
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 2);
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
							this.xCoords[i] = this.originX - (i / 2);
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
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + (j/2);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
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
							this.xCoords[i] = this.originX + ((i-1) - (i/2));
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
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length; j++) 
					{
						this.yCoords[j] = this.originY + ((j-1) - (j/2));
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX )
					{
						// Set the xCoords to the right edge
						this.originX = xBorder - 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 2);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					else if( 0 > this.originX-1 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 2);
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
							this.xCoords[i] = this.originX - (i / 2);
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

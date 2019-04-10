package nj.eecs40.tetris;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class TPiece extends TetrisPiece 
{

	public TPiece(Resources res) 
	{
		// Use the parent constructor to initialize common variables
		super();
		originX=TetrisGrid.getCols()/2;
		originY=1;
		// Set the unique bitmap using the given resources
		// TPiece will be purple
		setBitmap(BitmapFactory.decodeResource(res, R.drawable.purple_square));
		// Initialize to display position
		this.setOrientation(DISPLAY);
		this.setCoords();
		this.settled = false;
		// Set the grid position to top and middle
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
				this.getRectArray()[0].set(dispOrigX - 2*TetrisPiece.getSize(), 
											dispOrigY - TetrisPiece.getSize(), 
											dispOrigX - TetrisPiece.getSize(),
											dispOrigY);
				this.getRectArray()[1].set(dispOrigX - TetrisPiece.getSize(),
											dispOrigY - TetrisPiece.getSize(),
											dispOrigX, dispOrigY);
				this.getRectArray()[2].set(dispOrigX - TetrisPiece.getSize(),
											dispOrigY, dispOrigX, 
											dispOrigY + TetrisPiece.getSize());
				this.getRectArray()[3].set(dispOrigX, dispOrigY - TetrisPiece.getSize(), 
										   dispOrigX + TetrisPiece.getSize(), dispOrigY);
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
						this.yCoords[j] = this.originY + (j / 3);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the left edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (((i%2)-1) + ((i%3)*(i/2)));
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
							this.xCoords[i] = this.originX + (((i%2)-1) + ((i%3)*(i/2)));
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
							this.xCoords[i] = this.originX + (((i%2)-1) + ((i%3)*(i/2)));
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
					for(int j = 0; j < this.yCoords.length;   j++) 
					{
						this.yCoords[j] = this.originY + (((j%2)-1) + ((j%3)*(j/2)));
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the left edge
						this.originX = xBorder - 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 3);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-1  )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (i / 3);
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
			case DOWN:
			{
				// Border checks
				if( yBorder > this.originY+1 )
				{
					// Set the yCoords to their prescribed places
					for(int j = 0; j < this.yCoords.length;   j++) 
					{
						this.yCoords[j] = this.originY - (j / 3);
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+2 )
					{
						// Set the xCoords to the left edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (((i%2)-1) + ((i%3)*(i/2)));
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-2 )
					{
						// Set the xCoords to the left edge
						this.originX = 1;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX - (((i%2)-1) + ((i%3)*(i/2)));
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
							this.xCoords[i] = this.originX - (((i%2)-1) + ((i%3)*(i/2)));
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
					for(int j = 0; j < this.yCoords.length;   j++) 
					{
						this.yCoords[j] = this.originY - (((j%2)-1) + ((j%3)*(j/2)));
					}
					
					// Check for hitting the right wall
					if( xBorder <= this.originX+1 )
					{
						// Set the xCoords to the left edge
						this.originX = xBorder - 2;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i / 3);
						}
						this.setRects();
						this.setOrientation(orientation);
						return;
					}
					// Check for hitting the left wall
					else if( 0 > this.originX-1  )
					{
						// Set the xCoords to the left edge
						this.originX = 0;
						for(int i = 0; i < this.xCoords.length; i++) 
						{
							this.xCoords[i] = this.originX + (i / 3);
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
							this.xCoords[i] = this.originX + (i / 3);
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

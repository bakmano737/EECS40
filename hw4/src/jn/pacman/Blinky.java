package jn.pacman;

import android.graphics.Bitmap;

/**
 * Blinky is the red ghost and, according to wikipedia, is the "chaser".
 * In other words, Blinky will try to follow pacman
 */
public class Blinky extends Ghost 
{
	public Blinky(Bitmap bmp, Player player, int x, int y, int speed)
	{
		super(player, x, y, speed);
		this.setBmp(bmp);
	}
	
	/* (non-Javadoc)
	 * @see jn.pacman.Ghost#translate()
	 */
	@Override
	public synchronized void translate() 
	{
		super.translate();
		if(outOfGame) return;
		if( this.isStuck() )
		{
			// If the stuck condition is true, we need to move in the set direction
			// until the appropriate perpendicular movement is made
			
			// Get the horizontal and vertical distances between pacman and ghost
			int dx = this.getPlayer().getTile().getRectangle().right - this.getRect().right;
			int dy = this.getPlayer().getTile().getRectangle().top - this.getRect().top;
			
			switch(this.getDirection())
			{
				case LEFT:
				{
					// Try to move in the appropriate vertical direction
					if( dy < 0 )
					{
						// Pacman is above ghost. Try to move UP
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so perform it
							this.getRect().offset(0, -getSpeed());
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
							{
								this.setTile(this.getTile().getUpper());
							}
							return;
						}
					}
					else
					{
						// Pacman is below ghost. Try to move DOWN
						if( this.getTile().getLower() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so perform it
							this.getRect().offset(0, getSpeed());
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
							{
								this.setTile(this.getTile().getLower());
							}
							return;
						}
						
					}
					// The desired vertical movement is invalid
					// Move in the current direction
					// Check that the movement is valid.
					if( this.getTile().getLeft() != null && 
						this.getRect().top >= this.getTile().getRectangle().top &&
						this.getRect().bottom <= this.getTile().getRectangle().bottom )
					{
						// The movement is valid so perform it
						this.getRect().offset(-getSpeed(), 0);
						
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
						{
							this.setTile(this.getTile().getLeft());
						}
						return;
					}
					// The set movement is invalid
					// Try to move in the direction opposite of the desired
					// vertical direction
					if( dy < 0 )
					{
						// Pacman is above ghost. Try to move DOWN
						if( this.getTile().getLower() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, getSpeed());
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
							{
								this.setTile(this.getTile().getLower());
							}
							return;
						}
					}
					else
					{
						// Pacman is below ghost. Try to move UP
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, -getSpeed());
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
							{
								this.setTile(this.getTile().getUpper());
							}
							return;
						}
						
					}
				}
				case RIGHT:
				{
					// Try to move in the appropriate vertical direction
					if( dy < 0 )
					{
						// Pacman is above ghost. Try to move up
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, -getSpeed());
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
							{
								this.setTile(this.getTile().getUpper());
							}
							return;
						}
					}
					else
					{
						// Pacman is below ghost. Try to move down
						if( this.getTile().getLower() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, getSpeed());
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
							{
								this.setTile(this.getTile().getLower());
							}
							return;
						}
						
					}
					// The desired vertical movement is invalid
					// Move in the current direction
					// Check that the movement is valid.
					if( this.getTile().getRight() != null && 
						this.getRect().top >= this.getTile().getRectangle().top &&
						this.getRect().bottom <= this.getTile().getRectangle().bottom )
					{
						// The movement is valid so prepare it
						this.getRect().offset(getSpeed(), 0);
						
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getRight().getRectangle().right == this.getRect().right )
						{

							this.setTile(this.getTile().getRight());
						}
						return;
					}
					// The set movement is invalid
					// Try to move in the direction opposite of the desired
					// vertical direction
					if( dy < 0 )
					{
						// Pacman is above ghost. Try to move DOWN
						if( this.getTile().getLower() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, getSpeed());
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
							{
								this.setTile(this.getTile().getLower());
							}
							return;
						}
					}
					else
					{
						// Pacman is below ghost. Try to move UP
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The movement is valid so prepare it
							this.getRect().offset(0, -getSpeed());
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
							{
								this.setTile(this.getTile().getUpper());
							}
							return;
						}
						
					}
				}
				case UP:
				{
					// Try to move in the appropriate horizontal direction
					if( dx < 0 )
					{
						// Pacman is left of ghost. Try to move LEFT
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(-getSpeed(), 0);
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
							{
								this.setTile(this.getTile().getLeft());
							}
							return;
						}
					}
					else
					{
						// Pacman is right of ghost. Try to move RIGHT
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(getSpeed(), 0);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getRight().getRectangle().right == this.getRect().right )
							{
								this.setTile(this.getTile().getRight());
							}
							return;
						}
						
					}
					// The desired movement is invalid
					// Try to perform the set direction
					// Check that the move is valid
					if( this.getTile().getUpper() != null && 
						this.getRect().left >= this.getTile().getRectangle().left &&
						this.getRect().right <= this.getTile().getRectangle().right )
					{
						// The movement is valid so prepare it
						this.getRect().offset(0, -getSpeed());
					
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
						{
							this.setTile(this.getTile().getUpper());
						}
						return;
					}
					// The set movement is invalid
					// Try to perform the opposite of
					// the desired horizontal motion
					if( dx < 0 )
					{
						// Pacman is left of ghost. Try to move RIGHT
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(getSpeed(), 0);
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getRight().getRectangle().right == this.getRect().right )
							{
								this.setTile(this.getTile().getRight());
							}
							return;
						}
					}
					else
					{
						// Pacman is right of ghost. Try to move LEFT
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(-getSpeed(), 0);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
							{
								this.setTile(this.getTile().getLeft());
							}
							return;
						}
						
					}
				}
				case DOWN:
				{
					// Try to move in the appropriate horizontal direction
					if( dx < 0 )
					{
						// Pacman is left of ghost. Try to move LEFT
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(-getSpeed(), 0);
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
							{
								this.setTile(this.getTile().getLeft());
							}
							return;
						}
					}
					else
					{
						// Pacman is right of ghost. Try to move RIGHT
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(getSpeed(), 0);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getRight().getRectangle().right == this.getRect().right )
							{
								this.setTile(this.getTile().getRight());
							}
							return;
						}
						
					}
					// The desired movement is invalid
					// Try to perform the set direction
					// Check that the move is valid
					if( this.getTile().getLower() != null && 
						this.getRect().left >= this.getTile().getRectangle().left &&
						this.getRect().right <= this.getTile().getRectangle().right )
					{
						// The movement is valid so prepare it
						this.getRect().offset(0, getSpeed());
					
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
						{
							this.setTile(this.getTile().getLower());
						}
						return;
					}
					// The set movement is invalid
					// Try to perform the opposite of
					// the desired horizontal motion
					if( dx < 0 )
					{
						// Pacman is left of ghost. Try to move RIGHT
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(getSpeed(), 0);
							// We successfully moved vertically, we are no longer stuck
							this.setStuck(false);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getRight().getRectangle().right == this.getRect().right )
							{
								this.setTile(this.getTile().getRight());
							}
							return;
						}
					}
					else
					{
						// Pacman is right of ghost. Try to move LEFT
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The movement is valid so prepare it
							this.getRect().offset(-getSpeed(), 0);
						
							// Check to see if the ghost has moved into the next grid square
							if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
							{
								this.setTile(this.getTile().getLeft());
							}
							return;
						}
						
					}
				}
			}
			
		}
		else
		{
			// If the difference between pacmans right side and ghosts right side is large 
			// than the distance between pacmans top and ghosts top, then move horizontally if possible
			// otherwise move vertically
			// Check that the horizontal movement is valid. If so, perform the movement
			switch(this.getDirection())
			{
				case Ghost.RIGHT:
				{
					// Check that the horizontal movement is valid.
					if( this.getTile().getRight() != null && 
						this.getRect().top >= this.getTile().getRectangle().top &&
						this.getRect().bottom <= this.getTile().getRectangle().bottom )
					{
						// The movement is valid so prepare it
						this.getRect().offset(getSpeed(), 0);
					
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getRight().getRectangle().right == this.getRect().right )
						{
							this.setTile(this.getTile().getRight());
						}
						return;
					}
				}
				case Ghost.LEFT:
				{
					// Check that the horizontal movement is valid.
					if( this.getTile().getLeft() != null && 
						this.getRect().top >= this.getTile().getRectangle().top &&
						this.getRect().bottom <= this.getTile().getRectangle().bottom )
					{
						// The movement is valid so prepare it
						this.getRect().offset(-getSpeed(), 0);
						
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
						{
							this.setTile(this.getTile().getLeft());
						}
						return;
					}
				}
				case Ghost.UP:
				{
					// Check that the horizontal movement is valid.
					if( this.getTile().getUpper() != null && 
						this.getRect().left >= this.getTile().getRectangle().left &&
						this.getRect().right <= this.getTile().getRectangle().right )
					{
						// The movement is valid so prepare it
						this.getRect().offset(0, -getSpeed());
							
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
						{
							this.setTile(this.getTile().getUpper());
						}
						return;
					}
				}
				case Ghost.DOWN:
				{
					// Check that the horizontal movement is valid.
					if( this.getTile().getLower() != null && 
						this.getRect().left >= this.getTile().getRectangle().left &&
						this.getRect().right <= this.getTile().getRectangle().right )
					{
						// The movement is valid so prepare it
						this.getRect().offset(0, getSpeed());
						
						// Check to see if the ghost has moved into the next grid square
						if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
						{
							this.setTile(this.getTile().getLower());
						}
						return;
					}
				}
			}
			//UP
			if( this.getTile().getUpper() != null && 
				this.getRect().left >= this.getTile().getRectangle().left &&
				this.getRect().right <= this.getTile().getRectangle().right )
			{
				// The movement is valid so prepare it
				this.getRect().offset(0, -getSpeed());
				// We successfully moved vertically, we are no longer stuck
				this.setStuck(false);
			
				// Check to see if the ghost has moved into the next grid square
				if( this.getTile().getUpper().getRectangle().top == this.getRect().top )
				{
					this.setTile(this.getTile().getUpper());
				}
				return;
			}
			//DOWN
			if( this.getTile().getLower() != null && 
				this.getRect().left >= this.getTile().getRectangle().left &&
				this.getRect().right <= this.getTile().getRectangle().right )
			{
				// The movement is valid so prepare it
				this.getRect().offset(0, getSpeed());
			
				// Check to see if the ghost has moved into the next grid square
				if( this.getTile().getLower().getRectangle().bottom == this.getRect().bottom )
				{
					this.setTile(this.getTile().getLower());
				}
				return;
			}
			//RIGHT
			if( this.getTile().getRight() != null && 
				this.getRect().top >= this.getTile().getRectangle().top &&
				this.getRect().bottom <= this.getTile().getRectangle().bottom )
			{
				// The movement is valid so prepare it
				this.getRect().offset(getSpeed(), 0);
				
				// Check to see if the ghost has moved into the next grid square
				if( this.getTile().getRight().getRectangle().right == this.getRect().right )
				{
					this.setTile(this.getTile().getRight());
				}
				return;
			}
			//LEFT
			if( this.getTile().getLeft() != null && 
				this.getRect().top >= this.getTile().getRectangle().top &&
				this.getRect().bottom <= this.getTile().getRectangle().bottom )
			{
				// The movement is valid so prepare it
				this.getRect().offset(-getSpeed(), 0);
				
				// Check to see if the ghost has moved into the next grid square
				if( this.getTile().getLeft().getRectangle().left == this.getRect().left )
				{
					this.setTile(this.getTile().getLeft());
				}
				return;
			}
			
			this.setStuck(true);
		}
	}
	
	public synchronized void updateDirection()
	{
		// Get the horizontal and vertical distances between pacman and ghost
		int dx = this.getPlayer().getTile().getRectangle().right - this.getRect().right;
		int dy = this.getPlayer().getTile().getRectangle().top - this.getRect().top;
		
		// Set the horizontal direction
		// Check which is larger to determine which direction to try and move first
		if( dx >= dy )
		{
			// dx is larger that dy so try and move in the horizontal direction if possible
			// Check which direction the distance indicates
			if( dx < 0 )
			{
				// If dx is less than zero than pacman is to the left of ghost
				// Check that the left movement is valid and that the piece is lined up
				if( this.getTile().getLeft() != null && 
					this.getRect().top >= this.getTile().getRectangle().top &&
					this.getRect().bottom <= this.getTile().getRectangle().bottom )
				{
					// The left movement is valid so set the horizontal direction to LEFT
					this.setDirection(LEFT);
					return;
				}
				else
				{
					// The left movement is invalid so we try the appropriate vertical movement
					if( dy < 0 )
					{
						// The appropriate vertical movement is up
						// Check that an upward movement is possible
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(UP);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(DOWN);
							return;
						}
					}
					else
					{
						// The appropriate vertical movement is down
						// Check that a downward movement is possible
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(UP);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(DOWN);
							return;
						}
					}
				}
			}
			else if( dx >= 0 )
			{
				// If dx is greater than zero than pacman is to the right of ghost
				// Check that the right movement is valid and that the piece is lined up
				if( this.getTile().getRight() != null && 
					this.getRect().top >= this.getTile().getRectangle().top &&
					this.getRect().bottom <= this.getTile().getRectangle().bottom )
				{
					// The left movement is valid so set the horizontal direction to LEFT
					this.setDirection(RIGHT);
					return;
				}
				else
				{
					// The right movement is invalid so we try the appropriate vertical movement
					if( dy < 0 )
					{
						// The appropriate vertical movement is up
						// Check that an upward movement is possible
						if( this.getTile().getUpper() != null && 
							this.getRect().left >= this.getTile().getRectangle().left &&
							this.getRect().right <= this.getTile().getRectangle().right )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(UP);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(DOWN);
							return;
						}
					}
					else
					{
						// The appropriate vertical movement is down
						// Check that a downward movement is possible
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(UP);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(DOWN);
							return;
						}
					}
				}
			}
		}
		else
		{
			// dx is smaller than dy so try and move in the vertical direction if possible
			// Check which direction the distance indicates
			if( dy < 0 )
			{
				// If dy is less than zero than pacman is above ghost
				// Check that the up movement is valid and that the piece is lined up
				if( this.getTile().getUpper() != null && 
					this.getRect().left >= this.getTile().getRectangle().left &&
					this.getRect().right <= this.getTile().getRectangle().right )
				{
					// The up movement is valid so set the direction to UP
					this.setDirection(UP);
					return;
				}
				else
				{
					// The up movement is invalid so we try the appropriate horizontal movement
					if( dx < 0 )
					{
						// The appropriate horizontal movement is left
						// Check that a left movement is possible
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(LEFT);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either, therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(RIGHT);
							return;
						}
					}
					else
					{
						// The appropriate horizontal movement is down
						// Check that a downward movement is possible
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(RIGHT);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(LEFT);
							return;
						}
					}
				}
			}
			else if( dy >= 0 )
			{
				// If dy is greater than zero than pacman is below the ghost
				// Check that the down movement is valid and that the piece is lined up
				if( this.getTile().getLower() != null && 
						this.getRect().left >= this.getTile().getRectangle().left &&
						this.getRect().right <= this.getTile().getRectangle().right )
				{
					// The left movement is valid so set the horizontal direction to LEFT
					this.setDirection(DOWN);
					return;
				}
				else
				{
					// The right movement is invalid so we try the appropriate vertical movement
					if( dx < 0 )
					{
						// The appropriate horizontal movement is left
						// Check that a left movement is possible
						if( this.getTile().getLeft() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(LEFT);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either, therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(RIGHT);
							return;
						}
					}
					else
					{
						// The appropriate horizontal movement is down
						// Check that a downward movement is possible
						if( this.getTile().getRight() != null && 
							this.getRect().top >= this.getTile().getRectangle().top &&
							this.getRect().bottom <= this.getTile().getRectangle().bottom )
						{
							// The left movement is valid so set the horizontal direction to LEFT
							this.setDirection(RIGHT);
							return;
						}
						else
						{
							// The appropriate vertical motion is not
							// possible either therefore the ghost is stuck
							this.setStuck(true);
							this.setDirection(LEFT);
							return;
						}
					}
				}
			}			
		}
				
		/*// Find the player's current position and set the ghosts horizontal direction
		if(this.getPlayer().getTile().getRectangle().right - this.getRect().right > 0)
		{				
			// Pacman is to the right of the ghost. Therefore set hDirection to RIGHT
			this.setHDirection(RIGHT);
		}
		else if(this.getPlayer().getTile().getRectangle().right - this.getRect().right == 0)
		{
			// Pacman is directly above or below the ghost, set hDirection to right
			this.setHDirection(RIGHT);
		}
		else
		{
			// Pacman is to the left of the ghost, set hDirection to LEFT
			this.setHDirection(LEFT);
		}
		
		// Find the player's current position and set the ghosts vertical direction
		if(this.getPlayer().getTile().getRectangle().top - this.getRect().top > 0)
		{
			// Pacman is above the ghost. Therefore set goal direction to UP
			this.setVDirection(UP);
		}
		else if( this.getPlayer().getTile().getRectangle().top - this.getRect().top == 0 )
		{
			// Pacman is in line with the ghost
			this.setVDirection(DOWN);
		}
		else
		{
			// Pacman is below the ghost, set vDirection to DOWN
			this.setVDirection(DOWN);
		}*/
	} 

}

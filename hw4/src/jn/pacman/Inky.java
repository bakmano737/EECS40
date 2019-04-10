package jn.pacman;

import java.util.ArrayList;

import jn.pacman.Pinky.node;
import android.graphics.Bitmap;

/**
 * Inky is the light blue ghost and also tries to cut off pac man, but at a slower
 * rate than Pinky.
 */
public class Inky extends Ghost 
{
	class node
	{
		int x,y;
		int distance;
		boolean visited;
		node previous;
		GridPiece piece;
		public node(int x, int y)
		{
			this.x = x;
			this.y = y;
			visited = false;
			previous = null;
			distance = -1;
			piece = PacmanGrid.getGridBoard()[x][y];
		}
		
		public void setDistance(int d)
		{
			this.distance = d;
		}
		
		public void setVisited(boolean b)
		{
			this.visited = b;
		}
	}

	public Inky(Bitmap bmp, Player player, int x, int y, int speed) 
	{
		super(player, x, y, speed);
		this.setBmp(bmp);
	}

	/* (non-Javadoc)
	 * @see jn.pacman.Ghost#translate()
	 */
	@Override
	public void translate() 
	{
		super.translate();
		if(outOfGame) return;
		// Apply Dijkstra's Algorithm to determine next move
		
		// Get Ghost's position on the Grid board
		int gx = this.getTile().getX();
		int gy = this.getTile().getY();
		
		// Get Pacman's position on the Grid board
		int px = this.getTile().getX();
		int py = this.getTile().getY();
		
		// Create the grid of nodes
		node [][] nodeGrid = new node[PacmanGrid.getCols()][PacmanGrid.getRows()];
		
		// Create the array list of optimal moves
		ArrayList<node> moves = new ArrayList<node>();
		ArrayList<node> queue = new ArrayList<node>();
		
		for( int i = 0; i < nodeGrid.length; i++ )
		{
			for( int j = 0; j < nodeGrid[0].length; j++ )
			{
				queue.add(nodeGrid[i][j]);
			}
		}

		// Create the current gridpiece
		node currNode;
		 
		// Set all the nodes to -1 to represent infinity
		for( int i = 0; i < PacmanGrid.getCols(); i++ )
		{
			for( int j = 0; j < PacmanGrid.getRows(); j++ )
			{
				nodeGrid[i][j] = new node(i, j);
			}
		}
		
		// Set the initial node to zero
		nodeGrid[gx][gy].setDistance(0);
		currNode = nodeGrid[gx][gy];
		
		
		// Update the distance grid until the pacman node is visited
		while(!queue.isEmpty())
		{
			// Make the current node the node with the smallest distance value
			int minDist = 10000;
			for( int i = 0; i < PacmanGrid.getCols(); i++ )
			{
				for( int j = 0; j < PacmanGrid.getRows(); j++ )
				{
					if( !nodeGrid[i][j].visited && 
						!(nodeGrid[i][j].distance == -1) && 
						minDist > nodeGrid[i][j].distance)
					{
						currNode = nodeGrid[i][j];
						minDist = nodeGrid[i][j].distance;
					}
				}
			}
			
			if( currNode.distance == -1 )
			{
				break;
			}
			// Update each of the neighbor nodes if they arent walls
			// Left
			if(currNode.piece.getLeft() != null && !nodeGrid[currNode.x-1][currNode.y].visited)
			{
				int alt = currNode.distance + 1;
				if( nodeGrid[currNode.x-1][currNode.y].distance >= 0 )
				{
					nodeGrid[currNode.x-1][currNode.y].distance = alt;
					nodeGrid[currNode.x-1][currNode.y].previous = currNode;
				}
				else if( alt < nodeGrid[currNode.x-1][currNode.y].distance )
				{
					nodeGrid[currNode.x-1][currNode.y].distance = alt;
					nodeGrid[currNode.x-1][currNode.y].previous = currNode;
				}				
			}
			// Up
			if(currNode.piece.getUpper() != null && !nodeGrid[currNode.x][currNode.y-1].visited)
			{
				int alt = currNode.distance + 1;
				if( nodeGrid[currNode.x][currNode.y-1].distance >= 0 )
				{
					nodeGrid[currNode.x][currNode.y-1].distance = alt;
					nodeGrid[currNode.x][currNode.y-1].previous = currNode;
				}
				else if( alt < nodeGrid[currNode.x][currNode.y-1].distance )
				{
					nodeGrid[currNode.x][currNode.y-1].distance = alt;
					nodeGrid[currNode.x][currNode.y-1].previous = currNode;
				}
			}
			// Right
			if(currNode.piece.getRight() != null && !nodeGrid[currNode.x+1][currNode.y].visited)
			{
				int alt = currNode.distance + 1;
				if( !(nodeGrid[currNode.x+1][currNode.y].distance >= 0))
				{
					nodeGrid[currNode.x+1][currNode.y].distance = alt;
					nodeGrid[currNode.x+1][currNode.y].previous = currNode;
				}
				else if( alt < nodeGrid[currNode.x+1][currNode.y].distance )
				{
					nodeGrid[currNode.x+1][currNode.y].distance = alt;
					nodeGrid[currNode.x+1][currNode.y].previous = currNode;					
				}
			}
			// Down
			if(currNode.piece.getLower() != null && !nodeGrid[currNode.x][currNode.y+1].visited)
			{
				int alt = currNode.distance + 1;
				if( nodeGrid[currNode.x][currNode.y+1].distance >= 0)
				{
					nodeGrid[currNode.x][currNode.y+1].distance = alt;
					nodeGrid[currNode.x][currNode.y+1].previous = currNode;
				}
				else if( alt < nodeGrid[currNode.x][currNode.y+1].distance )
				{
					nodeGrid[currNode.x][currNode.y+1].distance = alt;
					nodeGrid[currNode.x][currNode.y+1].previous = currNode;
				}
			}
			
			currNode.setVisited(true);
			queue.remove(currNode);
			if( currNode == nodeGrid[px][py])
			{
				break;
			}
		}
		
		currNode = nodeGrid[px][py];
		while( currNode.previous != null )
		{
			moves.add(currNode.previous);
			currNode = currNode.previous;
		}
		
		if( !moves.isEmpty() )
		{
			this.getRect().set(moves.get(1).piece.getRectangle());
		}
		else
		{
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
		}
	}
}

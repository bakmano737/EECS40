package jn.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Pacman 
{
	private final static int maxSEEDS = 183;
	private static Integer seedsEaten;
	private static int lives;
	
	//boolean value that is true when pacman can eat ghosts
	private static boolean isPacmanVicious;
	
	private PacmanGrid map;
	private Player player;
	private Ghost[] ghosts;
	
	public Pacman(Canvas c, Bitmap[] bmps, int numLives, int ghostSpeed)
	{
		map = new PacmanGrid(c.getWidth(), c.getHeight());
		player = new Player(PacmanGrid.getGridBoard()[1][1]);
		//blinky = new Blinky(bmps[0], player, ghostSpeed);
		
		lives = numLives; 
		isPacmanVicious = false;
		
		ghosts = new Ghost[4];
		ghosts[0] = new Blinky(bmps[0], player, 0, 10,  ghostSpeed);
		ghosts[1] = new Clyde(bmps[1], player, 18, 10, ghostSpeed);
		ghosts[2] = new Pinky(bmps[2], player, 9, 4, ghostSpeed);
		ghosts[3] = new Blinky(bmps[3], player, 9, 15, ghostSpeed);
		
		seedsEaten = new Integer(0);
	}
	
	public void update()
	{
		player.translate();
		/*blinky.updateDirection(); TODO: ??? */
		for(int i = 0; i < ghosts.length; i++)
		{
			ghosts[i].translate();
		}
		
	}
	
	public void draw(Canvas c)
	{
		//Draw stuff
		drawSideInfo(c);
		map.drawGrid(c);
		player.drawPlayer(c);
		for(int i = 0; i < ghosts.length; i++)
		{
			ghosts[i].draw(c);
		}
	}
	
	/** This is to be called only by Pacman.
	 *  It draws the scoring. */
	private void drawSideInfo(Canvas c)
	{
		//Score:
		float x = c.getWidth()/2;
		float y = PacmanGrid.getRows()*GridPiece.getSize() + (c.getHeight() - PacmanGrid.getRows()*GridPiece.getSize())/2;
		Paint p = new Paint();
		p.setTextSize(20);
		p.setStrokeWidth(40);
		p.setColor(Color.WHITE);
		c.drawText("Score: " + seedsEaten.toString(), x, y, p);

		//Number of lives left:
		Rect R = new Rect();
		R.set(GridPiece.getSize(), (int) y - GridPiece.getSize(), 2*GridPiece.getSize(), (int) y);
		for(int i = 1; i <= lives; i++)
		{
			c.drawBitmap(Player.getOpenR(), null, R, null);
			R.offset(GridPiece.getSize(), 0);
		}
	}
	
	public Player getPlayer() { return player; }
	
	public static void setLives (int l) { lives = l; 	}
	public static int  getLives ()		{ return lives; }
	
	public static void pacmanAteBigSeed() { isPacmanVicious = true; }
	public static void specialModeOver()  { isPacmanVicious = false; }
	public static boolean isPacmanVicious() { return isPacmanVicious; }
	public static void incSeeds() 
	{ 
		seedsEaten++; 
		if(seedsEaten == maxSEEDS)
		{
			int speed = Ghost.getSpeed();
			speed*=2;
			Ghost.setSpeed(speed);
			PacmanView.restartGame();
		}
	}
}

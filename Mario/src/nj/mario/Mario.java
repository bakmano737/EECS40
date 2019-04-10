package nj.mario;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Mario 
{
	// Game Objects: 
	// Map, Player, Enemies, Obstacles, Special Boxes
	private Map map;
	private Player player;
	private Enemy[] enemies;
	private final int numEnemies = 10;
	
	private static Integer score;
	private static Integer level;
	
	// Public game state boolean values for movement
	public static boolean jump;
	public static boolean mRight;
	public static boolean mLeft;
	public static boolean duck;
	
	public static boolean gameWon;
	
	public static boolean help; 
	/**
	 * Constructor 
	 * @param c - Canvas on which everything is drawn
	 * @param playerBmps - Array of bitmaps for animating player
	 */
	public Mario(Canvas c, Bitmap[] playerBmps, Bitmap[] enemyBmps, int l)
	{
		// TODO: Add Enemies
		
		//If both levels were passed, no need to create anything:
		if(l > 2) 
		{
			gameWon = true;
			return;
		}
		else
		{
			map = new Map(c.getWidth(), c.getHeight(), l);
			player = new Player(c.getWidth(), c.getHeight(), playerBmps);
			enemies = new Enemy[numEnemies];
			enemies[0] = new Enemy(10, Map.getHeight()-2, enemyBmps, player);
			enemies[1] = new Enemy(20, Map.getHeight()-2, enemyBmps, player);
			score = 0;
			level = l;
			gameWon = false;
			help = false;
		}
		
	}

	/**
	 * draw - calls draw methods for individual objects
	 * @param c - canvas on which objects are drawn
	 */
	public void draw(Canvas c) 
	{
		if(!gameWon)
		{
			if(player.getX() >= Map.getDWidth()/2 && player.getX() <= Map.getWidth() - Map.getDWidth()/2)
			{
				map.drawGrid(c, player.getX() - Map.getDWidth()/2, player.getX() + Map.getDWidth()/2);
			}
			else if( player.getX() < Map.getDWidth()/2)
			{
				map.drawGrid(c, 0, Map.getDWidth());
			}
			else if( player.getX() > Map.getWidth() - Map.getDWidth()/2)
			{
				map.drawGrid(c, Map.getWidth() - Map.getDWidth(), Map.getWidth());
			}
			player.setRect(Map.getGridBoard()[player.getX()][player.getY()].getRect());
			enemies[0].setRect(Map.getGridBoard()[enemies[0].getX()][enemies[0].getY()].getRect());
			enemies[1].setRect(Map.getGridBoard()[enemies[1].getX()][enemies[1].getY()].getRect());
			enemies[0].draw(c);
			enemies[1].draw(c);
			player.draw(c);
			drawSideInfo(c);
			
			//Draw the help screen if player is pressing "h":
			if(help) drawHelpScreen(c);
		}
		else gameWon(c);
	}
	
	/** Draws current score.
	 * To be called only by Mario: */
	private void drawSideInfo(Canvas c)
	{
		//Score:
		int txtSize = 20;
		float x = (float) txtSize;
		float y = (float) txtSize;
		Paint p = new Paint();
		p.setTextSize(txtSize);
		p.setStrokeWidth(40);
		p.setColor(Color.WHITE);
		c.drawText("Score: " + score.toString(), x, y, p);
		
		c.drawText("Level: " + level.toString(), x*6, y, p);
	}
	
	/** Draws a help screen. Called only by Mario: */
	private void drawHelpScreen(Canvas c)
	{
		int txtSize = 20;
		float x = 0;
		float y = (float) 2*txtSize;
		Paint p = new Paint();
		p.setTextSize(txtSize);
		p.setStrokeWidth(40);
		p.setColor(Color.WHITE);
		c.drawText("Use -> to move right", x, y, p);
		c.drawText("Use <- to move left", x, y+txtSize, p);
		c.drawText("Use the up arrow to jump", x, y+2*txtSize, p);
		c.drawText("Collect at least 5 coins", x, y+3*txtSize, p);
		c.drawText("to pass each level in the end", x, y+4*txtSize, p);
	}
	
	/** Called by Mario once both levels are passed
	 * to draw the winning screen.
	 * Takes in width and height of canvas
	 * to located center of screen */
	private void gameWon(Canvas c)
	{
		int txtSize = 30;
		float x = c.getWidth()/4;
		float y = c.getHeight()/2;
		Paint p = new Paint();
		p.setTextSize(txtSize);
		p.setStrokeWidth(40);
		p.setColor(Color.WHITE);
		c.drawText("Congratulations!", x, y, p);
		c.drawText("You Won the Game!", x, y+txtSize, p);
	}
	/**
	 * update - update state for the player jumping
	 * and enemies AI's
	 */
	public void update()
	{
		// Update the player
		player.update(mLeft, mRight, jump, duck);
		
		//Update the enemies	
		enemies[0].update();
		enemies[1].update();
	}
	
	/** @return the player */
	public Player getPlayer() { return player; }
	
	/** Increment the score when player collects coins: */
	public static void incScore() { score++; }
	/** Get the current score of the game: */
	public static int getScore() { return score; }
	/** Increment the current level when a level is won,
	 * and send a signal to the View class to restart game: */
	public static void passLevel() 
	{ 
		level++; 
		MarioView.restartGame();
	}
	/** Get the current level to draw the right map: */
	public static int getLevel() { return level; }
}

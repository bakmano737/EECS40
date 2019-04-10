package jn.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PacmanView extends SurfaceView 
{
	//The main game class variable
	private Pacman game;
	
	//variable to know if we have canvas yet or not:
	private boolean gameStarted;
	
	private static boolean gameRestart;
	
	private int keyCode;
	
	// Temporary storage for ghost bitmaps
	private Bitmap[] ghostBitmaps;
	
	public PacmanView(Context context) 
	{
		// Call the SurfaceView constructor
		super(context);
		// Set Focusable to true so as to activate the key listener
		setFocusable(true);
		gameStarted = false;
		ghostBitmaps = new Bitmap[4];
	}
	
	/** Return the holder of the parent SurfaceView */
	public SurfaceHolder getHolder() 
	{
		return super.getHolder();
	}
	
	private void Initialize(Canvas c)
	{
		gameStarted = true;
		gameRestart = false;
		
		//Can only decode Resource in Surface View:
		PacmanGrid.setWall(BitmapFactory.decodeResource(getResources(), R.drawable.blue_square));
		PacmanGrid.setRoad(BitmapFactory.decodeResource(getResources(), R.drawable.road_smallseed));
		PacmanGrid.setBigSeed(BitmapFactory.decodeResource(getResources(), R.drawable.road_seed));
		
		//Other orientations for pacman:
		Player.setOpenR(BitmapFactory.decodeResource(getResources(), R.drawable.open_right));
		Player.setOpenL(BitmapFactory.decodeResource(getResources(), R.drawable.open_left));
		Player.setOpenD(BitmapFactory.decodeResource(getResources(), R.drawable.open_down));
		Player.setOpenU(BitmapFactory.decodeResource(getResources(), R.drawable.open_up));
		Player.setClosed(BitmapFactory.decodeResource(getResources(), R.drawable.pacman_closed));
		
		//Unique images for each ghost:
		ghostBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
		ghostBitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.clyde);
		ghostBitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.pinky);
		ghostBitmaps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.inky);
		//for special mode ghosts:
		Ghost.setScaredGhostImg(BitmapFactory.decodeResource(getResources(), R.drawable.ghost));
		
		//Create the new game with 3 lives 
		// and ghost speed 2 (beginning settings):
		game = new Pacman(c, ghostBitmaps, 3, 2);
	}
	
	protected void onDraw(Canvas c)
	{
		if(gameStarted)
		{
			if(gameRestart)
			{
				if(Pacman.getLives() <= 0) 
				{
					float x = c.getWidth()/4;
					float y = c.getHeight()/2;
					int txtSize = 20;
					Paint p = new Paint();
					p.setTextSize(txtSize);
					p.setStrokeWidth(400);
					p.setColor(Color.YELLOW);
					c.drawText("GAME OVER", x, y-txtSize/2, p);
					c.drawText("USE MENU TO RESTART", x/2, y+txtSize/2, p);
					game = null;
				}
				else
				{
					game = new Pacman(c, ghostBitmaps, Pacman.getLives(), Ghost.getSpeed());
					gameRestart = false;
				}
			}
			
			else
			{
				// Set the background to black
				c.drawColor(Color.BLACK);
				
				//Draw the whole game:
				game.draw(c);
			}
		}
		
		else 
		{
			Initialize(c);
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_LEFT:
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			case KeyEvent.KEYCODE_DPAD_DOWN:
			case KeyEvent.KEYCODE_DPAD_UP:
				this.setKeyCode(keyCode);
				
				//Set the current direction to key press if it's the first time a key is pressed:
				if(game.getPlayer().getDirection() == 0 ) game.getPlayer().setDirection(keyCode);
				
				//Set the new goal:
				game.getPlayer().setGoal(keyCode);
				return true;
		}
		return false;
	}
	
	public Pacman getPacman() 		 { return game;   }
	
	public static void restartGame() { gameRestart = true;  }
	/**
	 * @return the keyCode
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * @param keyCode the keyCode to set
	 */
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}

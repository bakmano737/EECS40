package nj.mario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MarioView extends SurfaceView implements SurfaceHolder.Callback
{
	//boolean to know if we have the canvas
	//and can start initializing/drawing or not:
	private boolean gameStarted;
	private static boolean gameRestart;
	//Variable for the whole game:
	private Mario game;
	
	//Array of Bitmaps for player
	Bitmap [] playerBmps;
	//Array of Bitmaps for enemy
	Bitmap [] enemyBmps;
	//Bitmap for obstacles
	static Bitmap obstacleBmp;
	
	//Canvas variable for surfaceCreated
	Canvas c;
	
	public MarioView(Context context) 
	{
		// Call the SurfaceView constructor
		super(context);
		// Set Focusable to true so as to activate the key listener
		setFocusable(true);
		gameStarted = false;
		
		playerBmps = new Bitmap[1];
		enemyBmps = new Bitmap[4];
		
		// Decode Bitmaps
		// Player Bitmaps
		playerBmps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		enemyBmps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.goomba_left);
		enemyBmps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.goomba_right);
		enemyBmps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.goomba_dead_left);
		enemyBmps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goomba_dead_right);
		
		obstacleBmp = BitmapFactory.decodeResource(getResources(), R.drawable.blue_square);	
		
		MapTile.setBrick(BitmapFactory.decodeResource(getResources(), R.drawable.bricks));
		MapTile.setWall(BitmapFactory.decodeResource(getResources(), R.drawable.blue_square));
		MapTile.setSurprise(BitmapFactory.decodeResource(getResources(), R.drawable.question_box));
	}

	/** Return the holder of the parent SurfaceView */
	public SurfaceHolder getHolder() 
	{
		return super.getHolder();
	}
	
	private void Initialize(Canvas c)
	{
		gameStarted = true;
		//Start at level 1:
		game = new Mario(c, playerBmps, enemyBmps, 1);
	}
	
	protected void onDraw(Canvas c)
	{
		if(gameStarted)
		{
			if(gameRestart)
			{
				game = new Mario(c, playerBmps, enemyBmps, Mario.getLevel());
				gameRestart = false;
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
			{
				Mario.mLeft = true;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			{
				Mario.mRight = true;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_DOWN:
			{
				Mario.duck = true;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_UP:
			{
				Mario.jump = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_LEFT:
			{
				Mario.mLeft = false;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			{
				Mario.mRight = false;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_DOWN:
			{
				Mario.duck = false;
				return true;
			}
			case KeyEvent.KEYCODE_H:
			{
				Mario.help = !Mario.help;
				return true;
			}
		}
		return false;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		this.onDraw(c);		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {}
	
	public Mario getGame() { return game; }
	public Canvas getCanvas() { return c; }
	public void setCanvas(Canvas c) { this.c = c; }
	
	public static void restartGame() { gameRestart = true; }

}

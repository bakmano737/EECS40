package n.shuba;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SpaceInvaders extends SurfaceView 
{
	private boolean fire;
	private Player player;
	
	// These fields should be accessed statically
	public static boolean gameOver;
	public static boolean loser;
	public static Bitmap playerBMP;
	public static Bitmap alienBMP;
	public static Bitmap fireball;
	public static Bullet b1;
	ArrayList<Bullet> Bullets;
	Aliens aliens;
	
	public SpaceInvaders(Context context) {
		super(context);
		setFocusable(true);
		
		//Create the Player Object
		player = new Player();
		playerBMP = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		player.setBitmap(playerBMP);
		//Create the Bullets Array
		Bullets = new ArrayList<Bullet>();
		fireball = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
		//Create the Aliens Array
		aliens = new Aliens();
		alienBMP = BitmapFactory.decodeResource(getResources(), R.drawable.invader);
		aliens.setBitmap(alienBMP);
		fire=false;
		gameOver = false;
		loser = false;
		
	}
	
	public SurfaceHolder getHolder() 
	{
		return super.getHolder();
	}

	protected void onDraw(Canvas c) 
	{
		// Set the background to black
		c.drawColor(Color.BLACK);
		
		/* PLAYER DRAWING */
		player.drawPlayer(c);
		
		/* ALIENS DRAWING */
		aliens.drawAliens(c);
		
		/* BULLETS DRAWING */
		//We decided to limit bullets to 3
		//Fire a bullet
		if(fire && Bullets.size() < 3 )
		{
			Bullets.add(b1 = new Bullet(this));
			c.drawBitmap(fireball, null, b1.shot, null);
			fire=false;
		}
		
		//this is to prevent an extra bullet from flying
		//if key is pressed while 3 bullets are still in flight
		if(Bullets.size() == 3) fire=false; 
		
		//Translate the bullets
		if(Bullets.size() > 0)
		{
			int i=0;
			ILOOP: while(i < Bullets.size() && Bullets.get(i) != null)
			{	
				for(int k = 0; k < 4; k++ )
				{
					for( int j = 0; j < 5; j++ )
					{
						if( Bullets.get(i).shot.intersect(aliens.getAlienArray()[k][j].getAlienRect()) 
								&& aliens.getAlienArray()[k][j].isAlienAlive() ) 
						{ 
							aliens.getAlienArray()[k][j].killAlien();
							aliens.numDead++;
							Bullets.remove(i);
							i--;
							break ILOOP; 
						}
					}
				}
				
				if( Bullets.get(i).shot.top >= 0 )
				{
					Bullets.get(i).shot.offset(0, -10);
					c.drawBitmap(fireball, null, Bullets.get(i).shot, null);
					i++;
				}
				
				else
				{
					Bullets.remove(i);
					i--;
					break;
				}
			}
		}
	}
	
	public boolean onKeyDown( int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_LEFT:
			{
				player.setMovingLeft(true);
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			{
				player.setMovingRight(true);
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_UP:
			{
				this.fire = true;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_DOWN:
			{
				this.fire = true;
				return true;
			}
			case KeyEvent.KEYCODE_ENTER:
			{
				this.fire = true;
				return true;
			}
			case KeyEvent.KEYCODE_SPACE:
			{
				this.fire = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean onKeyUp( int keyCode, KeyEvent event )
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_LEFT:
			{
				player.setMovingLeft(false);
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			{
				player.setMovingRight(false);
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_UP:
			{
				this.fire = false;
				return true;
			}
			case KeyEvent.KEYCODE_DPAD_DOWN:
			{
				this.fire = false;
				return true;
			}
			case KeyEvent.KEYCODE_ENTER:
			{
				this.fire = false;
				return true;
			}
			case KeyEvent.KEYCODE_SPACE:
			{
				this.fire = false;
				return true;
			}
		}
		return false;
	}
	
	// Accessor Methods
	public Player getPlayer() 	 { return player; 		}
	public Bitmap getPlayerBMP() { return playerBMP; 	}
	public Bitmap getFireball()	 { return fireball; 	}
	public Aliens getAliens()	 { return aliens;		}
	
}



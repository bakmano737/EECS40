/*
 * CHANGES, LATES ONE FIRST:
 * 
 * 4/30/12 @ 12:12PM - Added a Grid. It is still unfinished and untested.
 * 						Key events work and object falls properly.
 * 						TO DO:
 * 							1. Finish Grid
 * 							2. Key Events need to check boundaries.
 * 							3. What happens when key is held?
 * 
 */

package nj.eecs40.tetris;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class TetrisThread extends Thread implements SurfaceHolder.Callback
{
	// Fields
	private TetrisView tetris;
	private Canvas c;
	private long currTime;
	private long previousTime;
	
	// Constructor
	public TetrisThread(TetrisView tetris)
	{
		this.tetris = tetris;
		previousTime = System.currentTimeMillis();
	}
	
	@Override
	public void run()
	{

		while(true)
		{
			currTime = System.currentTimeMillis();
			if(currTime < previousTime + 5000/(1+TetrisView.getSpeed()))
			{
				try
				{
					Thread.sleep(200 - (previousTime - currTime));
				}catch(InterruptedException e)
				{
					//do nothing :)
				}
			}
			
			// Get the surface holder 
			SurfaceHolder sh= tetris.getHolder();
			
			// Use lockCanvas() to obtain a canvas on which
			// to draw the images
			c = sh.lockCanvas();

			if (c!=null) 
			{
				surfaceCreated(sh);
				sh.unlockCanvasAndPost(c);
			}
			
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		// Draw the piece
		tetris.onDraw(c);
		// Update previous time
		previousTime = System.currentTimeMillis();
	
		// Check if it is above a frozen piece
		if(!tetris.getActivePiece().checkBottom())
		{
			// If it is not above a frozen piece shift it down
			tetris.getActivePiece().translate(0, 1);
			TetrisView.gameOver = false;
		}
		else
		{
			if(TetrisView.gameOver) 
			{
				System.out.println("Game Over!");
				System.exit(0);
			}
			// Freeze the piece
			tetris.getActivePiece().freezePiece();
		}			
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}

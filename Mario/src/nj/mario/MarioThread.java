package nj.mario;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MarioThread extends Thread
{
	private MarioView mv;
	private Canvas c;
	
	public MarioThread(MarioView mv)
	{
		this.mv = mv;
	}
	
	public void run() 
	{
		//Game Loop
		while(true) 
		{
			try
			{
				Thread.sleep(50);
			}
			catch( Exception e){}
			
			// Get the surface holder 
			SurfaceHolder sh = mv.getHolder();
			
			// Use lockCanvas() to obtain a canvas on which
			// to draw the images
			c = sh.lockCanvas(null);

			if (c!=null) 
			{
				mv.setCanvas(c);
				mv.surfaceCreated(sh);
				sh.unlockCanvasAndPost(c);
				if(!Mario.gameWon)
				{
					mv.getGame().update();
				}
				
			}
			
		}
	}
}

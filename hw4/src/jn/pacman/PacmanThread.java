package jn.pacman;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class PacmanThread extends Thread 
{
	PacmanView pv;
	public PacmanThread(PacmanView pv)
	{
		this.pv = pv;
	}
	
	public void run() 
	{
		//Game Loop
		while(true) 
		{
			// Get the surface holder 
			SurfaceHolder sh= pv.getHolder();
			
			
			if(pv.getPacman() != null) pv.getPacman().update();
			// Use lockCanvas() to obtain a canvas on which
			// to draw the images
			Canvas c = sh.lockCanvas();

			if (c!=null) 
			{
				pv.onDraw(c);
				sh.unlockCanvasAndPost(c);
			}
		}
	}
}

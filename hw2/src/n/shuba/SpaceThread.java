package n.shuba;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SpaceThread extends Thread 
{
	SpaceInvaders si;
	public SpaceThread(SpaceInvaders si) 
	{
		this.si=si;
	}
		
	public void run() 
	{
		//Game Loop
		while(true) 
		{
			
			//We added this code to smooth 
			//the drawing out a little :P
			try{
				Thread.sleep(50, 0);
			}catch(InterruptedException e){}
			
			// Get the surface holder 
			SurfaceHolder sh=si.getHolder();
			
			// Use lockCanvas() to obtain a canvas on which
			// to draw the images
			Canvas c=sh.lockCanvas();
			
			if (c!=null) 
			{
				si.getAliens().translateAliens(c, si);
				si.getPlayer().translatePlayer(c);
				si.onDraw(c);
				sh.unlockCanvasAndPost(c);
				
				//All aliens are dead. New batch dispatch
				if(si.getAliens().numDead == 20)
				{
					si.aliens = new Aliens(si.aliens);
				}
				
				//aliens reached the player. New batch dispatch
				else if(SpaceInvaders.gameOver)
				{
					SpaceInvaders.loser=true;
					si.aliens = new Aliens(si.aliens);
					SpaceInvaders.gameOver = false;
					SpaceInvaders.loser = false;
					
				}
				
			}
		}
	}
}


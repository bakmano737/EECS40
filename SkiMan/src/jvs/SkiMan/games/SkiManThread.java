package jvs.SkiMan.games;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * @author James
 * SkiManThread - dedicated to computing game state and drawing GUI
 */
public class SkiManThread extends Thread 
{
	/** smv - SkiManView for drawing */
	private SkiManView smv;
	
	/**
	 * Constructor
	 * @param smv - SkiManView object for drawing
	 */
	public SkiManThread(SkiManView smv)
	{
		this.smv = smv;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			try 
			{
				Thread.sleep(50);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			// TODO: Update game state
			SurfaceHolder sh = smv.getSurfaceHolder();
			Canvas c = sh.lockCanvas();
			if(c != null)
			{
				smv.onDraw(c);
			}
		}
	}
}

/**
 * 
 */
package jvs.SkiMan.games;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author James
 *
 */
public class SkiManView extends SurfaceView implements SurfaceHolder.Callback {

	/**
	 * @param context
	 */
	public SkiManView(Context context) {
		super(context);
		setFocusable(true);
		setClickable(true);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * getSurfaceHolder - get the surface holder from the parent class
	 * @return parent's surface holder
	 */
	public SurfaceHolder getSurfaceHolder()
	{
		return super.getHolder();
	}

	/**
	 * onDraw - draw all of the game's major components
	 * @param c - the canvas on which everything is drawn
	 */
	public void onDraw(Canvas c)
	{
		//TODO: Draw the background image. Draw black for initial testing
		this.setBackgroundColor(Color.BLACK);
		//TODO: Draw Map
		//TODO: Draw Obstacles
		//TODO: Draw Player
		this.draw(c);
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
	{
		// TODO Auto-generated method stub
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub		
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
	}

}

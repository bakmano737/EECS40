package jvs.pkg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;

public class InvadeSpaceView extends View 
{
	public class InvadeSpaceThread extends Thread
	{
		InvadeSpaceView si;
		public InvadeSpaceThread(InvadeSpaceView si) 
		{
			this.si=si;
		}
			
		public void run() 
		{
			//This is your basic game loop. Feel free to add other code to it.
			while(true) 
			{
				SurfaceHolder sh=si.getHolder();
				//You might want to do game specific processing in call you add in here.
				Canvas c=sh.lockCanvas();
				if (c!=null) 
				{
					si.onDraw(c);
					sh.unlockCanvasAndPost(c);
				}
			}
		}
		
	}
	

	public int centerX;
	public int centerY;
	
	public InvadeSpaceView(Context context, ImageView v) {
		super(context);
		setFocusable(true);
		centerX = 15;
		centerY = 35;
		// TODO Auto-generated constructor stub
	}

	Bitmap mybitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	
	public SurfaceHolder getHolder() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	protected void onDraw(Canvas c) 
	{
		c.drawColor(Color.BLACK); // Set the background to black
		Rect dst=new Rect();
		dst.set(centerX-5, centerY-5, centerX+5, centerY+5); //Set window to place image from (10,30) to (20,40)
		c.drawBitmap(mybitmap, null, dst, null); //Draw the bitmap
	}
	
	public void translate(int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
		return;
	}
	
	public void fire()
	{
		System.out.println("Fire!");
		return;
	}

}


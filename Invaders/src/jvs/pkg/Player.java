package jvs.pkg;

import android.content.Context;
import android.view.SurfaceView;

public class Player extends SurfaceView
{
	public int centerX;
	public int centerY;
	public int shotsFired;
	
	public Player(Context context)
	{
		super(context);
		this.centerX = 15;
		this.centerY = 35;
		this.shotsFired = 0;
	}
	public Player(Context context, int x, int y)
	{
		super(context);
		this.centerX = x;
		this.centerY = y;
		this.shotsFired = 0;
	}
	
	public void translate(int dx, int dy)
	{
		this.centerX += dx;
		this.centerY += dy;
	}
	
	public void fire()
	{
		this.shotsFired++;
		System.out.println("Fire!");
	}
}

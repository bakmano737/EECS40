package bdemsky.Tetris;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Grid {
	//0 represents empty block
	private int[][] grid;
	
	public Grid(int x,int y) {
		grid=new int[x][y];
	}
	
	public int getWidth() {
		return grid.length;		
	}
	
	public int getHeight() {
		return grid[0].length;
	}
	
	public boolean hasElement(int x, int y) {
		return grid[x][y]!=0;		
	}
	
	public void setElement(int x, int y, int color) {
		grid[x][y]=color;		
	}
	
	private void removeRow(int row) {
		for(int y=row; y>=0;y--) {
			for(int x=0;x<getWidth();x++) {
				if (y==0)
					grid[x][y]=0;
				else
					grid[x][y]=grid[x][y-1];				
			}
		}		
	}
	
	public void checkFullRow() {
		outerloop:
		for(int y=getHeight()-1;y>=0;y--) {
			for (int x=0;x<getWidth();x++) {
				if (!hasElement(x,y)) {
					//row is not full.
					continue outerloop;			
				}
			}
			//Row is full
			removeRow(y);
		}
	}
	
	public void drawBox(Canvas c, int x, int y, Paint p) {
		float dx=((float)c.getWidth()-1)/getWidth();
		float dy=((float)c.getHeight()-1)/getHeight();
		c.drawRect(dx*x,dy*y,dx*(x+1),dy*(y+1),p);
	}
	
	public void draw(Canvas c) {
		//Draw right line
		Paint box=new Paint();

		for(int x=0;x<getWidth();x++) {
			for(int y=0;y<getHeight();y++) {
				if (grid[x][y]!=0) {
					box.setColor(grid[x][y]);
					drawBox(c,x,y,box);
				}
			}
		}
	}
}

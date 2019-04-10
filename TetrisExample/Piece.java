package bdemsky.Tetris;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;

public class Piece {
	private PrimPiece piece;
	private int x, y;
	private int rotation;
	private Grid grid;
	
	public Piece(PrimPiece piece, Grid grid) {
		this.piece=piece;
		this.grid=grid;
		y=0;
		x=(grid.getWidth()-4)/2;
		rotation=0;
	}
	
	boolean isMoveLegal(int dx, int dy, int drot) {
		int newrotation=(rotation+drot)%4;
		int newx=x+dx;
		int newy=y+dy;
		for(int px=0;px<4;px++) {
			for (int py=0;py<4;py++) {
				boolean hasBox=piece.hasBlock(px, py, newrotation);
				int gridx=newx+px;
				int gridy=newy+py;
				if (hasBox) {
					if (gridx<0||gridx>=grid.getWidth())
						return false;
					if (gridy<0)
						continue;
					if (gridy>=grid.getHeight())
						return false;
					if (grid.hasElement(gridx, gridy))
						return false;
				}
			}
		}
		return true;
	}
	
	public synchronized void draw(Canvas c) {
		//Draw right line
		Paint box=new Paint();
		box.setColor(piece.getColor());

		for(int px=0;px<4;px++) {
			for(int py=0;py<4;py++) {
				boolean hasBox=piece.hasBlock(px, py, rotation);
				int gridx=x+px;
				int gridy=y+py;
				if (gridy<0)
					continue;
				if( hasBox ) {
					grid.drawBox(c,gridx,gridy,box);
				}
			}
		}
	}
	
	public synchronized void keyPress(int keyCode) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (isMoveLegal(-1,0,0))
				x--;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (isMoveLegal(1,0,0))
				x++;
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			if (isMoveLegal(0,0,1))
				rotation=(rotation+1)%4;
			break;
		}
	}
	
	// return true if this piece settles
	public synchronized boolean fall() {
		if( isMoveLegal(0,1,0) ) {
			y++;
			return false;
		} else {
			for(int px=0;px<4;px++) {
				for(int py=0;py<4;py++) {
					if( piece.hasBlock(px, py, rotation) ) {
						grid.setElement(x+px, y+py, piece.getColor());
					}
				}
			}
			return true;
		}
	}
}

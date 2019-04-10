package bdemsky.Tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.SurfaceView;


public class TetrisView extends SurfaceView {
	Tetris tetris;
	
	public TetrisView(Context cnt, Tetris tetris) {
		super(cnt);
		this.tetris=tetris;
		setFocusable(true);
	}
	
	public Tetris getTetris() {
		return tetris;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
		case KeyEvent.KEYCODE_DPAD_RIGHT:
		case KeyEvent.KEYCODE_DPAD_UP:
			tetris.getPiece().keyPress(keyCode);
			return true;
		}
		return false;
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return false;
	}
	
	protected void onDraw(Canvas c) {
		c.drawColor(Color.BLACK);
		tetris.draw(c);	
	}
}

package bdemsky.Tetris;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class TetrisThread extends Thread {
	TetrisView tv;
	
	public TetrisThread(TetrisView tv) {
		this.tv=tv;
	}
	
	public void run() {
        while(true) {
        	SurfaceHolder sh=tv.getHolder();
        	tv.getTetris().update();
        	Canvas c=sh.lockCanvas();
          	if (c!=null) {
        		tv.onDraw(c);
        		sh.unlockCanvasAndPost(c);
        	}
        }
		
	}
	
}

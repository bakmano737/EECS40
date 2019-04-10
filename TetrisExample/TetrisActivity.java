package bdemsky.Tetris;

import android.app.Activity;
import android.os.Bundle;

public class TetrisActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tetris t=new Tetris();
        TetrisView tv=new TetrisView(this.getBaseContext(),t);
        setContentView(tv);
        TetrisThread tt=new TetrisThread(tv);
        tt.start();
    }
}
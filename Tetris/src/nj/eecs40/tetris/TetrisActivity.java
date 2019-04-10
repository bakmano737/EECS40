package nj.eecs40.tetris;

import android.app.Activity;
import android.os.Bundle;

public class TetrisActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        // Create the TetrisView and set it to the content view
        // of the activity
        final TetrisView tv = new TetrisView(getBaseContext());
        setContentView(tv);
        
        // Create the Tetris thread and call start on it
        TetrisThread tt = new TetrisThread(tv);
        tt.start();
    }
}
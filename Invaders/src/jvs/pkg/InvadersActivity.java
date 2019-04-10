package jvs.pkg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class InvadersActivity extends Activity 
{
	public final Player player = new Player(this);
	public ImageView playerView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerView = new ImageView(this);
        setContentView(R.layout.main);
    }
}
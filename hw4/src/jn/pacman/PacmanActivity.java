package jn.pacman;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PacmanActivity extends Activity 
{
	//Constants for menu options that control game settings:
	private static final int LIFE1 = 1;
    private static final int LIFE3 = 3;
    private static final int LIFE5 = 5;
    
    /** The Android Menu option, override and add some stuff */
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        super.onCreateOptionsMenu(menu);
        menu.add(0, LIFE1, 0, "Start With 1 Life");
        menu.add(0, LIFE3, 0, "Start With 3 Lives");
        menu.add(0, LIFE5, 0, "Start With 5 Lives");

        return true;
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final PacmanView pv = new PacmanView(getBaseContext());
        setContentView(pv);
        
        PacmanThread pt=new PacmanThread(pv);
        pt.start();	
    }
    
    //TODO: the whole game needs to restart:
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch (item.getItemId()) 
        {
            case LIFE1:
                Pacman.setLives(LIFE1);
                PacmanView.restartGame();
                return true;
            case LIFE3:
            	Pacman.setLives(LIFE3);
            	PacmanView.restartGame();
                return true;
            case LIFE5:
            	Pacman.setLives(LIFE5);
            	PacmanView.restartGame();
            	return true;
        }
        return false;
    }
}
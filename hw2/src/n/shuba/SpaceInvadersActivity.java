package n.shuba;

import android.app.Activity;
import android.os.Bundle;

public class SpaceInvadersActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SpaceInvaders sv=new SpaceInvaders(getBaseContext());
        setContentView(sv);
        
        SpaceThread st=new SpaceThread(sv);
        st.start();		
        }
        
        		
    }
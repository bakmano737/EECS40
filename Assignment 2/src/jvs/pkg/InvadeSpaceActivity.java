package jvs.pkg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InvadeSpaceActivity extends Activity 
{
	private LinearLayout root;
	Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(root);
        
        /* Build up the ViewGroup tree of LinearLayouts */
        
        /* Create layout parameter variables */
        @SuppressWarnings("deprecation")
		LinearLayout.LayoutParams layoutParams
        	= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.5F);
        
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.LTGRAY);
        root.setLayoutParams(layoutParams);
        
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(Color.DKGRAY);
        ll.setLayoutParams(layoutParams);
        root.addView(ll);
        
        ImageView player = new ImageView(this);
        player.setImageBitmap(myBitmap);
        player.setVisibility(ImageView.VISIBLE);
        ll.addView(player);
        
        setContentView(root);
        
        final InvadeSpaceView sv = new InvadeSpaceView(this, player);
        
        /** Wire the Controller */
        OnKeyListener l = new OnKeyListener() 
		{
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				if( event.getAction() != KeyEvent.ACTION_UP ) {	return false; }
				switch(keyCode)
				{
					case KeyEvent.KEYCODE_DPAD_LEFT:
						sv.translate(-5, 0);
						break;
						
					case KeyEvent.KEYCODE_DPAD_RIGHT:
						sv.translate(5, 0);
						break;
						
					case KeyEvent.KEYCODE_DPAD_UP:
						sv.fire();
						break;
						
					case KeyEvent.KEYCODE_SPACE:
						sv.fire();
						break;
						
					case KeyEvent.KEYCODE_DPAD_CENTER:
						sv.fire();
						break;
						
					default:
						return false;
						
				}
				return true;
			}
		};
        sv.setOnKeyListener(l);
        
    }
}
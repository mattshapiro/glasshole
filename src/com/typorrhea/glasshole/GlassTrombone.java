package com.typorrhea.glasshole;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.GestureDetector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GlassTrombone extends Activity {
    private GestureDetector mGestureDetector;
    private String outputString;
    private Card card;
    // ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
    	super.onCreate(savedInstanceState);
		card = new Card(this);
		card.setImageLayout(Card.ImageLayout.FULL);
		card.setText("Slide to play");
		card.setFootnote("(glasshole)");
		setContentView(card.getView());
        mGestureDetector = createGestureDetector(this);
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event)
    {
    	if(keycode == KeyEvent.KEYCODE_DPAD_CENTER)
    	{
    		card.setText("tep");
    		return true;
    	}
    	return super.onKeyDown(keycode, event);
    }
    
    @Override
    public boolean onKeyUp(int keycode, KeyEvent event)
    {
    	if (keycode == KeyEvent.KEYCODE_DPAD_CENTER)
    	{
    		card.setText("notep");
    		return true;
    	}
    	return super.onKeyUp(keycode, event);
    }
    
    private GestureDetector createGestureDetector(Context context) {
    GestureDetector gestureDetector = new GestureDetector(context);
        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
            @Override
            public void onFingerCountChanged(int previousCount, int currentCount) {
              // do something on finger count changes
            }
        });
        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
            @Override
            public boolean onScroll(float displacement, float delta, float velocity) {
                // do something on scrolling
            	return true;
            }
        });
        return gestureDetector;
    }

    /*
     * Send generic motion events to the gesture detector
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }

}
package com.typorrhea.glasshole.bouncy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BouncySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	DrawThread drawThread;
	BouncySensorManager accelerometer;
	
	public BouncySurfaceView(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		getHolder().addCallback(this);
		accelerometer = new BouncySensorManager(context);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawThread = new DrawThread(this);
		drawThread.setRunning(true);
		drawThread.run();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawThread.setRunning(false);
		boolean retry = true;
		while(retry)
		{
			try
			{
				drawThread.join();
				retry = false;
			}
			catch(Exception e)
			{
			}
		}
		drawThread = null;
	}
	
	public void doDraw(Canvas canvas)
	{
		Log.d(getClass().getName(), "drawing");
		Paint paint = new Paint();
		/*paint.setColor(Color.BLACK);
		Rect rect = new Rect(0,0,canvas.getWidth(),canvas.getHeight());
		canvas.drawRect(rect, paint);*/
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTextSize(16);
		canvas.drawText(""+accelerometer.x, 10, 10, paint);
		//Log.d(getClass().getName(), "finished @" + accelerometer.x);
	}

	public class DrawThread extends Thread
	{
		boolean bRunning;
		BouncySurfaceView bsv;
		
		public DrawThread(BouncySurfaceView surfaceView)
		{
			bsv = surfaceView;
		}
		
		public void setRunning(boolean running)
		{
			bRunning = running;
		}
		
		public void run()
		{
			while(bRunning)
			{
				Canvas canvas = bsv.getHolder().lockCanvas();
				if(canvas != null)
				{
					bsv.doDraw(canvas);
					bsv.getHolder().unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

}

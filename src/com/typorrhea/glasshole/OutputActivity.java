package com.typorrhea.glasshole;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends Activity implements SensorEventListener {

	public float x, y, z;
	final static float FILTERING_FACTOR = .3f;
	SensorManager mSensorManager;
	Sensor mAccelerometer;
	TextView xVal, yVal, zVal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
		
		this.setContentView(R.layout.output_card);
		xVal = (TextView)findViewById(R.id.x_value);
		yVal = (TextView)findViewById(R.id.y_value);
		zVal = (TextView)findViewById(R.id.z_value);
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		/*
		x = (float) (event.values[0]);
		y = (float) (event.values[1]);
		z = (float) (event.values[2]);*/
		// ripped off http://developer.android.com/reference/android/hardware/SensorEvent.html
		final float alpha = 1f;
		
		// fuck this cosmological constant - switch to tick-based
		
// g = 0.9 * g + 0.1 * v
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
		
		xVal.setText("" + x);
		yVal.setText("" + y);
		zVal.setText("" + z);
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	
}

package com.typorrhea.glasshole.bouncy;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class BouncySensorManager implements SensorEventListener {

	public float x, y;
	final static float FILTERING_FACTOR = .3f;
	SensorManager mSensorManager;
	Sensor mAccelerometer;
	
	
	public BouncySensorManager(Context context)
	{
		mSensorManager = (SensorManager) context.getSystemService(Activity.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		x = (float) (-event.values[1] * FILTERING_FACTOR + x * (1.0 - FILTERING_FACTOR));
		y = (float) (event.values[0] * FILTERING_FACTOR + y * (1.0 - FILTERING_FACTOR));
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
	
}

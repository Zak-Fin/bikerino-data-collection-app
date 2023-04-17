package com.example.bikerino_prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Initialize accelerometer sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the SensorEventListener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the SensorEventListener to conserve resources
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView accelerometerValuesTextView = findViewById(R.id.accelerometerValuesTextView);
        // Handle accelerometer values here
        float x = event.values[0]; // Acceleration along x-axis
        float y = event.values[1]; // Acceleration along y-axis
        float z = event.values[2]; // Acceleration along z-axis

        // Log accelerometer values
//        Log.d("Accelerometer", "X: " + x + " Y: " + y + " Z: " + z);
        float[] arr = new float[] {x, y, z};
        String accelerometerValuesString = "\nX: " + x + "\nY: " + y + "\nZ: " + z;

        // Update the text of the TextView
        accelerometerValuesTextView.setText("Accelerometer Values: " + accelerometerValuesString);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }
}
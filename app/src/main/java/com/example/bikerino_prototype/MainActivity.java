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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    private Sensor pressureSensor;
    private TextView accelerometerValuesTextView;
    private TextView gyroscopeValuesTextView;
    private TextView pressureValuesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Initialize sensors
        // Check if accelerometer sensor is available
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor == null) {
            // Handle case when accelerometer sensor is not available
            Toast.makeText(this, "Accelerometer sensor not available", Toast.LENGTH_SHORT).show();
        }

        // Check if gyroscope sensor is available
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscopeSensor == null) {
            // Handle case when gyroscope sensor is not available
            Toast.makeText(this, "Gyroscope sensor not available", Toast.LENGTH_SHORT).show();
        }

        // Check if pressure sensor is available
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressureSensor == null) {
            // Handle case when pressure sensor is not available
            Toast.makeText(this, "Pressure sensor not available", Toast.LENGTH_SHORT).show();
        }

        // Initialize TextViews
        accelerometerValuesTextView = findViewById(R.id.accelerometerValuesTextView);
        gyroscopeValuesTextView = findViewById(R.id.gyroscopeValuesTextView2);
        pressureValuesTextView = findViewById(R.id.pressureValuesTextView3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the SensorEventListener
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the SensorEventListener to conserve resources
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Handle accelerometer values here
            float ax = event.values[0]; // Acceleration along x-axis
            float ay = event.values[1]; // Acceleration along y-axis
            float az = event.values[2]; // Acceleration along z-axis
            String accelerometerValuesString = "\nX: " + ax + "\nY: " + ay + "\nZ: " + az;
            accelerometerValuesTextView.setText("\nAccelerometer Values: " + accelerometerValuesString);
        }
        // Handle gyroscope values
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float gx = event.values[0]; // Rotation rate around x-axis
            float gy = event.values[1]; // Rotation rate around y-axis
            float gz = event.values[2]; // Rotation rate around z-axis
            String gyroscopeValuesString = "\nX: " + gx + "\nY: " + gy + "\nZ: " + gz;
            gyroscopeValuesTextView.setText("\nGyroscope Values: " + gyroscopeValuesString);
        }
        // Handle pressure sensor value
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float pressure = event.values[0]; // Atmospheric pressure
            String pressureValueString = String.valueOf(pressure);
            pressureValuesTextView.setText("\nPressure Value: \n" + pressureValueString);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }
}
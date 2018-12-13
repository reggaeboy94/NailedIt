package academy.learnprogramming.nailedit;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StrengthActivity extends AppCompatActivity {
    public static final String TAG = "StrengthActivity";
    private static final String PUSHUPS = "PUSHUPS" ;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    private TextView tvPushUpsTotal;
    private TextView tvPushUpsCounter;
    private Button pushUpsBtn;
    private Button stopBtn;

    private boolean isWorking;
    private int pushUpsCounter = 0;
    private static int pushUpsTotal=1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);


        tvPushUpsTotal = (TextView) findViewById(R.id.tvPushUpsTotal);
        tvPushUpsCounter = (TextView) findViewById(R.id.tvPushUpsCounter);
        pushUpsBtn = (Button) findViewById(R.id.pushUpsBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(proximitySensor==null){
            Toast.makeText(this,"Proximity sensor is not available!", Toast.LENGTH_LONG).show();
        }

        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0] > proximitySensor.getMaximumRange()){
                    pushUpsCounter++;
                    tvPushUpsCounter.setText(String.valueOf(pushUpsCounter));
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        pushUpsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWorking){
                    isWorking=true;
                    sensorManager.registerListener(proximitySensorListener, proximitySensor,2*1000*1000);
                    pushUpsBtn.setBackgroundResource(R.drawable.pause);
                }else{
                    isWorking=false;
                    sensorManager.unregisterListener(proximitySensorListener);
                    pushUpsBtn.setBackgroundResource(R.drawable.play);
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWorking=false;
                pushUpsBtn.setBackgroundResource(R.drawable.play);
                pushUpsTotal+=pushUpsCounter;
                pushUpsCounter = 0;
                tvPushUpsCounter.setText(String.valueOf(pushUpsCounter));
                sensorManager.unregisterListener(proximitySensorListener);

            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PUSHUPS, pushUpsCounter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        pushUpsCounter = savedInstanceState.getInt(PUSHUPS);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public static int getPushUpsTotal() {
        return pushUpsTotal;
    }


}

package academy.learnprogramming.nailedit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class StaminaActivity extends AppCompatActivity implements SensorEventListener, CompoundButton.OnCheckedChangeListener {
    public static final String TAG = "StaminaActivity";
    private SensorManager sensorManager;
    private TextView stepsSinceEnabling;
    private TextView stepsTotal;
    private Switch stepCounterSwitch;
    private boolean activityRunning;
    private static int stepsTotalCounter=25000;
    private int stepsSinceEnablingCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamina);

    stepsSinceEnabling = (TextView) findViewById(R.id.stepsSinceEnabling);
    stepsTotal = (TextView) findViewById(R.id.stepsTotal);
    stepCounterSwitch = (Switch) findViewById(R.id.stepCounterSwitch);
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    stepCounterSwitch.setOnCheckedChangeListener(this);
    stepsTotal.setText(String.valueOf(stepsTotalCounter));
    }

    protected void onResume(){
        super.onResume();
        activityRunning=true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Sensor is not available!", Toast.LENGTH_LONG).show();
        }
    }

    protected void onStop(){
        super.onStop();
        stepsTotalCounter += stepsSinceEnablingCounter;
        stepsSinceEnablingCounter =0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning){
            stepsSinceEnablingCounter = (int) event.values[0];
            stepsSinceEnabling.setText(stepsSinceEnablingCounter);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static int getStepsTotalCounter() {
        return stepsTotalCounter;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean b){
        if(stepCounterSwitch.isChecked()){
            activityRunning=true;
        }else{
            activityRunning=false;
        }
    }
}

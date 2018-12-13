package academy.learnprogramming.nailedit;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {
    public static final String TAG = "Stats Activity";
    private TextView strengthLevel;
    private TextView staminaLevel;
    private TextView willPowerLevel;
    private ProgressBar strengthBar;
    private ProgressBar staminaBar;
    private ProgressBar willPowerBar;

    private int staminaLvl=1;
    private int strengthLvl=1;
    private int willPowerLvl=1;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        strengthBar = new ProgressBar(this);
        staminaBar = new ProgressBar(this);

        strengthLevel = (TextView) findViewById(R.id.TVStrengthLvl);
        staminaLevel = (TextView) findViewById(R.id.TVStaminaLvl);
        willPowerLevel = (TextView) findViewById(R.id.TVWillPowerLvl);
        strengthBar = (ProgressBar) findViewById(R.id.progressBarStrength);
        staminaBar = (ProgressBar) findViewById(R.id.progressBarStamina);
        willPowerBar = (ProgressBar) findViewById(R.id.progressBarWillPower);


        calibrateBars();


        strengthLevel.setText("Lvl: " + String.valueOf(strengthLvl));
        staminaLevel.setText("Lvl: " + String.valueOf(staminaLvl));
        willPowerLevel.setText("Lvl: " + String.valueOf(willPowerLvl));


    }


    public void calibrateBars() {
        calibrateStrengthBar();
        calibrateStaminaBar();
        calibrateWillPowerBar();
    }



    public void calibrateStrengthBar() {
        int progress = StrengthActivity.getPushUpsTotal();
        while (progress > strengthBar.getMax()) {
            int getCurrentMax = strengthBar.getMax();
            strengthLvl++;
            strengthBar.setMax((int) (getCurrentMax * 1.4));
            progress -= strengthBar.getMax();
        }
        strengthBar.setProgress(progress);
        Log.i(TAG, "calibrateStrengthBar: " + strengthBar.getMax());
    }

    public void calibrateStaminaBar() {
        int progress = StaminaActivity.getStepsTotalCounter();
        staminaBar.setMax(10000);
        while (progress > staminaBar.getMax()) {
            int getCurrentMax = staminaBar.getMax();
            staminaLvl++;
            staminaBar.setMax((int) (getCurrentMax * 1.8));
            progress -= strengthBar.getMax();
        }
        staminaBar.setProgress(progress);
        Log.i(TAG, "calibrateStaminahBar: " + staminaBar.getMax());
    }

    public void calibrateWillPowerBar() {
        int progress = WillPowerActivity.getTasksCompletedCounter();
        willPowerBar.setMax(50);
        while (progress > willPowerBar.getMax()) {
            int getCurrentMax = willPowerBar.getMax();
            willPowerLvl++;
            willPowerBar.setMax((int) (getCurrentMax * 1.5));
            progress -= willPowerBar.getMax();
        }
        willPowerBar.setProgress(progress);
        Log.i(TAG, "calibrateWillPowerhBar: " + willPowerBar.getMax());
    }

}

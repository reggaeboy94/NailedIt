package academy.learnprogramming.nailedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainingActivity extends AppCompatActivity {
    public static final String TAG="TrainingActivity";
    //fields
    private Button strengthBtn;
    private Button staminaBtn;
    private Button willPowerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        strengthBtn = (Button) findViewById(R.id.strengthBtn);
        staminaBtn = (Button) findViewById(R.id.staminaBtn);
        willPowerBtn = (Button) findViewById(R.id.intelligenceBtn);

        configureTrainingMenuButtons();
    }

    //buttons configuration
    public void configureTrainingMenuButtons() {
        strengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrainingActivity.this, StrengthActivity.class));
            }

        });

        staminaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrainingActivity.this, StaminaActivity.class));
            }

        });
        willPowerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrainingActivity.this, WillPowerActivity.class));
            }

        });


    }
}
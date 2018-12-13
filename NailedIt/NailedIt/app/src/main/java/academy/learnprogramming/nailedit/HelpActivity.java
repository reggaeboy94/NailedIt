package academy.learnprogramming.nailedit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity{

    public static final String TAG="HelpActivity";
    private Button strengthBtn;
    private Button staminaBtn;
    private Button willPowerBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        strengthBtn = (Button) findViewById(R.id.StrengthHelpBtn);
        staminaBtn= (Button) findViewById(R.id.StaminaHelpBtn);
        willPowerBtn= (Button) findViewById(R.id.WillPowerHelpBtn);


    }

}

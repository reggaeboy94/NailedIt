package academy.learnprogramming.nailedit;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
public class MainActivity extends AppCompatActivity {
    //fields
    public static final String TAG = "MainActivity";
    private Button statsBtn;
    private Button trainingBtn;
    private Button achieveBtn;
    private Button helpBtn;
    private Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        statsBtn = (Button) findViewById(R.id.statsBtn);
        trainingBtn = (Button) findViewById(R.id.trainingBtn);
        achieveBtn = (Button) findViewById(R.id.achieveBtn);
        helpBtn = (Button) findViewById(R.id.helpBtn);
        logOutBtn = (Button) findViewById(R.id.logOutBtn);

        configureButtons();
        }

        //buttons configuration
        private void configureButtons(){
            statsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, StatsActivity.class));
                }
            });
            trainingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, TrainingActivity.class));
                }
            });
            achieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, AchievementsActivity.class));
                }
            });
            logOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
            helpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,HelpActivity.class));
                }
            });


        }

}







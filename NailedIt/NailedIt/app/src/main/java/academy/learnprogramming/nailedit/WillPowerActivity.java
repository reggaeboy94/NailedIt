package academy.learnprogramming.nailedit;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WillPowerActivity extends AppCompatActivity {
    public static final String TAG = "WillPowerActivity";
    private static final String TOTALTASKSCOMPLETED = "TOTALTASKSCOMPLETED";
    //fields
    public static int tasksCompletedCounter=236;
    private Button toDoListBtn;
    private TextView tasksCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_power);

        toDoListBtn = (Button) findViewById(R.id.toDoListBtn);
        tasksCompleted = (TextView) findViewById(R.id.tvCompletedTasks);
        tasksCompleted.setText(String.valueOf(tasksCompletedCounter));
        configureButtons();
    }

    //buttons configuration
    private void configureButtons(){
        toDoListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WillPowerActivity.this, ToDoListActivity.class));
            }
        });

    };

    public static void incrementTasksCompleted(){
        tasksCompletedCounter++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(TOTALTASKSCOMPLETED, tasksCompletedCounter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        tasksCompletedCounter = savedInstanceState.getInt(TOTALTASKSCOMPLETED);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public static int getTasksCompletedCounter() {
        return tasksCompletedCounter;
    }
}

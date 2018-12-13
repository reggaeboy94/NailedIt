package academy.learnprogramming.nailedit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {
    public static final String TAG = "AchievementActivity";
    private ListView listView;
    private TextView achievementsCounterDisplay;
    private ArrayList<Achievement> achievementList;
    private AchievementListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        Achievement achievement1 = new Achievement("Zrób 15 pompek");
        if (StrengthActivity.getPushUpsTotal() >= 15) {
            achievement1.setAchieved(true);
        }

        Achievement achievement2 = new Achievement("zrób 50 pompek");
        if (StrengthActivity.getPushUpsTotal() >= 50) {
            achievement2.setAchieved(true);
        }
        Achievement achievement3 = new Achievement("Zrób 1000 pompek");
        if (StrengthActivity.getPushUpsTotal() >= 1000) {
            achievement3.setAchieved(true);
        }
        Achievement achievement4 = new Achievement("Zrób 1000 kroków ");
        if(StaminaActivity.getStepsTotalCounter()>=1000){
            achievement4.setAchieved(true);
        }
        Achievement achievement5 = new Achievement("Zrób 10 000 kroków");
        if(StaminaActivity.getStepsTotalCounter()>=10000){
            achievement5.setAchieved(true);
        }

        Achievement achievement6 = new Achievement("Zrób 100 000 kroków");
        if(StaminaActivity.getStepsTotalCounter()>=100000){
            achievement6.setAchieved(true);
        }

        Achievement achievement7 = new Achievement("Wykonaj 10 zadań");
        if(WillPowerActivity.getTasksCompletedCounter()>=10){
            achievement7.setAchieved(true);
        }

        Achievement achievement8 = new Achievement("Wykonaj 100 zadań");
        if(WillPowerActivity.getTasksCompletedCounter()>=100){
            achievement8.setAchieved(true);
        }

        Achievement achievement9 = new Achievement("Wykonaj 1000 zadań");
        if(WillPowerActivity.getTasksCompletedCounter()>=1000){
            achievement9.setAchieved(true);
        }

        Achievement achievement10 = new Achievement("Osiągnij 2 poziom siły");
        if(StrengthActivity.getPushUpsTotal()>=250){
            achievement10.setAchieved(true);
        }




        achievementList = new ArrayList();

        addAchievement(achievement1);
        addAchievement(achievement2);
        addAchievement(achievement3);
        addAchievement(achievement4);
        addAchievement(achievement5);
        addAchievement(achievement6);
        addAchievement(achievement7);
        addAchievement(achievement8);
        addAchievement(achievement9);
        addAchievement(achievement10);



        achievementsCounterDisplay = (TextView) findViewById(R.id.achievementsCounter);
        listView = (ListView) findViewById(R.id.achievementsLV);
        adapter = new AchievementListAdapter(this, R.layout.achievement_list, achievementList);

        listView.setAdapter(adapter);
        achievementsCounterDisplay.setText(setAchievementCounterDisplay());
    }


    public void addAchievement(Achievement a) {
        achievementList.add(a);
    }



    public String setAchievementCounterDisplay(){
        int count=0;
        for(Achievement a : achievementList){
            if(a.isAchieved()){
                count++;
            }
        }

        return count +  "/" + String.valueOf(achievementList.size() );
    }

}




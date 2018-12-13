package academy.learnprogramming.nailedit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AchievementListAdapter extends ArrayAdapter<Achievement> {
    public static final String TAG = "ItemListAdapter";
    private Context mContext;
    private int mResource;

    public AchievementListAdapter(@NonNull Context context, int resource, @NonNull List<Achievement> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String achievementName = getItem(position).getDescription();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView achievementDescription = (TextView) convertView.findViewById(R.id.achievementNameTV);
        ImageView achievementStatus = (ImageView) convertView.findViewById(R.id.AchievementStatusIV);

        achievementDescription.setText(achievementName);
        if(getItem(position).isAchieved()){
            achievementStatus.setImageResource(R.drawable.checked);

        }

        return convertView;
    }
}

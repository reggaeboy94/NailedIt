package academy.learnprogramming.nailedit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ItemListAdapter extends ArrayAdapter<ToDoItem> {
    public static final String TAG = "ItemListAdapter";
    private Context mContext;
    private int mResource;

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull List<ToDoItem> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String itemName = getItem(position).getItemName();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView itemDescription = (TextView) convertView.findViewById(R.id.itemName2);
        Button confirmBtn = (Button) convertView.findViewById(R.id.confirmBtn);
        Button deleteBtn = (Button) convertView.findViewById(R.id.deleteHistoryBtn);

        itemDescription.setText(itemName);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(position));
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).setCompleted();
                WillPowerActivity.incrementTasksCompleted();
                remove(getItem(position));
                Log.i(TAG, "onClick: " + WillPowerActivity.tasksCompletedCounter);
            }
        });



        return convertView;
    }

public void checkStatus(){

}



    }


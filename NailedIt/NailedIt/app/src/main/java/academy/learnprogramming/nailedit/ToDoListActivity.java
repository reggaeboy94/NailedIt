package academy.learnprogramming.nailedit;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ToDoListActivity extends AppCompatActivity {
    //fields
    private final String TAG = "ToDoListActivity";
    private Button addItemBtn;
    private EditText itemText;
    private ArrayList<ToDoItem> itemList;
    private ItemListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        addItemBtn = (Button) findViewById(R.id.addItemBtn);
        itemText = (EditText) findViewById(R.id.tfItemName);
        ListView listView = (ListView) findViewById(R.id.lvToDoList);

        itemList = new ArrayList<ToDoItem>();
        adapter = new ItemListAdapter(this, R.layout.item_list, itemList);

        //calling methods required on create state
        configureButtons();
        listView.setAdapter(adapter);
        itemText.setText("");
    }

    //button configuration for view
    private void configureButtons() {
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (adapter.getCount() <= 5) {
                    if (!itemText.getText().toString().equals("")) {
                        ToDoItem item = new ToDoItem(itemText.getText().toString());
                        itemList.add(item);
                        itemText.setText("");
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ToDoListActivity.this, "Pole jest puste!", Toast.LENGTH_SHORT).show();
                    }
                } else if (adapter.getCount() > 5) {
                    Toast.makeText(ToDoListActivity.this, "Zbyt dużo rozpoczętych zadań!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    ;


}


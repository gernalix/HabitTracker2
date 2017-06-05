package com.example.android.habittracker;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> habitsList = new ArrayList<>();
    public static ArrayList<String> habitsListName = new ArrayList<>();
    private EditText habitNameTF;
    private Button submitBtn;
    private ListView habitsListView;
    private ArrayAdapter<String> arrayAdapter;
    private HabitDbHelper mHelper;
    private Button deleteTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        habitsListView = (ListView) findViewById(R.id.habitsListView);
        habitNameTF = (EditText) findViewById(R.id.habitNameTF);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        deleteTable = (Button) findViewById(R.id.deleteTable);

        mHelper = new HabitDbHelper(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, habitsList);
        habitsListView.setAdapter(arrayAdapter);

        getFromDB();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitName = habitNameTF.getText().toString();
                mHelper.insert(habitName);
                getFromDB();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        deleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitsList.clear();
                habitsListName.clear();
                mHelper.deleteHabitsDB();
                getFromDB();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        habitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mHelper.update(position);

                getFromDB();
            }


        });
    }

    private void getFromDB() {
        habitsList.clear();
        habitsListName.clear();
        try {
            mHelper.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayAdapter.notifyDataSetChanged();
    }
}


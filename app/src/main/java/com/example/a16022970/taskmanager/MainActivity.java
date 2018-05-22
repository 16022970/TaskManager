package com.example.a16022970.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;
    DBHelper db;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd1);
        lv = (ListView) findViewById(R.id.lv);
        db = new DBHelper(this);
        db.getWritableDatabase();
        al = db.getAllTask();
        db.close();

        aa = new TaskArrayAdapter(MainActivity.this, R.layout.row, al);
        aa.setNotifyOnChange(true);
        lv.setAdapter(aa);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        AddActivity.class);
                startActivityForResult(i, 9);
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9) {
            btnAdd.performClick();
        }
    }
}

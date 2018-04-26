package com.example.louis.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ModuleLibrary extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_assigned_modules);

        ListView listView =(ListView)findViewById(R.id.myList);
        String[] items={"Databases", "Programming", "Web Design", "ITPM"}; //list of modules
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);

        final Intent i1 = new Intent(getApplicationContext(), VideoActivity.class); //html
        final Intent i2 = new Intent(getApplicationContext(), VideoActivity1.class);//java
        final Intent i3 = new Intent(getApplicationContext(), VideoActivity2.class);//database
        final Intent i4 = new Intent(getApplicationContext(), VideoActivity3.class);//itpm

        //when a module is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //adapterView
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //databases
                if(i==0) {
                    System.out.println(arrayList.toString());
                    Toast.makeText(getApplicationContext(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                    startActivity(i3);
                }
                //programming
                if(i==1) {
                    System.out.println(arrayList.toString());
                    Toast.makeText(getApplicationContext(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                    startActivity(i2);
                }
                //html
                if(i==2) {
                    System.out.println(arrayList.toString());
                    Toast.makeText(getApplicationContext(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                    startActivity(i1);
                }
                //IT project Management
                if(i==3) {
                    System.out.println(arrayList.toString());
                    Toast.makeText(getApplicationContext(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                    startActivity(i4);
                }

            }
        });
    }
}

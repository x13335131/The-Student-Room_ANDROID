package com.example.louis.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_menu);
        //declaring and initialising
        Button b1= (Button) findViewById(R.id.getAverageBtn);
        Button b2= (Button) findViewById(R.id.assignedModBtn);
        Button b3 = (Button) findViewById(R.id.instantMsg);
        //if get average btn is clicked do:
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(getApplicationContext(), CalcAverage.class);
                    startActivity(i1);

            }
        });
        //if module library btn is clicked do:
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getApplicationContext(), ModuleLibrary.class);
                startActivity(i1);

            }
        });//when instant messaging btn is clicked do:
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(getApplicationContext(), RealTimeChat.class);
                startActivity(i3);

            }
        });
    }
}

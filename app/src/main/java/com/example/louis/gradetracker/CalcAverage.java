package com.example.louis.gradetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalcAverage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_grade);

    }
    public void onButtonClick(View v) {

        //declaring and instantiating variables (grade side)
        EditText no1 = (EditText) findViewById(R.id.no1);
        EditText no2 = (EditText) findViewById(R.id.no2);
        EditText no3 = (EditText) findViewById(R.id.no3);
        EditText no4 = (EditText) findViewById(R.id.no4);

        //declaring and instantiating variables (weight side)
        EditText weight1 = (EditText) findViewById(R.id.weight1ET);
        EditText weight2 = (EditText) findViewById(R.id.weight2ET);
        EditText weight3 = (EditText) findViewById(R.id.weight3ET);
        EditText weight4 = (EditText) findViewById(R.id.weight4ET);
        TextView textview =(TextView) findViewById(R.id.resultTV);

        try {
                //grab numbers from fields
                double num1 = Double.parseDouble(no1.getText().toString());
                double num2 = Double.parseDouble(no2.getText().toString());
                double num3 = Double.parseDouble(no3.getText().toString());
                double num4 = Double.parseDouble(no4.getText().toString());

                Double weightNum1 = Double.parseDouble(weight1.getText().toString());
                Double weightNum2 = Double.parseDouble(weight2.getText().toString());
                Double weightNum3 = Double.parseDouble(weight3.getText().toString());
                Double weightNum4 = Double.parseDouble(weight4.getText().toString());

                //calculations
                double result = (weightNum1 * num1 + weightNum2 * num2 + weightNum3 * num3 + weightNum4 * num4) / (weightNum1 + weightNum2 + weightNum3 + weightNum4);
                String r = String.format("%.2f", result);
                textview.setText("You're average is " + r + "%");

      }//exception
      catch (Exception e){
          System.out.println("exception: "+e);
          textview.setText("check all feilds are filled out");
      }

    }
}


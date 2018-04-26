package com.example.louis.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    //declaring variables
    EditText email ;
    EditText password;
    Button b1,b2;
    String e,p;
    List<String> errorList = new ArrayList<String>();
    TextView tx1;
    int counter = 3;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        b1 = (Button)findViewById(R.id.login_btn);
        email = (EditText) findViewById(R.id.emailLogET);
        password = (EditText) findViewById(R.id.password_et);
        tx1 = (TextView)findViewById(R.id.attemptNo_tv);
        tx1.setVisibility(View.GONE);//counter text view-unable to see to users until incorrect credentials are entered
        //login btn
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });

        final Button signupBtn = (Button) findViewById(R.id.signup_btn);
        //bring to sign up screen
        signupBtn.setOnClickListener(new View.OnClickListener(){

                                         @Override
                                         public void onClick(View v) {
                                             Intent i3 = new Intent(getApplicationContext(), SignUpActivity.class);
                                             startActivity(i3);
                                         }
                                     }
        );
    }
    public void Login(){
        initialize();//calling initialisation method
        if(!validate()){//if not valid
            Toast.makeText(this,"Signup has failed", Toast.LENGTH_SHORT).show();
        }else {//if valid
            mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if ((e.equals("admin@gmail.com") || e.equals("Admin@gmail.com")) &&
                                p.equals("Admin12345")) { //if admin
                            Toast.makeText(getApplicationContext(),
                                    "Redirecting admin...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),RealTimeChat.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent); //bring to admin page
                        } else { //other user
                            Toast.makeText(getApplicationContext(),
                                    "Redirecting...", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this, UserMainMenu.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1); //redirects to user main menu
                        }
                    } else {//get error message if unsuccessful login
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        tx1.setVisibility(View.VISIBLE);//counter number text is set to visible
                        //tx1.setBackgroundColor(Color.RED);
                        counter--; //counter goes down
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            b1.setEnabled(false);//login button is disabled if login fails 3 times
                        }
                    }
                }
            });
        }
    }
    //validation of fields
    public boolean validate(){
        boolean valid = true;
        errorList.clear();
        if(e.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("Please enter valid email address");
            valid = false;
        }
        if(p.isEmpty()){
            password.setError("please enter a valid password");
            valid = false;
        }
        return valid;
    }
    //getting user input
    public void initialize(){
        e = email.getText().toString().trim();
        p = password.getText().toString().trim();

    }
}

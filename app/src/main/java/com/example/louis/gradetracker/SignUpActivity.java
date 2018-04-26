package com.example.louis.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    //declaring variables
    private FirebaseAuth mAuth;
    private EditText et_name,et_email, et_password, et_cpassword;
    private String name, email, password, cpassword;
    Button regBtn;
    List<String> errorList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //setting variables to certain feilds
        et_name = (EditText) findViewById(R.id.username_et);
        et_email = (EditText) findViewById(R.id.email_et);
        et_password = (EditText) findViewById(R.id.password_et);
        et_cpassword = (EditText) findViewById(R.id.cpassword_et);
        regBtn = (Button) findViewById(R.id.register_btn);
        mAuth = FirebaseAuth.getInstance();
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register();//call when button is clicked to validate the input
            }
        });
    }
    public void register(){
        initialize(); //initialize the input to string variables
        if(!validate()){//not valid
            Toast.makeText(this,"Signup has failed", Toast.LENGTH_SHORT).show();
        }
        else{//valid reg
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        onSignupSuccess();
                    }else{
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(),"You Are Already Registered", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
    }
    public void onSignupSuccess(){//redirects to new page on success
        //to do after valid input
        Toast.makeText(getApplicationContext(),
                "Sign Up Success Redirecting...",Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i1);
    }
        public boolean validate(){//vlidates fields
            Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
            Pattern lowerCasePatten = Pattern.compile("[a-z ]");
            Pattern digitCasePatten = Pattern.compile("[0-9 ]");
            boolean valid = true;
            errorList.clear();

            if(name.isEmpty()||name.length()>32){
                et_name.setError("Please Enter valid name");
                valid=false;
            }
            if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.setError("Please enter valid email address");
                valid = false;
            }
            if(password.isEmpty()){
                et_password.setError("please enter a valid password");
                valid = false;
            }
            if (password.length() < 8) {
                errorList.add("8 characters");
            valid = false;
        }
        if (!UpperCasePatten.matcher(password).find()) {
            errorList.add("one uppercase character");
            valid=false;
        }
        if (!lowerCasePatten.matcher(password).find()) {
            errorList.add("one lowercase character");
            valid=false;
        }
        if (!digitCasePatten.matcher(password).find()) {
            errorList.add("one digit character");
            valid=false;
        }
        if(!password.matches(cpassword)){
            et_password.setError("passwords do not match");
            valid = false;
        }
        if(cpassword.isEmpty()|| !cpassword.matches(password)){
            et_cpassword.setError("passwords do not match");
            valid = false;
        }
        /*for (String error : errorList) {
            set_password.setError(error);
        }*/
        if(errorList.isEmpty()) {
            System.out.println("no error");
        }else{
            et_password.setError("Password must have at least " + errorList.toString());
        }
        return valid;
    }
    public void initialize(){
        name = et_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        cpassword = et_cpassword.getText().toString().trim();
    }
}

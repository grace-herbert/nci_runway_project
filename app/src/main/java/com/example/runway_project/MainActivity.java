package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private String hEmail;
    private String hk;
    private String vaultID;

    private Database db =  new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference dbU = db.getDBU();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Log.v("Debug", "Emails in DB: " + String.valueOf((dbU.child("email")) + String.valueOf((dbU.child("hk")))));

        AppCompatButton showLPwdBtn = this.findViewById(R.id.showLPwd);
        AppCompatButton hideLPwdBtn = this.findViewById(R.id.hideLPwd);

        EditText email = this.findViewById(R.id.email);
        EditText pwd = this.findViewById(R.id.password);
        MaterialButton loginBtn = this.findViewById(R.id.loginBtn);
        Button tempBtn = this.findViewById(R.id.tempButton);
        Button homeBtn = this.findViewById(R.id.homeButton);

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Vault.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestOut t = new TestOut();
                t.searchDB("testingrunway@gmail.com");
//                t.searchDB("herbert.grace.c@gmail.com");
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });

        showLPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showLPwdBtn.setVisibility(View.INVISIBLE);
                hideLPwdBtn.setVisibility(View.VISIBLE);

            }
        });

        hideLPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                hideLPwdBtn.setVisibility(View.INVISIBLE);
                showLPwdBtn.setVisibility(View.VISIBLE);

            }
        });



//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");

        // set usable variables for the edittexts


        //get the texts from the edittexts and set them toString
        final String emailStrg = email.getText().toString();
        final String hkStrg = pwd.getText().toString();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {
//                Hashing h = new Hashing(emailStrg, hkStrg);
//                String hEmail = h.getHashEmail();
//                String hPwd = h.getHk();
                //if count is below 3
                if (count < 3) {
                    //if email or hk strings are empty send msg and count 1 and clear edittexts
                    if (emailStrg.equals(" ") || hkStrg.equals(" ")) {
                        Toast.makeText(MainActivity.this, "Please email and password.", Toast.LENGTH_SHORT).show();
                        email.setText(" ");
                        pwd.setText(" ");
                        count++;
                     //if email and hk correct send home  and reset count
//                    } else if (BCrypt.checkpw(emailStrg, String.valueOf((dbU.child("email")))) && (BCrypt.checkpw(hkStrg, String.valueOf((dbU.child("hk")))))) {
                    } else if (BCrypt.checkpw(emailStrg, String.valueOf((dbU.orderByChild("email"))))){
                        System.out.println(String.valueOf((dbU.orderByChild("email"))));
//                        dbU.orderByChild("email").equalTo(BCrypt.checkpw(emailStrg, String.valueOf((dbU.child("email")))));
                        Toast.makeText(MainActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                        count = 0;
                    }else{
                        // if email or password incorrect
                        Toast.makeText(MainActivity.this, "Please enter a valid email and password.", Toast.LENGTH_SHORT).show();
                        count++;
                        email.setText(" ");
                        pwd.setText(" ");
                    }
                  // if count > 3
                }else{
                    //send msg and send to locked page
                    Toast.makeText(MainActivity.this, "3 failed attempts. Account locked.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Locked.class);
                    startActivity(intent);
                    //set time the incident has occurred here
                }
            }
        });


        // for the Register text send to reg 1 page
        TextView regLink = this.findViewById(R.id.regTxt);

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register_1.class);
                startActivity(intent);
            }
        });
    }
}
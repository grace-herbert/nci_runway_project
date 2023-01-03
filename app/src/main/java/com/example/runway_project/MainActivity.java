package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Boolean correctP = false;
    private Boolean triggerOut = false;
    private Database db =  new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference dbU = db.getDBU();
    public static String vltID;
    public static final String SHAREDV = "SharedV";


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
//                Intent intent = new Intent(MainActivity.this, Register_2.class);
//                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TestOut t = new TestOut();
//                t.login("herbert.grace.c@gmail.com", "TestThisN0w!");
//                t.login(emailStrg, hkStrg);
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



        loginBtn.setOnClickListener(new View.OnClickListener() {

            int count = 0;
            @Override
            public void onClick(View v) {
                //get the texts from the edittexts and set them toString
                final String emailStrg = email.getText().toString().trim();
                final String hkStrg = pwd.getText().toString().trim();
                System.out.println("EmailStrg = " + emailStrg + ", hkStrg = " + hkStrg);
                //if count is below 3
                if (count < 2) {
                    login(emailStrg, hkStrg, email, pwd);

                    if(triggerOut){
                        count++;
                        Toast.makeText(MainActivity.this, "Please enter a valid email and password.", Toast.LENGTH_SHORT).show();
                        email.setText("");
                        pwd.setText("");

                        System.out.println(count);
                    }
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

    private boolean login(String plainP, String pwdInput, EditText email, EditText pwd){

        if(email != null && pwd != null) {
            Query q = dbU.orderByChild("email");

            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    SharedPreferences vltSP = getSharedPreferences(SHAREDV, Context.MODE_PRIVATE);
                    if (snapshot.exists()) {

                            while (!correctP && !triggerOut) {
                                for (DataSnapshot searchEml : snapshot.getChildren()) {
                                    String emailVal = searchEml.child("email").getValue(String.class);
                                    if (emailVal != null) {
                                        try {
                                            if (BCrypt.checkpw(plainP, emailVal)) {
                                                String hkCheck = searchEml.child("hk").getValue(String.class);
                                                vltID = searchEml.child("vaultID").getValue(String.class);
                                                if (BCrypt.checkpw(pwdInput, hkCheck)) {
                                                    System.out.println("hkCheck true");
                                                    correctP = true;
//                                                    setVltID(vID);
                                                    SharedPreferences.Editor vSPEditor = vltSP.edit();
                                                    vSPEditor.putString("vltID", vltID);
                                                    vSPEditor.commit();
                                                    System.out.println("hk : " + hkCheck);
                                                    System.out.println("vlt : " + vltID);
                                                    System.out.println("Email = " + emailVal);
                                                    Toast.makeText(MainActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                                    startActivity(intent);

                                                } else {
                                                    triggerOut = true;
                                                    System.out.println("hkCheck false");
                                                    correctP = false;
                                                    Toast.makeText(MainActivity.this, "Please enter a valid email and password.", Toast.LENGTH_SHORT).show();
                                                    email.setText("");
                                                    pwd.setText("");
                                                    break;

                                                }
                                                break;
                                            } else {
                                                System.out.println("BCrypt email doesn't match. i = " );

                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Whoops!");
                                        }
                                    } else {
                                        System.out.println("Email null");
                                    }

                                }
                            }
                    } else {
                        System.out.println("Snapshot not exists");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            Toast.makeText(MainActivity.this, "Please email and password.", Toast.LENGTH_SHORT).show();
        }
            return correctP;
    }

    private void setVltID(String vID){
        this.vltID = vID;
    }

    public String getVltID(){
        return this.vltID;
    }

}

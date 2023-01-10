package com.example.runway_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    private Boolean correctP = false;
    private Boolean triggerOut = false;
    private Database db =  new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference dbU = db.getDBU();
    private static String vltID;
    private static final String SHAREDV = "SharedV";


    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //change colour of actionbar
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
            //remove name from Actionbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            SharedPreferences lockSP = getApplicationContext().getSharedPreferences("Lock", Context.MODE_PRIVATE);
            SharedPreferences.Editor lockSPEditor = lockSP.edit();
            Boolean isLocked = lockSP.getBoolean("isLocked", false);

            if (!isLocked) {

            AppCompatButton showLPwdBtn = this.findViewById(R.id.showLPwd);
            AppCompatButton hideLPwdBtn = this.findViewById(R.id.hideLPwd);

            EditText email = this.findViewById(R.id.email);
            EditText pwd = this.findViewById(R.id.password);
            MaterialButton loginBtn = this.findViewById(R.id.loginBtn);
//            Button tempBtn = this.findViewById(R.id.tempButton);
//            Button homeBtn = this.findViewById(R.id.homeButton);


////            tempBtn.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent intent = new Intent(MainActivity.this, Locked.class);
////                    startActivity(intent);
//////                Intent intent = new Intent(MainActivity.this, Register_2.class);
//////                startActivity(intent);
////                }
////            });
//
//            homeBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                TestOut t = new TestOut();
////                t.login("herbert.grace.c@gmail.com", "TestThisN0w!");
////                t.login(emailStrg, hkStrg);
////                t.searchDB("herbert.grace.c@gmail.com");
//                    lockSPEditor.putBoolean("isLocked", false);
//                    lockSPEditor.commit();
//                    Intent intent = new Intent(MainActivity.this, Home.class);
//                    startActivity(intent);
//                }
//            });

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
                    //if count is below 3
                    if (count < 2) {
                        login(emailStrg, hkStrg, email, pwd);

                        if (triggerOut) {
                            count++;
                            Toast.makeText(MainActivity.this, "Please enter a valid email and password.", Toast.LENGTH_SHORT).show();
                            email.setText("");
                            pwd.setText("");

                            Log.d("Debug", "Count" + count);
                        }
                    } else {
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
        }else{
            Intent intent = new Intent(MainActivity.this, Locked.class);
            startActivity(intent);
        }
    }

        private boolean login (String plainP, String pwdInput, EditText email, EditText pwd){

            if (email != null && pwd != null) {
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
                                                    Log.d("Debug", "p22");
                                                    correctP = true;
                                                    SharedPreferences.Editor vSPEditor = vltSP.edit();
                                                    vSPEditor.putString("vltID", vltID);
                                                    vSPEditor.commit();
                                                    Toast.makeText(MainActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                                    startActivity(intent);

                                                } else {
                                                    triggerOut = true;
                                                    correctP = false;
                                                    Toast.makeText(MainActivity.this, "Please enter a valid email and password.", Toast.LENGTH_SHORT).show();
                                                    email.setText("");
                                                    pwd.setText("");
                                                    break;

                                                }
                                                break;
                                            }
                                        } catch (IllegalArgumentException e) {
                                            Log.e("ERROR", "ill33");
                                        }
                                    }

                                }
                            }
                        } else {
                            Log.d("Debug", "!s22");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("ERROR", "d33");
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Please email and password.", Toast.LENGTH_SHORT).show();
            }
            return correctP;
        }

        private void setVltID (String vID){
            this.vltID = vID;
        }

        public String getVltID () {
            return this.vltID;
        }

}

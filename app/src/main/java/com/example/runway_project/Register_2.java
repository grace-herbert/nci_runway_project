package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_2 extends AppCompatActivity {

    Database db;
    DatabaseReference dbRef;


    public Register_2() {
        this.db = new Database();
        this.dbRef = db.getRefDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        AppCompatButton showEmlBtn = this.findViewById(R.id.showEml);
        AppCompatButton hideEmlBtn = this.findViewById(R.id.hideEml);
        AppCompatButton showPwdBtn = this.findViewById(R.id.showPwd);
        AppCompatButton hidePwdBtn = this.findViewById(R.id.hidePwd);
        AppCompatButton showPwdCBtn = this.findViewById(R.id.showPwdC);
        AppCompatButton hidePwdCBtn = this.findViewById(R.id.hidePwdC);

        EditText regEmail = this.findViewById(R.id.regEmail);
        EditText pwd = this.findViewById(R.id.regPwd);
        EditText pwdConf = this.findViewById(R.id.regReEntPwd);
        MaterialButton reg2Btn = this.findViewById(R.id.reg2Btn);

        //Methods to show/hide buttons

        showEmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regEmail.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showEmlBtn.setVisibility(View.INVISIBLE);
                hideEmlBtn.setVisibility(View.VISIBLE);

            }
        });

        hideEmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
                hideEmlBtn.setVisibility(View.INVISIBLE);
                showEmlBtn.setVisibility(View.VISIBLE);

            }
        });

        showPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showPwdBtn.setVisibility(View.INVISIBLE);
                hidePwdBtn.setVisibility(View.VISIBLE);

            }
        });

        hidePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                hidePwdBtn.setVisibility(View.INVISIBLE);
                showPwdBtn.setVisibility(View.VISIBLE);

            }
        });

        showPwdCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdConf.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showPwdCBtn.setVisibility(View.INVISIBLE);
                hidePwdCBtn.setVisibility(View.VISIBLE);

            }
        });

        hidePwdCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdConf.setTransformationMethod(PasswordTransformationMethod.getInstance());
                hidePwdCBtn.setVisibility(View.INVISIBLE);
                showPwdCBtn.setVisibility(View.VISIBLE);

            }
        });


        final String rgEmail = regEmail.getText().toString();
        final String rgPwd = pwd.getText().toString();
        final String rgPwdConf = pwdConf.getText().toString();



        reg2Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Can you see this now?");
                try {
                    emailHasValidFormat();
                    //emailIsValid = val.validEmail();
                } catch (NullPointerException e) {
                    System.out.println("Yep this is throwing a Null Pointer exception, but whyyyyyyy?");
                }


//                boolean emailFormat = val.emailHasValidFormat("test@test.ie");
//                System.out.println("Email format is: " + emailFormat);


                //set variables
//                final boolean emailFormat;
//                final boolean emailIsValid;
//                final boolean pwdIsValid;
//                //use methods to validate inputs
//                emailFormat = val.emailHasValidFormat(rgEmail);
//                emailIsValid = val.validEmail();
//                pwdIsValid = val.passwordValid(rgPwd,rgPwdConf);
//                //if inputs are valid
//                if(emailFormat && emailIsValid && pwdIsValid){
//                    //Generator object
//                    Gen g = new Gen();
//                    //Generate ID
//                    g.setIds();
//                    String id = g.computeGen();
//                    System.out.println(id);
//                    //Determine if ID already exists, if it doesn't set the values in the DB and enter home
//                    dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.child("iD").getValue().equals(id)) {
//                                Toast.makeText(Register_2.this, "Error. Please try again", Toast.LENGTH_SHORT).show();
//                            } else {
//                                dbRef.child("email").setValue(rgEmail);
//                                dbRef.child("hk").setValue(rgPwd);
//                                dbRef.child("id").setValue(id);
//                                Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(Register_2.this, Home.class);
//                                startActivity(intent);
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Toast.makeText(Register_2.this, "Error: " + error, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }else{
//                    Toast.makeText(Register_2.this, "Invalid information, please try again.", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

//    public void emailHasValidFormat() {
////        boolean emailIsValid;
////        String regex = "[a-zA-Z]";
////        emailIsValid = Pattern.matches(regex, email);
////        Matcher mtr = ptn.matcher(email);
////        emailIsValid = mtr.find();
//        System.out.println("email");
//
//    }

    private boolean validEmail() {
            if (!regEmail.equals(" ")) {
                dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String emailCk = snapshot.child("email").getValue().toString();
                        if (emailCk.equals(rgEmail)) {
                            isValid = false;
                        } else {
                            isValid = true;
                        }
                    }@Override
                    public void onCancelled(@NonNull DatabaseError error) {
                            isValid = false;
                    }
                });
                }else{
                Toast.makeText(this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                            isValid = false;
                }
            return isValid;
        }
    }

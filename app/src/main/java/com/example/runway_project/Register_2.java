package com.example.runway_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Register_2 extends AppCompatActivity  {
    private String email;
    private String hk;
    private String vaultID;
    private boolean isValidated;
    FirebaseAuth firebaseAuth;
    FirebaseUser fUser;
    Database db;
    DatabaseReference dbRef;
    DatabaseReference dbU;
//    ValidateCallback callback;


    public Register_2() {
        this.db = new Database();
        this.dbRef = db.getRefDB();
        this.dbU = db.getDBU();
    }

    public void setIsValidated(boolean bool) {
        this.isValidated = bool;
    }



    public boolean getIsValidated() {
        return this.isValidated;
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


        reg2Btn.setOnClickListener(new View.OnClickListener() {


            boolean emailIsValid;
            boolean eValidFormat;
            boolean validPwd;
            boolean methodCalled;
//            boolean isValidated;


            @Override
            public void onClick(View v) {
                final String rgEmail = regEmail.getText().toString();
                final String rgPwd = pwd.getText().toString();
                final String rgPwdConf = pwdConf.getText().toString();

                validateAndSend(rgEmail,rgPwd,rgPwdConf);
                regEmail.setText(" ");
                pwd.setText(" ");
                pwdConf.setText(" ");

                Log.v("Debug", "rgEmail: " + rgEmail + "\nrgPwd: " + rgPwd + "\nrgPwdConf: " + rgPwdConf);
                Log.v("Debug", " R validation bool 2: " + emailIsValid);

            }


        });

    }

    private User user;

    //@Override
    private void validateAndSend(String email, String pwd, String pwdC) {
        dbU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hashing hsh = new Hashing(email, pwd, pwdC);
                //Generator object
                Gen g = new Gen();
                //Generate ID
                String userID = g.computeGen();
                Validation vl = new Validation();
                if (!email.isEmpty() || email != null || vl.emailHasValidFormat(email) || vl.validEmail(email)) {
                    String emailChk = snapshot.child("userID").child("email").getValue().toString();
                    if (emailChk.equals(hsh.getHashEmail())) {
                        System.out.println("emailChk.equals(email)= " + emailChk.equals(hsh.getHashEmail()));
                        System.out.println(hsh.getHashEmail());
                        System.out.println("Match was found in DB");
                    } else {
                        if (vl.passwordValid(pwd, pwdC)) {
                            System.out.println("This is a valid email, it was not empty and was not found in the DB. The password was also valid");
                            // get vaultID
                            String vaultId = g.computeGen();
                            System.out.println(vaultId);
                            //Determine if ID already exists, if it doesn't set the values in the DB and enter home
                            // if (snapshot.child("iD").getValue().equals(id)) {
                            // So if dbU
                            if (snapshot.getValue().equals(userID)) {
                                //  Toast.makeText(context, "Error. Please try again", Toast.LENGTH_SHORT).show();
                                System.out.println("ID found in DB");
                            } else {
                                firebaseAuth = FirebaseAuth.getInstance();
                                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Log.v("Debug", "Email and Password going into firebaseAuth: " + email + " + " + pwd);
                                        fUser = firebaseAuth.getCurrentUser();
                                        if(fUser != null){
                                            fUser.sendEmailVerification();
                                            Toast.makeText(Register_2.this, "Email verification has been sent. Please verify your email to continue.", Toast.LENGTH_LONG).show();
                                            Log.v("Debug", "Email sent to " + fUser.getEmail());
                                            Intent intent = new Intent(Register_2.this, AwaitingVerification.class);
                                            startActivity(intent);
                                            System.out.println(fUser);
                                        }
                                    }
                                });


                            }
                        }

                    }
                } else {
                    System.out.println("Invalid information, please try again.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error: " + error);
            }
        });

    }

    public User getUser(){
        return this.user;
    }

    public FirebaseUser getFUser(){
        return this.fUser;
    }
}

//    //@Override
//    private void validateAndSend(String email, String pwd, String pwdC) {
//        dbU.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Hashing hsh = new Hashing(email, pwd, pwdC);
//                //Generator object
//                Gen g = new Gen();
//                //Generate ID
//                String userID = g.computeGen();
//                Validation vl = new Validation();
//                if (!email.isEmpty() || email != null || vl.emailHasValidFormat(email) || vl.validEmail(email)) {
//                    String emailChk = snapshot.child("userID").child("email").getValue().toString();
//                    if (emailChk.equals(hsh.getHashEmail())) {
//                        System.out.println("emailChk.equals(email)= " + emailChk.equals(hsh.getHashEmail()));
//                        System.out.println(hsh.getHashEmail());
//                        System.out.println("Match was found in DB");
//                    } else {
//                        if (vl.passwordValid(pwd, pwdC)) {
//                            System.out.println("This is a valid email, it was not empty and was not found in the DB. The password was also valid");
//                            // get vaultID
//                            String vaultId = g.computeGen();
//                            System.out.println(vaultId);
//                            //Determine if ID already exists, if it doesn't set the values in the DB and enter home
//                            // if (snapshot.child("iD").getValue().equals(id)) {
//                            // So if dbU
//                            if (snapshot.getValue().equals(userID)) {
//                                //  Toast.makeText(context, "Error. Please try again", Toast.LENGTH_SHORT).show();
//                                System.out.println("ID found in DB");
//                            } else {
////                                Register_2 r2 = new Register_2();
////                                r2.setIsValidated(true);
//                                User user = new User(hsh.getHashEmail(), hsh.getHk(), vaultId);
//                                String pushUser = dbU.push().getKey();
////                                System.out.println(pushUser);
//                                dbU.child(pushUser).push().setValue(user);
//                                System.out.println("Sent to DB");
////                                Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
////                                Intent intent = new Intent(Register_2.this, Home.class);
////                                startActivity(intent);
//                                firebaseAuth = FirebaseAuth.getInstance();
//                                firebaseAuth.createUserWithEmailAndPassword(email, pwd);
//                                fUser = firebaseAuth.getCurrentUser();
//                                if(fUser != null){
//                                    fUser.sendEmailVerification();
//                                    Toast.makeText(Register_2.this, "Registered! And email verification sent.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Register_2.this, AwaitingVerification.class);
//                                    startActivity(intent);
//                                    System.out.println(fUser);
//                                }
//
//                            }
//                        }
//
//                    }
//                } else {
//                    System.out.println("Invalid information, please try again.");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("Error: " + error);
//            }
//        });
//
//    }
//}

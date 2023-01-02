package com.example.runway_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Register_2 extends AppCompatActivity {
    private String hshEmail;
    private String hk;
    private boolean isValidated;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser fUser;
    private Database db;
    private DatabaseReference dbRef;
    private DatabaseReference dbU;


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

        SharedPreferences sharedPreferences = getSharedPreferences("SendPrefs", Context.MODE_PRIVATE);


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
                final String rgEmail = regEmail.getText().toString().trim();
                final String rgPwd = pwd.getText().toString().trim();
                final String rgPwdConf = pwdConf.getText().toString().trim();

                Gen g = new Gen();
                String vaultId = g.computeGen();

                SharedPreferences.Editor sPEditor = sharedPreferences.edit();
                Hashing hsh = new Hashing(rgEmail, rgPwd);
                sPEditor.putString("hshEmail", hsh.getHashEmail());
                sPEditor.putString("hshk", hsh.getHk());
                sPEditor.putString("vaultID", vaultId);

                sPEditor.commit();

                validateAndSend(rgEmail, rgPwd, rgPwdConf);
                regEmail.setText(" ");
                pwd.setText(" ");
                pwdConf.setText(" ");

                Log.v("Debug", "rgEmail: " + rgEmail + "\nrgPwd: " + rgPwd + "\nrgPwdConf: " + rgPwdConf);
                Log.v("Debug", " R validation bool 2: " + emailIsValid);

            }


        });

    }


    //@Override
    private void validateAndSend(String email, String pwd, String pwdC) {
        Validation vl = new Validation();
        vl.validEmail(email);
        dbU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hashing hsh = new Hashing(email, pwd, pwdC);

                if (email != null && vl.emailHasValidFormat(email)) {
                    if (!vl.getIsEmailFound()) {
                        System.out.println("Valid email is valid");
                        if (vl.passwordValid(pwd, pwdC)) {
                            System.out.println("This is a valid email, it was not empty and was not found in the DB. The password was also valid");

                            System.out.println("We're in.");
                            firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Log.v("Debug", "Email and Password going into firebaseAuth: " + email + " + " + pwd);
                                    fUser = firebaseAuth.getCurrentUser();
                                    if (fUser != null) {
                                        fUser.sendEmailVerification();
                                        Toast.makeText(Register_2.this, "Email verification has been sent. Please verify your email to continue.", Toast.LENGTH_LONG).show();
                                        Log.v("Debug", "Email sent to " + fUser.getEmail());
                                        Intent intent = new Intent(Register_2.this, AwaitingVerification.class);
                                        intent.putExtra("hshEmail", hshEmail);
                                        startActivity(intent);
                                        System.out.println(fUser);
                                    }
                                }
                            });
                        } else {
                            System.out.println("Password not valid");
                        }
                    } else {
                        System.out.println("Email is not valid");
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

    public void setHshEmail(String hshEm) {
        this.hshEmail = hshEm;
    }


    public String getHshEmail() {
        return this.hshEmail;
    }

}
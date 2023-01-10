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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnSuccessListener;
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
    private Database db = new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference dbU = db.getDBU();


    public void setIsValidated(boolean bool) {
        this.isValidated = bool;
    }


    public boolean getIsValidated() {
        return this.isValidated;
    }

    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
                        Log.d("Debug","e22");
                        if (vl.passwordValid(pwd, pwdC)) {
                            Log.d("Debug","p22");
                            Log.d("Debug","e22");
                            firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Log.v("Debug", "fi22");
                                    fUser = firebaseAuth.getCurrentUser();
                                    if (fUser != null) {
                                        fUser.sendEmailVerification();
                                        Toast.makeText(Register_2.this, "Email verification has been sent. Please verify your email to continue.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register_2.this, AwaitingVerification.class);
                                        intent.putExtra("hshEmail", hshEmail);
                                        startActivity(intent);
                                    }
                                }
                            });
                        } else {
                            Log.d("Debug","p33");
                        }
                    } else {
                        Log.d("Debug","e33");
                    }
                } else {
                    Log.d("Debug","i33");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR","d33");
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
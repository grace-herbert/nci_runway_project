package com.example.runway_project;

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

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Register_2 extends AppCompatActivity  {
    private String email;
    private String hk;
    private String vaultID;
    private boolean isValidated;
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

//    @Override
//    protected void onDestroy(){
//        if(isValidated){
//            Log.v("Debug", " R validation bool Destroy: " + isValidated);
//            Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Register_2.this,Home.class);
//            startActivity(intent);
//            super.onDestroy();
//        }
//    }

//    private void checkValidation(String email, String pwd, String pwdC){
////        Validation validate = new Validation();
////        validate.validateAndSend(email, pwd, pwdC, Validate validate);
//        onDestroy();
//    }


    public boolean getIsValidated() {
        return this.isValidated;
    }

//    private synchronized void nextPage(String email, String pwd, String pwdC, ValidateCallback callback){
//        Validation vl = new Validation();
//        vl.validate(email, pwd, pwdC, callback);
//        vl.setCallback(this);
//        boolean isValid = getIsValidated();
//        Log.v("Debug", " R validation bool 1: " + isValid);
//        if(isValid){
//            Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Register_2.this,Home.class);
//            startActivity(intent);
//        }else{
//            Toast.makeText(Register_2.this, "Something went wrong! Account did not register.", Toast.LENGTH_SHORT).show();
//        }
//    }


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


//                final boolean emailIsValid;
//                Validation val = new Validation();
//                String test = "test";
//                // check email is valid
//                Log.v("Debug", "Test: " + test);
                Log.v("Debug", "rgEmail: " + rgEmail + "\nrgPwd: " + rgPwd + "\nrgPwdConf: " + rgPwdConf);

//                @Override
//                public void onComplete(checkValidation(rgEmail, rgPwd, rgPwdConf)) {
//                    nextPage();
//                }
//
//                checkValidation(rgEmail, rgPwd, rgPwdConf);
//                nextPage(rgEmail, rgPwd, rgPwdConf, callback.validateCallback(false) );
                Log.v("Debug", " R validation bool 2: " + emailIsValid);


////                boolean isValidated = val.getIsValidated();
//                Log.v("Debug", " R validation bool: " + isValidated);
//                if(isValidated){
//                    Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Register_2.this,Home.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(Register_2.this, "Something went wrong! Account did not register.", Toast.LENGTH_SHORT).show();
//                }
//test14@test.ie
                //TestThisN0w!
                //3718.0SitG2180.0


//
                //Validate email format

                //String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//                try {
//                    System.out.println(rgEmail);
//
//                    String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
//                    Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
////        emailIsValid = ptn.matcher(email).matches();
//                    Matcher mtr = ptn.matcher(rgEmail);
//                    eValidFormat = mtr.matches();
//                    System.out.println("Format was found to be: " + eValidFormat);
//
//                    //Is email Valid?
//
//                    if (!rgEmail.isEmpty() || rgEmail != null) {
//                        dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                String emailCk = snapshot.child("email").getValue().toString();
//                                if (emailCk.equals(rgEmail)) {
//                                    System.out.println("Match was found in DB");
//                                } else {
//                                    emailIsValid = true;
//                                    System.out.println("This is a valid email, it was not empty and was not found in the DB");
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                System.out.println("Twas cancelled.");
//                            }
//                        });
//                    } else {
//                        Toast.makeText(Register_2.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                    //Passwords
//
//                    //Pwd and pwdConf match
//                    //neither are empty
//                    //pwds contain what they need to
//
//
//                    if (!rgPwd.isEmpty() || rgPwd != null || !rgPwdConf.isEmpty() || rgPwdConf != null) {
//                        if (rgPwd.equals(rgPwdConf)) {
//                            if (rgPwd.matches("^[a-zA-Z .]*$")) {
//                                validPwd = true;
//                                System.out.println("Valid Password");
//                            } else {
//                                System.out.println("Invalid Password: Characters");
//                            }
//                        } else {
//                            System.out.println("Invalid Password: Passwords don't match");
//                            Toast.makeText(Register_2.this, "Please make sure the passwords match.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } finally {
//
//
////
////                //set variables
//////                final boolean emailFormat;
//////                final boolean emailIsValid;
//////                final boolean pwdIsValid;
////
////                //if inputs are valid
//                    if (eValidFormat && emailIsValid && validPwd) {
//                        System.out.println(eValidFormat + " " + emailIsValid + " " + validPwd);
//                        //Generator object
//                        Gen g = new Gen();
//                        //Generate ID
//                        g.setIds();
//                        String id = g.computeGen();
//                        System.out.println(id);
//                        //Determine if ID already exists, if it doesn't set the values in the DB and enter home
//                        dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.child("iD").getValue().equals(id)) {
//                                    Toast.makeText(Register_2.this, "Error. Please try again", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    dbRef.child("email").setValue(rgEmail);
//                                    dbRef.child("hk").setValue(rgPwd);
//                                    dbRef.child("id").setValue(id);
//                                    Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Register_2.this, Home.class);
//                                    startActivity(intent);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Toast.makeText(Register_2.this, "Error: " + error, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    } else {
//                        Toast.makeText(Register_2.this, "Invalid information, please try again.", Toast.LENGTH_SHORT).show();
//                        System.out.println("Something is false?");
//                    }
//                }
            }


        });

    }

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
//                                Register_2 r2 = new Register_2();
//                                r2.setIsValidated(true);
                                User user = new User(hsh.getHashEmail(), hsh.getHk(), vaultId);
                                String pushUser = dbU.push().getKey();
//                                System.out.println(pushUser);
                                dbU.child(pushUser).push().setValue(user);
                                System.out.println("Sent to DB");
                                Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register_2.this, Home.class);
//                                //Toast.makeText(this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//                                Register_2 r2 = new Register_2();
//                                Intent intent = new Intent(r2.getApplicationContext(), Home.class);
                                startActivity(intent);


//                                setIsValidated(true);
//                                Log.v("Debug", "R2 Is validated? " + isValidated);
                                //startActivity(intent);
                                //Toast.makeText(this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(String.valueOf(Home.class));
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                                startActivity(intent);

                            }
                        }

                    }
                } else {
                    // Toast.makeText(context, "Invalid information, please try again.", Toast.LENGTH_SHORT).show();
                    System.out.println("Invalid information, please try again.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //                    Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
                System.out.println("Error: " + error);
            }
        });

    }
}

//    @Override
//    public ValidateCallback validate(String email, String pwd, String pwdC, ValidateCallback callback) {
//        return null;
//    }

//    @Override
//    public ValidateCallback validate(String email, String pwd, String pwdC, ValidateCallback callback) {
//        return null;
//    }
//
//    @Override
//    public ValidateCallback validateCallback(boolean bool) {
//        return null;
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }

//    @Override
//    public ValidateCallback validate(String email, String pwd, String pwdC, ValidateCallback callback) {
//
//        return callback;
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }


//    public void emailHasValidFormat() {
////        boolean emailIsValid;
////        String regex = "[a-zA-Z]";
////        emailIsValid = Pattern.matches(regex, email);
////        Matcher mtr = ptn.matcher(email);
////        emailIsValid = mtr.find();
//        System.out.println("email");
//
//    }

//    private boolean validEmail() {
//            if (!regEmail.equals(" ")) {
//                dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String emailCk = snapshot.child("email").getValue().toString();
//                        if (emailCk.equals(rgEmail)) {
//                            isValid = false;
//                        } else {
//                            isValid = true;
//                        }
//                    }@Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                            isValid = false;
//                    }
//                });
//                }else{
//                Toast.makeText(this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
//                            isValid = false;
//                }
//            return isValid;
//        }
//    }

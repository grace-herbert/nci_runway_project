package com.example.runway_project;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation extends Register_2 {
    Database db;
    DatabaseReference dbRef;
    DatabaseReference dbU;

    public Validation() {
        this.db = new Database();
        this.dbRef = db.getRefDB();
        this.dbU = db.getDBU();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register2);
//
//    }
//
//    Register_2 r2 = new Register_2();
//    EditText regEmail = r2.findViewById(R.id.regEmail);
//    EditText pwd = r2.findViewById(R.id.regPwd);
//    EditText pwdConf = r2.findViewById(R.id.regReEntPwd);
//    MaterialButton reg2Btn = r2.findViewById(R.id.reg2Btn);
////
    Context context = this;
////
//    final String rgEmail = regEmail.getText().toString();
//    final String rgPwd = pwd.getText().toString();
//    final String rgPwdConf = pwdConf.getText().toString();
//
//    boolean isValid;

        public void testThis() {
            System.out.println("How about now?");
        }


        //check email address is valid email
        //check is not empty
        //check is not in the system

//    public void emailHasValidFormat(String email){
//        boolean emailIsValid;
//        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//        Pattern ptn = Pattern.compile(regex);
//        Matcher mtr = ptn.matcher(email);
//        emailIsValid = mtr.matches();
//        System.out.println(mtr.matches());
//
//    }

//    public static boolean emailHasValidFormat(String email){
//            String emlRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
//            Pattern ptn = Pattern.compile(emlRegex, Pattern.CASE_INSENSITIVE);
//            Matcher mtr = ptn.matcher(email);
//            return mtr.find();
//    }

        //regex pattern found from https://www.youtube.com/watch?v=OOdO785p3Qo
        private boolean emailHasValidFormat(String email) {
            System.out.println(email);
        boolean emailIsValid;
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        emailIsValid = ptn.matcher(email).matches();
        Matcher mtr = ptn.matcher(email);
        emailIsValid = mtr.matches();
            System.out.println("Format was found to be: " + emailIsValid);
            return emailIsValid;
        }

//        boolean isValid;
//    public boolean validEmail(String email) {
//
//            dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String emailCk = snapshot.child("email").getValue().toString();
//                    if (emailCk.equals(email)) {
//                        isValid = false;
//                        System.out.println("Match was found in DB");
//                    } else {
//                        isValid = true;
//                        System.out.println("This is a valid email, it was not empty and was not found in the DB");
//                    }
//                }@Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    isValid = false;
//                    System.out.println("Twas cancelled.");
//                }
//            });
//            return isValid;
//        }
        boolean isValid;
        private boolean validEmail(String email) {

            if (!email.isEmpty() || email != null) {
                dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String emailCk = snapshot.child("email").getValue().toString();
                        if (emailCk.equals(email)) {
                            isValid = false;
                            System.out.println("Match was found in DB");
                        } else {
                            isValid = true;
                            System.out.println("This is a valid email, it was not empty and was not found in the DB");
                        }
                    }@Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        isValid = false;
                        System.out.println("Twas cancelled.");
                    }
                });
            }else{
                Toast.makeText(this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            return isValid;
        }

        //Pwd and pwdConf match
        //neither are empty
        //pwds contain what they need to
        private boolean passwordValid(String pwd1, String pwd2){
            boolean pwdIsValid = false;
            if(!pwd1.equals(" ") || !pwd2.equals(" ")){
                if(pwd1.equals(pwd2)){
                    if (pwd1.matches("^[a-zA-Z .]*$")){
                        pwdIsValid = true;
                        System.out.println("Valid Password- Goes through");
                    }else{
                        pwdIsValid = false;
                        System.out.println("Invalid Password: Characters");
                    }
                }else{
                    pwdIsValid = false;
                    System.out.println("Invalid Password: Passwords don't match");
    //                Toast.makeText(this, "Please make sure the passwords match.", Toast.LENGTH_SHORT).show();
                }
            }return pwdIsValid;

        }

//    if inputs are valid
    public void validateAndSend(String email, String pwd, String pwdC) {
        Log.v("Debug", "Pwd: " + pwd);
            dbU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Generator object
                Gen g = new Gen();
                //Generate ID
                String userID = g.computeGen();
                if (!email.isEmpty() || email != null || emailHasValidFormat(email)){
                    String emailChk = snapshot.child("userID").child("email").getValue().toString();
                    if (emailChk.equals(email)) {
                        System.out.println("emailChk.equals(email)= " + emailChk.equals(email));
                        System.out.println(email);
                        System.out.println("Match was found in DB");
                    } else {
                        if(passwordValid(pwd, pwdC)){
                            System.out.println("This is a valid email, it was not empty and was not found in the DB. The password was also valid");
                            // get vaultID
                            String vaultId = g.computeGen();
                            System.out.println(vaultId);
                            //Determine if ID already exists, if it doesn't set the values in the DB and enter home
                           // if (snapshot.child("iD").getValue().equals(id)) {
                            if (snapshot.getValue().equals(userID)) {
                                //  Toast.makeText(context, "Error. Please try again", Toast.LENGTH_SHORT).show();
                                System.out.println("ID found in DB");
                            }else {
                                User user = new User(email, pwd, vaultId);
                                String pushUser = dbU.push().getKey();
                                dbU.child(pushUser).push().setValue(user);
                                System.out.println("Sent to DB");
                                //                        Toast.makeText(context, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, Home.class);
                                startActivity(intent);
                            }
                        }

                    }
                }else {
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

//
//    Boolean format = emailHasValidFormat(email);
//                System.out.println(email);
//                System.out.println("Email format: " + format);
//                Boolean vEmail = validEmail(email);
//                System.out.println("Valid email: " + vEmail);
//                Boolean vPwd = passwordValid(pwd, pwdC);
//                System.out.println("Valid pwd: " + vPwd + ". and Password 1. " + pwd + " and Password 2. " + pwdC);
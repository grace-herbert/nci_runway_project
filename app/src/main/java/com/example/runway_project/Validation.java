package com.example.runway_project;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    public Validation() {
        this.db = new Database();
        this.dbRef = db.getRefDB();
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
        public boolean emailHasValidFormat(String email) {
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

        boolean isValid;
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
        public synchronized boolean validEmail(String email, Thread thread) {
            thread = new Thread();
            thread.setPriority(10);
            if (!email.equals(" ")) {
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
                            notifyAll();
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
            thread.notifyAll();
            return isValid;
        }

        //Pwd and pwdConf match
        //neither are empty
        //pwds contain what they need to
        public boolean passwordValid(String pwd1, String pwd2){
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
    public synchronized void validateAndSend(boolean format, boolean vEmail, boolean vPwd, String email, String pwd, Thread thread) throws InterruptedException {
        wait();
        thread = new Thread();
        thread.setPriority(1);
        if (format && vEmail && vPwd) {
            //Generator object
            Gen g = new Gen();
            //Generate ID
            g.setIds();
            String id = g.computeGen();
            System.out.println(id);
            //Determine if ID already exists, if it doesn't set the values in the DB and enter home
            dbRef.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("iD").getValue().equals(id)) {
    //                        Toast.makeText(context, "Error. Please try again", Toast.LENGTH_SHORT).show();
                        System.out.println("ID found in DB");
                    } else {
                        dbRef.child("email").setValue(email);
                        dbRef.child("hk").setValue(pwd);
                        dbRef.child("id").setValue(id);
                        System.out.println("Sent to DB");
    //                        Toast.makeText(context, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Home.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
    //                    Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
                    System.out.println("Error: " + error);
                }
            });

        } else {
    //            Toast.makeText(context, "Invalid information, please try again.", Toast.LENGTH_SHORT).show();
            System.out.println("Invalid information, please try again.");
        }
    }

}

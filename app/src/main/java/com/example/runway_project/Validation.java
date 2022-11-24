package com.example.runway_project;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

    }

    Register_2 r2 = new Register_2();
    EditText regEmail = r2.findViewById(R.id.regEmail);
    EditText pwd = r2.findViewById(R.id.regPwd);
    EditText pwdConf = r2.findViewById(R.id.regReEntPwd);
    MaterialButton reg2Btn = r2.findViewById(R.id.reg2Btn);
//
////    Context context = this;
//
    final String rgEmail = regEmail.getText().toString();
    final String rgPwd = pwd.getText().toString();
    final String rgPwdConf = pwdConf.getText().toString();

    boolean isValid;

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

        public void emailHasValidFormat() {
//        boolean emailIsValid;
//        String regex = "[a-zA-Z]";
//        emailIsValid = Pattern.matches(regex, email);
//        Matcher mtr = ptn.matcher(email);
//        emailIsValid = mtr.find();
            System.out.println("email");

        }

//        public boolean validEmail() {
//
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

        //Pwd and pwdConf match
        //neither are empty
        //pwds contain what they need to
//        public boolean passwordValid(String pwd1, String pwd2){
//            boolean pwdIsValid = false;
//            if(!pwd1.equals(" ") || !pwd2.equals(" ")){
//                if(pwd1.equals(pwd2)){
//                    if (rgPwd.matches("^[a-zA-Z .]*$")){
//                        pwdIsValid = true;
//                        System.out.println("Valid Password");
//                    }else{
//                        pwdIsValid = false;
//                        System.out.println("Invalid Password: Characters");
//                    }
//                }else{
//                    pwdIsValid = false;
//                    System.out.println("Invalid Password: Passwords don't match");
//    //                Toast.makeText(this, "Please make sure the passwords match.", Toast.LENGTH_SHORT).show();
//                }
//                System.out.println("Password box null");
//            }return pwdIsValid;
//
//        }

}

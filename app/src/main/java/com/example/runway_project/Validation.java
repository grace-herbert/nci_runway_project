package com.example.runway_project;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation extends Register_2 {

    //regex pattern found from https://www.youtube.com/watch?v=OOdO785p3Qo
    boolean emailHasValidFormat(String email) {
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

    boolean validEmail(String email) {

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
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    isValid = false;
                    System.out.println("Twas cancelled.");
                }
            });
        } else {
            Toast.makeText(this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    //Pwd and pwdConf match
    //neither are empty
    //pwds contain what they need to
    boolean passwordValid(String pwd1, String pwd2) {
        boolean pwdIsValid = false;
        if (!pwd1.equals(" ") || !pwd2.equals(" ")) {
            if (pwd1.equals(pwd2)) {
                String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$";
                Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher mtr = ptn.matcher(pwd1);
                boolean pwdMatches = mtr.matches();
                if (pwdMatches) {
//                    if (pwd1.matches("^[a-zA-Z .]*$")){
                    pwdIsValid = true;
                    System.out.println("Valid Password- Goes through");
                } else {
                    pwdIsValid = false;
                    System.out.println("Invalid Password: Characters");
                }
            } else {
                pwdIsValid = false;
                System.out.println("Invalid Password: Passwords don't match");
                //                Toast.makeText(this, "Please make sure the passwords match.", Toast.LENGTH_SHORT).show();
            }
        }
        return pwdIsValid;

    }
}

//        }
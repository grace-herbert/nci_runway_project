package com.example.runway_project;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation extends Register_2 {
    private Database db;
    private DatabaseReference dbRef;
    private DatabaseReference dbU;

    public Validation(){
        this.db = new Database();
        this.dbRef = db.getRefDB();
        this.dbU = db.getDBU();
    }

    //regex pattern found from https://www.youtube.com/watch?v=OOdO785p3Qo
    boolean emailHasValidFormat(String email) {
        System.out.println(email);
        boolean emailIsValid;
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher mtr = ptn.matcher(email);
        emailIsValid = mtr.matches();
        System.out.println("Format was found to be: " + emailIsValid);
        return emailIsValid;
    }


    boolean emailFound = false;

    void validEmail(String email) {

        if (email != null) {

            Query q = dbU.orderByChild("email");

            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot searchEml : snapshot.getChildren()) {
                            String emailVal = searchEml.child("email").getValue(String.class);
                            if (emailVal != null) {
                                try {
                                    if (BCrypt.checkpw(email, emailVal)) {
                                        emailFound = true;
                                        System.out.println("Email found in DB = " + emailVal);
                                        break;
                                    } else {
                                        System.out.println("Email is valid and not found in DB.");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    } else {
                        System.out.println("Snapshot not exists");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    boolean getIsEmailFound(){
        System.out.println("emailFound = " + emailFound);
        return this.emailFound;
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
                    pwdIsValid = true;
                    System.out.println("Valid Password- Goes through");
                } else {
                    pwdIsValid = false;
                    System.out.println("Invalid Password: Characters");
                }
            } else {
                pwdIsValid = false;
                System.out.println("Invalid Password: Passwords don't match");
            }
        }
        return pwdIsValid;

    }

}

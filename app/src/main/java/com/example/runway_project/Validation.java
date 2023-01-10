package com.example.runway_project;

import android.util.Log;

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
//public class Validation {

    private Database db = new Database();;
    private DatabaseReference dbRef = db.getRefDB();;
    private DatabaseReference dbU = db.getDBU();



    //regex pattern found from https://www.youtube.com/watch?v=OOdO785p3Qo
    boolean emailHasValidFormat(String email) {
        boolean emailIsValid;
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher mtr = ptn.matcher(email);
        emailIsValid = mtr.matches();
        Log.d("Debug","f22");
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
                                        Log.d("Debug","e22");
                                        break;
                                    } else {
                                        Log.d("Debug","e24");
                                    }
                                } catch (IllegalArgumentException e) {
                                    Log.e("ERROR",e.getMessage());
                                }
                            }
                        }
                    } else {
                        Log.d("Debug","!s22");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ERROR", String.valueOf(error.getCode()));
                }
            });

        }
    }

    boolean getIsEmailFound(){
        Log.d("Debug","e22 " + emailFound);
        return this.emailFound;
    }

    //Pwd and pwdConf match
    //neither are empty
    //pwds contain what they need to
    public boolean passwordValid(String pwd1, String pwd2) {
        boolean pwdIsValid = false;
        if (!pwd1.equals(" ") || !pwd2.equals(" ")) {
            if (pwd1.equals(pwd2)) {
                String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!£$%^&*|@#~-])(?=\\S+$).{10,}$";
                Pattern ptn = Pattern.compile(regex);
                Matcher mtr = ptn.matcher(pwd1);
                boolean pwdMatches = mtr.matches();
                if (pwdMatches) {
                    pwdIsValid = true;
                    Log.d("Debug","p22");
                } else {
                    pwdIsValid = false;
                    Log.d("Debug","p33");
                }
            } else {
                pwdIsValid = false;
                Log.d("Debug","p33");
            }
        }
        return pwdIsValid;

    }

}

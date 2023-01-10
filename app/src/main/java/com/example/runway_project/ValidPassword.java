package com.example.runway_project;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPassword{

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
                Log.d("Debug","p44");
            }
        }
        return pwdIsValid;

    }
}
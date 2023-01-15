package com.example.runway_project;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormat {

        boolean emailHasValidFormat(String email) {
            boolean emailIsValid;
            String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
            Pattern ptn = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    //        emailIsValid = ptn.matcher(email).matches();
            Matcher mtr = ptn.matcher(email);
            emailIsValid = mtr.matches();
//            Log.d("Debug", "f22");
            return emailIsValid;
    }
}

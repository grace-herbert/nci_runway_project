package com.example.runway_project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormat {

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
}

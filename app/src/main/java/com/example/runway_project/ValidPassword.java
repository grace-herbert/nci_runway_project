package com.example.runway_project;

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
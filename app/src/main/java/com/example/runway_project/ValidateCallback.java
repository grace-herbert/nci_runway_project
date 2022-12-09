package com.example.runway_project;

public interface ValidateCallback {

//    public default void validate(String email, String pwd, String pwdC, Validate validate){
//
//    }

    ValidateCallback validate(String email, String pwd, String pwdC, ValidateCallback callback);

    ValidateCallback validateCallback(boolean bool);
}

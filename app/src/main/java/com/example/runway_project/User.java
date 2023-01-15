package com.example.runway_project;

public class User {

    private String email;
    private String hk;
    private String vaultID;

    public User(String email, String hk, String vaultID){
        this.email = email;
        this.hk = hk;
        this.vaultID = vaultID;
    }


    public String getEmail() {
        return email;
    }

    public String getHk() {
        return hk;
    }

    public String getVaultID() {
        return vaultID;
    }




}

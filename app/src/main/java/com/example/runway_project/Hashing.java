package com.example.runway_project;

import org.mindrot.jbcrypt.BCrypt;

class Hashing {

    private String hashImgName;
    private String hashEmail;
    private String hk;
    private String hkC;

    //constructors
    public Hashing(String email, String pwd, String pwdC){
        computeBcrypt(email, pwd , pwdC);
    }

    public Hashing(String imgName){
        computeBcrypt(imgName);
    }

    //compute
    private void computeBcrypt(String email, String pwd, String pwdC){
        this.hashEmail = BCrypt.hashpw(email, BCrypt.gensalt(11));
        this.hk = BCrypt.hashpw(pwd, BCrypt.gensalt(12));
        this.hkC = BCrypt.hashpw(pwdC, BCrypt.gensalt(12));
    }

    private void computeBcrypt(String imgName){
        this.hashImgName = BCrypt.hashpw(imgName, BCrypt.gensalt(11));
    }

    //outputs/getters
    public String getHashImgName() {
        return hashImgName;
    }

    public String getHashEmail() {
        return hashEmail;
    }

    public String getHk() {
        return hk;
    }

    public String getHkC(){ return hkC; }




}

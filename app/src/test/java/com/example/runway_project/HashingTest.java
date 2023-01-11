package com.example.runway_project;

import junit.framework.TestCase;

import org.mindrot.jbcrypt.BCrypt;

public class HashingTest extends TestCase {
    private String hashImgName;
    private String hashEmail;
    private String hk;
    private String hkC;

    private String testEmail1 = "test@test.ie";
    private String testEmail2 = "test2@test.ie";
    private String testPwd1 = "testthis";
    private String testPwd2 = "testing";
    private String testPwd3 = "test";
    private String testImg2 = "image";
    private String testImg1 = "image2";


    Hashing h = new Hashing(testEmail1,testPwd1);
    Hashing hImg = new Hashing(testImg1);
    Hashing allH = new Hashing(testEmail2, testPwd2, testPwd3);

    public void testHashingEmailH(){
        assertEquals(true, BCrypt.checkpw(testEmail1, h.getHashEmail()));
    }

    public void testHashingPwdH(){
        assertEquals(true, BCrypt.checkpw(testPwd1, h.getHk()));
    }

    public void testHashingEmailHExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testPwd1, h.getHashEmail()));
    }

    public void testHashingPwdHExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testEmail1, h.getHk()));
    }

    public void testHashingEmailAllH(){
        assertEquals(true, BCrypt.checkpw(testEmail2, allH.getHashEmail()));
    }

    public void testHashingPwdAllH(){
        assertEquals(true, BCrypt.checkpw(testPwd2, allH.getHk()));
    }

    public void testHashingPwdCAllH(){
        assertEquals(true, BCrypt.checkpw(testPwd3, allH.getHkC()));
    }


    public void testHashingEmailAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testPwd3, allH.getHashEmail()));
    }

    public void testHashingPwdAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testEmail1, allH.getHk()));
    }

    public void testHashingPwdCAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testEmail1, allH.getHkC()));
    }

    public void testHashingHImg(){
        assertEquals(true, BCrypt.checkpw(testImg1, hImg.getHashImgName()));
    }

    public void testHashingHImgExpectFalse(){
        assertEquals(false, BCrypt.checkpw(testImg2, hImg.getHashImgName()));
    }

}
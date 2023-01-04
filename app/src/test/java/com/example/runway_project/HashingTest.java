package com.example.runway_project;

import junit.framework.TestCase;

import org.mindrot.jbcrypt.BCrypt;

public class HashingTest extends TestCase {
    private String hashImgName;
    private String hashEmail;
    private String hk;
    private String hkC;

    Hashing h = new Hashing("test@test.ie","testthis");
    Hashing hImg = new Hashing("image2");
    Hashing allH = new Hashing("test2@test.ie", "testing", "test");

    public void testHashingEmailH(){
        assertEquals(true, BCrypt.checkpw("test@test.ie", h.getHashEmail()));
    }

    public void testHashingPwdH(){
        assertEquals(true, BCrypt.checkpw("testthis", h.getHk()));
    }

    public void testHashingEmailHExpectFalse(){
        assertEquals(false, BCrypt.checkpw("testthis", h.getHashEmail()));
    }

    public void testHashingPwdHExpectFalse(){
        assertEquals(false, BCrypt.checkpw("test@test.ie", h.getHk()));
    }

    public void testHashingEmailAllH(){
        assertEquals(true, BCrypt.checkpw("test2@test.ie", allH.getHashEmail()));
    }

    public void testHashingPwdAllH(){
        assertEquals(true, BCrypt.checkpw("testing", allH.getHk()));
    }

    public void testHashingPwdCAllH(){
        assertEquals(true, BCrypt.checkpw("test", allH.getHkC()));
    }


    public void testHashingEmailAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw("test", allH.getHashEmail()));
    }

    public void testHashingPwdAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw("test@test.ie", allH.getHk()));
    }

    public void testHashingPwdCAllHExpectFalse(){
        assertEquals(false, BCrypt.checkpw("test@test.ie", allH.getHkC()));
    }

    public void testHashingHImg(){
        assertEquals(true, BCrypt.checkpw("image2", hImg.getHashImgName()));
    }

    public void testHashingHImgExpectFalse(){
        assertEquals(false, BCrypt.checkpw("image", hImg.getHashImgName()));
    }

}
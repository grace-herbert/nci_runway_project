package com.example.runway_project;

import junit.framework.TestCase;

public class ValidPasswordTest extends TestCase {

    ValidPassword vp = new ValidPassword();

    public void testPasswordValidOnTrue() {
        assertEquals(true,vp.passwordValid("TestThisN0w!", "TestThisN0w!"));
    }

    public void testPasswordValidOnFalseLessThan10L() {
        assertEquals(false,vp.passwordValid("TestN0w!", "TestN0w!"));
    }

    public void testPasswordValidOnFalseNoSymbol() {
        assertEquals(false,vp.passwordValid("TestThisN0w", "TestThisN0w"));
    }

    public void testPasswordValidOnFalseNoNumber() {
        assertEquals(false,vp.passwordValid("TestThisNow!", "TestThisNow!"));
    }

    public void testPasswordValidOnFalseNoCapital() {
        assertEquals(false,vp.passwordValid("testthisn0w!", "testthisn0w!"));
    }

    public void testPasswordValidOnFalseAllCapital() {
        assertEquals(false,vp.passwordValid("TESTTHISN0W!", "TESTTHISN0W!"));
    }

    public void testPasswordValidOnFalse2DifferentPwd() {
        assertEquals(false,vp.passwordValid("TestThisN0w!", "TestThisN3w!"));
    }

    public void testPasswordValidOnFalseNumsOnly() {
        assertEquals(false,vp.passwordValid("12345678910!", "12345678910!"));
    }

    public void testPasswordValidOnFalseWithSpace() {
        assertEquals(false,vp.passwordValid("TestThis N0w!", "TestThis N3w!"));
    }

    public void testPasswordValidOnFalseWithUnknownSymbol() {
        assertEquals(false,vp.passwordValid("TestThisN0w+", "TestThisN0w+"));
    }
}
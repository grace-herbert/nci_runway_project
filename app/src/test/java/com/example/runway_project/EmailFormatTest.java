package com.example.runway_project;

import junit.framework.TestCase;

public class EmailFormatTest extends TestCase {

    EmailFormat e = new EmailFormat();


    public void testEmailHasValidFormatExpTrue() {
        assertEquals(true, e.emailHasValidFormat("test@test.ie"));
    }

    public void testEmailHasValidFormatWNoExpTrue() {
        assertEquals(true, e.emailHasValidFormat("test123@test.ie"));
    }

    public void testEmailHasValidFormatWNumExpTrue() {
        assertEquals(true, e.emailHasValidFormat("123@test123.ie"));
    }

    public void testEmailHasValidFormatNumsExpTrue() {
        assertEquals(true, e.emailHasValidFormat("123@123.ie"));
    }

    public void testEmailHasValidFormatTopLevelDMissingExpFalse() {
        assertEquals(false, e.emailHasValidFormat("test@123"));
    }

    public void testEmailHasValidFormatMissingAtAnd2ndLvlDExpFalse() {
        assertEquals(false, e.emailHasValidFormat("test123.ie"));
    }

    public void testEmailHasValidFormatMissingNameExpFalse() {
        assertEquals(false, e.emailHasValidFormat("@test.ie"));
    }

    public void testEmailHasValidFormatMissing2ndLvlDExpFalse() {
        assertEquals(false, e.emailHasValidFormat("test@.ie"));
    }
}
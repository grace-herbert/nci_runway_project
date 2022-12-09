package com.example.runway_project;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatValidationTest extends TestCase {

    Validation val = new Validation();


    public void testEmailHasValidFormat() {
        assertEquals(true, val.emailHasValidFormat("test@test.ie"));


    }
}
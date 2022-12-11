package com.example.runway_project;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationTest extends TestCase {

    Database mockDb = new Database();
    DatabaseReference mockDbRef;
    Validation v;



    @Before
    public void setUp() {
        mockDb = mock(Database.class);
        DatabaseReference mockDbRef = mock(DatabaseReference.class);
        v = new Validation();

    }

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

    @Test
    public void testValidatePwd(){
        String rgPwd = "test";
        String rgPwdConf = "test";
        v.passwordValid(rgPwd,rgPwdConf);
        assertEquals(true, v.passwordValid(rgPwd,rgPwdConf));


    }

}
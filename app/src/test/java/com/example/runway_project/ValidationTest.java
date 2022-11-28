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

    @Test
    public void testValidatePwd(){
        String rgPwd = "test";
        String rgPwdConf = "test";
        v.passwordValid(rgPwd,rgPwdConf);
        assertEquals(true, v.passwordValid(rgPwd,rgPwdConf));


    }

}
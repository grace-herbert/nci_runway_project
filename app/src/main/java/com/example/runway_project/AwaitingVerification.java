package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AwaitingVerification extends AppCompatActivity {

//    private FirebaseUser fUser;
    private User cUser;
    private boolean emailIsVerified;
    FirebaseAuth firebaseAuth;
    FirebaseUser fUser;
    Database db;
    DatabaseReference dbRef;
    DatabaseReference dbU;

    public AwaitingVerification(){
        this.db = new Database();
        this.dbRef = db.getRefDB();
        this.dbU = db.getDBU();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awaiting_verification);



        refresh();




    }

    private void refresh(){
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AwaitingVerification.this, AwaitingVerification.class);
                startActivity(intent);
                System.out.println("Refreshed");
                sendUser();
            }
        };
        handler.postDelayed(runnable, 30000);
    }

    private void sendUser() {
        Register_2 r2 = new Register_2();
        firebaseAuth = FirebaseAuth.getInstance();
        fUser = firebaseAuth.getCurrentUser();

        cUser = r2.getUser();
        if (fUser != null) {
            fUser.reload();
            emailIsVerified = fUser.isEmailVerified();
            Log.v("Debug", "emailIsVerified: " + emailIsVerified);
            if (emailIsVerified) {
                Log.v("Debug", "Email sent to " + fUser.getEmail());
                String pushUser = dbU.push().getKey();
                dbU.child(pushUser).push().setValue(cUser);
                System.out.println("Sent to DB");
                fUser.delete();
                Intent intent = new Intent(AwaitingVerification.this, Home.class);
                startActivity(intent);

            }else{
                Log.v("Debug", "EmailUnverified");
            }
        }else{
            Log.v("Debug", "EmailNull");
        }
    }
}
package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AwaitingVerification extends AppCompatActivity {

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
        handler.postDelayed(runnable, 15000);
    }

    private void sendUser() {

        firebaseAuth = FirebaseAuth.getInstance();
        fUser = firebaseAuth.getCurrentUser();

        if (fUser != null) {
            fUser.reload();
            emailIsVerified = fUser.isEmailVerified();
            Log.v("Debug", "emailIsVerified: " + emailIsVerified);
            if (emailIsVerified) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SendPrefs", Context.MODE_PRIVATE);
                String hEmail = sharedPreferences.getString("hshEmail", "");
                String hk = sharedPreferences.getString("hshk", "");
                String vltID = sharedPreferences.getString("vaultID", "");
                System.out.println("hEmail: " + hEmail + ". Pwd: " + hk + ". VaultID: " + vltID);
                User cUser = new User(hEmail, hk, vltID);
                DatabaseReference pushRef = dbU.push();
                String userID = pushRef.getKey();
                pushRef.setValue(cUser);
                SharedPreferences.Editor sPEdit = sharedPreferences.edit();
                sPEdit.putString("userID", userID);
                sPEdit.commit();
                System.out.println(userID);
                System.out.println("Sent to DB");
                Log.v("Debug", "Email sent to " + fUser.getEmail());
                fUser.delete();
                Intent intent3 = new Intent(AwaitingVerification.this, Home.class);
                startActivity(intent3);

            }else{
                Log.v("Debug", "EmailUnverified");
            }
        }else{
            Log.v("Debug", "EmailNull");
        }
    }
}

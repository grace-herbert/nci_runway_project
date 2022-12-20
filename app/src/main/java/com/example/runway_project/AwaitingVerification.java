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

//    private FirebaseUser fUser;
    Register_2 r2 = new Register_2();
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
//        Register_2 r2 = new Register_2();
//        String hEmail = r2.getHshEmail();
//        Intent intent2 = getIntent();
//        String hEmail = intent2.getStringExtra("hshEmail");

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

        firebaseAuth = FirebaseAuth.getInstance();
        fUser = firebaseAuth.getCurrentUser();

//        cUser = r2.getUser();
        Log.v("Debug", "cUser " + cUser);
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
                String pushUser = dbU.push().getKey();
                dbU.child(pushUser).push().setValue(cUser);
                System.out.println("Sent to DB");
//                String hshEm = r2.getHshEmail();
//                AwaitingVerification aV = new AwaitingVerification();
//                aV.hEm = r2.getHshEmail();
                Log.v("Debug", "Email sent to " + fUser.getEmail());
//                String pushUser = dbU.push().getKey();
//                dbU.child(pushUser).push().setValue(cUser);
//                Log.v("Debug", "cUser email:  " + cUser.getEmail());
                ;
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

////    User getcUser(){
////        return cUser;
////    }
//
//    String getEm(){
//        return this.hshEmail;
//    }
//    void setVars(String hshEm, String hshP, String vlt){
//        this.hshEmail = hshEm;
//        this.hk = hshP;
//        this.vaultID = vlt;
//    }

//}
package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
    private String hEmail;

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
                Log.d("Debug", "r22");
                sendUser();
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    private void sendUser() {

        firebaseAuth = FirebaseAuth.getInstance();
        fUser = firebaseAuth.getCurrentUser();

        if (fUser != null) {
            fUser.reload();
            emailIsVerified = fUser.isEmailVerified();
            Log.d("Debug", "e28");
            if (emailIsVerified) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SendPrefs", Context.MODE_PRIVATE);
                hEmail = sharedPreferences.getString("hshEmail", "");
                String hk = sharedPreferences.getString("hshk", "");
                String vltID = sharedPreferences.getString("vaultID", "");
                User cUser = new User(hEmail, hk, vltID);
                DatabaseReference pushRef = dbU.push();
                String userID = pushRef.getKey();
                pushRef.setValue(cUser);
                SharedPreferences.Editor sPEdit = sharedPreferences.edit();
                sPEdit.putString("userID", userID);
                sPEdit.commit();
                Log.d("Debug", "d22");
                fUser.delete();
                Intent intent3 = new Intent(AwaitingVerification.this, Home.class);
                startActivity(intent3);

            }else{
                Log.v("Debug", "e25");
            }
        }else{
            Log.v("Debug", "e26");
        }
    }

}

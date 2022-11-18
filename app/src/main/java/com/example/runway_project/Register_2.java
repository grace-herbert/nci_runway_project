package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_2 extends AppCompatActivity {

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://runwayproject-b5853-default-rtdb.europe-west1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        EditText regEmail = this.findViewById(R.id.regEmail);
        EditText pwd = this.findViewById(R.id.regPwd);
        EditText pwdConf = this.findViewById(R.id.regReEntPwd);
        MaterialButton reg2Btn = this.findViewById(R.id.reg2Btn);

        final String rgEmail = regEmail.getText().toString();
        final String rgPwd = pwd.getText().toString();
        final String rgPwdConf = pwdConf.getText().toString();

        reg2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rgEmail.isEmpty() || rgPwd.isEmpty() || rgPwdConf.isEmpty()){
                    Toast.makeText(Register_2.this, "Please enter the email and password.", Toast.LENGTH_SHORT).show();
                }else if(rgPwd.equals(rgPwdConf)){
                    dbRef.child("email").setValue(rgEmail);
                    dbRef.child("hk").setValue(rgPwd);
                    Toast.makeText(Register_2.this, "Congratulations! You are now registered.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register_2.this, Home.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Register_2.this, "Invalid information, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.runway_project;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Register_1 extends AppCompatActivity {

    private Database db;
    private DatabaseReference dbRef;

    public Register_1(){
        this.db =  new Database();
        this.dbRef = db.getRefDB();
    }

    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

            AppCompatButton showBtn = this.findViewById(R.id.showBtn);
            AppCompatButton hideBtn = this.findViewById(R.id.hideBtn);
            EditText voucher = this.findViewById(R.id.voucherNo);

            TextView privacyPolicy = this.findViewById(R.id.privacy_policy);
            privacyPolicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register_1.this, PrivacyPolicy.class);
                    startActivity(intent);
                }
            });

            showBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        voucher.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showBtn.setVisibility(View.INVISIBLE);
                        hideBtn.setVisibility(View.VISIBLE);

                }
            });

            hideBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voucher.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hideBtn.setVisibility(View.INVISIBLE);
                    showBtn.setVisibility(View.VISIBLE);

                }
            });


            MaterialButton vouchBtn = this.findViewById(R.id.vouchBtn);

            vouchBtn.setOnClickListener(new View.OnClickListener() {
                int count = 0;
                Gen g = new Gen();
                @Override
                public void onClick(View v) {
                    final String VNo = voucher.getText().toString();
                    if (count < 3) {
                        dbRef.child("voucher").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.getValue().equals(VNo)){
                                    Log.d("Debug","r122");
                                    Intent intent = new Intent(Register_1.this, Register_2.class);
                                    startActivity(intent);
                                }else{
                                    voucher.setText(" ");
                                    count++;
                                    Log.d("Debug", "r133");
                                    Toast.makeText(Register_1.this, "Wrong Voucher No.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                count++;
                            }
                        });
                    } else {
                        Log.d("Debug", "r133");
                        Toast.makeText(Register_1.this, "Wrong Voucher No. entered. Please try again later.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register_1.this, Locked.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

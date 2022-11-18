package com.example.runway_project;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Register_1 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            AppCompatButton showBtn = this.findViewById(R.id.showBtn);
            AppCompatButton hideBtn = this.findViewById(R.id.hideBtn);
            EditText voucher = this.findViewById(R.id.voucherNo);
//            ImageView hideImg = this.findViewById(R.id.Hide);

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

//            final String VNo = voucher.getText().toString();


            MaterialButton vouchBtn = this.findViewById(R.id.vouchBtn);

            vouchBtn.setOnClickListener(new View.OnClickListener() {
                int count = 0;
                Gen g = new Gen();
                @Override
                public void onClick(View v) {
                    final String VNo = voucher.getText().toString();
                    if (count < 3) {
                        if (VNo.equals("test")) {
//                            String gg = g.getGen();
//                            System.out.println("gen: " + gg);
                            Intent intent = new Intent(Register_1.this, Register_2.class);
                            startActivity(intent);
                        } else {
                            count++;
                            Toast.makeText(Register_1.this, "Wrong Voucher No.", Toast.LENGTH_SHORT).show();
                            System.out.println("VNo: "+VNo);
                        }
                    } else {
                        Toast.makeText(Register_1.this, "Wrong Voucher No. entered. Please try again later.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register_1.this, Locked.class);
                        startActivity(intent);
                        //vouchBtn.setEnabled(false);
                    }
                }


            });
        }
    }

//if(count < 3){
//        if(voucherNo.getText() == "TestingThis0UT"){
//            Intent intent = new Intent(Register.this, Register_2.class);
//            startActivity(intent);
//            count = 0;
//        }else{
//            count++;
//            Toast.makeText(Register.this, "Wrong Voucher No.", Toast.LENGTH_SHORT).show();
//        }
//    }else{
//        Toast.makeText(Register.this, "Wrong Voucher No. entered. Please try again later.", Toast.LENGTH_SHORT).show();
//        vouchBtn.setEnabled(false);
//    }
//}

//                if (count < 3 && voucherNo.getText() == "TestingThis0UT") {
//                    Intent intent = new Intent(Register.this, Register_2.class);
//                    startActivity(intent);
//                    count = 0;
//                } else if (count < 3 && voucherNo.getText() != "TestingThis0UT") {
//                    count++;
//                    Toast.makeText(Register.this, "Wrong Voucher No.", Toast.LENGTH_SHORT).show();
//
//
//                } else {
//                    Toast.makeText(Register.this, "Wrong Voucher No. entered. Please try again later.", Toast.LENGTH_SHORT).show();
//                    vouchBtn.setEnabled(false);
//                }
//            }
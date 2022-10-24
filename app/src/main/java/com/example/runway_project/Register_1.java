package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView voucherNo = this.findViewById(R.id.voucherNo);
        int count = 0;

        MaterialButton vouchBtn = this.findViewById(R.id.vouchBtn);

        return vouchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count < 3 && voucherNo.getText() == "TestingThis0UT") {
                    Intent intent = new Intent(Register.this, Register_2.class);
                    startActivity(intent);
                    count = 0;
                } else if (count < 3 && voucherNo.getText() != "TestingThis0UT") {
                    count++;
                    Toast.makeText(Register.this, "Wrong Voucher No.", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(Register.this, "Wrong Voucher No. entered. Please try again later.", Toast.LENGTH_SHORT).show();
                    vouchBtn.setEnabled(false);
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
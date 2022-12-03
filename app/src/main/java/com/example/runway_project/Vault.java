package com.example.runway_project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Vault extends AppCompatActivity {

    private static final int CHOOSE_IMG_REQ = 1;
    private Button chooseImgBtn;
    private EditText inputTitle;
    private ProgressBar progressBar;
    private Button uploadBtn;
    private TextView viewImgs;
    private ImageView imageView;
    private String imgName;
    private Uri imgUri;
    private StorageReference cldStorageRef;
    private Database db = new Database();
    private DatabaseReference dbV;
    private StorageReference storageRef;





    //        Storage storage = new Storage();
//
//        StorageReference storageRef = storage.getReference("gs://runwayproject-39823.appspot.com");

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == CHOOSE_IMG_REQ && resultCode == RESULT_OK && data != null && data.getData() != null){
//            imgUri = data.getData();
//            Picasso.get().load(imgUri).into(imageView);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        chooseImgBtn = findViewById(R.id.chooseImgBtn);
        inputTitle = findViewById(R.id.enter_file_name);
        progressBar = findViewById(R.id.progressBar);
        uploadBtn = findViewById(R.id.uploadBtn);
        viewImgs = findViewById(R.id.viewUploads);
        imageView = findViewById(R.id.imagesView);

        cldStorageRef = FirebaseStorage.getInstance().getReference("Vault");
        dbV = db.getDBV();



        chooseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImg();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile(imageView, progressBar);
            }
        });

        viewImgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    void uploadFile(ImageView imgView, ProgressBar progressBar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.UK); //no Ireland or Europe apparently, will check this later.
        Date now = new Date();
        String imgName = dateFormat.format(now);

        storageRef = cldStorageRef.child("/testID/" + imgName);
        storageRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgView.setImageURI(null);
                Log.v("Debug", "Successful upload");
                Toast.makeText(Vault.this, "Image successfully uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Vault.this, "Image failed to upload", Toast.LENGTH_SHORT).show();
                Log.v("Debug", "Failed to upload");

// own code below
                if (progressBar.isShown())
                    progressBar.setEnabled(false);
                    progressBar.setVisibility(View.INVISIBLE);
                //Toast.makeText(MainActivity.this,"Upload failed",Toast.LENGTH_SHORT).show();


            }
        });

    }

ActivityResultLauncher<Intent> chooseImgLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    imgUri = data.getData();
                    Picasso.get().load(imgUri).into(imageView);

                }
            }
        });

        private void chooseImg(){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            chooseImgLauncher.launch(intent);


        }

}
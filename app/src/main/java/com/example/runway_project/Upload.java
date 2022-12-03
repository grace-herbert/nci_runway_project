package com.example.runway_project;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

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

public class Upload {

    private String imgName;
    private Uri imgUri;
    private String imgUrl;
    private String imgUriString;
    private String date;
    private ImageView imageView;
    private String vaultID;
    private Database db = new Database();
    private StorageReference storage;


    //constructors

    public Upload(){
        imgName = null;

    }

    public Upload(String imgUrl, String date, String imgName){
        //this.vaultID = vaultID;
        this.imgUrl = imgUrl;
        this.date = date;
        this.imgName = imgName;
    }

//    public Upload(String imgName, Uri imgUri, String imgUriString){
//        this.imgName = imgName;
//        this.imgUri = imgUri;
//        this.imgUriString = imgUriString;
//    }


    //Getters and Setters


    public String getImgUrl() {
        return imgUrl;
    }

    public String getDate() {
        return date;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Uri getImgUri(Uri imgUri) {
        return imgUri;
    }

    public String getImgUriString(String imgUriString) {
        return imgUriString;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }



////    private String getFileExtension(Uri uri){
////        ContentResolver cR = getContentResolver();
////        MimeTypeMap mime = MimeTypeMap.getSingleton();
////        return mime.getExtensionFromMimeType(cR.getType(uri));
////    }
//
//    void uploadFile(ImageView imgView, ProgressBar progressBar) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.UK); //no Ireland or Europe apparently, will check this later.
//        Date now = new Date();
//        String imgName = dateFormat.format(now);
//
//        storage = FirebaseStorage.getInstance().getReference("Vault/testID/fileName" + imgName);
//        storage.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                imgView.setImageURI(null);
//                Log.v("Debug", "Successful upload");
//                //Toast.makeText(this,Vault.class, "Congrats! Image has been uploaded", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.v("Debug", "Failed to upload");
//
//// own code below
//                if (progressBar.isShown())
//                    progressBar.setEnabled(false);
//                progressBar.setVisibility(View.INVISIBLE);
//                //Toast.makeText(MainActivity.this,"Upload failed",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//    }

}




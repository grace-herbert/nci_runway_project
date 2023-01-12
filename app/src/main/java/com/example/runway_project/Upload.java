package com.example.runway_project;

import android.net.Uri;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

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

    public Upload(String imgUrl, String imgName, String date){
        //this.vaultID = vaultID;
        this.imgUrl = imgUrl;
        this.date = date;
        this.imgName = imgName;
    }


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



}




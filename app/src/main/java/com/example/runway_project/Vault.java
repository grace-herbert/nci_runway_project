package com.example.runway_project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Vault extends AppCompatActivity {
    private DrawerLayout drawerL;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private static final int CHOOSE_IMG_REQ = 1;
    public static final String SHAREDV = "SharedV";
    public static String vaultID;
    private Button chooseImgBtn;
    private EditText inputTitle;
    private ProgressBar progressBar;
    private Button uploadBtn;
    private TextView viewImgs;
    private ImageView imageView;
    private String imgName;
    private Uri imgUri;
    private String imgUriString;
    private StorageReference cldStorageRef;
    private StorageReference vltCldStorageRef;
    private Database db = new Database();
    private DatabaseReference dbVltRef;
    private StorageReference storageRef;
//    private String vaultID;

    //Navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //get the vaultID
//    private void setVaultID(){
//        this.vaultID = vltSP.getString("vltID", "");
//        System.out.println("vaultID = " + vaultID);
//    }

//    private String getVaultID(){
//        return this.vaultID;
//    }





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

    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //get the vaultID
        SharedPreferences vltSP = getApplicationContext().getSharedPreferences(SHAREDV, Context.MODE_PRIVATE);
//
//        vaultID = vltSP.getString("vltID", "");
        MainActivity m = new MainActivity();
        vaultID = vltSP.getString("vltID", "default");
        System.out.println("VaultID = " + vaultID);
//        setVaultID();
//        vaultID = getVaultID();

        chooseImgBtn = findViewById(R.id.chooseImgBtn);
        inputTitle = findViewById(R.id.enter_file_name);
        progressBar = findViewById(R.id.progressBar);
        uploadBtn = findViewById(R.id.uploadBtn);
        viewImgs = findViewById(R.id.viewUploads);
        imageView = findViewById(R.id.imagesView);

        try {
            cldStorageRef = FirebaseStorage.getInstance().getReference("Vault");
            vltCldStorageRef = cldStorageRef.child(vaultID);
        }catch (IllegalArgumentException e){
            Log.v("Debug", "Illegal Arg Ex");
        }
        dbVltRef = db.getDBV();


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

        //emergencyLogout
        ImageButton emergLogout = this.findViewById(R.id.logoutImgBtn);
        emergLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vault.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Navigation
        navigationView = findViewById(R.id.vaultNav);
        drawerL = findViewById(R.id.vaultDrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerL, R.string.open_menu, R.string.close_menu);
        drawerL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.getItemId();
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.vltHomeMenu:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Vault.this, com.example.runway_project.Home.class);
                        startActivity(intent);
                        Log.v("Debug", "Home menu item clicked");
                        break;
                    case R.id.vltEmergInfo:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Vault.this, EmergencyInformation.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                    case R.id.vltGetHelpMenu:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Vault.this, GetHelp.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.vltSSOItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Vault.this, StayingSafeOnline.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.vltLogoutMenu:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Vault.this, MainActivity.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                }

                return true;
            }

        });

    }

    void uploadFile(ImageView imgView, ProgressBar progressBar)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.UK); //no Ireland or Europe apparently, will check this later.
        Date now = new Date();
        String imgName = dateFormat.format(now);
        String vChild =  "/"+vaultID.trim()+"/";
        System.out.println("vChild: "+vChild);


        try {
            storageRef = cldStorageRef.child(vChild + imgName);
            storageRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imgView.setImageURI(null);
                    progressBar.setProgress(0);
                    Log.v("Debug", "Successful upload");
                    Toast.makeText(Vault.this, "Image successfully uploaded", Toast.LENGTH_SHORT).show();
                    imgUriString = imgUri.toString();
                    Log.v("Debug", "ImgUriString: " + imgUriString);
//                vaultID = getVaultID();
//                dbVltRef.setValue(vaultID);
                    String dUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    Log.v("Debug", "ldUrl: " + dUrl);
                    Log.v("Debug", "ImgName: " + imgName);
                    Upload uploadObj = new Upload(dUrl, imgName, imgUriString);
                    String upObjDate = uploadObj.getDate();
                    String upObjUrl = uploadObj.getImgUrl();
                    String upObjName = uploadObj.getImgName();
                    System.out.println("Date: " + upObjDate + ". Url: " + upObjUrl + ". Name: " + upObjName);

//
                    //get the location to push to (key)
                    //this is going to the db Vault
                    String putVltID = dbVltRef.push().getKey();
                    dbVltRef.child(putVltID).push().setValue(uploadObj);
//
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Vault.this, "Image failed to upload", Toast.LENGTH_SHORT).show();
                    Log.v("Debug", "Failed to upload");

// own code below
                    if (progressBar.isShown()) {
                        progressBar.setEnabled(false);
                        progressBar.setVisibility(View.INVISIBLE);
                        //Toast.makeText(MainActivity.this,"Upload failed",Toast.LENGTH_SHORT).show();
                    }

                }
                //https://www.youtube.com/watch?v=lPfQN-Sfnjw&list=RDCMUC_Fh8kvtkVPkeihBs42jGcA&start_radio=1
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double prgrs = 100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount();
                    progressBar.setProgress((int) prgrs);
                }
            });
        }catch (NullPointerException e){
            Log.d("Debug", "Null Pointer");
        }
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
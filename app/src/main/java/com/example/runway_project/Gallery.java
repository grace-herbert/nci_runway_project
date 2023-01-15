package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class Gallery extends AppCompatActivity {
    private Database db =  new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference vltRef;
    private DatabaseReference usrVltRef;
    private String vltID;
    private RecyclerView galleryRecyclerView;
    private DrawerLayout drawerL;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
//helped by code from https://www.youtube.com/watch?v=g-Qcb5PjsGo

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        SharedPreferences vltSP = getApplicationContext().getSharedPreferences("sharedV", Context.MODE_PRIVATE);
        vltID = vltSP.getString("vltID", " ");

        galleryRecyclerView = findViewById(R.id.recyclerView);
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        vltRef = (dbRef.child("/"+vltID+"/"));
        usrVltRef = vltRef.getRef();

        //Navigation
        navigationView = findViewById(R.id.galleryNav);
        drawerL = findViewById(R.id.glDrawerLayout);
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
                    case R.id.glHomeItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, Home.class);
                        startActivity(intent);
                        Log.d("Debug", "Home menu item clicked");
                        break;
                    case R.id.glVaultItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, Vault.class);
                        startActivity(intent);
                        Log.d("Debug", "Emergency info menu item clicked");
                        break;
                    case R.id.glEmergInfoItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, EmergencyInformation.class);
                        startActivity(intent);
                        Log.d("Debug", "Emergency info menu item clicked");
                        break;
                    case R.id.glGetHelpItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, GetHelp.class);
                        startActivity(intent);
                        Log.d("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.glSSOItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, StayingSafeOnline.class);
                        startActivity(intent);
                        Log.d("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.logoutItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Gallery.this, MainActivity.class);
                        startActivity(intent);
                        Log.d("Debug", "Emergency info menu item clicked");
                        break;
                }

                return true;
            }

        });

    }

    public void onStart() {

        super.onStart();
        FirebaseRecyclerAdapter<ImageItem, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageItem, ViewHolder>(ImageItem.class,
                R.layout.card, ViewHolder.class, usrVltRef) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, ImageItem imageItem, int i) {
                viewHolder.getTitleAndImg(getApplicationContext(), imageItem.getImgName(),imageItem.getImgUrl(),imageItem.getDate());
            }
        };
        galleryRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
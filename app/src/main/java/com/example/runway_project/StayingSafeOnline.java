package com.example.runway_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.runway_project.databinding.ActivityStayingSafeOnlineBinding;

public class StayingSafeOnline extends AppCompatActivity {

    private DrawerLayout drawerL;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    //Navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        setContentView(R.layout.activity_staying_safe_online);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //emergencyLogout
        ImageButton emergLogout = this.findViewById(R.id.logoutImgBtn);
        emergLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StayingSafeOnline.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Navigation
        navigationView = findViewById(R.id.sSONav);
        drawerL = findViewById(R.id.sSOdrawerLayout);
        //Open and close toggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerL, R.string.open_menu, R.string.close_menu);
        drawerL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.getItemId();
                Intent intent;

                switch (item.getItemId()){
                    case R.id.sSOHomeItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(StayingSafeOnline.this, Home.class);
                        startActivity(intent);
                        Log.v("Debug", "Home menu item clicked");
                        break;
                    case R.id.sSOVaultItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(StayingSafeOnline.this, Vault.class);
                        startActivity(intent);
                        Log.v("Debug", "Vault menu item clicked");
                        break;
                    case R.id.sSOEmergInfo:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(StayingSafeOnline.this, EmergencyInformation.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                    case R.id.sSOGetHelpItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(StayingSafeOnline.this, GetHelp.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.sSOLogoutItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(StayingSafeOnline.this, MainActivity.class);
                        startActivity(intent);
                        Log.v("Debug", "Logout item clicked");
                        break;
                }

                return true;
            }

        });

        TextView incognito = findViewById(R.id.incogLink);
        TextView unknownApps = findViewById(R.id.chkAppsLink);
        TextView looseFitbit = findViewById(R.id.looseTheFitbitLink);
        TextView beVigilant = findViewById(R.id.incomingVigilanceLink);
        TextView photoSync = findViewById(R.id.turnOffPhotoSyncLink);
        TextView locationTracking = findViewById(R.id.turnOffLocationTrackingLink);
        TextView antiV = findViewById(R.id.downloadAntiVLink);
        TextView vpn = findViewById(R.id.downloadVPNLink);
        TextView canaries = findViewById(R.id.setUpCanaries);

        TextView incognitoHeading = findViewById(R.id.incogHeadingTxt);

//        incognito.setMovementMethod(LinkMovementMethod.getInstance());
////        unknownApps.setMovementMethod(findViewById(R.id.unknownAppsHeadingTxt));
////        looseFitbit.setMovementMethod(findViewById(R.id.looseTheFitbitHeadingTxt));
////        beVigilant.setMovementMethod(findViewById(R.id.beVigilantHeadingTxt));
////        photoSync.setMovementMethod(findViewById(R.id.turnOffPhotoSyncHeadingTxt));
////        locationTracking.setMovementMethod(findViewById(R.id.turnOffLocationTrackingHeadingTxt));
////        antiV.setMovementMethod(findViewById(R.id.downloadAntiVHeadingTxt));
////        vpn.setMovementMethod(findViewById(R.id.downloadAVPNHeadingTxt));
////        canaries.setMovementMethod(findViewById(R.id.setUpCanariesHeadingTxt));


    }
}
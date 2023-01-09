package com.example.runway_project;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    private DrawerLayout drawerL;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;


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
        setContentView(R.layout.activity_home);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //emergencyLogout
        ImageButton emergLogout = this.findViewById(R.id.logoutImgBtn);
        emergLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Navigation
        navigationView = findViewById(R.id.homeNav);
        drawerL = findViewById(R.id.drawerLayout);
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
                    case R.id.vaultItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Home.this, Vault.class);
                        startActivity(intent);
                        Log.v("Debug", "Vault menu item clicked");
                        break;
                    case R.id.emergInfoItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Home.this, EmergencyInformation.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                    case R.id.getHelpItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Home.this, GetHelp.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.hmSSOItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Home.this, StayingSafeOnline.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.logoutItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(Home.this, MainActivity.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                }

                return true;
            }

        });

        TextView getHelpLink = findViewById(R.id.getHelpTxt);
        TextView emInfLink= findViewById(R.id.emInfoTxt);
        TextView sSOLink = findViewById(R.id.sSOTxt);
        TextView theVltLink = findViewById(R.id.theVltTxt);

        getHelpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GetHelp.class);
                startActivity(intent);
            }
        });

        emInfLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, EmergencyInformation.class);
                startActivity(intent);
            }
        });

        sSOLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, StayingSafeOnline.class);
                startActivity(intent);
            }
        });

        theVltLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Vault.class);
                startActivity(intent);
            }
        });

    }
}

//package com.example.runway_project;
//
//import static java.lang.String.valueOf;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.content.Intent;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.Toolbar;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationView;
//
//public class Home extends AppCompatActivity {
//    private DrawerLayout drawerL;
//    private ActionBarDrawerToggle actionBarDrawerToggle;
//    private NavigationView navigationView;
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        //change colour of actionbar
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
//        //remove name from Actionbar
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        //emergencyLogout
//        ImageButton emergLogout = this.findViewById(R.id.logoutImgBtn);
//        emergLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                item.getItemId();
//                Intent intent;
//
//                 switch (item.getItemId()){
//                     case R.id.vaultItem:
//                        drawerL.closeDrawer(GravityCompat.START);
//                        intent = new Intent(Home.this, Vault.class);
//                        startActivity(intent);
//                        Log.v("Debug", "Vault menu item clicked");
//                        break;
//                     case R.id.emergInfoItem:
//                        drawerL.closeDrawer(GravityCompat.START);
//                        intent = new Intent(Home.this, EmergencyInformation.class);
//                        startActivity(intent);
//                        Log.v("Debug", "Emergency info menu item clicked");
//                        break;
//                    case R.id.getHelpItem:
//                        drawerL.closeDrawer(GravityCompat.START);
//                        intent = new Intent(Home.this, GetHelp.class);
//                        startActivity(intent);
//                        Log.v("Debug", "Get help info menu item clicked");
//                        break;
//                     case R.id.logoutItem:
//                        drawerL.closeDrawer(GravityCompat.START);
//                        intent = new Intent(Home.this, MainActivity.class);
//                        startActivity(intent);
//                        Log.v("Debug", "Emergency info menu item clicked");
//                        break;
//                }
//
//                    return true;
//                }
//
//        });
//    }
//}
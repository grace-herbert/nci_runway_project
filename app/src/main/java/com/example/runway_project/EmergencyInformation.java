package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class EmergencyInformation extends AppCompatActivity {
    private DrawerLayout drawerL;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_information);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Navigation
        navigationView = findViewById(R.id.emNav);
        drawerL = findViewById(R.id.emDrawerLayout);
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

                switch (item.getItemId()) {
                    case R.id.emHomeItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(EmergencyInformation.this, Home.class);
                        startActivity(intent);
                        Log.v("Debug", "Home menu item clicked");
                        break;
                    case R.id.emVaultItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(EmergencyInformation.this, Vault.class);
                        startActivity(intent);
                        Log.v("Debug", "Vault menu item clicked");
                        break;
                    case R.id.emGetHelpItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(EmergencyInformation.this, GetHelp.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.emLogoutItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(EmergencyInformation.this, MainActivity.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                }

                return true;
            }

        });
    }
}
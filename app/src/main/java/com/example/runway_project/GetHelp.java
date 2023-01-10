package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class GetHelp extends AppCompatActivity {
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

    //solution to app closing on home button found: https://stackoverflow.com/questions/21901015/how-to-kill-the-application-with-the-home-button
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);
        //change colour of actionbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //remove name from Actionbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //emergencyLogout
        ImageButton emergLogout = this.findViewById(R.id.logoutImgBtn);
        emergLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetHelp.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Navigation
        navigationView = findViewById(R.id.ghNav);
        drawerL = findViewById(R.id.ghDrawerLayout);
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
                    case R.id.ghHomeItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(GetHelp.this, Home.class);
                        startActivity(intent);
                        Log.v("Debug", "Home menu item clicked");
                        break;
                    case R.id.ghVaultItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(GetHelp.this, Vault.class);
                        startActivity(intent);
                        Log.v("Debug", "Vault menu item clicked");
                        break;
                    case R.id.ghEmergInfoItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(GetHelp.this, EmergencyInformation.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.ghSSOItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(GetHelp.this, StayingSafeOnline.class);
                        startActivity(intent);
                        Log.v("Debug", "Get help info menu item clicked");
                        break;
                    case R.id.ghLogoutItem:
                        drawerL.closeDrawer(GravityCompat.START);
                        intent = new Intent(GetHelp.this, MainActivity.class);
                        startActivity(intent);
                        Log.v("Debug", "Emergency info menu item clicked");
                        break;
                }

                return true;
            }

        });

        //set up dial onclick listeners
        TextView womensAidNo = findViewById(R.id.ghWomensAidTxt);
        womensAidNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 341 900"));
                startActivity(intent);
            }
        });
        TextView safeIrelandNo = findViewById(R.id.ghSafeIrelandTxt);
        safeIrelandNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:090 6479078"));
                startActivity(intent);
            }
        });
        TextView galwayDVRNo = findViewById(R.id.ghGalwayDVRTxt);
        galwayDVRNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:091 866740"));
                startActivity(intent);
            }
        });
        TextView galwayCOPENo = findViewById(R.id.ghGalwayCOPETxt);
        galwayCOPENo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:091 565985"));
                startActivity(intent);
            }
        });
        TextView mayoWSSNo = findViewById(R.id.ghMayoWSSTxt);
        mayoWSSNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:094 9025409"));
                startActivity(intent);
            }
        });
        TextView roscommonSLDASNo = findViewById(R.id.ghRoscommonSLDASTxt);
        roscommonSLDASNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:071 9664200"));
                startActivity(intent);
            }
        });
        TextView dVASNo = findViewById(R.id.ghDVASTxt);
        dVASNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:071 9141515"));
                startActivity(intent);
            }
        });
        TextView dDVS = findViewById(R.id.ghDDVSTxt);
        dDVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 262677"));
                startActivity(intent);
            }
        });
        TextView donegalWomensCentre = findViewById(R.id.ghDonegalWomensCentreTxt);
        donegalWomensCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:074 912 4985"));
                startActivity(intent);
            }
        });
        TextView lifelineInishowen = findViewById(R.id.ghLifelineInishowenTxt);
        lifelineInishowen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:074 937 3232"));
                startActivity(intent);
            }
        });
        TextView tDVS = findViewById(R.id.ghTDVSTxt);
        tDVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:047 72311"));
                startActivity(intent);
            }
        });
        TextView carlowWomensAid = findViewById(R.id.ghCarlowWomensAidTxt);
        carlowWomensAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 444 944"));
                startActivity(intent);
            }
        });
        TextView sonasDVC = findViewById(R.id.ghSonasDVCTxt);
        sonasDVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 222223"));
                startActivity(intent);
            }
        });
        TextView iOVAWC = findViewById(R.id.ghIOVAWCTxt);
        iOVAWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01 453 3938"));
                startActivity(intent);
            }
        });
        TextView aDASFWAC = findViewById(R.id.ghADASFWACTxt);
        aDASFWAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 767767"));
                startActivity(intent);
            }
        });
        TextView saoirseWR = findViewById(R.id.ghSaoirseWRTxt);
        saoirseWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01 463 0000"));
                startActivity(intent);
            }
        });
        TextView dublinWomensAid = findViewById(R.id.ghDublinWomensAidTxt);
        dublinWomensAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 341 900"));
                startActivity(intent);
            }
        });
        TextView teachTearmainn = findViewById(R.id.ghTeachTearmainnTxt);
        teachTearmainn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:045 527584"));
                startActivity(intent);
            }
        });
        TextView amber = findViewById(R.id.ghAmberTxt);
        amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0818 424244"));
                startActivity(intent);
            }
        });
        TextView lDAS = findViewById(R.id.ghLDASTxt);
        lDAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:057 8671100"));
                startActivity(intent);
            }
        });
        TextView lWL = findViewById(R.id.ghLWLTxt);
        lWL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:043 33 41511"));
                startActivity(intent);
            }
        });
        TextView dWR = findViewById(R.id.ghDWRTxt);
        dWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:041 9844550"));
                startActivity(intent);
            }
        });
        TextView wADundalk = findViewById(R.id.ghWADundalkTxt);
        wADundalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:042 9333244"));
                startActivity(intent);
            }
        });
        TextView meathWRSS = findViewById(R.id.ghMeathWRSSTxt);
        meathWRSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:046 9022393"));
                startActivity(intent);
            }
        });
        TextView offalyDVSS = findViewById(R.id.ghOffalyDVSSTxt);
        offalyDVSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:057 9351886"));
                startActivity(intent);
            }
        });
        TextView esker = findViewById(R.id.ghEskerTxt);
        esker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:090 6474122"));
                startActivity(intent);
            }
        });
        TextView brayWR = findViewById(R.id.ghBrayWRTxt);
        brayWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01 286 6163"));
                startActivity(intent);
            }
        });
        TextView yana = findViewById(R.id.ghYANATxt);
        yana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:022 53915"));
                startActivity(intent);
            }
        });
        TextView oSS = findViewById(R.id.ghOSSTxt);
        oSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 497 497"));
                startActivity(intent);
            }
        });
        TextView wCWADVP = findViewById(R.id.ghWCWADVPTxt);
        wCWADVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 203 136"));
                startActivity(intent);
            }
        });
        TextView cuanlee = findViewById(R.id.ghCuanleeTxt);
        cuanlee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:021 4277 698"));
                startActivity(intent);
            }
        });
        TextView mnaFeasa = findViewById(R.id.ghMnaFeasaTxt);
        mnaFeasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:021 4211757"));
                startActivity(intent);
            }
        });
        TextView goodShepherd = findViewById(R.id.ghGoodShepherdTxt);
        goodShepherd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:021 4274240"));
                startActivity(intent);
            }
        });
        TextView aKWRSS = findViewById(R.id.ghAKWRSSTxt);
        aKWRSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:066 7129100"));
                startActivity(intent);
            }
        });
        TextView aDAS = findViewById(R.id.ghADASTxt);
        aDAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 200 504"));
                startActivity(intent);
            }
        });
        TextView tippAscend = findViewById(R.id.ghTippAscendTxt);
        tippAscend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0505 23999"));
                startActivity(intent);
            }
        });
        TextView tippCuan = findViewById(R.id.ghTippCuanTxt);
        tippCuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 576757"));
                startActivity(intent);
            }
        });
        TextView oasis = findViewById(R.id.ghOasisTxt);
        oasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0818 272 372"));
                startActivity(intent);
            }
        });
        TextView wexfordWR = findViewById(R.id.ghWexfordWRTxt);
        wexfordWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800 220 444"));
                startActivity(intent);
            }
        });

    }
}

package com.example.runway_project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://runwayproject-39823-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference dbV = FirebaseDatabase.getInstance().getReferenceFromUrl("https://runwayproject-39823-default-rtdb.europe-west1.firebasedatabase.app/Vault");

    public DatabaseReference getRefDB(){
        this.dbRef = dbRef;

        return dbRef;
    }

    public DatabaseReference getDBV(){
        this.dbV = dbV;

        return dbV;
    }



}

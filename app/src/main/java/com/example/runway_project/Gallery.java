package com.example.runway_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class Gallery extends AppCompatActivity {
    private Database db =  new Database();
    private DatabaseReference dbRef = db.getRefDB();
    private DatabaseReference vltRef;
    private DatabaseReference usrVltRef;
    private String vltID;
    private RecyclerView galleryRecyclerView;
//helped by code from https://www.youtube.com/watch?v=g-Qcb5PjsGo

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
    }

    public void onStart() {

        super.onStart();
        FirebaseRecyclerAdapter<ImageItem, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageItem, ViewHolder>(ImageItem.class, R.layout.card, ViewHolder.class, usrVltRef) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, ImageItem imageItem, int i) {
                viewHolder.getTitleAndImg(getApplicationContext(), imageItem.getImgName(),imageItem.getImgUrl(),imageItem.getDate());
            }
        };
        galleryRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
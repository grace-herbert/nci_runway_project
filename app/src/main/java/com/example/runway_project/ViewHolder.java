package com.example.runway_project;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{
    View viewImg;
    public ViewHolder(View itemView) {
        super(itemView);
        viewImg = itemView;
    }


    public void getTitleAndImg(Context ctx, String imgName, String img, String date){

        ImageView cImg = viewImg.findViewById(R.id.cardImg);
        TextView cImgName = viewImg.findViewById(R.id.cardTitle);
        TextView cImgDate = viewImg.findViewById(R.id.cardDate);

        Picasso.get().load(img).into(cImg);
        cImgName.setText(imgName);
        cImgDate.setText(date);

    }
}

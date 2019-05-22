package com.example.nandurshop.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.nandurshop.activities;

import com.example.nandurshop.Model.Commodity;
import com.example.nandurshop.R;
import com.example.nandurshop.activities.EditCommodityActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by root on 2/3/17.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.MyViewHolder>{
    List<Commodity> commodities;

    public CommodityAdapter(List <Commodity> KontakList) {
        commodities = KontakList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.commodity_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewVarid.setText("Variety Id = " + commodities.get(position).getVarietyId());
        holder.mTextViewNama.setText("Nama = " + commodities.get(position).getName());
        holder.mTextViewPlantedat.setText("Planted = " + commodities.get(position).getPlantedAt());
        holder.mTextViewImg.setText("Image = " + commodities.get(position).getImageUrl());
        Picasso.get().load(commodities.get(position).getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent mIntent = new Intent(view.getContext(), EditCommodityActivity.class);
            mIntent.putExtra("Id", commodities.get(position).getId());
            mIntent.putExtra("Nama", commodities.get(position).getName());
            mIntent.putExtra("Plantedat", commodities.get(position).getPlantedAt());
            mIntent.putExtra("Varid", commodities.get(position).getVarietyId());
            mIntent.putExtra("Imgurl", commodities.get(position).getImageUrl());
            view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return commodities.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewPlantedat, mTextViewNama, mTextViewVarid, mTextViewImg;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mTextViewNama = (TextView) itemView.findViewById(R.id.name);
            mTextViewPlantedat = (TextView) itemView.findViewById(R.id.planted_at);
            mTextViewImg = (TextView) itemView.findViewById(R.id.image_url);
            mTextViewId = (TextView) itemView.findViewById(R.id.edtId);
            mTextViewVarid = (TextView) itemView.findViewById(R.id.variety_id);
        }
    }
}
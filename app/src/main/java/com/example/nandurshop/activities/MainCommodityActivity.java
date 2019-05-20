package com.example.nandurshop.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nandurshop.Adapter.CommodityAdapter;
import com.example.nandurshop.Model.Commodity;
import com.example.nandurshop.Model.GetCommodity;
import com.example.nandurshop.Model.RetrofitClientInstance;
import com.example.nandurshop.Interface.GetDataService;
import com.example.nandurshop.R;
import com.example.nandurshop.activities.InsertCommodityActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCommodityActivity extends AppCompatActivity {
    Button btIns;
    GetDataService mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainCommodityActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity_main);

        btIns = (Button) findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainCommodityActivity.this, InsertCommodityActivity.class));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        ma = this;
        refresh();
    }

    public void refresh() {
        Call<GetCommodity> kontakCall = mApiInterface.getCommodity();
        kontakCall.enqueue(new Callback<GetCommodity>() {
            @Override
            public void onResponse(Call<GetCommodity> call, Response<GetCommodity>
                    response) {
                List<Commodity> KontakList = response.body().getListDataCommodity();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(KontakList.size()));
                mAdapter = new CommodityAdapter(KontakList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCommodity> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
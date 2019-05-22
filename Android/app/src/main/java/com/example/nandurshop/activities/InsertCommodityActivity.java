package com.example.nandurshop.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nandurshop.Model.PostPutDelCommodity;
import com.example.nandurshop.Model.RetrofitClientInstance;
import com.example.nandurshop.Interface.GetDataService;
import com.example.nandurshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertCommodityActivity extends AppCompatActivity {
    EditText edtNama, edtVarid, edtPlantedad, edImgurl;
    Button btInsert, btBack;
    GetDataService mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_commodity);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtVarid = (EditText) findViewById(R.id.edtVarid);
        edtPlantedad = (EditText) findViewById(R.id.edtPlantedad);
        edImgurl = (EditText) findViewById(R.id.edImgurl);

        mApiInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        btInsert = (Button) findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelCommodity> postKontakCall =
                        mApiInterface.createCommodity(
                                edtNama.getText().toString(),
                                Integer.parseInt(edtVarid.getText().toString()),
                                edtPlantedad.getText().toString(),
                                edImgurl.getText().toString()
                        );

                postKontakCall.enqueue(new Callback<PostPutDelCommodity>() {
                    @Override
                    public void onResponse(Call<PostPutDelCommodity> call, Response<PostPutDelCommodity> response) {
                        MainCommodityActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelCommodity> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainCommodityActivity.ma.refresh();
                finish();
            }
        });
    }
}
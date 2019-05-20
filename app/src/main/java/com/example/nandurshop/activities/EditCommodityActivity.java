package com.example.nandurshop.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class EditCommodityActivity extends AppCompatActivity {
    EditText edtId, edtNama, edtVarid, edtPlantedad, edImgurl;
    Button btUpdate, btDelete, btBack;
    GetDataService mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_commodity);
        edtId = (EditText) findViewById(R.id.edtId);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtVarid = (EditText) findViewById(R.id.edtVarid);
        edtPlantedad = (EditText) findViewById(R.id.edtPlantedad);
        edImgurl = (EditText) findViewById(R.id.edImgurl);

        Intent mIntent = getIntent();
        edtId.setText(mIntent.getStringExtra("Id"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtNama.setText(mIntent.getStringExtra("Nama"));
        edtVarid.setText(mIntent.getStringExtra("Varid"));
        edtPlantedad.setText(mIntent.getStringExtra("Plantedat"));
        edImgurl.setText(mIntent.getStringExtra("Imgurl"));

        mApiInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelCommodity> updateKontakCall = mApiInterface.updateCommodity(
                        Integer.parseInt(edtId.getText().toString()),
                        edtNama.getText().toString(),
                        Integer.parseInt(edtVarid.getText().toString()),
                        edtPlantedad.getText().toString(),
                        edImgurl.getText().toString(),
                        "PUT"
                );
                updateKontakCall.enqueue(new Callback<PostPutDelCommodity>() {
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
        btDelete = (Button) findViewById(R.id.btDelete2);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelCommodity> deleteKontak = mApiInterface.deleteCommodity(Integer.parseInt(edtId.getText().toString()), "DELETE");
                    deleteKontak.enqueue(new Callback<PostPutDelCommodity>() {
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
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
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

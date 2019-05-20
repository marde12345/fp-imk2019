package com.example.nandurshop.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nandurshop.Interface.GetDataService;
import com.example.nandurshop.Model.Commodity;
import com.example.nandurshop.Model.PostPutDelCommodity;
import com.example.nandurshop.Model.RetrofitClientInstance;
import com.example.nandurshop.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraFormActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;
    ImageView imageView;
    Button btnCameraForm, btnNext, btnGallery;
    Commodity plant;
    GetDataService mApiInterface;
    String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_form);
        plant = (Commodity) getIntent().getSerializableExtra("plant");

        imageView = (ImageView) findViewById(R.id.imageView);
        btnCameraForm = (Button) findViewById(R.id.btnCamera);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnGallery = (Button) findViewById(R.id.btnGallery) ;

        btnCameraForm.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnGallery.setOnClickListener(this);

        mApiInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.btnNext:
                intent = new Intent(this,Main2Activity.class);
                if (plant.getImageUrl() == null){
                    imageURL = "https://www.randomlists.com/img/fruits/banana.jpg";
                } else {
                    imageURL = plant.getImageUrl();
                }
                Call<PostPutDelCommodity> postKontakCall =
                        mApiInterface.createCommodity(
                                plant.getName(),
                                plant.getVarietyId(),
                                plant.getPlantedAt(),
                                imageURL
                        );

                postKontakCall.enqueue(new Callback<PostPutDelCommodity>() {
                    @Override
                    public void onResponse(Call<PostPutDelCommodity> call, Response<PostPutDelCommodity> response) {
                        Toast.makeText(CameraFormActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelCommodity> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });

                startActivity(intent);
                break;
            case R.id.btnGallery:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            if (data != null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
        if (requestCode == REQUEST_IMAGE_GALLERY){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

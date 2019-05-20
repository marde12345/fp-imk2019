package com.example.nandurshop.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nandurshop.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnCameraForm, btnLogin, btnBottomNav, btnFormInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFormInput = (Button) findViewById(R.id.btnFormInput);
        btnCameraForm = (Button) findViewById(R.id.btnCameraForm);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnBottomNav = (Button) findViewById(R.id.btnBottomNav);

        btnFormInput.setOnClickListener(this);
        btnBottomNav.setOnClickListener(this);
        btnCameraForm.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnCameraForm:
                intent = new Intent(this,CameraFormActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBottomNav:
                intent = new Intent(this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFormInput:
                intent = new Intent(this,FormInputTanduranActivity.class);
                startActivity(intent);
                break;
        }
    }
}

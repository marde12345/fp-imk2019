package com.example.nandurshop.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nandurshop.R;
import com.example.nandurshop.activities.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goLogin(View v){
        Intent regIntent = new Intent(this, LoginActivity.class);
        startActivity(regIntent);
    }
}

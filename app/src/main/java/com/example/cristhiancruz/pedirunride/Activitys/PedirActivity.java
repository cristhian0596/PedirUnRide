package com.example.cristhiancruz.pedirunride.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cristhiancruz.pedirunride.R;

public class PedirActivity extends AppCompatActivity {
View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir);
        showToolbar(getString(R.string.title_pedir), false);


    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}


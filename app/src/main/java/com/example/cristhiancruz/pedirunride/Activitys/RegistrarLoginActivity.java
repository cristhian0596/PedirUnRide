package com.example.cristhiancruz.pedirunride.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cristhiancruz.pedirunride.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarLoginActivity extends AppCompatActivity {
    TextView btn_registrar;
    TextView btn_ingresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__login);

        btn_registrar= findViewById(R.id.btn_registrar);
        btn_ingresar= findViewById(R.id.btn_ingresar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSignup = new Intent(RegistrarLoginActivity.this, RegistrarActivity.class);
                RegistrarLoginActivity.this.startActivity(intentSignup);
            }
        });

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSign = new Intent(RegistrarLoginActivity.this, LoginActivity.class);
                RegistrarLoginActivity.this.startActivity(intentSign);
            }
        });
    }
}

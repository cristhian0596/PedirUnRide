package com.example.cristhiancruz.pedirunride.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristhiancruz.pedirunride.Clases.Variable;
import com.example.cristhiancruz.pedirunride.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //Para texto
    TextView lbl_resgistrar;

    //Botones
    Button btn_ingresar;
    //Etiquetas


    EditText txt_usuario_login;
    EditText txt_contraseña_login;
    //Variables
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    Variable variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showToolbar(getString(R.string.title_login), true);
        //Firebase
        mAuth = FirebaseAuth.getInstance();


        txt_usuario_login = ( EditText ) findViewById(R.id.txt_correo_login);
        txt_contraseña_login = ( EditText ) findViewById(R.id.txt_contraseña_login);
        btn_ingresar = ( Button ) findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);

        lbl_resgistrar = findViewById(R.id.link_registrar);
        lbl_resgistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSignup = new Intent(LoginActivity.this, RegistrarActivity.class);
                intentSignup.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                LoginActivity.this.startActivity(intentSignup);
            }
        });

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = "";
                String password = "";
                correo = txt_usuario_login.getText().toString();
                password = txt_contraseña_login.getText().toString();
                SignIn(correo, password);
            }
        });

    }

    private void SignIn(String correo, String password) {
        if (ValidateFields()) {
            progressDialog.setMessage(getString(R.string.progresDialog_cargando));
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(correo, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                FirebaseUser user = mAuth.getCurrentUser();
                                variables.user = user.getUid();
                                Toast.makeText(getBaseContext(), getString(R.string.toast_autentificacion_correcta),
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getBaseContext(), "Correo Electrónico o Contraseña invalida"
                                        , Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

    }

    private boolean ValidateFields() {
        String user = txt_usuario_login.getText().toString();
        String pass = txt_contraseña_login.getText().toString();
        boolean aux = true;
        if (TextUtils.isEmpty(user)) {
            txt_usuario_login.setError(getString(R.string.error_requerido));
            aux = false;
        }
        if (TextUtils.isEmpty(pass)) {
            txt_contraseña_login.setError(getString(R.string.error_requerido));
            aux = false;
        }
        return aux;
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}

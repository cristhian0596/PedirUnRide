package com.example.cristhiancruz.pedirunride.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristhiancruz.pedirunride.Clases.Usuario;
import com.example.cristhiancruz.pedirunride.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrarActivity extends AppCompatActivity {
    TextView lbl_ingresar;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    //Variables

    //EditText
    private EditText nombre;
    private EditText ubicacion;
    private EditText telefono;
    private EditText correo;
    private EditText contraseña;
    //Botones
    private Button registrarCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        showToolbar(getString(R.string.title_registro), true);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //Inicialiazar lo demas
        nombre = (EditText) findViewById(R.id.nombre_registrar_usuario);
        ubicacion = (EditText) findViewById(R.id.lugar_registrar_usuario);
        telefono = (EditText) findViewById(R.id.telefono_registrar_usuario);
        correo =(EditText)  findViewById(R.id.correo_registrar_usuario);
        contraseña = (EditText) findViewById(R.id.contraseña_registrar_usuario);
        registrarCuenta= (Button) findViewById(R.id.btn_signup);




        lbl_ingresar =  findViewById(R.id.link_signin);
        lbl_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSignin = new Intent(RegistrarActivity.this, LoginActivity.class);
                intentSignin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                RegistrarActivity.this.startActivity(intentSignin);
            }
        });

        registrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ValidateFields()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(R.string.msj_Está_seguro)
                            .setTitle(R.string.msj_creando_cuenta)
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.msj_aceptar), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String nombreO = nombre.getText().toString();
                                    String ubicacionO = ubicacion.getText().toString();
                                    String telefonoO = telefono.getText().toString();
                                    String correoO = correo.getText().toString();
                                    String contraseñaO = contraseña.getText().toString();

                                    RegistrarCuenta(nombreO,ubicacionO,telefonoO,correoO,contraseñaO);
                                }
                            })
                            .setNegativeButton(getString(R.string.msj_cancelar), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else{

                }

            }
        });

    }

    private boolean ValidateFields(){
        boolean aux = true;
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String nameV = nombre.getText().toString();
        String ubicationV = ubicacion.getText().toString();
        String  phoneV = telefono.getText().toString();
        String emailV = correo.getText().toString();
        String passwordV = contraseña.getText().toString();

        if(TextUtils.isEmpty(nameV)){
            nombre.setError(getString(R.string.error_requerido));
            nombre.requestFocus();
            aux = false;
        }
        if(TextUtils.isEmpty(ubicationV)){
            ubicacion.setError(getString(R.string.error_requerido));
            ubicacion.requestFocus();
            aux = false;
        }
        if(TextUtils.isEmpty(phoneV)){
            telefono.setError(getString(R.string.error_requerido));
            telefono.requestFocus();
            aux = false;
        }else if(phoneV.length()<8){
            telefono.setError(getString(R.string.error_valor_mímino_telefono));
            telefono.requestFocus();
            aux = false;
        }
        if(TextUtils.isEmpty(emailV)){
            correo.setError(getString(R.string.error_requerido));
            correo.requestFocus();
            aux = false;
        }else if(!pattern.matcher(emailV).matches()){
            correo.setError(getString(R.string.error_formato_correo_incorrecto));
            correo.requestFocus();
            aux = false;
        }
        if(TextUtils.isEmpty(passwordV)){
            contraseña.setError(getString(R.string.error_requerido));
            contraseña.requestFocus();
            aux = false;
        }else if(passwordV.length()<6){
            contraseña.setError(getString(R.string.error_valor_mímino));
            contraseña.requestFocus();
            aux = false;
        }
        return aux;
    }

    private void RegistrarCuenta(final String nombreP, final String ubicacionP, final String telefonoP, final String correoP, final String contraseñaP){
        //  if(ValidateFields()){
        mAuth.createUserWithEmailAndPassword(correoP,contraseñaP)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            SignIn(nombreP,ubicacionP,telefonoP,correoP,contraseñaP);

                        }else{
                            // If sign in fails, display a message to the user.
                            Log.w("aux", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), getString(R.string.msj_autentificacion_fallida),
                                    Toast.LENGTH_SHORT).show();
                            correo.setError("Email not available");
                        }
                    }
                });
        // }

    }

    private void SignIn(final String nameS, final String ubicationS, final String phoneS, final String emailS, final String passwordS){
        // if (ValidateFields()) {
        mAuth.signInWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            CreateUser(nameS, ubicationS, phoneS, emailS, passwordS);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Aux", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

        //}

    }

    private void CreateUser(String nameU, String ubicationU, String phoneU, String emailU, String passwordU){
        FirebaseUser user = mAuth.getCurrentUser();
        Usuario usuario = new Usuario(nameU,ubicationU,phoneU,"-",emailU,passwordU);
        String uid = user.getUid();

        myRef.child("usuario").child(uid);
        myRef.child("usuario").child(uid).setValue(usuario);
        Toast.makeText(getBaseContext(),getString(R.string.msj_usuario_creado),Toast.LENGTH_LONG).show();
        ClearFields();
        finish();
    }

    private void ClearFields(){
        nombre.setText("");
        ubicacion.setText("");
        telefono.setText("");
        correo.setText("");
        contraseña.setText("");
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}

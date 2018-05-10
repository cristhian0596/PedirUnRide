package com.example.cristhiancruz.pedirunride.Fragments;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristhiancruz.pedirunride.Clases.Usuario;
import com.example.cristhiancruz.pedirunride.Clases.Variable;
import com.example.cristhiancruz.pedirunride.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */



public class PerfilFragment extends Fragment {
    View view;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseStorage storage;
    StorageReference storageRef;
    UploadTask uploadTask;
    Variable v;
    ProgressDialog progressDialog;
    static final int TYPE_CLASS_TEXT = 0x00000001;
    static final int TYPE_CLASS_PHONE = 0x00000003;
    static final int TYPE_TEXT_VARIATION_PERSON_NAME = 0x00000060;
    static final int RESULT_LOAD_IMG = 1;
    private Uri selectedImage;

    //EditText,imageView,TextView
    CircleImageView foto;
    TextView nombre;
    TextView ubicacion;
    TextView telefono;
    TextView email;
    EditText datoEdit;
    FloatingActionButton btn_foto;
    ImageButton btn_editar_ubicacion;
    ImageButton btn_editar_nombre;
    ImageButton btn_editar_telefono;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Inicializar Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        //Inicializar
        progressDialog = new ProgressDialog(view.getContext());
        nombre = (TextView) view.findViewById(R.id.txt_nombre_perfil);
        ubicacion = (TextView) view.findViewById(R.id.txt_ubicacion_perfil);
        telefono = (TextView) view.findViewById(R.id.txt_telefono_perfil);

        foto = (CircleImageView) view.findViewById(R.id.imagen_perfil);
        progressDialog.setMessage(getString(R.string.progresDialog_cargando));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        LoadDataProfile(view);

        btn_editar_nombre = (ImageButton) view.findViewById(R.id.btn_nombre_perfil);
        btn_editar_nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadDialogEdit(nombre.getText().toString(), "nombre", TYPE_TEXT_VARIATION_PERSON_NAME,R.drawable.user);
            }
        });

        btn_editar_ubicacion = (ImageButton) view.findViewById(R.id.btn_ubicacion_perfil);
        btn_editar_ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadDialogEdit(ubicacion.getText().toString(), "lugar", TYPE_CLASS_TEXT,R.drawable.lugar);
            }
        });

        btn_editar_telefono = (ImageButton) view.findViewById(R.id.btn_telefono_perfil);
        btn_editar_telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadDialogEdit(telefono.getText().toString(), "telefono", TYPE_CLASS_PHONE,R.drawable.telefono);
            }
        });
        btn_foto = (FloatingActionButton) view.findViewById(R.id.btn_floating_perfil);
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadImagefromGallery(view);
            }
        });



        return view ;

    }

    //Método para cargar la información del usuario logueado
    public void LoadDataProfile(final View view2) {
        myRef.child("usuario").child(v.user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                nombre.setText(usuario.getNombre());
                ubicacion.setText(usuario.getLugar());
                telefono.setText(usuario.getTelefono());

                Picasso.with(view2.getContext())
                        .load(usuario.getFoto())
                        .error(R.drawable.perfil)
                        .into(foto, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressDialog.dismiss();
                            }
                            @Override
                            public void onError() {
                                progressDialog.dismiss();
                                Toast.makeText(view2.getContext(),"Error al cargar la foto",Toast.LENGTH_LONG).show();
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Prueba", "Fallo al leer el valor.", error.toException());
            }
        });
    }

    //Metodo para abrir el dialogo y editar la información requerida
    private void LoadDialogEdit(String dato, final String campoEditar, int tipoDato,int icono) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View viewEdit;
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        viewEdit = inflater.inflate(R.layout.editar_campo, null);
        datoEdit = (EditText) viewEdit.findViewById(R.id.edittext_editar_campo);
        datoEdit.setInputType(tipoDato);
        datoEdit.setText(dato);
        builder.setView(viewEdit)
                // Add action buttons
                .setPositiveButton(getString(R.string.msj_aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText msj = (EditText) viewEdit.findViewById(R.id.edittext_editar_campo);
                        myRef.child("usuario").child(v.user).child(campoEditar).setValue(msj.getText().toString());
                        Toast.makeText(view.getContext(), getString(R.string.toast_editado_exito), Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(getString(R.string.msj_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setCancelable(false)
                .setIcon(icono)
                .setTitle(getString(R.string.title_editar));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Método para abrir la galeria
    public void LoadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        int permiso = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permiso != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }
        if (permiso == PackageManager.PERMISSION_GRANTED){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Start the Intent
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == -1 && null != data) {
            // Get the Image from data
            String ruta;
            selectedImage = data.getData();
            StorageReference path = storageRef.child("usuario").child(selectedImage.getLastPathSegment());
            path.putFile(selectedImage);
            ruta = path.getPath();
            storageRef.child(ruta).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    progressDialog.setMessage(getString(R.string.progresDialog_cargando));
                    progressDialog.show();
                    myRef.child("usuario").child(v.user).child("foto").setValue("" + uri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });

            progressDialog.dismiss();


        }
    }





}

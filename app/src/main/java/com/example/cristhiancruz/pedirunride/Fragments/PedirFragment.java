package com.example.cristhiancruz.pedirunride.Fragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.cristhiancruz.pedirunride.Activitys.LoginActivity;
import com.example.cristhiancruz.pedirunride.Activitys.PedirActivity;
import com.example.cristhiancruz.pedirunride.Activitys.RegistrarActivity;
import com.example.cristhiancruz.pedirunride.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedirFragment extends Fragment {
View view;
FloatingActionButton btn_pedir;


    public PedirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pedir, container, false);

        btn_pedir = (FloatingActionButton ) view.findViewById(R.id.btn_floating_pedir);
        btn_pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentpedir = new Intent(getActivity(), PedirActivity.class);
                getActivity().startActivity(intentpedir);
            }
        });

        return view ;
    }


}

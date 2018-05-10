package com.example.cristhiancruz.pedirunride.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristhiancruz.pedirunride.Activitys.OfrecerActivity;
import com.example.cristhiancruz.pedirunride.Activitys.PedirActivity;
import com.example.cristhiancruz.pedirunride.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfrecerFragment extends Fragment {
    View view;
    FloatingActionButton btn_ofrecer;

    public OfrecerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ofrecer, container, false);

        btn_ofrecer = (FloatingActionButton ) view.findViewById(R.id.btn_floating_ofrecer);
        btn_ofrecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentofrecer = new Intent(getActivity(), OfrecerActivity.class);
                getActivity().startActivity(intentofrecer);
            }
        });

        return view ;
    }

}

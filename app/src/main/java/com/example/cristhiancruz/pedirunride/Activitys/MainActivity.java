package com.example.cristhiancruz.pedirunride.Activitys;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cristhiancruz.pedirunride.Fragments.OfrecerFragment;
import com.example.cristhiancruz.pedirunride.Fragments.PedirFragment;
import com.example.cristhiancruz.pedirunride.Fragments.PerfilFragment;
import com.example.cristhiancruz.pedirunride.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolbar(getString(R.string.title_principal), false);
        ShowTabs();
    }

    private void ShowTabs() {
        BottomBar bottomBar = findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.tab_pedir);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_pedir:
                        PedirFragment pedir = new PedirFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, pedir)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.tab_ofrecer:
                        OfrecerFragment ofrecer = new OfrecerFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ofrecer)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.tab_perfil:
                        PerfilFragment perfil = new PerfilFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, perfil)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = toolbar = ( Toolbar ) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.Cerrrar_Aplicacion)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

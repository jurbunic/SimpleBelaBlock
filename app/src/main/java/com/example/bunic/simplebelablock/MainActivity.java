package com.example.bunic.simplebelablock;

import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.bunic.simplebelablock.Helpers.StartFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private Toolbar toolbar;
    private FragmentManager mFragmentManager;

    public static FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        FragmentChoosePlayers fcp = new FragmentChoosePlayers();
        StartFragment.StartNewFragment(fcp,this);
    }

    private Toolbar setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),android.R.color.white));
        toolbar.setTitle("Bela blok");
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mFragmentManager.getBackStackEntryCount()<1){
            this.finish();
        }
        else{
            try{
                fab.setVisibility(View.VISIBLE);
            }catch (NullPointerException e){
                // This exception will be triggerd if back button is pressed before the scoreboard is opened
            }
        }
    }

    @Override
    public void onBackStackChanged() {
    }
}

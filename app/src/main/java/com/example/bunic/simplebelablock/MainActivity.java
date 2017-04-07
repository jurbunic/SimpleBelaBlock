package com.example.bunic.simplebelablock;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.bunic.simplebelablock.Helpers.StartFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private Toolbar toolbar;
    private FragmentManager mFragmentManager;

    Player we = new Player("We");
    Player they = new Player("They");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        Intent intent = getIntent();
        intent.putExtra("we",we);
        intent.putExtra("they",they);

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
    }

    @Override
    public void onBackStackChanged() {
    }
}

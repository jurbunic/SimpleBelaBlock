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

    Score scoreWe = new Score();
    Score scoreThey = new Score();
    Player we = new Player("We",scoreWe);
    Player they = new Player("They", scoreThey);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        scoreWe.setCurrentScore(0);
        scoreThey.setCurrentScore(0);
        //scoreWe.addScore(0);
        //scoreThey.addScore(0);
        Intent intent = getIntent();
        intent.putExtra("we",we);
        intent.putExtra("they",they);

        Fragment_Scoreboard fs = new Fragment_Scoreboard();
        StartFragment.StartNewFragment(fs,this);

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

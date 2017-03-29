package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bunic.simplebelablock.Helpers.StartFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 3/29/17.
 */

public class FragmentChoosePlayers extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @OnClick(R.id.img_btn_three_players)
    public void onThreePlayersClick(){
        FragmentScoreBoardThree fs3 = new FragmentScoreBoardThree();
        StartFragment.StartNewFragment(fs3, getActivity());
    }

    @OnClick(R.id.img_btn_four_players)
    public void onFourPlayersClick(){
        Fragment_Scoreboard fs = new Fragment_Scoreboard();
        StartFragment.StartNewFragment(fs, getActivity());
    }
}

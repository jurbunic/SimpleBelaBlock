package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.IntentService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bunic.simplebelablock.scoreboard.ThreePlayersScoreboard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * Created by bunic on 3/29/17.
 */

public class FragmentAddScoreThree extends Fragment {
    FragmentManager mFragmentManager;
    ThreePlayersScoreboard board;

    @BindView(R.id.edit_score_three_player1)
    EditText score1;
    @BindView(R.id.edit_score_three_player2)
    EditText score2;
    @BindView(R.id.edit_score_three_player3)
    EditText score3;

    @BindView(R.id.edit_zvanje_three_player1)
    EditText zvanje1;
    @BindView(R.id.edit_zvanje_three_player2)
    EditText zvanje2;
    @BindView(R.id.edit_zvanje_three_player3)
    EditText zvanje3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_score_three, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        board = ThreePlayersScoreboard.getInstance();
        mFragmentManager = getFragmentManager();
    }

    @OnFocusChange(R.id.edit_score_three_player1)
    public void onScore1Change(){
        if(score2.getText().toString().equals("") && score3.getText().toString().equals("")){
            return;
        }
        Integer totalScore;
        if(!score2.getText().toString().equals("") && !score1.getText().toString().equals("")){
            totalScore = 162 - (Integer.parseInt(score2.getText().toString())+Integer.parseInt(score1.getText().toString()));
            score3.setText(totalScore.toString());
            return;
        }if(!score3.getText().toString().equals("") && !score1.getText().toString().equals("")){
            totalScore = 162 -(Integer.parseInt(score1.getText().toString())+Integer.parseInt(score3.getText().toString()));
            score3.setText(totalScore.toString());
        }
    }

    @OnFocusChange(R.id.edit_score_three_player2)
    public void onScore2Change(){
        if(score1.getText().toString().equals("") && score3.getText().toString().equals("")){
            return;
        }
        Integer totalScore;
        if(!score1.getText().toString().equals("") && !score2.getText().toString().equals("")){
            totalScore = 162 - (Integer.parseInt(score2.getText().toString())+Integer.parseInt(score1.getText().toString()));
            score3.setText(totalScore.toString());
            return;
        }if(!score3.getText().toString().equals("") && !score2.getText().toString().equals("")){
            totalScore = 162 -(Integer.parseInt(score2.getText().toString())+Integer.parseInt(score3.getText().toString()));
            score1.setText(totalScore.toString());
        }
    }

    @OnFocusChange(R.id.edit_score_three_player3)
    public void onScore3Change(){
        if(score1.getText().toString().equals("") && score2.getText().toString().equals("")){
            return;
        }
        Integer totalScore;
        if(!score1.getText().toString().equals("") && !score3.getText().toString().equals("")){
            totalScore = 162 - (Integer.parseInt(score3.getText().toString())+Integer.parseInt(score1.getText().toString()));
            score2.setText(totalScore.toString());
            return;
        }if(!score3.getText().toString().equals("") && !score2.getText().toString().equals("")){
            totalScore = 162 -(Integer.parseInt(score3.getText().toString())+Integer.parseInt(score2.getText().toString()));
            score1.setText(totalScore.toString());
        }
    }
    @OnClick(R.id.fab_reject)
    public void onClickReject(){
        getActivity().getFragmentManager().popBackStack();
        refresh();
    }

    public void refresh(){
        Fragment f = mFragmentManager.findFragmentById(R.id.fragment_container);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(f).attach(f).commit();
    }
}
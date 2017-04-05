package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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
    Integer onTurn;

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

    @BindView(R.id.radio_turn_player1)
    RadioButton player1Turn;
    @BindView(R.id.radio_turn_player2)
    RadioButton player2Turn;
    @BindView(R.id.radio_turn_player3)
    RadioButton player3Turn;

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
        onTurn = board.listSize() % 3;
        if(onTurn == 0){
            player1Turn.setChecked(true);
        }else if (onTurn == 1){
            player2Turn.setChecked(true);
        }else if (onTurn == 2){
            player3Turn.setChecked(true);
        }
    }

    @OnFocusChange(R.id.edit_score_three_player1)
    public void onScore1Change(){
        if(scoreIsEmpty()){
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
        if(scoreIsEmpty()){
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
        if(scoreIsEmpty()){
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

    @OnClick(R.id.fab_confirm)
    public void onClickConfirm(){
        Integer score[] = new Integer[3];
        Integer calls[] = new Integer[3];

        if(scoreIsEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "Insert at least two scores! ", Toast.LENGTH_SHORT).show();
            return;
        }

        if(score1.getText().toString().equals("")){
            calculateScore(score1,score2,score3);
        }else if(score2.getText().toString().equals("")){
            calculateScore(score2,score1,score3);
        }else if(score3.getText().toString().equals("")){
            calculateScore(score3,score1,score2);
        }

        score[0] = Integer.parseInt(score1.getText().toString());
        score[1] = Integer.parseInt(score2.getText().toString());
        score[2] = Integer.parseInt(score3.getText().toString());

        if(zvanje1.getText().toString().equals("")){
            zvanje1.setText("0");
        }if(zvanje2.getText().toString().equals("")){
            zvanje2.setText("0");
        }if(zvanje3.getText().toString().equals("")){
            zvanje3.setText("0");
        }

        calls[0] = Integer.parseInt(zvanje1.getText().toString());
        calls[1] = Integer.parseInt(zvanje2.getText().toString());
        calls[2] = Integer.parseInt(zvanje3.getText().toString());

        board.newRow(score,calls,onTurn);
        getActivity().getFragmentManager();
        refresh();
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

    private boolean scoreIsEmpty(){
        String valScore1 = score1.getText().toString();
        String valScore2 = score2.getText().toString();
        String valScore3 = score3.getText().toString();

        if(valScore1.equals("") && valScore2.equals("") && valScore3.equals("")){
            return true;
        }else if(valScore2.equals("") && valScore3.equals("")){
            return true;
        }else if(valScore1.equals("") && valScore3.equals("")){
            return true;
        }else if(valScore1.equals("") && valScore2.equals("")){
            return true;
        }
        return false;
    }

    private void calculateScore(EditText result, EditText firstScore, EditText secondScore){
        Integer score = 162 - (Integer.parseInt(firstScore.getText().toString())+Integer.parseInt(secondScore.getText().toString()));
        result.setText(score.toString());
    }

}

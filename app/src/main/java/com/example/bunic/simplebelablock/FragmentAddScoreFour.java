package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bunic.simplebelablock.Helpers.InputFilterMinMax;
import com.example.bunic.simplebelablock.scoreboard.Row;
import com.example.bunic.simplebelablock.scoreboard.ScoreboardFour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * Created by bunic on 05.04.17..
 */

public class FragmentAddScoreFour extends Fragment {
    FragmentManager mFragmentManager;
    ScoreboardFour board;
    Integer onTurn;

    @BindView(R.id.txt_player_1)
    TextView player1Name;
    @BindView(R.id.txt_player_2)
    TextView player2Name;

    @BindView(R.id.edit_score_four_player1)
    EditText score1;
    @BindView(R.id.edit_score_four_player2)
    EditText score2;

    @BindView(R.id.edit_zvanje_four_player1)
    EditText zvanje1;
    @BindView(R.id.edit_zvanje_four_player2)
    EditText zvanje2;

    @BindView(R.id.radio_turn_player1)
    RadioButton player1Turn;
    @BindView(R.id.radio_turn_player2)
    RadioButton player2Turn;

    List<Player> players;
    SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_score_four, container, false);
        ButterKnife.bind(this,view);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        board = ScoreboardFour.getInstance();
        players = board.getPlayers();
        player1Name.setText(players.get(0).getName());
        player2Name.setText(players.get(1).getName());
    }

    @Override
    public void onStart() {
        super.onStart();

        mFragmentManager = getFragmentManager();
        onTurn = board.getScoreListSize() % 2;
        if(onTurn == 0){
            player1Turn.setChecked(true);
        }else if (onTurn == 1){
            player2Turn.setChecked(true);
        }

        score1.setFilters(new InputFilter[]{new InputFilterMinMax(0,162)});
        score2.setFilters(new InputFilter[]{new InputFilterMinMax(0,162)});
    }

    @OnFocusChange(R.id.edit_score_four_player1)
    public void onScore1Change(){
        if(scoreIsEmpty()){
            return;
        }
        calculateScore(score2,score1);
    }

    @OnFocusChange(R.id.edit_score_four_player2)
    public void onScore2Change(){
        if(scoreIsEmpty()){
            return;
        }
        calculateScore(score1,score2);
    }


    @OnClick(R.id.fab_confirm)
    public void onClickConfirm(){
        Integer score[] = new Integer[2];
        Integer calls[] = new Integer[2];
        onTurn = player1Turn.isChecked() == true ? 1 : 2;
        if(scoreIsEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "Insert at least one score! ", Toast.LENGTH_SHORT).show();
            return;
        }

        if(score1.getText().toString().equals("")){
            calculateScore(score1,score2);
        }else if(score2.getText().toString().equals("")){
            calculateScore(score2,score1);
        }

        score[0] = Integer.parseInt(score1.getText().toString());
        score[1] = Integer.parseInt(score2.getText().toString());

        if(zvanje1.getText().toString().equals("")){
            zvanje1.setText("0");
        }if(zvanje2.getText().toString().equals("")){
            zvanje2.setText("0");
        }

        calls[0] = Integer.parseInt(zvanje1.getText().toString());
        calls[1] = Integer.parseInt(zvanje2.getText().toString());
        Row row = new Row();
        Integer[] scores = new Integer[2];
        scores[0] = score[0]+calls[0];
        scores[1] = score[1]+calls[1];
        row.setRowScores(scores);
        row.calculateRow(onTurn-1);
        board.addRow(row);
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

        if(valScore1.equals("") && valScore2.equals("")){
            return true;
        }
        return false;
    }

    private void calculateScore(EditText scoreResult, EditText scoreEntered){
        Integer score = 162 - (Integer.parseInt(scoreEntered.getText().toString()));
        scoreResult.setText(score.toString());
    }
}

package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bunic.simplebelablock.Adapters.IDataChanged;
import com.example.bunic.simplebelablock.Adapters.ThreePlayersScoreList;
import com.example.bunic.simplebelablock.Helpers.StartFragment;
import com.example.bunic.simplebelablock.scoreboard.ScoreboardControls;
import com.example.bunic.simplebelablock.scoreboard.ScoreboardThree;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by bunic on 3/29/17.
 */

public class FragmentScoreBoardThree extends Fragment implements IDataChanged {
    @BindView(R.id.fab_new_score)
    FloatingActionButton fab;
    @BindView(R.id.new_score_fragment_container)
    FrameLayout newScore;

    @BindView(R.id.txt_player_1)
    TextView player1Name;
    @BindView(R.id.txt_player_2)
    TextView player2Name;
    @BindView(R.id.txt_player_3)
    TextView player3Name;
    @BindView(R.id.txt_total_score_player1)
    TextView totalScorePlayer1;
    @BindView(R.id.txt_total_score_player2)
    TextView totalScorePlayer2;
    @BindView(R.id.txt_total_score_player3)
    TextView totalScorePlayer3;

    @BindView(R.id.txt_total_points_player1)
    TextView totalPointsPlayer1;
    @BindView(R.id.txt_total_points_player2)
    TextView totalPointsPlayer2;
    @BindView(R.id.txt_total_points_player3)
    TextView totalPointsPlayer3;

    RecyclerView recyclerView;
    ThreePlayersScoreList mAdapter;
    ScoreboardThree board;

    SharedPreferences preferences;
    ArrayList<Player> players;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard_three, container, false);
        ButterKnife.bind(this,view);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(players == null){
            players = setPlayers();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        player1Name.setText(players.get(0).getName());
        player2Name.setText(players.get(1).getName());
        player3Name.setText(players.get(2).getName());
        MainActivity.fab = fab;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(fab.getVisibility() == View.GONE){
            fab.setVisibility(View.VISIBLE);
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Bela Blok");

        if(board == null){
            board = ScoreboardThree.getInstance(players);
        }

        readTotalScores();

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_three);
        mAdapter = new ThreePlayersScoreList(getActivity().getApplicationContext(), board, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);


        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(fab.getVisibility() == View.GONE){
            fab.setVisibility(View.VISIBLE);
        }
        int roundResults = ScoreboardControls.checkVictoryCondition(701, board.getTotalScores());
        switch (roundResults){
            case -1:
                break;
            case 0:
                ScoreboardControls.addPointToPlayer(players.get(0),board);
                Toast.makeText(getActivity().getApplicationContext(),"Player 1 has won the round", Toast.LENGTH_SHORT);
                break;
            case 1:
                ScoreboardControls.addPointToPlayer(players.get(1),board);
                Toast.makeText(getActivity().getApplicationContext(),"Player 2 has won the round", Toast.LENGTH_SHORT);
                break;
            case 2:
                ScoreboardControls.addPointToPlayer(players.get(2),board);
                Toast.makeText(getActivity().getApplicationContext(),"Player 2 has won the round", Toast.LENGTH_SHORT);
                break;
        }
        readTotalScores();
    }

    @OnClick(R.id.fab_new_score)
    public void onFabClick(){
        FragmentAddScoreThree fast = new FragmentAddScoreThree();
        StartFragment.StartNewFragment(fast,getActivity(),"1");
        fab.setVisibility(View.GONE);
    }

    private ArrayList<Player> setPlayers(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        player1.setName(preferences.getString("PLAYER1NAME","player1"));
        player2.setName(preferences.getString("PLAYER2NAME","player2"));
        player3.setName(preferences.getString("PLAYER3NAME","player3"));

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return players;
    }

    @Override
    public void dataChanged() {
        Toast.makeText(getActivity().getApplicationContext(),"Izbrisan redak",Toast.LENGTH_SHORT).show();
        readTotalScores();
    }

    public void readTotalScores(){
        totalPointsPlayer1.setText(String.valueOf(players.get(0).getPoints()));
        totalPointsPlayer2.setText(String.valueOf(players.get(1).getPoints()));
        totalPointsPlayer3.setText(String.valueOf(players.get(2).getPoints()));
        totalScorePlayer1.setText(String.valueOf(board.getTotalScores()[0]));
        totalScorePlayer2.setText(String.valueOf(board.getTotalScores()[1]));
        totalScorePlayer3.setText(String.valueOf(board.getTotalScores()[2]));
    }
}

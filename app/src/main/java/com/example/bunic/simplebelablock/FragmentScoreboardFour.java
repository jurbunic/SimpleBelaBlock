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

import com.example.bunic.simplebelablock.Adapters.FourPlayersScoreList;
import com.example.bunic.simplebelablock.Helpers.StartFragment;
import com.example.bunic.simplebelablock.scoreboard.FourPlayersScoreboard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 05.04.17..
 */

public class FragmentScoreboardFour extends Fragment{
    @BindView(R.id.fab_new_score)
    FloatingActionButton fab;
    @BindView(R.id.new_score_fragment_container)
    FrameLayout newScore;
    SharedPreferences preferences;

    @BindView(R.id.txt_player_1)
    TextView player1Name;
    @BindView(R.id.txt_player_2)
    TextView player2Name;
    @BindView(R.id.txt_total_score_player1)
    TextView totalScorePlayer1;
    @BindView(R.id.txt_total_score_player2)
    TextView totalScorePlayer2;

    RecyclerView recyclerView;
    FourPlayersScoreList mAdapter;
    FourPlayersScoreboard board;
    List<Player> players;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard_four, container, false);
        ButterKnife.bind(this,view);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        players = setPlayers();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        player1Name.setText(players.get(0).getName());
        player2Name.setText(players.get(1).getName());
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
            board = FourPlayersScoreboard.getInstance(players);
        }

        totalScorePlayer1.setText(board.getTotalScore(0).toString());
        totalScorePlayer2.setText(board.getTotalScore(1).toString());

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_four);
        mAdapter = new FourPlayersScoreList(getActivity().getApplicationContext(), board);
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
    }

    @OnClick(R.id.fab_new_score)
    public void onFabClick(){
        FragmentAddScoreFour fasf = new FragmentAddScoreFour();
        StartFragment.StartNewFragment(fasf,getActivity(),"1");
        fab.setVisibility(View.GONE);
    }

    private List<Player> setPlayers(){
        Player player1 = new Player();
        Player player2 = new Player();

        player1.setName(preferences.getString("TEAM1NAME","Team1"));
        player2.setName(preferences.getString("TEAM2NAME","Team2"));

        player1.getName();
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        return players;
    }
}

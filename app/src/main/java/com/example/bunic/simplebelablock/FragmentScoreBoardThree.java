package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bunic.simplebelablock.Adapters.ThreePlayersScoreList;
import com.example.bunic.simplebelablock.Helpers.StartFragment;
import com.example.bunic.simplebelablock.scoreboard.Scoreboard;
import com.example.bunic.simplebelablock.scoreboard.ThreePlayersScoreboard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 3/29/17.
 */

public class FragmentScoreBoardThree extends Fragment {
    @BindView(R.id.fab_new_score)
    FloatingActionButton fab;
    @BindView(R.id.new_score_fragment_container)
    FrameLayout newScore;

    RecyclerView recyclerView;
    ThreePlayersScoreList mAdapter;
    ThreePlayersScoreboard board;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard_three, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(fab.getVisibility() == View.GONE){
            fab.setVisibility(View.VISIBLE);
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Bela Blok");

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler);

        if(board == null){
            board = ThreePlayersScoreboard.getInstance(setPlayers());
        }
        /*
        //--------- Test values-------------
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        ThreePlayersScoreboard board = new ThreePlayersScoreboard(players);
        Integer[] scores = new Integer[3];
        Integer[] calls = new Integer[3];

        scores[0] = 82; scores[1]=40; scores[2]=40;
        calls[0] = 0; calls[1] = 0; calls[2] = 0;

        board.newRow(scores,calls,0);
        //-----------------------------------
        */
        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler_three);
        mAdapter = new ThreePlayersScoreList(getActivity().getApplicationContext(), board);
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
        FragmentAddScoreThree fast = new FragmentAddScoreThree();
        StartFragment.StartNewFragment(fast,getActivity(),"1");
        fab.setVisibility(View.GONE);
    }

    private List<Player> setPlayers(){
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return players;
    }
}

package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.bunic.simplebelablock.Adapters.ResultsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Jurica Bunić on 7.2.2017..
 */

public class Fragment_Scoreboard extends Fragment{

    @BindView(R.id.txt_total_score_we)
    TextView totalWe;

    @BindView(R.id.txt_total_score_they)
    TextView totalThey;


    ResultsListAdapter mAdapter;
    RecyclerView recyclerView;

    Player we;
    Player they;
    Score scoreWe;
    Score scoreThey;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_scoreboard, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        we = intent.getExtras().getParcelable("we");
        they = intent.getExtras().getParcelable("they");
        scoreWe = we.getScore();
        scoreThey = they.getScore();

        recyclerView = (RecyclerView) getView().findViewById(R.id.main_recycler);

        mAdapter = new ResultsListAdapter(getActivity().getApplicationContext(),scoreWe,scoreThey);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);


        mAdapter.notifyDataSetChanged();


       // listScoreWe.setAdapter(mScoreWe);
     //   listScoreThey.setAdapter(mScoreThey);

        totalWe.setText(String.valueOf(scoreWe.getTotalScore()));
        totalThey.setText(String.valueOf(scoreThey.getTotalScore()));
    }



    @OnClick(R.id.btn_add_score)
    public void onBtnAddScore(){
        Fragment_Add_Score fas = new Fragment_Add_Score();
        StartFragment.StartNewFragment(fas,getActivity(),"");

    }

}

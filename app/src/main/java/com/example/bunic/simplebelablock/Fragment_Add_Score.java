package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

/**
 * Created by Jurica Bunić on 8.2.2017..
 */

public class Fragment_Add_Score extends Fragment {

    @BindView(R.id.edit_new_score_we)
    EditText addScoreWe;

    @BindView(R.id.edit_new_score_they)
    EditText addScoreThey;

    @BindView(R.id.edit_new_zvanje_we)
    EditText addZvanjeWe;

    @BindView(R.id.edit_new_zvanje_they)
    EditText addZvanjeThey;

    Player we;
    Player they;

    Score ourScore;
    Score thereScore;

    Score_Calculations score_calculations;

    FragmentManager mFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_score, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFragmentManager = getFragmentManager();
        we = (Player) getActivity().getIntent().getExtras().getParcelable("we");
        they = (Player) getActivity().getIntent().getExtras().getParcelable("they");

        ourScore = we.getScore();
        thereScore = they.getScore();

        score_calculations = Score_Calculations.getInstance(ourScore,thereScore);

    }
    @OnFocusChange(R.id.edit_new_zvanje_we)
    public void onZvanjeWeChange(){
        if(addZvanjeWe.getText().toString().equals("")){
            addZvanjeWe.setText("0");
        }else {
            ourScore.setZvanje(Integer.parseInt(addZvanjeWe.getText().toString()));
            addZvanjeThey.setText("0");
        }

    }

    @OnFocusChange(R.id.edit_new_zvanje_they)
    public void onZvanjeTheyChange(){
        if(addZvanjeThey.getText().toString().equals("")){
            addZvanjeThey.setText("0");
        }else {
            thereScore.setZvanje(Integer.parseInt(addZvanjeThey.getText().toString()));
            addZvanjeWe.setText("0");
        }

    }

    @OnTextChanged(R.id.edit_new_score_we)
    public void onScoreChange(){

        int maxScore = score_calculations.maxScore();
        if(addScoreWe.getText().toString().equals("")){
            addScoreThey.setText(String.valueOf(maxScore));
        }else {
            addScoreThey.setText(String.valueOf(maxScore-Integer.parseInt(addScoreWe.getText().toString())));
        }

    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm(){
        ourScore.addScore(Integer.parseInt(addScoreWe.getText().toString()));
        thereScore.addScore(Integer.parseInt(addScoreThey.getText().toString()));
        getActivity().getFragmentManager().popBackStack();
        refresh();
    }

    @OnClick(R.id.btn_reject)
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

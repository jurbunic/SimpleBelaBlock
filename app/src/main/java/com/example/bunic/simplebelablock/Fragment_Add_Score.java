package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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
    @BindView(R.id.check_bela_we)
    CheckBox belaWe;
    @BindView(R.id.check_bela_they)
    CheckBox belaThey;
    @BindView(R.id.radio_call_we)
    RadioButton callWe;
    @BindView(R.id.radio_call_they)
    RadioButton callThey;

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
        ourScore.setZvanje(0);
        thereScore.setZvanje(0);

        score_calculations = Score_Calculations.getInstance(we,they);

        callWe.setChecked(true);
    }

    @OnFocusChange(R.id.edit_new_zvanje_we)
    public void onZvanjeWeChange(){
        if(addZvanjeWe.getText().toString().equals("")){
            addZvanjeWe.setText("0");
        }else {
            addZvanjeThey.setText("0");
        }
        ourScore.setZvanje(Integer.parseInt(addZvanjeWe.getText().toString()));
        thereScore.setZvanje(0);
        //maxScore = score_calculations.getMaxScore();

    }

    @OnFocusChange(R.id.edit_new_zvanje_they)
    public void onZvanjeTheyChange(){
        if(addZvanjeThey.getText().toString().equals("")){
            addZvanjeThey.setText("0");
        }else {
            addZvanjeWe.setText("0");
        }
        thereScore.setZvanje(Integer.parseInt(addZvanjeThey.getText().toString()));
        ourScore.setZvanje(0);
        //maxScore = score_calculations.getMaxScore();
    }

    @OnTextChanged(R.id.edit_new_score_we)
    public void onScoreChange(){
        if(addScoreWe.getText().toString().equals("")){
            addScoreThey.setText(String.valueOf(162));
        }else {
            addScoreThey.setText(String.valueOf(162-Integer.parseInt(addScoreWe.getText().toString())));
        }

    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm(){
        if(callWe.isChecked()){
            we.setTurn(1);
            they.setTurn(0);
        }else {
            we.setTurn(0);
            they.setTurn(1);
        }
        if (belaWe.isChecked()){
            ourScore.setZvanje(Integer.parseInt(addZvanjeWe.getText().toString())+20);
        }else if (belaThey.isChecked()){
            thereScore.setZvanje(Integer.parseInt(addZvanjeWe.getText().toString())+20);
        }
        ourScore.addScore(Integer.parseInt(addScoreWe.getText().toString()),ourScore.getZvanje());
        thereScore.addScore(Integer.parseInt(addScoreThey.getText().toString()),thereScore.getZvanje());
        score_calculations.roundResult();
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

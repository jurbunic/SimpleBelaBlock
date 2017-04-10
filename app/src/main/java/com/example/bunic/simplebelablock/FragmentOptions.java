package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 07.04.17..
 */

public class FragmentOptions extends Fragment {

    @BindView(R.id.edit_options_player1name)
    EditText player1Name;
    @BindView(R.id.edit_options_player2name)
    EditText player2Name;
    @BindView(R.id.edit_options_player3name)
    EditText player3Name;

    @BindView(R.id.edit_options_team1name)
    EditText team1Name;
    @BindView(R.id.edit_options_team2name)
    EditText team2Name;

    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        ButterKnife.bind(this,view);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Options");
        player1Name.setText(preferences.getString("PLAYER1NAME","player1"));
        player2Name.setText(preferences.getString("PLAYER2NAME","player2"));
        player3Name.setText(preferences.getString("PLAYER3NAME","player3"));

        team1Name.setText(preferences.getString("TEAM1NAME","Team1"));
        team2Name.setText(preferences.getString("TEAM2NAME", "Team2"));
    }

    @OnClick(R.id.fab_confirm)
    public void onConfirmClick(){
        preferences.edit().putString("PLAYER1NAME",player1Name.getText().toString()).apply();
        preferences.edit().putString("PLAYER2NAME",player2Name.getText().toString()).apply();
        preferences.edit().putString("PLAYER3NAME",player3Name.getText().toString()).apply();
        preferences.edit().putString("TEAM1NAME",team1Name.getText().toString()).apply();
        preferences.edit().putString("TEAM2NAME",team2Name.getText().toString()).apply();
        getFragmentManager().popBackStack();
    }
    @OnClick(R.id.fab_reject)
    public void onRejectClick(){
        getFragmentManager().popBackStack();
    }

}

package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 3/29/17.
 */

public class FragmentScoreBoardThree extends Fragment {

    @BindView(R.id.fragment_add_score)
    LinearLayout newScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard_three, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick(R.id.fab_new_score)
    public void onFabClick(){
        Toast.makeText(getActivity().getApplicationContext(),"Deala",Toast.LENGTH_SHORT).show();
        newScore.setVisibility(View.VISIBLE);
    }
}

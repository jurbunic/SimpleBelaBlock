package com.example.bunic.simplebelablock;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bunic on 3/29/17.
 */

public class FragmentAddScoreThree extends Fragment {
    FragmentManager mFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_score_three, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFragmentManager = getFragmentManager();
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
}

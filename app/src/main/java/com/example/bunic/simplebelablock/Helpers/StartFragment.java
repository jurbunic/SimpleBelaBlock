package com.example.bunic.simplebelablock.Helpers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.bunic.simplebelablock.R;

/**
 * Created by Jurica Bunić on 7.2.2017..
 */

public class StartFragment {
    public static void StartNewFragment(Fragment fragment, Activity mActivity, String tag){
        if(mActivity.getFragmentManager().getBackStackEntryCount()>1){
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction()
                    .replace(R.id.new_score_fragment_container, fragment)
                    .addToBackStack("2")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }else {
            FragmentTransaction fm = mActivity.getFragmentManager().beginTransaction();
            fm.replace(R.id.new_score_fragment_container, fragment);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack(tag);
            fm.commit();
        }

    }

    public static void StartNewFragment(Fragment fragment, Activity mActivity){

        FragmentManager fragmentManager = mActivity.getFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("2")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}

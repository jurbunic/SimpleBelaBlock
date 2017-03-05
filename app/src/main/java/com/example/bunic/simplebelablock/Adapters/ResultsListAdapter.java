package com.example.bunic.simplebelablock.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bunic.simplebelablock.R;
import com.example.bunic.simplebelablock.Score;

import java.util.List;

/**
 * Created by Jurica Bunić on 12.2.2017..
 */

public class ResultsListAdapter extends  RecyclerView.Adapter<ResultsListAdapter.ScoreViewHolder>{

    Context context;
    List<Integer> scoresWe;
    List<Integer> scoresThey;

    public static class ScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView mScoreWe;
        public TextView mScoreThey;

        public ScoreViewHolder(View v){
            super(v);
            mScoreWe = (TextView) v.findViewById(R.id.txt_row_we);
            mScoreThey = (TextView) v.findViewById(R.id.txt_row_they);

        }
    }

    public ResultsListAdapter(Context context,Score we, Score they){
        this.context = context;
        this.scoresWe = we.getPreviousScores();
        this.scoresThey = they.getPreviousScores();
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_results, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.mScoreWe.setText(String.valueOf(scoresWe.get(position)));
        holder.mScoreThey.setText(String.valueOf(scoresThey.get(position)));

    }

    @Override
    public int getItemCount() {
        return scoresWe.size();
    }
}

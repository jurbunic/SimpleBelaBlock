package com.example.bunic.simplebelablock.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bunic.simplebelablock.R;
import com.example.bunic.simplebelablock.scoreboard.ThreePlayersScoreboard;

/**
 * Created by bunic on 3/30/17.
 */

public class ThreePlayersScoreList extends RecyclerView.Adapter<ThreePlayersScoreList.RowResults>{
    Context context;
    ThreePlayersScoreboard board;
    String[] individualScores = new String[3];

    public static class RowResults extends RecyclerView.ViewHolder {
        public TextView mScorePlayer1;
        public TextView mScorePlayer2;
        public TextView mScorePlayer3;

        public RowResults(View v){
            super(v);
            mScorePlayer1 = (TextView) v.findViewById(R.id.txt_row_player1);
            mScorePlayer2 = (TextView) v.findViewById(R.id.txt_row_player2);
            mScorePlayer3 = (TextView) v.findViewById(R.id.txt_row_player3);
        }

    }

    public ThreePlayersScoreList(Context context, ThreePlayersScoreboard board){
        this.context = context;
        this.board = board;
    }
    @Override
    public RowResults onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_results_three, parent, false);
        return new RowResults(itemView);
    }

    @Override
    public void onBindViewHolder(RowResults holder, int position) {
        String row = board.getRow(position);
        individualScores = row.split("\\$");
        holder.mScorePlayer1.setText(individualScores[0]);
        holder.mScorePlayer2.setText(individualScores[1]);
        holder.mScorePlayer3.setText(individualScores[2]);
    }

    @Override
    public int getItemCount() {
        return board.listSize();
    }

}

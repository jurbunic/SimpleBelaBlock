package com.example.bunic.simplebelablock.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bunic.simplebelablock.R;
import com.example.bunic.simplebelablock.scoreboard.FourPlayersScoreboard;

/**
 * Created by bunic on 05.04.17..
 */

public class FourPlayersScoreList extends RecyclerView.Adapter<FourPlayersScoreList.RowResults> {
    Context context;
    FourPlayersScoreboard board;
    String[] individualScores = new String[3];

    public static class RowResults extends RecyclerView.ViewHolder {
        public TextView mScorePlayer1;
        public TextView mScorePlayer2;

        public RowResults(View v){
            super(v);
            mScorePlayer1 = (TextView) v.findViewById(R.id.txt_row_player1);
            mScorePlayer2 = (TextView) v.findViewById(R.id.txt_row_player2);
        }

    }

    public FourPlayersScoreList(Context context, FourPlayersScoreboard board){
        this.context = context;
        this.board = board;
    }
    @Override
    public RowResults onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_results_four, parent, false);
        return new RowResults(itemView);
    }

    @Override
    public void onBindViewHolder(RowResults holder, int position) {
        String row = board.getRow(position);
        individualScores = row.split("\\$");
        holder.mScorePlayer1.setText(individualScores[0]);
        holder.mScorePlayer2.setText(individualScores[1]);
    }

    @Override
    public int getItemCount() {
        return board.listSize();
    }
}

package com.example.bunic.simplebelablock.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bunic.simplebelablock.R;
import com.example.bunic.simplebelablock.scoreboard.Row;
import com.example.bunic.simplebelablock.scoreboard.ScoreboardFour;


/**
 * Created by bunic on 05.04.17..
 */

public class FourPlayersScoreList extends RecyclerView.Adapter<FourPlayersScoreList.RowResults> {
    Context context;
    ScoreboardFour board;
    View itemView;
    private IDataChanged dataChangedListener;

    public static class RowResults extends RecyclerView.ViewHolder {
        public TextView mScorePlayer1;
        public TextView mScorePlayer2;
        public LinearLayout row;

        public RowResults(View v){
            super(v);
            mScorePlayer1 = (TextView) v.findViewById(R.id.txt_row_player1);
            mScorePlayer2 = (TextView) v.findViewById(R.id.txt_row_player2);
            row = (LinearLayout) v.findViewById(R.id.result_list_content_four);
        }
    }

    public FourPlayersScoreList(Context context, ScoreboardFour board, IDataChanged dataChangedListener){
        this.context = context;
        this.board = board;
        this.dataChangedListener = dataChangedListener;
    }
    @Override
    public RowResults onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_results_four, parent, false);
        return new RowResults(itemView);
    }

    @Override
    public void onBindViewHolder(final RowResults holder, int position) {
        Row row = board.getRow(position);
        holder.mScorePlayer1.setText(String.valueOf(row.getRowScores()[0]));
        holder.mScorePlayer2.setText(String.valueOf(row.getRowScores()[1]));

        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                alertDialog.setTitle("Do you wish to remove this item?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Deleting in list items
                        deleteItem(holder.getAdapterPosition());
                        dataChangedListener.dataChanged();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return board.getScoreListSize();
    }

    public void deleteItem(int position){
        board.deleteRow(position);
        notifyItemRemoved(position);
    }
}

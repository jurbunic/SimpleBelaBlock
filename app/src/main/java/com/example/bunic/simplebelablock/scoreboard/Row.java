package com.example.bunic.simplebelablock.scoreboard;

/**
 * Created by jurbunic on 12.07.17..
 */

public class Row {
    private int maxRowScore;
    private Integer[] rowScores;

    public Row(Integer[] rowScores) {
        this.rowScores = rowScores;
    }

    public Row() {
    }

    public Integer[] getRowScores() {
        return rowScores;
    }

    public void setRowScores(Integer[] rowScores) {
        this.rowScores = rowScores;
    }

    public void calculateRow(int player){
        for(int i=0;i<rowScores.length;i++){
            maxRowScore += rowScores[i];
        }
        if(rowScores.length==2){
            if(rowScores[player] <= maxRowScore/2){
                rowScores[player] = 0;
                rowScores[(player+1)%2] = maxRowScore;
            }
        }
        else if(rowScores.length==3){
            if(rowScores[player] <= maxRowScore/2){
                rowScores[player] = 0;
            }
        }
    }

}

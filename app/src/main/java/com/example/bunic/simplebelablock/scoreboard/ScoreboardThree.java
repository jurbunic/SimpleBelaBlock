package com.example.bunic.simplebelablock.scoreboard;

import com.example.bunic.simplebelablock.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurbunic on 12.07.17..
 */

public class ScoreboardThree extends Scoreboard {
    private static ScoreboardThree instance;
    public static final int MAX_SCORE = 700;
    private List<Player> players;
    private int[] totalScore = new int[3];


    public static ScoreboardThree getInstance(List<Player> players){
        if(instance == null){
            instance = new ScoreboardThree(players);
        }
        return instance;
    }

    public static ScoreboardThree getInstance(){
        return instance;
    }

    private ScoreboardThree(List<Player> players) {
        this.players = players;
        for(int i=0;i<totalScore.length;i++){
            totalScore[i] = 0;
        }
    }

    @Override
    public void addRow(Row row) {
        super.addRow(row);
        for(int i=0;i<totalScore.length;i++){
            totalScore[i] += row.getRowScores()[i];
        }
    }

    @Override
    public void deleteRow(int index) {
        Row row = getRow(index);
        for(int i=0;i<totalScore.length;i++){
            totalScore[i] -= row.getRowScores()[i];
        }
        super.deleteRow(index);
    }
    
    @Override
    public int[] getTotalScores() {
        return totalScore;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

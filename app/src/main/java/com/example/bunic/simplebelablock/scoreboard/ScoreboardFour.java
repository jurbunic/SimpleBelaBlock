package com.example.bunic.simplebelablock.scoreboard;

import com.example.bunic.simplebelablock.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurbunic on 12.07.17..
 */

public class ScoreboardFour extends Scoreboard {
    private static ScoreboardFour instance;
    public static final int MAX_SCORE = 1000;
    private List<Player> players;
    private int[] totalScore = new int[2];

    public ScoreboardFour(ArrayList<Player> players) {
        this.players = players;
        for(int i=0;i<totalScore.length;i++){
            totalScore[i] = 0;
        }

    }

    public static ScoreboardFour getInstance(ArrayList<Player> players){
        if(instance == null){
            instance = new ScoreboardFour(players);
        }
        return instance;
    }

    public static ScoreboardFour getInstance(){
        return instance;
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

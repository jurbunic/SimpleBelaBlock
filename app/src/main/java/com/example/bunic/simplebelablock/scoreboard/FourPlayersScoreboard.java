package com.example.bunic.simplebelablock.scoreboard;

import com.example.bunic.simplebelablock.Player;

import java.util.List;

/**
 * Created by bunic on 05.04.17..
 */

public class FourPlayersScoreboard extends Scoreboard {

    static FourPlayersScoreboard INSTANCE;
    Integer[] totalScores = new Integer[]{0, 0};

    private FourPlayersScoreboard(List<Player> players){
        INSTANCE = this;
        initializePlayers(players);
    }

    public static FourPlayersScoreboard getInstance(List<Player> players){
        if(INSTANCE == null){
            INSTANCE = new FourPlayersScoreboard(players);
        }
        return INSTANCE;
    }

    public static FourPlayersScoreboard getInstance(){
        return INSTANCE;
    }

    @Override
    protected void initializePlayers(List<Player> players) {
        super.initializePlayers(players);
    }

    @Override
    protected void addToScoreList() {
        super.addToScoreList();
    }

    public void newRow(Integer[] score, Integer[] calls, Integer playerOnTurn){
        String row = ScoreCalculator.calcuateRow(score,calls,totalScores,playerOnTurn);

        super.row = row;
        addToScoreList();
    }

    public Integer getTotalScore(Integer player){
        return totalScores[player];
    }
}

package com.example.bunic.simplebelablock.scoreboard;

import com.example.bunic.simplebelablock.Player;

import java.util.List;

/**
 * Created by bunic on 3/30/17.
 */

public class ThreePlayersScoreboard extends Scoreboard {

    static ThreePlayersScoreboard INSTANCE;

    private ThreePlayersScoreboard(List<Player> players) {
        INSTANCE = this;
        initializePlayers(players);
    }

    public static ThreePlayersScoreboard getInstance(List<Player> players){
        if(INSTANCE == null){
            INSTANCE = new ThreePlayersScoreboard(players);
        }
        return INSTANCE;
    }

    public static ThreePlayersScoreboard getInstance(){
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
        Integer possiblePoints = MAX_SCORE;
        for(int i=0; i<calls.length;i++){
            possiblePoints = possiblePoints + calls[i];
        }

        if(score[playerOnTurn] < possiblePoints / 2){
            score[playerOnTurn] = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score[0]).append("$").append(score[1]).append("$").append(score[2]);
        super.row = sb.toString();
        addToScoreList();
    }





}

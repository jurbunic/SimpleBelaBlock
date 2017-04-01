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
        String row = ScoreCalculator.calcuateRow(score,calls,playerOnTurn);
        super.row = row;
        addToScoreList();
    }





}

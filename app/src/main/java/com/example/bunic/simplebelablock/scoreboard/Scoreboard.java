package com.example.bunic.simplebelablock.scoreboard;


import com.example.bunic.simplebelablock.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bunic on 3/30/17.
 */

public abstract class Scoreboard {
    final Integer MAX_SCORE = 162;
    String row;
    List<String> allRows;
    List<Player> players;

    protected void initializePlayers(List<Player> players){
        this.players = players;
        this.allRows = new ArrayList<>();
    }

    protected void addToScoreList(){
        allRows.add(row);
    }

    public String getRow(int index){
        return allRows.get(index);
    }

    public Integer listSize(){
        return allRows.size();
    }


}

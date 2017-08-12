package com.example.bunic.simplebelablock.scoreboard;


import com.example.bunic.simplebelablock.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bunic on 3/30/17.
 */

public abstract class Scoreboard {

    protected List<Row> scoreList = new ArrayList<>();

    public Row getRow(int index){
        return scoreList.get(index);
    }

    public void addRow(Row row){
        scoreList.add(row);
    }

    public void deleteRow(int index){
        scoreList.remove(index);
    }

    public int getScoreListSize(){
        return scoreList.size();
    }

    public void clearList(){
        scoreList.clear();
    }

    public abstract int[] getTotalScores();
}

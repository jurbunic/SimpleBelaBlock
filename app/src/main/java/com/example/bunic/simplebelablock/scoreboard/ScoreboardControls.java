package com.example.bunic.simplebelablock.scoreboard;

import com.example.bunic.simplebelablock.Player;

/**
 * Created by Jurica Bunić on 10.8.2017..
 */

public class ScoreboardControls {
    public static int checkVictoryCondition(int maxTotalScore, int[] totalScores){
        for(int i=0;i<totalScores.length;i++){
            if(maxTotalScore <= totalScores[i]){
                return i;
            }
        }
        return -1;
    }

    public static void addPointToPlayer(Player player, Scoreboard board){
        player.setPoints(player.getPoints()+1);
        clearScoreList(board);
    }

    public static void clearScoreList(Scoreboard board){
        int[] totalScores = board.getTotalScores();
        for(int i=0;i<totalScores.length;i++){
            totalScores[i] = 0;
        }
        board.clearList();
    }
}

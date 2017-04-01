package com.example.bunic.simplebelablock.scoreboard;

/**
 * Created by bunic on 4/1/17.
 */

public class ScoreCalculator {
    private static final Integer MAX_SCORE = 162;

    public static String calcuateRow(Integer[] points, Integer[] calls, Integer playerOnTurn){
        Integer inGamePoints = MAX_SCORE;
        for(int i=0;i<calls.length;i++){
            inGamePoints +=calls[i];
        }
        if((points[playerOnTurn]+calls[playerOnTurn])<=inGamePoints/2){
            points[playerOnTurn]=0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(points[0].toString())
                .append("$")
                .append(points[1].toString())
                .append("$")
                .append(points[2].toString());
        return sb.toString();
    }
}

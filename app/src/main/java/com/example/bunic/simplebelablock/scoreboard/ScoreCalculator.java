package com.example.bunic.simplebelablock.scoreboard;

/**
 * Created by bunic on 4/1/17.
 */

public class ScoreCalculator {
    private static final Integer MAX_SCORE = 162;

    public static String calcuateRow(Integer[] points, Integer[] calls, Integer[] totalScores, Integer playerOnTurn){
        Integer inGamePoints = MAX_SCORE;
        for(int i=0;i<calls.length;i++){
            inGamePoints +=calls[i];
            points[i] += calls[i];
        }
        if(points[playerOnTurn]<=inGamePoints/2){
            points[playerOnTurn]=0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(points[0].toString())
                .append("$")
                .append(points[1].toString())
                .append("$")
                .append(points[2].toString());
        calculateTotalScore(totalScores,points);
        return sb.toString();
    }

    private static Integer[] calculateTotalScore(Integer[] totalScore, Integer[] points){
        for(int i=0; i<totalScore.length;i++){
            totalScore[i] += points[i];
        }
        return totalScore;
    }
}

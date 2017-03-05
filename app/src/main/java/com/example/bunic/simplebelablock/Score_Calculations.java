package com.example.bunic.simplebelablock;

/**
 * Created by Jurica Bunić on 18.2.2017..
 */

public class Score_Calculations {

    private static Score_Calculations insance;

    private Score score1;
    private Score score2;
    int maxScore = 162;

    private Score_Calculations(Score score1, Score score2){
        this.score1 = score1;
        this.score2 = score2;
    }

    public static Score_Calculations getInstance(Score score1, Score score2){
        if(insance == null) {
            insance = new Score_Calculations(score1, score2);
        }
        return insance;
    }

    public int maxScore(){
        maxScore = 162;
        if((score1.getZvanje()>0) || (score2.getZvanje()>0)){
            maxScore = 162 + score1.getZvanje()+score2.getZvanje();
        }
        return maxScore;
    }

    private void roundResult(){
        int scoreWe;
        int scoreThey;
    }
}

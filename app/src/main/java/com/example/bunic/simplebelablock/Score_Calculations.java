package com.example.bunic.simplebelablock;

/**
 * Created by Jurica Bunić on 18.2.2017..
 */

public class Score_Calculations {

    private static Score_Calculations insance;


    private Player we;
    private Player they;
    private Score scoreWe;
    private Score scoreThey;

    private static final int BASE_SCORE=162;
    private int maxScore;

    private Score_Calculations(Player we, Player they){
        this.we = we;
        this.we = we;

        this.scoreWe = we.getScore();
        this.scoreThey = they.getScore();
    }

    public static Score_Calculations getInstance(Player we, Player they){
        if(insance == null) {
            insance = new Score_Calculations(we, they);
        }
        return insance;
    }

    public int getMaxScore(){
        maxScore = BASE_SCORE + scoreWe.getZvanje() + scoreThey.getZvanje();
        return maxScore;
    }

    private int whoesTurn(){
        if(we.getTurn()==1){
            return 1;
        }else {
            return 0;
        }
    }

    public void roundResult(){
        // if 1 then it is our turn, else it's their turn
        maxScore = getMaxScore();
        int turn = whoesTurn();
        switch (turn){
            case 1:
                if(scoreWe.getCurrentScore()+scoreWe.getZvanje()<=(maxScore/2)){
                    scoreWe.addToList(0);
                    scoreThey.addToList(maxScore);
                }else if(scoreWe.getCurrentScore()+scoreWe.getZvanje()>(maxScore/2)){
                    scoreWe.addToList(scoreWe.getCurrentScore()+scoreWe.getZvanje());
                    scoreThey.addToList(scoreThey.getCurrentScore()+scoreThey.getZvanje());
                }
                break;
            default:
                if(scoreThey.getCurrentScore()+scoreThey.getZvanje()<=(maxScore/2)){
                    scoreThey.addToList(0);
                    scoreWe.addToList(maxScore);
                }else if(scoreThey.getCurrentScore()+scoreThey.getZvanje()>(maxScore/2)){
                    scoreThey.addToList(scoreThey.getCurrentScore()+scoreThey.getZvanje());
                    scoreWe.addToList(scoreWe.getCurrentScore()+scoreWe.getZvanje());
                }
        }
        endTurn();
    }

    private void endTurn(){
        scoreWe.setZvanje(0);
        scoreWe.setCurrentScore(0);
        scoreThey.setZvanje(0);
        scoreThey.setCurrentScore(0);
    }
}

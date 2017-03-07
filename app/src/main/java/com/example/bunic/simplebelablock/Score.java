package com.example.bunic.simplebelablock;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jurica Bunić on 9.2.2017..
 */

public class Score implements Parcelable{

    private List<Integer> previousScores;
    private int currentScore;
    private int zvanje;

    private int totalScore;

    public Score() {
        previousScores = new ArrayList<>();
    }

    public void addScore(int score, int zvanje){
        setZvanje(zvanje);
        setCurrentScore(score);
        //previousScores.add(getCurrentScore());
        //setTotalScore(this.totalScore+getCurrentScore());
    }

    public void addToList(int score){
        previousScores.add(score);
        setTotalScore(totalScore+score);
    }

    public List<Integer> getPreviousScores() {
        return previousScores;
    }
    public int getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    public int getZvanje() {
        return zvanje;
    }
    public void setZvanje(int zvanje) {
        this.zvanje = zvanje;
    }

    //Parcel--------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentScore);
        dest.writeInt(zvanje);
        dest.writeInt(totalScore);
        dest.writeList(previousScores);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Score createFromParcel(Parcel in){
            return new Score(in);
        }
        public Score[] newArray(int size){
            return new Score[size];
        }
    };

    public Score(Parcel in){
        currentScore = in.readInt();
        zvanje = in.readInt();
        totalScore = in.readInt();

        previousScores = in.readArrayList(Integer.class.getClassLoader());
    }
}

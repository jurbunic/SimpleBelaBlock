package com.example.bunic.simplebelablock;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Jurica Bunić on 9.2.2017..
 */

public class Player implements Parcelable{
    private String name;
    private Score score;

    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    //Parcel------------------------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(score, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(Parcel in){
        name = in.readString();
        score = (Score) in.readParcelable(Score.class.getClassLoader());
    }
}

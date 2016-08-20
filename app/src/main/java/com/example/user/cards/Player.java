package com.example.user.cards;

import android.support.v7.app.AppCompatActivity;

import java.lang.Integer;import java.lang.String;import java.util.*;import java.util.ArrayList;

public class Player extends AppCompatActivity {

    private String mName;
    private ArrayList<String> mHand;
    private int mChips;
    private int mBet;
    private int mSmallBlindValue;
    private int mBigBlindValue;
    private int mPlayerNumber;
    private int mLastBet;
    private Integer mScore;
    private Integer mKicker;
    private boolean mFolded;
    private boolean mFirstToBet;
    private boolean mBigBlind;

    public Player(String name, int playerNumber) {
        mName = name;
        mHand = new ArrayList<String>();
        mChips = 500;
        mBet = 0;
        mPlayerNumber = playerNumber;
        mScore = 0;
        mSmallBlindValue = 10;
        mBigBlindValue = 20;
        mFolded = true;
        mLastBet = 0;
        mBigBlind = false;
    }

    public void turnOnBigBlind() {
        mBigBlind = true;
    }

    public void turnOffBigBlind() {
        mBigBlind = false;
    }

    public boolean seeBlind() {
        return mBigBlind;
    }

    public String name() {
        return mName;
    }

    public Integer seeScore() {
        return mScore;
    }

    public void fold() {
        mFolded = true;
    }

    public void in() {
        mFolded = false;
    }

    public boolean status() {
        return mFolded;
    }

    public void awardScore(Integer handScore) {
        mScore = handScore;
    }

    public void takeCard(String card) {
        mHand.add(card);
    }

    public ArrayList seeHand() {
        return mHand;
    }

    public void awardKicker(Integer kickerScore) {
        mKicker = kickerScore;
    }

    public Integer seeKicker() {
        return mKicker;
    }

    public int countChips() {
        return mChips;
    }

    public void placeBet(int chips) {
        if (mChips >= chips) {
            mChips -= chips;
            mBet = chips;
            mLastBet = chips;
        } else {
            mBet = 0;
            mLastBet = 0;
        }
    }

    public void smallBlind() {
        mChips -= mSmallBlindValue;
        mBet += mSmallBlindValue;
        mLastBet = mSmallBlindValue;
    }

    public void bigBlind() {
        mChips -= mBigBlindValue;
        mBet += mBigBlindValue;
        mLastBet = mBigBlindValue;
    }

    public void call( Game game ) {

        if( mBigBlind == true ) {
            game.resetBets();
            resetLastBet();
            resetBet();
        } else {
            int chips = game.seeLastBet() - mLastBet;
            mChips -= chips;
            mBet = chips;
            mLastBet = chips;
            resetLastBet();
        }
//        int chips = game.seeLastBet() - mLastBet;
//        mChips -= chips;
//        mBet = chips;
//        mLastBet = chips;
    }

    public int check() {
        return mBet;
    }

    public int giveBet() {
        return mBet;
    }

    public int giveLastBet() {
        return mLastBet;
    }

    public int number() {
        return mPlayerNumber;
    }

    public int seeLastBet() {
        return mLastBet;
    }

    public void setLastBet() {
        mLastBet = 0;
    }

    public void setFirstBet() {
        mFirstToBet = true;
    }

    public boolean seeFirstBet() {
        return mFirstToBet;
    }

    public void winChips(int chips) {
        mChips += chips;
    }

    public void resetLastBet() {
        mLastBet = 0;
    }

    public void resetBet() {
        mBet = 0;
    }
}

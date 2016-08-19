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
    private Integer mScore;
    private Integer mKicker;
    private boolean mFolded;

    public Player(String name, int playerNumber) {
        mName = name;
        mHand = new ArrayList<String>();
        mChips = 500;
        mBet = 0;
        mPlayerNumber = playerNumber;
        mScore = 0;
        mSmallBlindValue = 5;
        mBigBlindValue = 10;
        mFolded = false;
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
        } else {
            mBet = 0;
        }
    }

    public int smallBlind() {
        mChips -= mSmallBlindValue;
        mBet = mSmallBlindValue;
        return mSmallBlindValue;
    }

    public int bigBlind() {
        mChips -= mBigBlindValue;
        mBet = mBigBlindValue;
        return mBigBlindValue;
    }

    public int check() {
        return mBet;
    }

    public int giveBet() {
        return mBet;
    }

    public int number() {
        return mPlayerNumber;
    }

    public void winChips(int chips) {
        mChips += chips;
    }
}

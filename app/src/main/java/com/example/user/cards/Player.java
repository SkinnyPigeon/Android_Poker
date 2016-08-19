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
}

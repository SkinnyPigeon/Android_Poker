package com.example.user.cards;


import java.util.ArrayList;
import java.util.Collections;

public class Game  {

    private int mPot;
    private int mlastBet;
    private int mPlayerTurn;
    private int mPlayerStart;
    private int mNoOfPlayers;
    private int mNoOfFoldedPlayers;
    private ArrayList< String > mSharedCards;
    private ArrayList< Player > mWinnerArray;
    private PlayerComparator mPlayerComparator;
    private KickerComparator mkickerComparator;
    private Player mHandWinner;

    public Game( int noOfPlayers ){
        mPot = 0;
        mPlayerTurn = 1;
        mPlayerStart = 1;
        mNoOfPlayers = noOfPlayers;
        mNoOfFoldedPlayers = 0;
//        mlastBet = mLastBet;
        mSharedCards = new ArrayList< String >();
        mWinnerArray = new ArrayList< Player >();
        mHandWinner = mHandWinner;
        mPlayerComparator = new PlayerComparator();
        mkickerComparator = new KickerComparator();
    }

    public int showPot() {
        return mPot;
    }

    public boolean playerCheck( Player player ) {
        if( player.number() == mPlayerTurn ) {
            return true;
        } else {
            return false;
        }
    }

    public void pickKicker() {
        Collections.sort(mWinnerArray, mkickerComparator);
        mHandWinner = mWinnerArray.get( mWinnerArray.size() - 1 );
    }

    public int starter() {
        return mPlayerStart;
    }

    public void pickWinner() {

        Collections.sort(mWinnerArray, mPlayerComparator);

        for( int i = 0; i < mWinnerArray.size() - 1; i++ ) {
            Player firstPlayer = mWinnerArray.get(i);
            Player secondPlayer = mWinnerArray.get( i + 1 );
            Integer firstPlayerScore = firstPlayer.seeScore();
            Integer secondPlayerScore = secondPlayer.seeScore();
            if( (int) firstPlayerScore  ==  (int) secondPlayerScore ) {
                pickKicker();
            } else {
                mHandWinner = mWinnerArray.get( mWinnerArray.size() - 1 );
            }
        }
    }

    public ArrayList seePlayerArray() {
        return mWinnerArray;
    }



    public Player seeWinner() {
        return mHandWinner;
    }

    public void takeCard( String card ) {
        mSharedCards.add( card );
    }

    public void addPlayer( Player player ) {
        mWinnerArray.add( player );
    }

    public void foldCheck( Player player ) {
        if( player.status() == true ) {
            setFoldedPlayerCount();
            nextTurn();
        }
    }

    public void foldMaster( Player player ) {
        foldCheck( player );
        foldWin( player );
    }

    public void setFoldedPlayerCount() {
        mNoOfFoldedPlayers += 1;
    }

    public void foldWin( Player player ) {
        if( mNoOfFoldedPlayers == ( mNoOfPlayers - 1 )) {
            if( player.status() == false ) {
                player.winChips( mPot );
            }
        }
    }

    public ArrayList showPlayers() {
        return mWinnerArray;
    }

    public ArrayList seeHand() {
        return mSharedCards;
    }

    public void addBet( Player player ) {
        if( ( playerCheck( player ) == true ) && ( mlastBet <= player.giveBet() )) {
            mPot += player.giveBet();
            mlastBet = player.giveBet();
        }
    }

    public int turn() {
        return mPlayerTurn;
    }

    public int numberOfPlayers() {
        return mNoOfPlayers;
    }

    public void nextTurn() {
        if( mPlayerTurn == mNoOfPlayers ) {
            mPlayerTurn = 1;
        } else {
            mPlayerTurn += 1;
        }
    }

    public void startHand() {
        mPlayerTurn = mPlayerStart;
    }

    public void handWon( Player player ) {
        player.winChips( mPot );
        if( mPlayerStart == mNoOfPlayers ) {
            mPlayerStart = 1;
        } else {
            mPlayerStart += 1;
        }
    }

    public int seeLastBet() {
        return mlastBet;
    }

}














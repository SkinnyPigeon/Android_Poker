package com.example.user.cards;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class Game  {

    private int mPot;
    private int mlastBet;
    private int mPlayerTurn;
    private int mCurrentPlayer;
    private int mPlayerStart;
    private int mFirstBet;
    private int mBetPlayer;
    private int mNoOfPlayers;
    private int mNoOfFoldedPlayers;
    private ArrayList< String > mSharedCards;
    private ArrayList< Player > mPlayers;
    private ArrayList< Player > mPlayerCell;
    private PlayerComparator mPlayerComparator;
    private KickerComparator mkickerComparator;
    private PlayerSorter mPlayerSorter;
    private Player mHandWinner;
    private Player mKickerWinner;

    public Game( int noOfPlayers ){
        mPot = 0;
        mPlayerTurn = 1;
        mPlayerStart = 1;
        mCurrentPlayer = 1;
        mFirstBet = 1;
        mNoOfPlayers = noOfPlayers;
        mNoOfFoldedPlayers = 0;
        mSharedCards = new ArrayList< String >();
        mPlayerComparator = new PlayerComparator();
        mkickerComparator = new KickerComparator();
        mPlayerSorter = new PlayerSorter();
        mPlayerCell = new ArrayList< Player >();

        mPlayers = new ArrayList< Player >();
        mlastBet = 0;
    }

    public Player getCurrentPlayer() {
        return mPlayers.get(0);
    }

    public int getArraySize() {
        return mPlayers.size();
    }

    public void addPlayerToGame( Player player ) {
        mPlayers.add(player);
    }

    public void turnEnd() {

        Integer number = mPlayers.size() - 1;
        Collections.rotate(mPlayers, number);
    }

    public void fold() {
        mPlayerCell.add(mPlayers.remove(0));
        turnEnd();
    }

    public void refillPlayerArray() {
        Integer number = mPlayerCell.size();
        if( mPlayerCell.size() > 0 ) {
            for( int i = 0; i < number; i ++ ) {
                mPlayers.add( mPlayerCell.remove( 0 ));
            }
        }
    }

    public String seePlayersArray() {
        Integer size = mPlayers.size();
        return size.toString();
    }

    public Player accessPlayer( int arrayIndex ) {
        return mPlayers.get(arrayIndex);
    }

    public int showPot() {
        return mPot;
    }

    public void resetPlayers() {
        Integer number = mPlayers.size();
        for( int i = 0; i < number; i ++ ) {
            mPlayers.remove(0);
        }
    }

    public void pickWinner() {

        ArrayList<Player> mWinnerArray = new ArrayList< Player >(mPlayers);

        Collections.sort(mWinnerArray, mPlayerComparator);

        Player firstPlayer = mWinnerArray.get( mWinnerArray.size() - 1 );
        Player secondPlayer = mWinnerArray.get( mWinnerArray.size() - 2 );

        Integer firstPlayerScore = firstPlayer.seeScore();
        Integer secondPlayerScore = secondPlayer.seeScore();

        if( firstPlayer.seeScore() == secondPlayer.seeScore() ) {
            for (int i = 0; i < mWinnerArray.size() - 1; i++) {

                firstPlayer = mWinnerArray.get(i);
                secondPlayer = mWinnerArray.get(i + 1);
                firstPlayerScore = firstPlayer.seeScore();
                secondPlayerScore = secondPlayer.seeScore();

                if ((int) firstPlayerScore == (int) secondPlayerScore) {
                    pickKicker();
                }

            }
        } else {
            mHandWinner = mWinnerArray.get( mWinnerArray.size() - 1 );
        }

    }

    public void pickKicker() {
        ArrayList<Player> mKickerArray  = new ArrayList<Player>(mPlayers);
        Collections.sort(mKickerArray, mkickerComparator);
        mKickerWinner = mKickerArray.get( mKickerArray.size() - 1 );
    }


    public void sortPlayers() {
        Collections.sort( mPlayers, mPlayerSorter );
    }


    public Player seeWinner() {

        return mHandWinner != null ? mHandWinner : mKickerWinner;
    }

    public void takeCard( String card ) {
        mSharedCards.add( card );
    }



    public void endHand() {
        Integer number = mPlayers.size();
        if( mPlayerStart == number ) {
            mPlayerStart = 1;
        } else {
            mPlayerStart ++;
        }
    }

    public Integer seePlayerStart() {
        return mPlayerStart;
    }

    public void firstTurn() {
        for( int i = 0; i < mPlayers.size(); i++ ) {
            if( mPlayers.get(0).number() != mPlayerStart ) {
                turnEnd();
            }
        }
        mPlayers.get(0).smallBlind();
        addBet(mPlayers.get(0));
        turnEnd();
        mPlayers.get(0).bigBlind();
        addBet(mPlayers.get(0));
        turnEnd();
    }


    public ArrayList seeHand() {
        return mSharedCards;
    }

    public void addBet( Player player ) {
        mPot += player.giveBet();
        mlastBet = player.giveBet();
    }

    public void handWon( Player player ) {
        player.winChips( mPot );
        mPot = 0;
    }

    public int seeLastBet() {
        return mlastBet;
    }

    public void resetBets() {
        mlastBet = 0;
    }

    public void resetHand() {
        Integer number = seeHand().size();
        if( number > 0 ) {
            for( int i = 0; i < number; i ++ ) {
                mSharedCards.remove(0);
            }
        }
        mPot = 0;
        resetBets();
    }

}


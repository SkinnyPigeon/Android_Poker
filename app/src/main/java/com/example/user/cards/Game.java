package com.example.user.cards;


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
    private ArrayList< Player > mWinnerArray;
    private PlayerComparator mPlayerComparator;
    private KickerComparator mkickerComparator;
    private Player mHandWinner;

    public Game( int noOfPlayers ){
        mPot = 0;
        mPlayerTurn = 1;
        mPlayerStart = 1;
        mCurrentPlayer = 1;
        mFirstBet = 1;
        mNoOfPlayers = noOfPlayers;
        mNoOfFoldedPlayers = 0;
        mSharedCards = new ArrayList< String >();
        mWinnerArray = new ArrayList< Player >();
        mPlayerComparator = new PlayerComparator();
        mkickerComparator = new KickerComparator();
    }

    public int showPot() {
        return mPot;
    }

    public int seeCurrentPlayer() {
        return mCurrentPlayer;
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

    public int seePlayerStart() {
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
        if( player.status() ) {
            setFoldedPlayerCount();
            nextTurn();
        }
    }

    public void endTurn() {
        if( mCurrentPlayer == mNoOfPlayers ) {
            mCurrentPlayer = 1;
        } else {
            mCurrentPlayer += 1;
        }
    }

    public void endHand() {
        if( mCurrentPlayer == mNoOfPlayers ) {
            mPlayerStart = 1;
        } else {
            mPlayerStart += 1;
        }
    }

    public void passOverFoldedPlayer() {
        if( mCurrentPlayer == mNoOfPlayers ) {
            mPlayerStart = 1;
        } else {
            mPlayerStart += 1;
        }
    }

    public void setFold( Player one, Player two, Player three, Player four ) {
        if( one.status() && one.number() == mPlayerStart ) {
            passOverFoldedPlayer();
        } else if( two.status() && two.number() == mPlayerStart ) {
            passOverFoldedPlayer();
        } else if( three.status() && three.number() == mPlayerStart ) {
            passOverFoldedPlayer();
        } else if( four.status() && four.number() == mPlayerStart ) {
            passOverFoldedPlayer();
        }
    }

    public void firstTurn( Player one, Player two, Player three ) {
        if( one.number() == mPlayerStart ) {
            one.smallBlind();
            two.bigBlind();
            three.setFirstBet();
        } else if ( two.number() == mPlayerStart ) {
            two.smallBlind();
            three.bigBlind();
            one.setFirstBet();
        } else {
            three.smallBlind();
            one.bigBlind();
            two.setFirstBet();
        }
    }
    public void firstTurn(Player one, Player two) {
        if( one.number() == mPlayerStart ) {
            one.smallBlind();
            two.bigBlind();
            one.setFirstBet();
        } else if ( two.number() == mPlayerStart ) {
            two.smallBlind();
            one.bigBlind();
            two.setFirstBet();
        }
    }

    public void megaCheck( Player one, Player two, Player three, Player four ) {
        if( one.status() && two.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( three, four );
        } else if( one.status() && three.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( two, four );
        } else if( one.status() && four.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( two, three );
        } else if( two.status() && three.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, four );
        } else if( two.status() && four.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, three );
        } else if( three.status() && four.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, two );
        } else if( one.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( two, three, four );
        } else if( two.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, three, four );
        } else if( three.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, two, four );
        } else if( four.status() ) {
            setFold( one, two, three, four );
            setFold( one, two, three, four );

            firstTurn( one, two, three );
        }
    }

    public void foldMaster( Player player ) {
        foldCheck( player );
        foldWin( player );
    }

    public void setFoldedPlayerCount() {
        mNoOfFoldedPlayers += 1;
    }

    public int seeFolded() {
        return mNoOfFoldedPlayers;
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

    public void setLastBet() {
        mlastBet = 0;
    }

    public void setFirstBet( int playerNumber ) {
        if( mFirstBet == mNoOfPlayers ) {
            mFirstBet = 1;
        } else {
            mFirstBet += 1;
        }
    }

    public int seeFirstBet() {
        return mFirstBet;
    }

    public void setBetPlayer() {
        if( mPlayerStart + 2 > mNoOfPlayers ) {
            mBetPlayer = mPlayerStart + 1;
        }
    }

}














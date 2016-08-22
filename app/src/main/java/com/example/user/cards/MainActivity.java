package com.example.user.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Switch;

/**
 * Created by user on 19/08/2016.
 */
public class MainActivity extends AppCompatActivity{

    TextView mPlayerName;

    TextView mCommunityCards;

    TextView mPlayerCards;

    Button mPlus;
    Button mCall;
    Button mBet;
    Button mStart;
    Button mCheck;
    Button mFold;
    Button mWinner;
    TextView mWinnerName;

    TextView mPlayerBet;


    TextView mPotValue;

    TextView mToCall;

    TextView mPlayerChips;

    Switch mPOneTog;
    Switch mPTwoTog;
    Switch mPThreeTog;
    Switch mPFourTog;

    boolean mPOneReady;
    boolean mPTwoReady;
    boolean mPThreeReady;
    boolean mPFourReady;

    Player mJeff;
    Player mSteve;
    Player mDave;
    Player mBob;

    Game mGame;
    TestCards mCards;
    Logic mLogic;

    private static Integer mBetValue;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerName = ( TextView )findViewById( R.id.player_name);

        mPlayerBet = (TextView)findViewById( R.id.player_bet );

        mPlayerCards = (TextView)findViewById( R.id.player_cards );

        mPlayerChips = (TextView )findViewById( R.id.player_chips );

        mToCall = ( TextView )findViewById( R.id.to_call );


        mCommunityCards = ( TextView )findViewById( R.id.community_cards );

        mGame = new Game(4);
        mPOneTog = ( Switch )findViewById( R.id.p_one_toggle );
        mPTwoTog = ( Switch )findViewById( R.id.p_two_toggle );
        mPThreeTog = ( Switch )findViewById( R.id.p_three_toggle );
        mPFourTog = ( Switch )findViewById( R.id.p_four_toggle );

        mPOneTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPOneReady = true;
                    mJeff = new Player("Jeff", 1);
                    mJeff.in();
                    mGame.addPlayerToGame(mJeff);
                }
            }
        });

        mPTwoTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    mPTwoReady = true;
                    mSteve = new Player( "Steve", 2 );
                    mSteve.in();
                    mGame.addPlayerToGame( mSteve );
                }
            }
        });

        mPThreeTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    mPThreeReady = true;
                    mDave = new Player( "Dave", 3 );
                    mDave.in();
                    mGame.addPlayerToGame( mDave );
                }
            }
        });

        mPFourTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    mPFourReady = true;
                    mBob = new Player( "Bob", 4 );
                    mBob.in();
                    mGame.addPlayerToGame( mBob );
                }
            }
        });

        mPlus = ( Button )findViewById( R.id.plus );
        mCall = ( Button )findViewById( R.id.call );
        mCheck = ( Button )findViewById( R.id.check );
        mFold = ( Button )findViewById( R.id.fold );
        mWinner = ( Button )findViewById( R.id.winner );
        mWinnerName = ( TextView )findViewById( R.id.winner_name );

        mBet = ( Button )findViewById( R.id.bet );
        mPotValue = ( TextView )findViewById( R.id.pot );

        mStart = ( Button )findViewById( R.id.start );

        mBetValue = 0;

        mPlus.setVisibility(View.INVISIBLE);
        mCall.setVisibility(View.INVISIBLE);
        mBet.setVisibility(View.INVISIBLE);
        mPotValue.setVisibility(View.INVISIBLE);
        mCommunityCards.setVisibility(View.INVISIBLE);
        mCheck.setVisibility(View.INVISIBLE);
        mFold.setVisibility(View.INVISIBLE);
        mWinner.setVisibility(View.INVISIBLE);
        mToCall.setVisibility(View.INVISIBLE);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCards = new TestCards();

                if (mPOneReady) {
                    mGame.accessPlayer(0).takeCard(mCards.deal());
                    mGame.accessPlayer(0).takeCard(mCards.deal());
                }

                if (mPTwoReady) {
                    mGame.accessPlayer(1).takeCard(mCards.deal());
                    mGame.accessPlayer(1).takeCard(mCards.deal());
                }

                if (mPThreeReady) {
                    mGame.accessPlayer(2).takeCard(mCards.deal());
                    mGame.accessPlayer(2).takeCard(mCards.deal());
                }

                if (mPFourReady) {
                    mGame.accessPlayer(3).takeCard(mCards.deal());
                    mGame.accessPlayer(3).takeCard(mCards.deal());
                }

                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());

                String mGameCardOne = mGame.seeHand().get(0).toString();
                String mGameCardTwo = mGame.seeHand().get(1).toString();
                String mGameCardThree = mGame.seeHand().get(2).toString();

                String mGameCards = mGameCardOne + " " + mGameCardTwo + " " + mGameCardThree;

                mCommunityCards.setText(mGameCards);

                mPOneTog.setVisibility(View.INVISIBLE);
                mPTwoTog.setVisibility(View.INVISIBLE);
                mPThreeTog.setVisibility(View.INVISIBLE);
                mPFourTog.setVisibility(View.INVISIBLE);
                mStart.setVisibility(View.INVISIBLE);

                mPlus.setVisibility(View.VISIBLE);
                mCall.setVisibility(View.VISIBLE);
                mBet.setVisibility(View.VISIBLE);
                mPotValue.setVisibility(View.VISIBLE);
                mFold.setVisibility((View.VISIBLE));
                mWinner.setVisibility(View.VISIBLE);
                mToCall.setVisibility(View.VISIBLE);

                mGame.megaCheck(mJeff, mSteve, mDave, mBob);

                Integer pot = mGame.showPot();
                String potText = "Pot: " + " " + pot.toString();
                mPotValue.setText( potText );

                setText();
            }
        });

        mPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( mBetValue <= ( mGame.getCurrentPlayer().countChips() - 10) ) {
                    mBetValue += 10;
                    String cash = "Bet: " + mBetValue.toString();
                    mPlayerBet.setText( cash );
                }
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerCall(mGame.getCurrentPlayer());

                mGame.turnEnd();

                setText();
                checkCheck( mGame.getCurrentPlayer() );
            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bet();
                setText();
                checkCheck( mGame.getCurrentPlayer() );


            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetBets();
                mCommunityCards.setVisibility(View.VISIBLE);
                mCheck.setVisibility(View.INVISIBLE);

            }

        });

        mFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame.fold();

                if( mGame.getArraySize() == 1 ) {
                    mGame.handWon(mGame.getCurrentPlayer());
//                    nextHand();

                }

                setText();
            }
        });

        mWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logicCheck();
                mGame.addPlayer();

                mGame.pickWinner();
                mGame.handWon(mGame.seeWinner());
                mWinnerName.setText(mGame.seeWinner().name());

                Log.d( "Array", mGame.seePlayersArray().toString() );

                nextHand();
            }
        });


    }



    public void playerBet( Player player, Integer chips, TextView betText ) {
        player.placeBet(chips);
        mGame.addBet(player);
        chips = 0;

        String cash = "Bet: " + chips.toString();
        betText.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = "Pot: " + " " + potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerSet() {
        if( mJeff.seeBlind() ) {
            mGame.setCurrentPlayer( mJeff );
        } else if ( mSteve.seeBlind() ) {
            mGame.setCurrentPlayer( mSteve );
        } else if ( mDave.seeBlind() ) {
            mGame.setCurrentPlayer( mDave );
        } else if ( mBob.seeBlind() ) {
            mGame.setCurrentPlayer( mBob );
        }
    }

    public void playerCall( Player player ) {
        player.call( mGame );
        mGame.addBet(player);
        Integer potInt = mGame.showPot();
        String pot = "Pot: " + potInt.toString();
        mPotValue.setText( pot );
    }


    public void checkCheck( Player player ) {

        if( mGame.showPot() > 0 && mGame.seeLastBet() <= mGame.getCurrentPlayer().seeLastBet() ) {
            mCheck.setVisibility(View.VISIBLE);
        } else {
            mCheck.setVisibility(View.INVISIBLE);
        }
    }

    public void resetBets() {
        for( int i = 0; i < mGame.getArraySize(); i ++ ) {
            mGame.accessPlayer(i).resetLastBet();
            mGame.accessPlayer(i).resetBet();
        }
        mGame.resetBets();
        mBetValue = 0;
    }


    public void logicCheck() {

        for( int i = 0; i < mGame.getArraySize(); i ++ ) {
            Logic logic = new Logic( mGame.seeHand(), mGame.accessPlayer(i).seeHand() );
            logic.combineCards();
            logic.setScore();
            mGame.accessPlayer(i).awardScore(logic.seeScore());
            mGame.accessPlayer(i).awardKicker(logic.seeKicker());
        }
    }

    public void setText() {
        String name = mGame.getCurrentPlayer().name();
        mPlayerName.setText(name);

        String cardOne = mGame.getCurrentPlayer().seeHand().get(0).toString();
        String cardTwo = mGame.getCurrentPlayer().seeHand().get(1).toString();
        String cards = cardOne + " " + cardTwo;
        mPlayerCards.setText( cards );

        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText( pot );

        Integer betInt = mBetValue;
        String betText = "Bet: " + betInt.toString();
        mPlayerBet.setText( betText );

        Integer chipCount = mGame.getCurrentPlayer().countChips();
        String chips = "Chips: " + chipCount.toString();
        mPlayerChips.setText(chips);
    }

    public void bet() {
        int bet = mBetValue;
        mGame.getCurrentPlayer().placeBet(bet);
        mGame.addBet(mGame.getCurrentPlayer());
        mBetValue = 0;
        mGame.turnEnd();
    }

    public void nextHand() {

//        for( int i = 0; i < mGame.getArraySize(); i ++ ) {
//            mGame.accessPlayer(i).resetHand();
//            mGame.accessPlayer(i).resetLastBet();
//            mGame.accessPlayer(i).resetBet();
//        }
//        mGame.resetPlayers();
//
//        mGame.addPlayerToGame( mJeff );
//        mGame.addPlayerToGame(mSteve );
//        mGame.addPlayerToGame( mDave );
//        mGame.addPlayerToGame( mBob );
//        mGame.endHand();
//        mGame.megaCheck( mJeff, mSteve, mDave, mBob );
        setText();
        Log.d( "Ending hand:", "OK");

    }


}

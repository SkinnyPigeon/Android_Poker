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

    TextView mPlayerOneText;
    TextView mPlayerTwoText;
    TextView mPlayerThreeText;

    TextView mPlayerOneCards;
    TextView mPlayerTwoCards;
    TextView mPlayerThreeCards;
    TextView mCommunityCards;

    Button mPlus;
    Button mCall;
    Button mBet;
    Button mStart;
    Button mCheck;

    TextView mPlayerOneBet;
    TextView mPlayerTwoBet;
    TextView mPlayerThreeBet;
    TextView mPotValue;

    Switch mPOneTog;
    Switch mPTwoTog;
    Switch mPThreeTog;

    boolean mPOneReady;
    boolean mPTwoReady;
    boolean mPThreeReady;

    Player mJeff;
    Player mSteve;
    Player mDave;

    Game mGame;
    TestCards mCards;
    Logic mLogic;

    private static Integer mPOneBet;
    private static Integer mPTwoBet;
    private static Integer mPThreeBet;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerOneText = ( TextView )findViewById( R.id.ponet);
        mPlayerTwoText = ( TextView )findViewById( R.id.ptwot );
        mPlayerThreeText = (TextView )findViewById( R.id.pthreet );

        mPlayerOneBet = ( TextView )findViewById( R.id.poneb );
        mPlayerTwoBet = ( TextView )findViewById( R.id.ptwob );
        mPlayerThreeBet = (TextView )findViewById( R.id.pthreeb );

        mPlayerOneCards = ( TextView )findViewById( R.id.player_one_cards);
        mPlayerTwoCards = ( TextView )findViewById( R.id.player_two_cards);
        mPlayerThreeCards = ( TextView )findViewById( R.id.player_three_cards);
        mCommunityCards = ( TextView )findViewById( R.id.community_cards );

        hidePlayerOne();
        hidePlayerTwo();
        hidePlayerThree();

        mPOneTog = ( Switch )findViewById( R.id.p_one_toggle );
        mPTwoTog = ( Switch )findViewById( R.id.p_two_toggle );
        mPThreeTog = ( Switch )findViewById( R.id.p_three_toggle );

        mPOneTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.d( "LayoutExperiment: ", "Player two toggle togged");
                    mPOneReady = true;
                    mJeff = new Player( "Jeff", 1 );

                }
            }
        });

        mPTwoTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.d( "LayoutExperiment: ", "Player two toggle togged");
                    mPTwoReady = true;
                    mSteve = new Player( "Steve", 2 );

                }
            }
        });

        mPThreeTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.d( "LayoutExperiment: ", "Player two toggle togged");
                    mPThreeReady = true;
                    mDave = new Player( "Dave", 3 );

                }
            }
        });

        mPlus = ( Button )findViewById( R.id.plus );
        mCall = ( Button )findViewById( R.id.call );
        mCheck = ( Button )findViewById( R.id.check );

        mBet = ( Button )findViewById( R.id.bet );
        mPotValue = ( TextView )findViewById( R.id.pot );

        mStart = ( Button )findViewById( R.id.start );

        mPOneBet = 0;
        mPTwoBet = 0;
        mPThreeBet = 0;

        mPlus.setVisibility(View.INVISIBLE);
        mCall.setVisibility(View.INVISIBLE);
        mBet.setVisibility(View.INVISIBLE);
        mPotValue.setVisibility(View.INVISIBLE);
        mCommunityCards.setVisibility(View.INVISIBLE);
        mCheck.setVisibility(View.INVISIBLE);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame = new Game(2);
                mCards = new TestCards();

                if( mPOneReady == true ) {
                    mJeff.takeCard(mCards.deal());
                    mJeff.takeCard(mCards.deal());
                    mPlayerOneCards.setText(mJeff.seeHand().toString());
                }

                if( mPTwoReady == true ) {
                    mSteve.takeCard(mCards.deal());
                    mSteve.takeCard(mCards.deal());
                    mPlayerTwoCards.setText(mSteve.seeHand().toString());
                }

                if( mPThreeReady == true ) {
                    mDave.takeCard( mCards.deal());
                    mDave.takeCard(mCards.deal());
                    mPlayerThreeCards.setText(mDave.seeHand().toString());
                }

                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());

//                mGame.takeCard(mCards.deal());
//                mGame.takeCard(mCards.deal());

                mCommunityCards.setText(mGame.seeHand().toString());


                mPOneTog.setVisibility(View.INVISIBLE);
                mPTwoTog.setVisibility(View.INVISIBLE);
                mPThreeTog.setVisibility(View.INVISIBLE);
                mStart.setVisibility(View.INVISIBLE);

                mPlus.setVisibility(View.VISIBLE);
                mCall.setVisibility(View.VISIBLE);
                mBet.setVisibility(View.VISIBLE);
                mPotValue.setVisibility(View.VISIBLE);

                showPlayerOne();
            }
        });

        mPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerOneText.getVisibility() == View.VISIBLE) {
                    if( mPOneBet <= ( mJeff.countChips() - 10) ) {
                        mPOneBet += 10;
                        String cash = mPOneBet.toString();
                        mPlayerOneBet.setText(cash);
                    }
                } else if (mPlayerTwoText.getVisibility() == View.VISIBLE) {
                    if( mPTwoBet <= ( mSteve.countChips() - 10 ) ) {
                        mPTwoBet += 10;
                        String cash = mPTwoBet.toString();
                        mPlayerTwoBet.setText(cash);
                    }
                } else if (mPlayerThreeText.getVisibility() == View.VISIBLE) {
                    if( mPThreeBet <= ( mDave.countChips() - 10 ) ) {
                        mPThreeBet += 10;
                        String cash = mPThreeBet.toString();
                        mPlayerThreeBet.setText(cash);
                    }
                }
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPlayerOneText.getVisibility() == View.VISIBLE ) {
                    if( mGame.seeLastBet() <= mJeff.countChips() ) {
                        playerOneCall();
                        playerOneBet();
                        playerOneEndTurn();
                    }
                } else if (mPlayerTwoText.getVisibility() == View.VISIBLE) {
                    if( mGame.seeLastBet() <= mSteve.countChips() ) {
                        playerTwoCall();
                        playerTwoBet();
                        playerTwoEndTurn();
                    }
                } else if (mPlayerThreeText.getVisibility() == View.VISIBLE) {
                    if( mGame.seeLastBet() <= mDave.countChips() ) {
                        playerThreeCall();
                        playerThreeBet();
                        playerThreeEndTurn();
                    }
                }
            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerOneText.getVisibility() == View.VISIBLE) {
                    playerOneBet();
                    playerOneEndTurn();

                } else if (mPlayerTwoText.getVisibility() == View.VISIBLE) {
                    playerTwoBet();
                    playerTwoEndTurn();

                } else if (mPlayerThreeText.getVisibility() == View.VISIBLE) {
                    playerThreeBet();
                    playerThreeEndTurn();
                }
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommunityCards.setVisibility(View.VISIBLE);
                mCheck.setVisibility(View.INVISIBLE);
                if( mPlayerOneText.getVisibility() == View.VISIBLE ) {
                    if( mGame.seeLastBet() <= mJeff.countChips() ) {
                        playerOneEndTurn();
                        mJeff.setLastBet();
                        mGame.setLastBet();
                        mGame.setFirstBet( mJeff.number() );
                    }
                } else if (mPlayerTwoText.getVisibility() == View.VISIBLE) {
                    if( mGame.seeLastBet() <= mSteve.countChips() ) {
                        playerTwoEndTurn();
                        mSteve.setLastBet();
                        mGame.setLastBet();
                        mGame.setFirstBet( mSteve.number() );
                    }
                } else if (mPlayerThreeText.getVisibility() == View.VISIBLE) {
                    if( mGame.seeLastBet() <= mDave.countChips() ) {
                        playerThreeEndTurn();
                        mDave.setLastBet();
                        mGame.setLastBet();
                        mGame.setFirstBet( mDave.number() );
                    }
                }
            }
        });
    }

    public void playerOneBet() {
        mJeff.placeBet(mPOneBet);
        mGame.addBet( mJeff );
        mPOneBet = 0;
        String cash = mPOneBet.toString();
        mPlayerOneBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText( pot );
    }

    public void playerOneCall() {
        mPOneBet = mGame.seeLastBet();
        String cash = mPOneBet.toString();
        mPlayerOneBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText( pot );
    }

    public void playerOneEndTurn() {
        if( mPTwoReady ) {
            mGame.nextTurn();
            showPlayerTwo();
            if( mGame.seeFirstBet() == mSteve.number() && mGame.showPot() > 0
                    && mSteve.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }

        } else if( mPThreeReady ) {
            mGame.nextTurn();
            showPlayerThree();
            if( mGame.seeFirstBet() == mDave.number() && mGame.showPot() > 0
                    && mDave.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }
        }
        hidePlayerOne();
    }

    public void playerTwoBet() {
        mSteve.placeBet( mPTwoBet );
        mGame.addBet(mSteve);
        mPTwoBet = 0;
        String cash = mPTwoBet.toString();
        mPlayerTwoBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerTwoCall() {
        mPTwoBet = mGame.seeLastBet();
        String cash = mPTwoBet.toString();
        mPlayerTwoBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerTwoEndTurn() {
        if( mPThreeReady ) {
            mGame.nextTurn();
            showPlayerThree();
            if( mGame.seeFirstBet() == mDave.number() && mGame.showPot() > 0
                    && mDave.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }

        } else if( mPOneReady ) {
            mGame.nextTurn();
            showPlayerOne();
            if( mGame.seeFirstBet() == mJeff.number() && mGame.showPot() > 0
                    && mJeff.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }
        }
        hidePlayerTwo();
    }

    public void playerThreeBet() {
        mDave.placeBet( mPThreeBet );
        mGame.addBet(mDave);
        mPThreeBet = 0;
        String cash = mPThreeBet.toString();
        mPlayerThreeBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerThreeCall() {
        mPThreeBet = mGame.seeLastBet();
        String cash = mPThreeBet.toString();
        mPlayerThreeBet.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerThreeEndTurn() {
        if( mPOneReady ) {
            mGame.nextTurn();
            showPlayerOne();
            if( mGame.seeFirstBet() == mJeff.number() && mGame.showPot() > 0
                    && mJeff.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }

        } else if( mPTwoReady ) {
            mGame.nextTurn();
            showPlayerTwo();
            if( mGame.seeFirstBet() == mSteve.number() && mGame.showPot() > 0
                    && mSteve.seeLastBet() == mGame.seeLastBet() && mCheck.getVisibility() != View.VISIBLE ) {
                mCheck.setVisibility(View.VISIBLE);
            } else {
                mCheck.setVisibility(View.INVISIBLE);
            }
        }
        hidePlayerThree();
    }


    public void hidePlayerOne() {
        mPlayerOneText.setVisibility(View.INVISIBLE);
        mPlayerOneBet.setVisibility(View.INVISIBLE);
        mPlayerOneCards.setVisibility(View.INVISIBLE);
    }

    public void showPlayerOne() {
        mPlayerOneText.setVisibility(View.VISIBLE);
        mPlayerOneBet.setVisibility(View.VISIBLE);
        mPlayerOneCards.setVisibility(View.VISIBLE);
    }

    public void hidePlayerTwo() {
        mPlayerTwoText.setVisibility(View.INVISIBLE);
        mPlayerTwoBet.setVisibility(View.INVISIBLE);
        mPlayerTwoCards.setVisibility(View.INVISIBLE);
    }

    public void showPlayerTwo() {
        mPlayerTwoText.setVisibility(View.VISIBLE);
        mPlayerTwoBet.setVisibility(View.VISIBLE);
        mPlayerTwoCards.setVisibility(View.VISIBLE);
    }

    public void hidePlayerThree() {
        mPlayerThreeText.setVisibility(View.INVISIBLE);
        mPlayerThreeBet.setVisibility(View.INVISIBLE);
        mPlayerThreeCards.setVisibility(View.INVISIBLE);
    }

    public void showPlayerThree() {
        mPlayerThreeText.setVisibility(View.VISIBLE);
        mPlayerThreeBet.setVisibility(View.VISIBLE);
        mPlayerThreeCards.setVisibility(View.VISIBLE);
    }


}

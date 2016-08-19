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

    Button mPlayerOneButton;
    Button mPlayerTwoButton;
    Button mPlayerThreeButton;
    TextView mPlayerOneText;
    TextView mPlayerTwoText;
    TextView mPlayerThreeText;
    Button mPlus;
    Button mMinus;
    Button mBet;
    Button mStart;
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
    private static Integer mPOneCash;
    private static Integer mPTwoBet;
    private static Integer mPTwoCash;
    private static Integer mPThreeBet;
    private static Integer mPThreeCash;
    private static Integer mPot;



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerOneButton = ( Button )findViewById( R.id.ponebut );
        mPlayerTwoButton = ( Button )findViewById( R.id.ptwobut );
        mPlayerThreeButton = ( Button )findViewById( R.id.pthreebut);

        mPlayerOneText = ( TextView )findViewById( R.id.ponet);
        mPlayerTwoText = ( TextView )findViewById( R.id.ptwot );
        mPlayerThreeText = (TextView )findViewById( R.id.pthreet );

        mPlayerOneBet = ( TextView )findViewById( R.id.poneb );
        mPlayerTwoBet = ( TextView )findViewById( R.id.ptwob );
        mPlayerThreeBet = (TextView )findViewById( R.id.pthreeb );

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
        mMinus = ( Button )findViewById( R.id.minus );

        mBet = ( Button )findViewById( R.id.bet );
        mPotValue = ( TextView )findViewById( R.id.pot );

        mStart = ( Button )findViewById( R.id.start );

        mPOneBet = 0;
        mPTwoBet = 0;
        mPThreeBet = 0;
        mPTwoCash = 500;
        mPOneCash = 500;
        mPThreeCash = 500;
        mPot = 0;

        mPlus.setVisibility(View.INVISIBLE);
        mMinus.setVisibility(View.INVISIBLE);
        mBet.setVisibility(View.INVISIBLE);
        mPotValue.setVisibility(View.INVISIBLE);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame = new Game(2);
                mCards = new TestCards();
                mJeff.takeCard( mCards.deal() );
                mJeff.takeCard( mCards.deal() );
                mSteve.takeCard( mCards.deal() );
                mSteve.takeCard( mCards.deal() );

                mGame.takeCard( mCards.deal() );
                mGame.takeCard( mCards.deal() );
                mGame.takeCard( mCards.deal() );
                mGame.takeCard( mCards.deal() );
                mGame.takeCard( mCards.deal() );

                mPOneTog.setVisibility(View.INVISIBLE);
                mPTwoTog.setVisibility(View.INVISIBLE);
                mPThreeTog.setVisibility(View.INVISIBLE);
                mStart.setVisibility(View.INVISIBLE);

                mPlus.setVisibility(View.VISIBLE);
                mMinus.setVisibility(View.VISIBLE);
                mBet.setVisibility(View.VISIBLE);
                mPotValue.setVisibility(View.VISIBLE);

                showPlayerOne();
            }
        });


        mPlayerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPTwoReady ) {
                    mGame.nextTurn();
                    showPlayerTwo();
                } else if( mPThreeReady ) {
                    mGame.nextTurn();
                    showPlayerThree();
                }
                hidePlayerOne();
            }
        });

        mPlayerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPThreeReady ) {
                    mGame.nextTurn();
                    showPlayerThree();
                } else if( mPOneReady ) {
                    mGame.nextTurn();
                    showPlayerOne();
                }
                hidePlayerTwo();
            }
        });

        mPlayerThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPOneReady ) {
                    mGame.nextTurn();
                    showPlayerOne();
                } else if( mPTwoReady ) {
                    mGame.nextTurn();
                    showPlayerTwo();
                }
                hidePlayerThree();
            }
        });

        mPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerOneButton.getVisibility() == View.VISIBLE) {
                    Log.d("Layout Tests: ", "Plus button pressed for Player One");
                    mPOneBet += 10;
                    String cash = mPOneBet.toString();
                    mPlayerOneBet.setText(cash);
                } else if (mPlayerTwoButton.getVisibility() == View.VISIBLE) {
                    Log.d("Layout Tests: ", "Plus button pressed for Player Two");
                    mPTwoBet += 10;
                    String cash = mPTwoBet.toString();
                    mPlayerTwoBet.setText(cash);
                } else if (mPlayerThreeButton.getVisibility() == View.VISIBLE) {
                    Log.d("Layout Tests: ", "Plus button pressed for Player Three");
                    mPThreeBet += 10;
                    String cash = mPThreeBet.toString();
                    mPlayerThreeBet.setText(cash);
                }
            }
        });

        mMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPlayerOneButton.getVisibility() == View.VISIBLE ) {
                    Log.d("Layout Tests: ", "Minus button pressed for Player One");
                    if( mPOneBet >= 10 ) {
                        mPOneBet -= 10;
                        String cash = mPOneBet.toString();
                        mPlayerOneBet.setText( cash );
                    }
                } else if ( mPlayerTwoButton.getVisibility() == View.VISIBLE ) {
                    Log.d("Layout Tests: ", "Minus button pressed for Player Two");
                    if( mPTwoBet >= 10 ) {
                        mPTwoBet -= 10;
                        String cash = mPTwoBet.toString();
                        mPlayerTwoBet.setText(cash);
                    }
                } else if ( mPlayerThreeButton.getVisibility() == View.VISIBLE ) {
                    Log.d("Layout Tests: ", "Minus button pressed for Player Two");
                    if( mPThreeBet >= 10 ) {
                        mPThreeBet -= 10;
                        String cash = mPThreeBet.toString();
                        mPlayerThreeBet.setText(cash);
                    }
                }
            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerOneButton.getVisibility() == View.VISIBLE) {
                    mPot += mPOneBet;
                    mJeff.placeBet(mPOneBet);
                    mGame.addBet( mJeff );
                    mPOneBet = 0;
                    String cash = mPOneBet.toString();
                    mPlayerOneBet.setText(cash);
                    Integer potInt = mGame.showPot();
                    String pot = potInt.toString();
                    mPotValue.setText( pot );

                } else if (mPlayerTwoButton.getVisibility() == View.VISIBLE) {
                    mPot += mPTwoBet;
                    mSteve.placeBet( mPTwoBet );
                    mGame.addBet(mSteve);
                    mPTwoBet = 0;
                    String cash = mPTwoBet.toString();
                    mPlayerTwoBet.setText(cash);
                    Integer potInt = mGame.showPot();
                    String pot = potInt.toString();
                    mPotValue.setText(pot);

                } else if (mPlayerThreeButton.getVisibility() == View.VISIBLE) {
                    mPot += mPThreeBet;
                    mDave.placeBet( mPTwoBet );
                    mGame.addBet(mSteve);
                    mPThreeBet = 0;
                    String cash = mPThreeBet.toString();
                    mPlayerThreeBet.setText(cash);
                    Integer potInt = mGame.showPot();
                    String pot = potInt.toString();
                    mPotValue.setText(pot);
                }
            }
        });
    }

    public void hidePlayerOne() {
        mPlayerOneButton.setVisibility(View.INVISIBLE);
        mPlayerOneText.setVisibility(View.INVISIBLE);
        mPlayerOneBet.setVisibility(View.INVISIBLE);
    }

    public void showPlayerOne() {
        mPlayerOneButton.setVisibility(View.VISIBLE);
        mPlayerOneText.setVisibility(View.VISIBLE);
        mPlayerOneBet.setVisibility(View.VISIBLE);
    }

    public void hidePlayerTwo() {
        mPlayerTwoButton.setVisibility(View.INVISIBLE);
        mPlayerTwoText.setVisibility(View.INVISIBLE);
        mPlayerTwoBet.setVisibility(View.INVISIBLE);
    }

    public void showPlayerTwo() {
        mPlayerTwoButton.setVisibility(View.VISIBLE);
        mPlayerTwoText.setVisibility(View.VISIBLE);
        mPlayerTwoBet.setVisibility(View.VISIBLE);
    }

    public void hidePlayerThree() {
        mPlayerThreeButton.setVisibility(View.INVISIBLE);
        mPlayerThreeText.setVisibility(View.INVISIBLE);
        mPlayerThreeBet.setVisibility(View.INVISIBLE);
    }

    public void showPlayerThree() {
        mPlayerThreeButton.setVisibility(View.VISIBLE);
        mPlayerThreeText.setVisibility(View.VISIBLE);
        mPlayerThreeBet.setVisibility(View.VISIBLE);
    }


}

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
    TextView mPlayerFourText;

    TextView mPlayerOneCards;
    TextView mPlayerTwoCards;
    TextView mPlayerThreeCards;
    TextView mPlayerFourCards;
    TextView mCommunityCards;

    Button mPlus;
    Button mCall;
    Button mBet;
    Button mStart;
    Button mCheck;
    Button mFold;
    Button mWinner;
    TextView mWinnerName;

    TextView mPlayerOneBet;
    TextView mPlayerTwoBet;
    TextView mPlayerThreeBet;
    TextView mPlayerFourBet;
    TextView mPotValue;

    TextView mPlayerOneChips;
    TextView mPlayerTwoChips;
    TextView mPlayerThreeChips;
    TextView mPlayerFourChips;



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

    private static Integer mPOneBet;
    private static Integer mPTwoBet;
    private static Integer mPThreeBet;
    private static Integer mPFourBet;
    private static Integer mCheckCount;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerOneText = ( TextView )findViewById( R.id.ponet);
        mPlayerTwoText = ( TextView )findViewById( R.id.ptwot );
        mPlayerThreeText = (TextView )findViewById( R.id.pthreet );
        mPlayerFourText = ( TextView )findViewById( R.id.pfourt );

        mPlayerOneBet = ( TextView )findViewById( R.id.poneb );
        mPlayerTwoBet = ( TextView )findViewById( R.id.ptwob );
        mPlayerThreeBet = (TextView )findViewById( R.id.pthreeb );
        mPlayerFourBet = ( TextView )findViewById( R.id.pfourb );

        mPlayerOneCards = ( TextView )findViewById( R.id.player_one_cards);
        mPlayerTwoCards = ( TextView )findViewById( R.id.player_two_cards);
        mPlayerThreeCards = ( TextView )findViewById( R.id.player_three_cards);
        mPlayerFourCards = ( TextView )findViewById( R.id.player_four_cards);

        mPlayerOneChips = ( TextView )findViewById( R.id.player_one_chips );
        mPlayerTwoChips = ( TextView )findViewById( R.id.player_two_chips );
        mPlayerThreeChips = ( TextView )findViewById( R.id.player_three_chips );
        mPlayerFourChips = ( TextView )findViewById( R.id.player_four_chips );


        mCommunityCards = ( TextView )findViewById( R.id.community_cards );

        hidePlayerOne();
        hidePlayerTwo();
        hidePlayerThree();
        hidePlayerFour();

        mPOneTog = ( Switch )findViewById( R.id.p_one_toggle );
        mPTwoTog = ( Switch )findViewById( R.id.p_two_toggle );
        mPThreeTog = ( Switch )findViewById( R.id.p_three_toggle );
        mPFourTog = ( Switch )findViewById( R.id.p_four_toggle );

//        mJeff = new Player("Jeff", 1);
//        mSteve = new Player( "Steve", 2 );
//        mDave = new Player( "Dave", 3 );
//        mBob = new Player( "Bob", 4 );

        mPOneTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("LayoutExperiment: ", "Player one toggle togged");
                    mPOneReady = true;
                    mJeff = new Player("Jeff", 1);
                    mJeff.in();
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
                    mSteve.in();
                }
            }
        });

        mPThreeTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.d( "LayoutExperiment: ", "Player three toggle togged");
                    mPThreeReady = true;
                    mDave = new Player( "Dave", 3 );
                    mDave.in();
                }
            }
        });

        mPFourTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    Log.d( "LayoutExperiment: ", "Player four toggle togged");
                    mPFourReady = true;
                    mBob = new Player( "Bob", 4 );
                    mBob.in();
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

        mPOneBet = 0;
        mPTwoBet = 0;
        mPThreeBet = 0;
        mPFourBet = 0;
        mCheckCount = 0;

        mPlus.setVisibility(View.INVISIBLE);
        mCall.setVisibility(View.INVISIBLE);
        mBet.setVisibility(View.INVISIBLE);
        mPotValue.setVisibility(View.INVISIBLE);
        mCommunityCards.setVisibility(View.INVISIBLE);
        mCheck.setVisibility(View.INVISIBLE);
        mFold.setVisibility(View.INVISIBLE);
        mWinner.setVisibility(View.INVISIBLE);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame = new Game(4);
                mCards = new TestCards();

                if (mPOneReady) {
                    mGame.foldMaster(mJeff);
                    mJeff.takeCard(mCards.deal());
                    mJeff.takeCard(mCards.deal());
                    mPlayerOneCards.setText(mJeff.seeHand().toString());
                }

                if (mPTwoReady) {
                    mGame.foldMaster(mSteve);
                    mSteve.takeCard(mCards.deal());
                    mSteve.takeCard(mCards.deal());
                    mPlayerTwoCards.setText(mSteve.seeHand().toString());
                }

                if (mPThreeReady) {
                    mGame.foldMaster(mDave);
                    mDave.takeCard(mCards.deal());
                    mDave.takeCard(mCards.deal());
                    mPlayerThreeCards.setText(mDave.seeHand().toString());
                }

                if (mPFourReady) {
                    mGame.foldMaster(mBob);
                    mBob.takeCard(mCards.deal());
                    mBob.takeCard(mCards.deal());
                    mPlayerFourCards.setText(mBob.seeHand().toString());
                }

                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());
                mGame.takeCard(mCards.deal());

                mCommunityCards.setText(mGame.seeHand().toString());

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

                mGame.megaCheck(mJeff, mSteve, mDave, mBob);
                Integer pot = mGame.showPot();
                mPotValue.setText(pot.toString());
                Log.d("This", pot.toString());
                showPlayerThree();
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
                } else if (mPlayerFourText.getVisibility() == View.VISIBLE) {
                    if( mPFourBet <= ( mBob.countChips() - 10 ) ) {
                        mPFourBet += 10;
                        String cash = mPFourBet.toString();
                        mPlayerFourBet.setText(cash);

                    }
                }
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerCall( mJeff );
                playerCall( mSteve );
                playerCall( mDave );
                playerCall( mBob );

                playerEndTurn();

                hidePlayerOne();
                hidePlayerTwo();
                hidePlayerThree();
                hidePlayerFour();

                if ( mJeff.number() == mGame.seeCurrentPlayer() && !mJeff.status()) {
                    showPlayerOne();
                } else if( mSteve.number() == mGame.seeCurrentPlayer() && !mSteve.status() ) {
                    showPlayerTwo();
                } else if( mDave.number() == mGame.seeCurrentPlayer() && !mDave.status() ) {
                    showPlayerThree();
                } else if( mBob.number() == mGame.seeCurrentPlayer() && !mBob.status() ) {
                    showPlayerFour();
                }
            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerOneText.getVisibility() == View.VISIBLE) {
                    playerBet(mJeff, mPOneBet, mPlayerOneBet);
                    playerEndTurn();

                } else if (mPlayerTwoText.getVisibility() == View.VISIBLE) {
                    playerBet(mSteve, mPTwoBet, mPlayerTwoBet);
                    playerEndTurn();

                } else if (mPlayerThreeText.getVisibility() == View.VISIBLE) {
                    playerBet(mDave, mPThreeBet, mPlayerThreeBet);
                    playerEndTurn();

                } else if (mPlayerFourText.getVisibility() == View.VISIBLE) {
                    playerBet( mBob, mPFourBet, mPlayerFourBet );
                    playerEndTurn();

                }
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePlayerOne();
                hidePlayerTwo();
                hidePlayerThree();
                hidePlayerFour();
                mCommunityCards.setVisibility(View.VISIBLE);
                mCheck.setVisibility(View.INVISIBLE);

                if ( mJeff.seeFirstBet() && !mJeff.status() ) {
                    showPlayerOne();
                } else if( mSteve.seeFirstBet() && !mSteve.status() ) {
                    showPlayerTwo();
                } else if( mDave.seeFirstBet() && !mDave.status() ) {
                    showPlayerThree();
                } else if( mBob.seeFirstBet() && !mBob.status() ) {
                    showPlayerFour();
                }
            }
        });



        mWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logicCheck( mGame, mJeff );
                logicCheck( mGame, mSteve );
                logicCheck( mGame, mDave );
                logicCheck( mGame, mBob );

                mGame.addPlayer(mJeff);
                mGame.addPlayer(mBob);
                mGame.addPlayer(mSteve);
                mGame.addPlayer(mDave);

                mGame.pickWinner();
                mGame.handWon(mGame.seeWinner());
                mWinnerName.setText( mGame.seeWinner().name() );
            }
        });
    }

    public void playerBet( Player player, Integer chips, TextView betText ) {
        player.placeBet(chips);
        mGame.addBet( player );
        chips = 0;
        String cash = chips.toString();
        betText.setText(cash);
        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText(pot);
    }

    public void playerCall( Player player ) {
        if( player.number() == mGame.seeCurrentPlayer() ) {
            player.call(mGame);
            mGame.addBet(player);
            Integer potInt = mGame.showPot();
            String pot = potInt.toString();
            mPotValue.setText(pot);
        }
    }

    public void playerEndTurn() {
        hidePlayerOne();
        hidePlayerTwo();
        hidePlayerThree();
        hidePlayerFour();
        mGame.endTurn();
        if( mPOneReady && mJeff.number() == mGame.seeCurrentPlayer() && !mJeff.status() ) {
            showPlayerOne();
            checkCheck( mJeff );
        } else if ( mPTwoReady && mSteve.number() == mGame.seeCurrentPlayer() && !mSteve.status() ) {
            showPlayerTwo();
            checkCheck( mSteve );
        } else if ( mPThreeReady && mDave.number() == mGame.seeCurrentPlayer() && !mDave.status() ) {
            showPlayerThree();
            checkCheck( mDave );
        } else if ( mPFourReady && mBob.number() == mGame.seeCurrentPlayer() && !mBob.status() ) {
            showPlayerFour();
            checkCheck( mBob );
        }
    }

    public void checkCheck( Player player ) {
        if( mGame.seeFirstBet() == player.number() && mGame.showPot() > 0
                && mGame.seeLastBet() <= player.seeLastBet()
                && mCheck.getVisibility() !=View.VISIBLE ) {
            mCheck.setVisibility(View.VISIBLE);
        } else {
            mCheck.setVisibility(View.INVISIBLE);
        }
    }


    public void hidePlayerOne() {
        mPlayerOneText.setVisibility(View.INVISIBLE);
        mPlayerOneBet.setVisibility(View.INVISIBLE);
        mPlayerOneCards.setVisibility(View.INVISIBLE);

        mPlayerOneChips.setVisibility(View.INVISIBLE);
    }

    public void showPlayerOne() {
        mPlayerOneText.setVisibility(View.VISIBLE);
        mPlayerOneBet.setVisibility(View.VISIBLE);
        mPlayerOneCards.setVisibility(View.VISIBLE);
        Integer chips = mJeff.countChips();
        mPlayerOneChips.setText(chips.toString());
        mPlayerOneChips.setVisibility(View.VISIBLE);
    }

    public void hidePlayerTwo() {
        mPlayerTwoText.setVisibility(View.INVISIBLE);
        mPlayerTwoBet.setVisibility(View.INVISIBLE);
        mPlayerTwoCards.setVisibility(View.INVISIBLE);
        mPlayerTwoChips.setVisibility(View.INVISIBLE);
    }

    public void showPlayerTwo() {
        mPlayerTwoText.setVisibility(View.VISIBLE);
        mPlayerTwoBet.setVisibility(View.VISIBLE);
        mPlayerTwoCards.setVisibility(View.VISIBLE);
        Integer chips = mSteve.countChips();
        mPlayerTwoChips.setText(chips.toString());
        mPlayerTwoChips.setVisibility(View.VISIBLE);
    }

    public void hidePlayerThree() {
        mPlayerThreeText.setVisibility(View.INVISIBLE);
        mPlayerThreeBet.setVisibility(View.INVISIBLE);
        mPlayerThreeCards.setVisibility(View.INVISIBLE);
        mPlayerThreeChips.setVisibility(View.INVISIBLE);
    }

    public void showPlayerThree() {
        mPlayerThreeText.setVisibility(View.VISIBLE);
        mPlayerThreeBet.setVisibility(View.VISIBLE);
        mPlayerThreeCards.setVisibility(View.VISIBLE);
        Integer chips = mDave.countChips();
        mPlayerThreeChips.setText(chips.toString());
        mPlayerThreeChips.setVisibility(View.VISIBLE);
    }

    public void hidePlayerFour() {
        mPlayerFourText.setVisibility(View.INVISIBLE);
        mPlayerFourBet.setVisibility(View.INVISIBLE);
        mPlayerFourCards.setVisibility(View.INVISIBLE);
        mPlayerFourChips.setVisibility(View.INVISIBLE);
    }

    public void showPlayerFour() {
        mPlayerFourText.setVisibility(View.VISIBLE);
        mPlayerFourBet.setVisibility(View.VISIBLE);
        mPlayerFourCards.setVisibility(View.VISIBLE);
        Integer chips = mBob.countChips();
        mPlayerFourChips.setText(chips.toString());
        mPlayerFourChips.setVisibility(View.VISIBLE);
    }

    public void resetBets() {
        mJeff.resetLastBet();
        mJeff.resetBet();
        mSteve.resetLastBet();
        mSteve.resetBet();
        mDave.resetLastBet();
        mDave.resetBet();
        mBob.resetLastBet();
        mBob.resetBet();
        mGame.resetBets();
    }

    public void logicCheck( Game game, Player player ) {
        Logic logic = new Logic( game.seeHand(), player.seeHand() );
        logic.combineCards();
        logic.setScore();
        player.awardScore(logic.seeScore());
        player.awardKicker(logic.seeKicker());
    }



}

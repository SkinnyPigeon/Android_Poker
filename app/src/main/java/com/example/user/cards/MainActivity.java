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
    String mToCallText;

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
    Game mTempGame;
    TestCards mCards;
    Logic mLogic;

    private static Integer mBetValue;

    private static Integer mCheckCount;

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

//        hidePlayers();

        mGame = new Game(4);
        mPOneTog = ( Switch )findViewById( R.id.p_one_toggle );
        mPTwoTog = ( Switch )findViewById( R.id.p_two_toggle );
        mPThreeTog = ( Switch )findViewById( R.id.p_three_toggle );
        mPFourTog = ( Switch )findViewById( R.id.p_four_toggle );

        mPOneTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("LayoutExperiment: ", "Player one toggle togged");
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
                    Log.d( "LayoutExperiment: ", "Player two toggle togged");
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
                    Log.d( "LayoutExperiment: ", "Player three toggle togged");
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
                    Log.d( "LayoutExperiment: ", "Player four toggle togged");
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
//
//        mPOneBet = 0;
//        mPTwoBet = 0;
//        mPThreeBet = 0;
//        mPFourBet = 0;
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
                Log.d("Bob: ", mBob.seeFolded());

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

                Integer number = mGame.seeCurrentPlayer();
                Log.d( "Player who press call: ", number.toString() );

                playerCall(mGame.getCurrentPlayer());

                mGame.turnEnd();

                number = mGame.seeCurrentPlayer();
                Log.d( "Player to skip check: ", number.toString() );
                skipPlayers();

                setText();

                number = mGame.seeCurrentPlayer();
                Log.d("Current Player: ", number.toString());

            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bet();
                setText();

            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mGame.foldMaster( mJeff );
//                mGame.foldMaster( mSteve );
//                mGame.foldMaster( mDave );
//                mGame.foldMaster(mBob);
//                setToCallText();

//
//                playerFoldMaster();
//                mGame.endTurn();
//
////                hidePlayers();
//
//                playerSet();
//                resetBets();
//
//
//                mCommunityCards.setVisibility(View.VISIBLE);
//                mCheck.setVisibility(View.INVISIBLE);
//
//                if ( mJeff.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerOne();
//                } else if( mSteve.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerTwo();
//                } else if( mDave.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerThree();
//                } else if( mBob.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerFour();
//                }

            }

        });

        mFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame.fold();

                if( mGame.getArraySize() == 1 ) {
                    mGame.handWon( mGame.getCurrentPlayer() );
                }

                setText();



//                Integer number = mGame.seeCurrentPlayer();
//
//                Log.d( "Folding player's number: ", number.toString() );
//
//                if( mPlayerOneText.getVisibility() == View.VISIBLE ) {
//                    mJeff.fold();
//                    mGame.endTurn();
//
//                } else if ( mPlayerTwoText.getVisibility() == View.VISIBLE ) {
//                    mSteve.fold();
//                    mGame.endTurn();
//
//                } else if ( mPlayerThreeText.getVisibility() == View.VISIBLE ) {
//                    mDave.fold();
//                    mGame.endTurn();
//
//                } else if ( mPlayerFourText.getVisibility() == View.VISIBLE ) {
//                    mBob.fold();
//                    mGame.endTurn();
//                }
//
//                skipPlayers();
//
//
//                if( mJeff.status() &&  mJeff.number() == mGame.seeCurrentPlayer() ) {
//                    mGame.endTurn();
//                } else if( mSteve.status() &&  mSteve.number() == mGame.seeCurrentPlayer()) {
//                    mGame.endTurn();
//                }   else  if ( mDave.status() &&  mDave.number() == mGame.seeCurrentPlayer() ) {
//                    mGame.endTurn();
//                }   else if( mBob.status() &&  mBob.number() == mGame.seeCurrentPlayer()) {
//                    mGame.endTurn();
//                }
//
//                Log.d("Has Jeff folded? ", mJeff.seeFolded());
//
//
//                hidePlayers();
//
//                if( mJeff.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerOne();
//                } else if ( mSteve.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerTwo();
//                } else if ( mDave.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerThree();
//                } else if ( mBob.number() == mGame.seeCurrentPlayer() ) {
//                    showPlayerFour();
//                }
            }
        });

        mWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !mJeff.status() ) {
                    logicCheck( mGame, mJeff );
                    mGame.addPlayer(mJeff);
                }

                if( !mSteve.status() ) {
                    logicCheck( mGame, mSteve );
                    mGame.addPlayer(mSteve);
                }

                if( !mDave.status() ) {
                    logicCheck(mGame, mDave);
                    mGame.addPlayer(mDave);
                }

                if( !mBob.status() ) {
                    logicCheck( mGame, mBob );
                    mGame.addPlayer(mBob);
                }

                mGame.pickWinner();
                mGame.handWon(mGame.seeWinner());
                mWinnerName.setText(mGame.seeWinner().name());
            }
        });


    }



    public void playerBet( Player player, Integer chips, TextView betText ) {
        player.placeBet(chips);
        mGame.addBet(player);
        chips = 0;
//        setToCallText();


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
//        if( player.number() == mGame.seeCurrentPlayer() ) {
//            player.call(mGame);
//            mGame.addBet(player);
//            Integer potInt = mGame.showPot();
//            String pot = "Pot: " + " " + potInt.toString();
//            mPotValue.setText(pot);
////            setToCallText();
//        }

        player.call( mGame );
        mGame.addBet(player);
        Integer potInt = mGame.showPot();
        String pot = "Pot: " + potInt.toString();
        mPotValue.setText( pot );

    }

    public void playerEndTurn() {

//        hidePlayers();
//        mGame.endTurn();
//        if( mPOneReady && mJeff.number() == mGame.seeCurrentPlayer() && !mJeff.status() ) {
//            showPlayerOne();
//            checkCheck( mJeff );
//        } else if ( mPTwoReady && mSteve.number() == mGame.seeCurrentPlayer() && !mSteve.status() ) {
//            showPlayerTwo();
//            checkCheck( mSteve );
//        } else if ( mPThreeReady && mDave.number() == mGame.seeCurrentPlayer() && !mDave.status() ) {
//            showPlayerThree();
//            checkCheck( mDave );
//        } else if ( mPFourReady && mBob.number() == mGame.seeCurrentPlayer() && !mBob.status() ) {
//            showPlayerFour();
//            checkCheck( mBob );
//        }
    }

    public void checkCheck( Player player ) {
//
//        if(  mGame.showPot() > 0 && player.number() == mGame.seeCurrentPlayer()
//                && mGame.seeLastBet() <= player.seeLastBet()
//                && mCheck.getVisibility() !=View.VISIBLE ) {
//            mCheck.setVisibility(View.VISIBLE);
//        } else {
//            mCheck.setVisibility(View.INVISIBLE);
//        }
    }

//    public void hidePlayerOne() {
//        hidePlayer(mPlayerOneText, mPlayerOneBet, mPlayerOneCards, mPlayerOneChips);
//    }
//
//    public void hidePlayer( TextView name, TextView bet, TextView cards, TextView playerChips ) {
//        name.setVisibility(View.INVISIBLE);
//        bet.setVisibility(View.INVISIBLE);
//        cards.setVisibility(View.INVISIBLE);
//        playerChips.setVisibility(View.INVISIBLE);
//    }
//    public void showPlayerOne() {
//        showPlayer(mPlayerOneText, mPlayerOneBet, mPlayerOneCards, mJeff, mPlayerOneChips);
//    }
//
//    public void showPlayerTwo() {
//        showPlayer(mPlayerTwoText, mPlayerTwoBet, mPlayerTwoCards, mSteve, mPlayerTwoChips);
//    }

    public void showPlayer(  TextView name, TextView bet, TextView cards, Player player, TextView playerChips ) {
        name.setVisibility(View.VISIBLE);
        bet.setVisibility(View.VISIBLE);
        cards.setVisibility(View.VISIBLE);
        Integer chips = player.countChips();
        String playerChipsDisplay = "Chips: " + " " + chips.toString();
        playerChips.setText( playerChipsDisplay );
        playerChips.setVisibility(View.VISIBLE);
    }
//    public void hidePlayerTwo() {
//        hidePlayer(mPlayerTwoText, mPlayerTwoBet, mPlayerTwoCards, mPlayerTwoChips);
//    }
//
//
//
//    public void hidePlayerThree() {
//        hidePlayer(mPlayerThreeText, mPlayerThreeBet, mPlayerThreeCards, mPlayerThreeChips);
//    }
//
//    public void showPlayerThree() {
//        showPlayer(mPlayerThreeText, mPlayerThreeBet, mPlayerThreeCards, mDave, mPlayerThreeChips);
//    }
//
//    public void hidePlayerFour() {
//        hidePlayer(mPlayerFourText, mPlayerFourBet, mPlayerFourCards, mPlayerFourChips);
//    }
//
//    public void showPlayerFour() {
//        showPlayer(mPlayerFourText, mPlayerFourBet, mPlayerFourCards, mBob, mPlayerFourChips);
//    }

//    public void hidePlayers() {
//        hidePlayerOne();
//        hidePlayerTwo();
//        hidePlayerThree();
//        hidePlayerFour();
//    }

//    public void showPlayers() {
//        if ( mJeff.number() == mGame.seeCurrentPlayer() && !mJeff.status() ) {
//            showPlayerOne();
//        } else if( mSteve.number() == mGame.seeCurrentPlayer() && !mSteve.status() ) {
//            showPlayerTwo();
//        } else if( mDave.number() == mGame.seeCurrentPlayer() && !mDave.status() ) {
//            showPlayerThree();
//        } else if( mBob.number() == mGame.seeCurrentPlayer() && !mBob.status() ) {
//            showPlayerFour();
//        }
//    }

    public void showPlayer() {

    }

    public void skipPlayers() {
        if( mJeff.status() &&  mJeff.number() == mGame.seeCurrentPlayer() ) {
            mGame.endTurn();
        }
        if( mSteve.status() &&  mSteve.number() == mGame.seeCurrentPlayer()) {
            mGame.endTurn();
        }
        if ( mDave.status() &&  mDave.number() == mGame.seeCurrentPlayer() ) {
            mGame.endTurn();
        }
        if( mBob.status() &&  mBob.number() == mGame.seeCurrentPlayer()) {
            mGame.endTurn();
        }
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
        mBetValue = 0;
    }

    public void logicCheck( Game game, Player player ) {
        Logic logic = new Logic( game.seeHand(), player.seeHand() );
        logic.combineCards();
        logic.setScore();
        player.awardScore(logic.seeScore());
        player.awardKicker(logic.seeKicker());
    }

    public void playerFoldMaster() {
        mGame.foldMaster(mJeff);
        mGame.foldMaster(mSteve);
        mGame.foldMaster(mDave);
        mGame.foldMaster(mBob);
    }

    public void setToCallText() {
            Integer lastBet = mGame.seeLastBet();
            String mToCallText = "To Call: " + lastBet.toString();
        mToCall.setText(mToCallText);
    }


    public void setText() {
        String name = mGame.getCurrentPlayer().name();
        mPlayerName.setText( name );

        String cardOne = mGame.getCurrentPlayer().seeHand().get(0).toString();
        String cardTwo = mGame.getCurrentPlayer().seeHand().get(1).toString();
        String cards = cardOne + " " + cardTwo;
        mPlayerCards.setText( cards );

        Integer potInt = mGame.showPot();
        String pot = potInt.toString();
        mPotValue.setText( pot );

        Integer chipCount = mGame.getCurrentPlayer().countChips();
        String chips = "Chips: " + chipCount.toString();
        mPlayerChips.setText( chips );
    }

    public void bet() {
        int bet = mBetValue;
        mGame.getCurrentPlayer().placeBet( bet);
        mGame.addBet(mGame.getCurrentPlayer() );
        mGame.turnEnd();
    }


}

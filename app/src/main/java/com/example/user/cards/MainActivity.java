package com.example.user.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Switch;

import java.util.ArrayList;

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
    ArrayList< Player > mDefaultPlayers;

    TextView mPlayerBet;

    TextView mPotValue;

    TextView mToCall;

    TextView mPlayerChips;

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

    private static Integer mBetValue;
    private static Integer mCounter;
    private static Integer mCheckCounter;

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

        mDefaultPlayers = new ArrayList<Player>();
        mJeff = new Player("Jeff", 1);
        mJeff.in();

        mSteve = new Player( "Steve", 2 );
        mSteve.in();

        mDave = new Player( "Dave", 3 );
        mDave.in();

        mBob = new Player( "Bob", 4 );
        mBob.in();


        mDefaultPlayers.add( mJeff );
        mDefaultPlayers.add(mSteve);
        mDefaultPlayers.add( mDave);
        mDefaultPlayers.add(mBob );

        cloner();

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
        mCounter = 0;
        mCheckCounter = 0;

        hideEverthing();

        mCards = new TestCards();
//        mCards.shuffle();


        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mGame.accessPlayer(0).takeCard(mCards.deal());
                mGame.accessPlayer(0).takeCard(mCards.deal());

                mGame.accessPlayer(1).takeCard(mCards.deal());
                mGame.accessPlayer(1).takeCard(mCards.deal());

                mGame.accessPlayer(2).takeCard(mCards.deal());
                mGame.accessPlayer(2).takeCard(mCards.deal());

                mGame.accessPlayer(3).takeCard(mCards.deal());
                mGame.accessPlayer(3).takeCard(mCards.deal());


                hideStart();

                showEverything();

                for( int i = 0; i < mGame.getArraySize(); i++ ) {
                    mGame.accessPlayer(i).in();
                }

                mGame.megaCheck( mGame.accessPlayer(0), mGame.accessPlayer(1), mGame.accessPlayer(2), mGame.accessPlayer(3) );

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
                mCounter++;
                checkCheck( mGame.getCurrentPlayer() );
            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bet();
                setText();
                mCounter++;
                checkCheck( mGame.getCurrentPlayer() );
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter = 0;
                mCheckCounter ++;
                resetBets();
                stageCheck();
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

                mGame.pickWinner();
                Player winner = mGame.seeWinner();
                Log.d("tag", winner.name());
                mGame.handWon(winner);
                mWinnerName.setText(mGame.seeWinner().name());

//                nextHand();
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

        if( mGame.showPot() > 0 && mGame.seeLastBet() <= mGame.getCurrentPlayer().seeLastBet()
                && mCounter == mGame.getArraySize() -1 ) {
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
            Log.d( "Player Hand:", mGame.accessPlayer(i).seeHand().toString() );
            Log.d("Game Hand:", mGame.seeHand().toString());


            Logic logic = new Logic( mGame.accessPlayer(i).seeHand(), mGame.seeHand() );
            logic.combineCards();
            logic.setScore();
            mGame.accessPlayer(i).awardScore(logic.seeScore());
            Log.d("Player Hand:", mGame.accessPlayer(i).seeScore().toString());

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
        String pot = "Pot: " + potInt.toString();
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
        TestCards cards = new TestCards();
        cards.shuffle();

        for( int i = 0; i < mGame.getArraySize(); i ++ ) {
            mGame.accessPlayer(i).resetHand();
            mGame.accessPlayer(i).resetLastBet();
            mGame.accessPlayer(i).resetBet();

            mGame.accessPlayer(i).takeCard(cards.deal());
            mGame.accessPlayer(i).takeCard(cards.deal());
        }

        mGame.megaCheck( mGame.accessPlayer(0), mGame.accessPlayer(1), mGame.accessPlayer(2), mGame.accessPlayer(3) );

        mGame.turnEnd();
        mGame.endHand();
        setText();
        Log.d("Ending hand:", "OK");

//        showStart();

    }

    public void hideEverthing() {
        mPlus.setVisibility(View.INVISIBLE);
        mCall.setVisibility(View.INVISIBLE);
        mBet.setVisibility(View.INVISIBLE);
        mPotValue.setVisibility(View.INVISIBLE);
        mCommunityCards.setVisibility(View.INVISIBLE);
        mCheck.setVisibility(View.INVISIBLE);
        mFold.setVisibility(View.INVISIBLE);
        mWinner.setVisibility(View.INVISIBLE);
        mToCall.setVisibility(View.INVISIBLE);
    }

    public void showEverything() {
        mPlus.setVisibility(View.VISIBLE);
        mCall.setVisibility(View.VISIBLE);
        mBet.setVisibility(View.VISIBLE);
        mPotValue.setVisibility(View.VISIBLE);
        mFold.setVisibility((View.VISIBLE));
        mWinner.setVisibility(View.VISIBLE);
        mToCall.setVisibility(View.VISIBLE);
    }

    public void hideStart() {
        mStart.setVisibility(View.INVISIBLE);
    }

    public void showStart() {
        hideEverthing();
        mStart.setVisibility(View.VISIBLE);
    }

    public void cloner() {
        for( int i = 0; i < mDefaultPlayers.size(); i ++ ) {
            mGame.addPlayerToGame( mDefaultPlayers.get(i).clone() );
        }
    }

    public void flop() {
        mGame.takeCard(mCards.deal());
        mGame.takeCard(mCards.deal());
        mGame.takeCard(mCards.deal());
        String mGameCardOne = mGame.seeHand().get(0).toString();
        String mGameCardTwo = mGame.seeHand().get(1).toString();
        String mGameCardThree = mGame.seeHand().get(2).toString();

        String mGameCards = mGameCardOne + " " + mGameCardTwo + " " + mGameCardThree;

        mCommunityCards.setText(mGameCards);
    }

    public void turn() {
        mGame.takeCard(mCards.deal());
        mGame.takeCard(mCards.deal());
        String mGameCardOne = mGame.seeHand().get(0).toString();
        String mGameCardTwo = mGame.seeHand().get(1).toString();
        String mGameCardThree = mGame.seeHand().get(2).toString();
        String mGameCardFour = mGame.seeHand().get(3).toString();

        String mGameCards = mGameCardOne + " " + mGameCardTwo + " " + mGameCardThree + " " + mGameCardFour;

        mCommunityCards.setText(mGameCards);
    }

    public void river() {
        mGame.takeCard(mCards.deal());
        String mGameCardOne = mGame.seeHand().get(0).toString();
        String mGameCardTwo = mGame.seeHand().get(1).toString();
        String mGameCardThree = mGame.seeHand().get(2).toString();
        String mGameCardFour = mGame.seeHand().get(3).toString();
        String mGameCardFive = mGame.seeHand().get(4).toString();


        String mGameCards = mGameCardOne + " " + mGameCardTwo + " " + mGameCardThree + " " + mGameCardFour + " " + mGameCardFive;

        mCommunityCards.setText(mGameCards);
    }

    public void stageCheck() {
        if (mCheckCounter == 1) {
            flop();
//        } else if ( mCheckCounter ==  2 ) {
//            turn();
//        } else if( mCheckCounter == 3 ) {
//            river();
        }
    }


}

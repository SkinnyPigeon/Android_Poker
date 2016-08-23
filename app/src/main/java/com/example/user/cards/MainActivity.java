package com.example.user.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 19/08/2016.
 */
public class MainActivity extends AppCompatActivity{

    TextView mPlayerName;

    TextView mCommunityCards;
    TextView mFlop;
    TextView mRiver;

    TextView mPlayerCards;

    CheckBox mPlayerCardOne;
    CheckBox mPlayerCardTwo;

    CheckBox mCommunityCardOne;
    CheckBox mCommunityCardTwo;
    CheckBox mCommunityCardThree;
    CheckBox mCommunityCardFour;
    CheckBox mCommunityCardFive;


    ArrayList<String> mPlayerSelectedCards;
    ArrayList<String> mCommunitySelectedCards;

    Button mPlus;
    Button mCall;
    Button mBet;
    Button mStart;
    Button mCheck;
    Button mFold;
    Button mWinner;

    Button mWin;
    TextView mWinnerName;



    TextView mPlayerBet;

    TextView mPotValue;

    TextView mToCall;

    TextView mPlayerChips;

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

        mPlayerCardOne = ( CheckBox )findViewById( R.id.player_card_one );
        mPlayerCardTwo = ( CheckBox )findViewById( R.id.player_card_two );

        mCommunityCardOne = ( CheckBox )findViewById( R.id.community_card_one );
        mCommunityCardTwo = ( CheckBox )findViewById( R.id.community_card_two );
        mCommunityCardThree = ( CheckBox )findViewById( R.id.community_card_three );
        mCommunityCardFour = ( CheckBox )findViewById( R.id.community_card_four );
        mCommunityCardFive = ( CheckBox )findViewById( R.id.community_card_five );

        mWin = ( Button )findViewById( R.id.win );

        mPlayerName = ( TextView )findViewById( R.id.player_name);

        mPlayerBet = (TextView)findViewById( R.id.player_bet );

        mPlayerCards = (TextView)findViewById( R.id.player_cards );

        mPlayerChips = (TextView )findViewById( R.id.player_chips );

        mToCall = ( TextView )findViewById( R.id.to_call );

        mCommunityCards = ( TextView )findViewById( R.id.community_cards );

        mGame = new Game(4);

        mJeff = new Player("Jeff", 1);

        mSteve = new Player( "Steve", 2 );

        mDave = new Player( "Dave", 3 );

        mBob = new Player( "Bob", 4 );

        mGame.addPlayerToGame(mJeff);
        mGame.addPlayerToGame(mSteve);
        mGame.addPlayerToGame(mDave);
        mGame.addPlayerToGame(mBob);

        mPlus = ( Button )findViewById( R.id.plus );
        mCall = ( Button )findViewById( R.id.call );
        mCheck = ( Button )findViewById( R.id.check );
        mFold = ( Button )findViewById( R.id.fold );
        mWinner = ( Button )findViewById( R.id.winner );
        mWinnerName = ( TextView )findViewById( R.id.winner_name );

        mBet = ( Button )findViewById( R.id.bet );
        mPotValue = ( TextView )findViewById( R.id.pot );

        mStart = ( Button )findViewById( R.id.start );

        mPlayerSelectedCards = new ArrayList<String>();
        mCommunitySelectedCards = new ArrayList<String>();


        mBetValue = 0;
        mCounter = 2;
        mCheckCounter = 0;

        hideEverthing();

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startHand();

                hideStart();

                showEverything();

                mGame.firstTurn();
                Integer pot = mGame.showPot();
                String potText = "Pot: " + " " + pot.toString();
                mPotValue.setText(potText);

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
                Log.d("Call check: ", mGame.getCurrentPlayer().name());

            }
        });

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bet();
                setText();
                mCounter++;
                checkCheck(mGame.getCurrentPlayer());
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter = 1;
                mCheckCounter ++;
                resetBets();
                stageCheck();
                mGame.turnEnd();
                mCommunityCards.setVisibility(View.VISIBLE);
                mCheck.setVisibility(View.INVISIBLE);
                setText();
            }
        });

        mFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGame.fold();

                if( mGame.getArraySize() == 1 ) {
                    mGame.handWon(mGame.getCurrentPlayer());
                    nextHand();
                }
                mCounter ++;
                checkCheck(mGame.getCurrentPlayer());
                setText();
            }
        });

        mWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logicCheck();

                mGame.pickWinner();
                Player winner = mGame.seeWinner();
                mGame.handWon(winner);
                mWinnerName.setText(mGame.seeWinner().name());

                nextHand();
            }
        });

        mWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPickLogicCheck();
                if( mPlayerCardOne.isChecked() ) {
                    mPlayerCardOne.toggle();
                }
                if( mPlayerCardTwo.isChecked() ) {
                    mPlayerCardTwo.toggle();
                }
            }

        });
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
                && mCounter >= mGame.getArraySize() ) {
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
            Logic logic = new Logic( mGame.accessPlayer(i).seeHand(), mGame.seeHand() );
            logic.combineCards();
            logic.setScore();
            mGame.accessPlayer(i).awardScore(logic.seeScore());
            mGame.accessPlayer(i).awardKicker(logic.seeKicker());
        }
    }

    public void cardPickLogicCheck() {

        Logic logic = new Logic( mPlayerSelectedCards, mCommunitySelectedCards );
        logic.combineCards();
        logic.setScore();
        mGame.getCurrentPlayer().awardScore(logic.seeScore());
        mGame.getCurrentPlayer().awardKicker(logic.seeKicker());

        removePlayerSelectedCards();
        removeCommunitySelectedCards();
    }

    public void onPlayerSelect(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch ( view.getId() ) {
            case R.id.player_card_one:
                if( checked ) {
                    mPlayerSelectedCards.add(mGame.getCurrentPlayer().seeHand().get(0).toString());
                }

                break;

            case  R.id.player_card_two:
                if( checked) {
                    Log.d("Here:", "Clicked ");
                    mPlayerSelectedCards.add(mGame.getCurrentPlayer().seeHand().get(1).toString()) ;
                }
                break;
        }
    }

    public void onCommunitySelect( View view ) {
        boolean checked = ((CheckBox) view).isChecked();

        switch( view.getId() ) {
            case R.id.community_card_one:
                if( checked && seeFlop() ) {
                    mCommunitySelectedCards.add( mGame.seeHand().get(0).toString() );
                }
                break;
            case R.id.community_card_two:
                if( checked && seeFlop() ) {
                    mCommunitySelectedCards.add(mGame.seeHand().get(1).toString());
                }
                break;
            case R.id.community_card_three:
                if( checked && seeFlop() ) {
                    mCommunitySelectedCards.add( mGame.seeHand().get(2).toString() );
                }
                break;
            case R.id.community_card_four:
                if( checked && seeTurn() ) {
                    mCommunitySelectedCards.add( mGame.seeHand().get(3).toString() );
                }
                break;
            case R.id.community_card_five:
                if( checked && seeRiver() ) {
                    mCommunitySelectedCards.add( mGame.seeHand().get(4).toString() );
                }
                break;
        }
    }

    public boolean seeFlop() {
        if( mGame.seeHand().size() == 3 ) {
            return true;
        }
        return false;
    }

    public boolean seeTurn() {
        if( mGame.seeHand().size() == 4 ) {
            return true;
        }
        return false;
    }

    public boolean seeRiver() {
        if( mGame.seeHand().size() == 5 ) {
            return true;
        }
        return false;
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
        mPlayerBet.setText(betText);

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

        mGame.refillPlayerArray();
        mGame.sortPlayers();
        mGame.resetHand();
        mGame.endHand();
        resetPlayerHands();
        mCommunityCards.setVisibility(View.INVISIBLE);
        startHand();
        mCheckCounter = 0;
        mGame.firstTurn();
        setText();
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

        mPlayerName.setVisibility(View.INVISIBLE);
        mPlayerBet.setVisibility(View.INVISIBLE);
        mPlayerCards.setVisibility(View.INVISIBLE);
        mPlayerChips.setVisibility(View.INVISIBLE);
    }

    public void showEverything() {
        mPlus.setVisibility(View.VISIBLE);
        mCall.setVisibility(View.VISIBLE);
        mBet.setVisibility(View.VISIBLE);
        mPotValue.setVisibility(View.VISIBLE);
        mFold.setVisibility((View.VISIBLE));
        mWinner.setVisibility(View.VISIBLE);

        mPlayerName.setVisibility(View.VISIBLE);
        mPlayerBet.setVisibility(View.VISIBLE);
        mPlayerCards.setVisibility(View.VISIBLE);
        mPlayerChips.setVisibility(View.VISIBLE);
    }

    public void hideStart() {
        mStart.setVisibility(View.INVISIBLE);
    }

    public void showStart() {
        hideEverthing();
        mStart.setVisibility(View.VISIBLE);
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

    public void resetPlayerHands() {
        for( int i = 0; i < mGame.getArraySize(); i++ ) {
            mGame.accessPlayer(i).resetHand();
        }
    }

    public void startHand() {
        mCards = new TestCards();

//        Shuffle the deck here

        for( int i = 0; i < mGame.getArraySize(); i ++ ) {
            mGame.accessPlayer(i).takeCard( mCards.deal() );
            mGame.accessPlayer(i).takeCard(mCards.deal());
        }
    }

    public void stageCheck() {
        if (mCheckCounter == 1) {
            flop();
        } else if ( mCheckCounter ==  2 ) {
            turn();
        } else if( mCheckCounter == 3 ) {
            river();
        }
    }

    public void removePlayerSelectedCards() {

        int size = mPlayerSelectedCards.size();
        if ( size > 0 ) {
            for( int i = 0; i < size; i++ ) {
                mPlayerSelectedCards.remove(0);
            }
        }
    }

    public void removeCommunitySelectedCards() {

        int size = mCommunitySelectedCards.size();
        if ( size > 0 ) {
            for( int i = 0; i < size; i++ ) {
                mCommunitySelectedCards.remove(0);
            }
        }
    }

}

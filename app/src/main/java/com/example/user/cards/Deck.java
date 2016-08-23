package com.example.user.cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 19/08/2016.
 */
public class Deck {
    private String mDealtCard;
    private ArrayList< String > mCards;
    private ArrayList< String > mShuffledCards;

    public Deck() {
        mCards = new ArrayList< String>();
        mCards.add( "A♠" );
        mCards.add( "A♥" );
        mCards.add( "A♣" );
        mCards.add( "A♦" );
        mCards.add( "K♠" );
        mCards.add( "K♥" );
        mCards.add( "K♣" );
        mCards.add( "K♦" );
        mCards.add( "Q♠" );
        mCards.add( "Q♥" );
        mCards.add( "Q♣" );
        mCards.add( "Q♦" );
        mCards.add( "J♠" );
        mCards.add( "J♥" );
        mCards.add( "J♣" );
        mCards.add( "J♦" );
        mCards.add( "10♠" );
        mCards.add( "10♥" );
        mCards.add( "10♣" );
        mCards.add( "10♦" );
        mCards.add( "9♠" );
        mCards.add( "9♥" );
        mCards.add( "9♣" );
        mCards.add( "9♦" );
        mCards.add( "8♠" );
        mCards.add( "8♥" );
        mCards.add( "8♣" );
        mCards.add( "8♦" );
        mCards.add( "7♠" );
        mCards.add( "7♥" );
        mCards.add( "7♣" );
        mCards.add( "7♦" );
        mCards.add( "6♠" );
        mCards.add( "6♥" );
        mCards.add( "6♣" );
        mCards.add( "6♦" );
        mCards.add( "5♠" );
        mCards.add( "5♥" );
        mCards.add( "5♣" );
        mCards.add( "5♦" );
        mCards.add( "4♠" );
        mCards.add( "4♥" );
        mCards.add( "4♣" );
        mCards.add( "4♦" );
        mCards.add( "3♠" );
        mCards.add( "3♥" );
        mCards.add( "3♣" );
        mCards.add( "3♦" );
        mCards.add( "2♠" );
        mCards.add( "2♥" );
        mCards.add( "2♣" );
        mCards.add( "2♦" );
    }

    public int cardLength() {
        return mCards.size();
    }

    public String shuffle() {
        Collections.shuffle( mCards );
        for( String card: mCards ) {
            if( card != null ) {
            }
        }
        return "Shuffled!";
    }

    public void pickCard() {
        mDealtCard = mCards.remove(0);
    }

    public String deal() {
        pickCard();
        return mDealtCard;
    }


}

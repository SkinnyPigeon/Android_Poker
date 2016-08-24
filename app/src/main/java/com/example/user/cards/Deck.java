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
        mCards.add( "A" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "A" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "A" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "A" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "K" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "K" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "K" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "K" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "10" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "10" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "10" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "10" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "9" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "9" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "9" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "9" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "8" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "8" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "8" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "8" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "7" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "7" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "7" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "7" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "6" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "6" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "6" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "6" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "5" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "5" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "5" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "5" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "4" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "4" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "4" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "4" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "3" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "3" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "3" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "3" + getEmijoByUnicode( 0x2666 ) );
        mCards.add( "2" + getEmijoByUnicode( 0x2660 ) );
        mCards.add( "2" + getEmijoByUnicode( 0x2665 ) );
        mCards.add( "2" + getEmijoByUnicode( 0x2663 ) );
        mCards.add( "2" + getEmijoByUnicode( 0x2666 ) );
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

    public String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }


}

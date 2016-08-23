package com.example.user.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Logic {

    private ArrayList< String > mPlayerCards;
    private ArrayList< String > mTableCards;
    private ArrayList< String > mProcessedCards;
    private Integer mAwardScore;
    private Integer mKickerScore;

    public Logic( ArrayList playerCards, ArrayList tableCards ) {
        mPlayerCards = playerCards;
        mTableCards = tableCards;
        mProcessedCards = new ArrayList< String >();
        mAwardScore = 0;
        mKickerScore = 0;
    }

    public Integer seeScore() {
        return mAwardScore;
    }

    public Integer seeKicker() {
        return mKickerScore;
    }

    public void setKicker( Integer points ) {
        mKickerScore += points;
    }

    public void combineCards() {
        mPlayerCards.addAll( mTableCards );
        Collections.sort(mPlayerCards);
    }

    public ArrayList seeHand() {
        return mPlayerCards;
    }

    public void countPoints() {
        char firstCard;
        if( mPlayerCards.size() > 0 ) {
            for( int i = 0; i < mPlayerCards.size(); i++ ) {
                firstCard = mPlayerCards.get( i ).charAt( 0 );

                if( firstCard == 'A' ) {
                    setKicker( 4196 );
                } else if ( firstCard == 'K' ) {
                    setKicker( 2048 );
                } else if ( firstCard == 'Q' ) {
                    setKicker( 1024 );
                } else if ( firstCard == 'J' ) {
                    setKicker( 512 );
                } else if ( firstCard == '1' ) {
                    setKicker( 256 );
                } else if ( firstCard == '9' ) {
                    setKicker( 128 );
                } else if ( firstCard == '8' ) {
                    setKicker( 64 );
                } else if ( firstCard == '7' ) {
                    setKicker( 32 );
                } else if ( firstCard == '6' ) {
                    setKicker( 16 );
                } else if ( firstCard == '5' ) {
                    setKicker( 8 );
                } else if ( firstCard == '4' ) {
                    setKicker( 4 );
                } else if ( firstCard == '3' ) {
                    setKicker( 2 );
                } else if ( firstCard == '2' ) {
                    setKicker( 1 );
                }
            }
        }
    }

    public boolean pair() {
        char firstCard;
        char secondCard;
        for( int i = 0; i < mPlayerCards.size() - 1; i++ ) {
            firstCard = mPlayerCards.get( i ).charAt( 0 );
            secondCard = mPlayerCards.get( i + 1).charAt( 0 );
            if( firstCard == secondCard ) {
                mProcessedCards.add( mPlayerCards.remove( i + 1) );
                mProcessedCards.add( mPlayerCards.remove( i ) );
                countPoints();
                return true;
            }
        }
        return false;
    }

    public boolean pairCheck( char firstNumber ) {
        pair();
        if( mProcessedCards.size() > 1 ) {
            char firstCard = mProcessedCards.get(0).charAt(0);
            if( firstCard == firstNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean twoPair() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        for( int i = 0; i < mPlayerCards.size() - 3; i++ ) {
            firstCard = mPlayerCards.get( i ).charAt( 0 );
            secondCard = mPlayerCards.get( i + 1 ).charAt( 0 );
            thirdCard = mPlayerCards.get( i + 2 ).charAt( 0 );
            fourthCard = mPlayerCards.get( i + 3 ).charAt( 0 );
            if( firstCard == secondCard && thirdCard == fourthCard ) {
                mProcessedCards.add( mPlayerCards.remove( i + 3 ) );
                mProcessedCards.add( mPlayerCards.remove( i + 2 ) );
                mProcessedCards.add( mPlayerCards.remove( i + 1 ) );
                mProcessedCards.add( mPlayerCards.remove( i ) );
                countPoints();
                return true;
            }
        }
        return false;
    }

    public boolean twoPairCheck( char firstNumber ) {
        if( mPlayerCards.size() > 3 ) {
            twoPair();
        }
        if( mProcessedCards.size() > 3) {
            char firstCard = mProcessedCards.get(0).charAt(0);
            char secondCard = mProcessedCards.get(2).charAt(0);
            if( firstCard == firstNumber || secondCard == firstNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean three() {
        char firstCard;
        char secondCard;
        char thirdCard;
        for( int i = 0; i < mPlayerCards.size() - 2; i++ ) {
            firstCard = mPlayerCards.get( i ).charAt( 0 );
            secondCard = mPlayerCards.get( i + 1 ).charAt( 0 );
            thirdCard = mPlayerCards.get( i + 2 ).charAt( 0 );
            if( firstCard == secondCard && firstCard == thirdCard ) {
                mProcessedCards.add( mPlayerCards.remove( i + 2 ) );
                mProcessedCards.add( mPlayerCards.remove( i + 1 ) );
                mProcessedCards.add( mPlayerCards.remove( i ) );
                countPoints();
                return true;
            }
        }
        return false;
    }

    public boolean threeCheck( char firstNumber ) {
        three();
        char firstCard;
        if( mProcessedCards.size() > 2 ) {
            firstCard = mProcessedCards.get(0).charAt(0);
            if( firstCard == firstNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean four() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        if( mPlayerCards.size() > 3 ) {
            for( int i = 0; i < mPlayerCards.size() - 3; i++ ) {
                firstCard = mPlayerCards.get( i ).charAt( 0 );
                secondCard = mPlayerCards.get( i + 1 ).charAt( 0 );
                thirdCard = mPlayerCards.get( i + 2 ).charAt( 0 );
                fourthCard = mPlayerCards.get( i + 3 ).charAt( 0 );
                if( firstCard == secondCard && firstCard == thirdCard
                        && firstCard == fourthCard ) {
                    mProcessedCards.add( mPlayerCards.remove( i + 3 ) );
                    mProcessedCards.add( mPlayerCards.remove( i + 2 ) );
                    mProcessedCards.add( mPlayerCards.remove( i + 1 ) );
                    mProcessedCards.add( mPlayerCards.remove( i ) );
                    countPoints();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fourCheck( char firstNumber ) {
        four();
        if( mProcessedCards.size() > 3 ) {
            char firstCard = mProcessedCards.get( 0 ).charAt( 0 );
            if( firstCard == firstNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean fullHouseTwoFirst() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        char fifthCard;
        if( mPlayerCards.size() > 4 ) {
            firstCard = mPlayerCards.get( 0 ).charAt( 0 );
            secondCard = mPlayerCards.get( 1 ).charAt( 0 );
            thirdCard = mPlayerCards.get( 2 ).charAt( 0 );
            fourthCard = mPlayerCards.get( 3 ).charAt( 0 );
            fifthCard = mPlayerCards.get( 4 ).charAt( 0 );
            if( firstCard == secondCard &&
                    thirdCard == fourthCard && thirdCard == fifthCard ) {
                mProcessedCards.add( mPlayerCards.remove( 4 ) );
                mProcessedCards.add( mPlayerCards.remove( 3 ) );
                mProcessedCards.add( mPlayerCards.remove( 2 ) );
                mProcessedCards.add( mPlayerCards.remove( 1 ) );
                mProcessedCards.add( mPlayerCards.remove( 0 ) );
                return true;
            }
        }
        return false;
    }

    public boolean fullHouseThreeFirst() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        char fifthCard;
        if( mPlayerCards.size() > 4 ) {
            firstCard = mPlayerCards.get( 0 ).charAt( 0 );
            secondCard = mPlayerCards.get( 1 ).charAt( 0 );
            thirdCard = mPlayerCards.get( 2 ).charAt( 0 );
            fourthCard = mPlayerCards.get( 3 ).charAt( 0 );
            fifthCard = mPlayerCards.get( 4 ).charAt( 0 );
            if( firstCard == secondCard && firstCard == thirdCard
                    && fourthCard == fifthCard ) {
                mProcessedCards.add( mPlayerCards.remove( 4 ) );
                mProcessedCards.add( mPlayerCards.remove( 3 ) );
                mProcessedCards.add( mPlayerCards.remove( 2 ) );
                mProcessedCards.add( mPlayerCards.remove( 1 ) );
                mProcessedCards.add(mPlayerCards.remove(0));
                return true;
            }
        }
        return false;
    }

    public boolean fullHouseCheck( char firstNumber, char secondNumber ) {
        fullHouseTwoFirst();
        fullHouseThreeFirst();
        if( mProcessedCards.size() > 4 ) {
            char firstCard = mProcessedCards.get( 0 ).charAt( 0 );
            char secondCard = mProcessedCards.get( 4 ).charAt( 0 );
            if( ( firstCard == firstNumber && secondCard == secondNumber ) ||
                    firstCard == secondNumber && secondCard == firstNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean processedFlush() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        char fifthCard;
        if( mProcessedCards.size() > 4) {
            firstCard = mProcessedCards.get( 0 ).charAt( 1 );
            if( firstCard == '0' ) {
                firstCard = mProcessedCards.get( 0 ).charAt( 2 );
            }

            secondCard = mProcessedCards.get( 1 ).charAt( 1 );
            if( secondCard == '0' ) {
                secondCard = mProcessedCards.get( 1 ).charAt( 2 );
            }

            thirdCard = mProcessedCards.get( 2 ).charAt( 1 );
            if( thirdCard == '0' ) {
                thirdCard = mProcessedCards.get( 2 ).charAt( 2 );
            }

            fourthCard = mProcessedCards.get( 3 ).charAt( 1 );
            if( fourthCard == '0' ) {
                fourthCard = mProcessedCards.get( 3 ).charAt( 2 );
            }

            fifthCard = mProcessedCards.get( 4 ).charAt( 1 );
            if( fifthCard == '0' ) {
                fifthCard = mProcessedCards.get( 4 ).charAt( 2 );
            }

            if( firstCard == secondCard && firstCard == thirdCard
                    && firstCard == fourthCard && firstCard == fifthCard ) {
                countPoints();
                return true;
            }
        }
        return false;
    }

    public boolean flush() {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        char fifthCard;
        if( mPlayerCards.size() > 4) {
            firstCard = mPlayerCards.get( 0 ).charAt( 1 );
            if( firstCard == '0' ) {
                firstCard = mPlayerCards.get( 0 ).charAt( 2 );
            }

            secondCard = mPlayerCards.get( 1 ).charAt( 1 );
            if( secondCard == '0' ) {
                secondCard = mPlayerCards.get( 1 ).charAt( 2 );
            }

            thirdCard = mPlayerCards.get( 2 ).charAt( 1 );
            if( thirdCard == '0' ) {
                thirdCard = mPlayerCards.get( 2 ).charAt( 2 );
            }

            fourthCard = mPlayerCards.get( 3 ).charAt( 1 );
            if( fourthCard == '0' ) {
                fourthCard = mPlayerCards.get( 3 ).charAt( 2 );
            }

            fifthCard = mPlayerCards.get( 4 ).charAt( 1 );
            if( fifthCard == '0' ) {
                fifthCard = mPlayerCards.get( 4 ).charAt( 2 );
            }

            if( firstCard == secondCard && firstCard == thirdCard
                    && firstCard == fourthCard && firstCard == fifthCard ) {
                countPoints();
                return true;
            }
        }
        return false;
    }

    public boolean straightCheck( char firstNumber, char secondNumber, char thirdNumber, char fourthNumber, char fifthNumber ) {
        char firstCard;
        char secondCard;
        char thirdCard;
        char fourthCard;
        char fifthCard;
        if( mPlayerCards.size() > 4 ) {
            firstCard = mPlayerCards.get( 0 ).charAt( 0 );
            secondCard = mPlayerCards.get( 1 ).charAt( 0 );
            thirdCard = mPlayerCards.get( 2 ).charAt( 0 );
            fourthCard = mPlayerCards.get( 3 ).charAt( 0 );
            fifthCard = mPlayerCards.get( 4 ).charAt( 0 );

            if( firstCard == firstNumber && secondCard == secondNumber && thirdCard == thirdNumber && fourthCard == fourthNumber && fifthCard == fifthNumber ) {
                return true;
            }
        }
        return false;
    }

    public boolean straightFlushCheck( char firstNumber, char secondNumber, char thirdNumber, char fourthNumber, char fifthNumber ) {
        if( straightCheck(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber) && processedFlush() ) {
            return true;
        }
        return false;
    }

    public void straightFlushPoints() {
        if( straightFlushCheck('1', 'A', 'J', 'K', 'Q') ) {
            mAwardScore = 1000;
        } else if( straightFlushCheck('1', '9', 'J', 'K', 'Q') ) {
            mAwardScore = 990;
        } else if( straightFlushCheck('1', '8', '9', 'J', 'Q') ) {
            mAwardScore = 980;
        } else if( straightFlushCheck('1', '7', '8', '9', 'J') ) {
            mAwardScore = 970;
        } else if( straightFlushCheck('1', '6', '7', '8', '9') ) {
            mAwardScore = 960;
        } else if( straightFlushCheck('5', '6', '7', '8', '9') ) {
            mAwardScore = 950;
        } else if( straightFlushCheck('4', '5', '6', '7', '8') ) {
            mAwardScore = 940;
        } else if( straightFlushCheck('3', '4', '5', '6', '7') ) {
            mAwardScore = 930;
        } else if( straightFlushCheck('2', '3', '4', '5', '6') ) {
            mAwardScore = 920;
        } else if( straightFlushCheck( '2', '3', '4', '5', 'A' ) ) {
            mAwardScore = 910;
        }
    }

    public void fourPoints() {
        if ( fourCheck('A') ) {
            mAwardScore = 813;
        } else if ( fourCheck('K') ) {
            mAwardScore = 812;
        } else if ( fourCheck('Q') ) {
            mAwardScore = 811;
        } else if ( fourCheck('J') ) {
            mAwardScore = 810;
        } else if ( fourCheck('1') ) {
            mAwardScore = 809;
        } else if ( fourCheck('9') ) {
            mAwardScore = 808;
        } else if ( fourCheck('8') ) {
            mAwardScore = 807;
        } else if ( fourCheck('7') ) {
            mAwardScore = 806;
        } else if ( fourCheck('6') ) {
            mAwardScore = 805;
        } else if ( fourCheck('5') ) {
            mAwardScore = 804;
        } else if ( fourCheck('4') ) {
            mAwardScore = 803;
        } else if ( fourCheck('3') ) {
            mAwardScore = 802;
        } else if ( fourCheck( '2' ) ) {
            mAwardScore = 801;
        }
    }

    public void fullPointsAce() {
        if ( fullHouseCheck('A', 'K') ) {
            mAwardScore = 799;
        } else if ( fullHouseCheck('A', 'Q') ) {
            mAwardScore = 798;
        } else if ( fullHouseCheck('A', 'J') ) {
            mAwardScore = 797;
        } else if ( fullHouseCheck('A', '1') ) {
            mAwardScore = 796;
        } else if ( fullHouseCheck('A', '9') ) {
            mAwardScore = 795;
        } else if ( fullHouseCheck('A', '8') ) {
            mAwardScore = 794;
        } else if ( fullHouseCheck('A', '7') ) {
            mAwardScore = 793;
        } else if ( fullHouseCheck('A', '6') ) {
            mAwardScore = 792;
        } else if ( fullHouseCheck('A', '5') ) {
            mAwardScore = 791;
        } else if ( fullHouseCheck('A', '4') ) {
            mAwardScore = 790;
        } else if ( fullHouseCheck('A', '3') ) {
            mAwardScore = 789;
        } else if ( fullHouseCheck('A', '2') ) {
            mAwardScore = 788;
        }
    }

    public void fullPointsKing() {
        if ( fullHouseCheck('K', 'Q') ) {
            mAwardScore = 787;
        } else if ( fullHouseCheck('K', 'J') ) {
            mAwardScore = 786;
        } else if ( fullHouseCheck('K', '1') ) {
            mAwardScore = 785;
        } else if ( fullHouseCheck('K', '9') ) {
            mAwardScore = 784;
        } else if ( fullHouseCheck('K', '8') ) {
            mAwardScore = 783;
        } else if ( fullHouseCheck('K', '7') ) {
            mAwardScore = 782;
        } else if ( fullHouseCheck('K', '6') ) {
            mAwardScore = 781;
        } else if ( fullHouseCheck('K', '5') ) {
            mAwardScore = 780;
        } else if ( fullHouseCheck('K', '4') ) {
            mAwardScore = 779;
        } else if ( fullHouseCheck('K', '3') ) {
            mAwardScore = 778;
        } else if ( fullHouseCheck('K', '2') ) {
            mAwardScore = 777;
        }
    }

    public void fullPointsQueen() {
        if ( fullHouseCheck('Q', 'J') ) {
            mAwardScore = 776;
        } else if ( fullHouseCheck('Q', '1') ) {
            mAwardScore = 775;
        } else if ( fullHouseCheck('Q', '9') ) {
            mAwardScore = 774;
        } else if ( fullHouseCheck('Q', '8') ) {
            mAwardScore = 773;
        } else if ( fullHouseCheck('Q', '7') ) {
            mAwardScore = 772;
        } else if ( fullHouseCheck('Q', '6') ) {
            mAwardScore = 771;
        } else if ( fullHouseCheck('Q', '5') ) {
            mAwardScore = 770;
        } else if ( fullHouseCheck('Q', '4') ) {
            mAwardScore = 769;
        } else if ( fullHouseCheck('Q', '3') ) {
            mAwardScore = 768;
        } else if ( fullHouseCheck('Q', '2') ) {
            mAwardScore = 767;
        }
    }

    public void fullPointsJack(){
        if ( fullHouseCheck('J', '1') ) {
            mAwardScore = 766;
        } else if ( fullHouseCheck('J', '9') ) {
            mAwardScore = 765;
        } else if ( fullHouseCheck('J', '8') ) {
            mAwardScore = 764;
        } else if ( fullHouseCheck('J', '7') ) {
            mAwardScore = 763;
        } else if ( fullHouseCheck('J', '6') ) {
            mAwardScore = 762;
        } else if ( fullHouseCheck('J', '5') ) {
            mAwardScore = 761;
        } else if ( fullHouseCheck('J', '4') ) {
            mAwardScore = 760;
        } else if ( fullHouseCheck('J', '3') ) {
            mAwardScore = 759;
        } else if ( fullHouseCheck('J', '2') ) {
            mAwardScore = 758;
        }
    }

    public void fullPointsTen() {
        if ( fullHouseCheck('1', '9') ) {
            mAwardScore = 757;
        } else if ( fullHouseCheck('1', '8') ) {
            mAwardScore = 756;
        } else if ( fullHouseCheck('1', '7') ) {
            mAwardScore = 755;
        } else if ( fullHouseCheck('1', '6') ) {
            mAwardScore = 754;
        } else if ( fullHouseCheck('1', '5') ) {
            mAwardScore = 753;
        } else if ( fullHouseCheck('1', '4') ) {
            mAwardScore = 752;
        } else if ( fullHouseCheck('1', '3') ) {
            mAwardScore = 751;
        } else if ( fullHouseCheck('1', '2') ) {
            mAwardScore = 750;
        }
    }

    public void fullPointsNine() {
        if ( fullHouseCheck('9', '8') ) {
            mAwardScore = 748;
        } else if ( fullHouseCheck('9', '7') ) {
            mAwardScore = 747;
        } else if ( fullHouseCheck('9', '6') ) {
            mAwardScore = 746;
        } else if ( fullHouseCheck('9', '5') ) {
            mAwardScore = 745;
        } else if ( fullHouseCheck('9', '4') ) {
            mAwardScore = 744;
        } else if ( fullHouseCheck('9', '3') ) {
            mAwardScore = 743;
        } else if ( fullHouseCheck('9', '2') ) {
            mAwardScore = 742;
        }
    }

    public void fullPointsEight() {
        if ( fullHouseCheck('8', '7') ) {
            mAwardScore = 741;
        } else if ( fullHouseCheck('8', '6') ) {
            mAwardScore = 739;
        } else if ( fullHouseCheck('8', '5') ) {
            mAwardScore = 738;
        } else if ( fullHouseCheck('8', '4') ) {
            mAwardScore = 737;
        } else if ( fullHouseCheck('8', '3') ) {
            mAwardScore = 736;
        } else if ( fullHouseCheck('8', '2') ) {
            mAwardScore = 735;
        }
    }

    public void fullPointsSeven() {
        if ( fullHouseCheck('7', '6') ) {
            mAwardScore = 734;
        } else if ( fullHouseCheck('7', '5') ) {
            mAwardScore = 733;
        } else if ( fullHouseCheck('7', '4') ) {
            mAwardScore = 732;
        } else if ( fullHouseCheck('7', '3') ) {
            mAwardScore = 731;
        } else if ( fullHouseCheck('7', '2') ) {
            mAwardScore = 730;
        }
    }

    public void fullPointsSix(){
        if ( fullHouseCheck('6', '5') ) {
            mAwardScore = 729;
        } else if ( fullHouseCheck('6', '4') ) {
            mAwardScore = 728;
        } else if ( fullHouseCheck('6', '3') ) {
            mAwardScore = 727;
        } else if ( fullHouseCheck('6', '2') ) {
            mAwardScore = 726;
        }
    }

    public void fullPointsFive(){
        if ( fullHouseCheck('5', '4') ) {
            mAwardScore = 725;
        } else if ( fullHouseCheck('5', '3') ) {
            mAwardScore = 724;
        } else if ( fullHouseCheck('5', '2') ) {
            mAwardScore = 723;
        }
    }


    public void fullPointsFour(){
        if ( fullHouseCheck('4', '3') ) {
            mAwardScore = 722;
        } else if ( fullHouseCheck('4', '2') ) {
            mAwardScore = 721;
        }
    }

    public void fullPointsThree(){
        if ( fullHouseCheck('3', '2') ) {
            mAwardScore = 720;
        }
    }

    public void flushPoints() {
        if ( flush() ) {
            mAwardScore = 600;
        }
    }

    public void straightPoints() {
        if ( straightCheck('1', 'A', 'J', 'K', 'Q') ) {
            mAwardScore = 590;
        } else if ( straightCheck('1', '9', 'J', 'K', 'Q') ) {
            mAwardScore = 580;
        } else if ( straightCheck('1', '8', '9', 'J', 'Q') ) {
            mAwardScore = 570;
        } else if ( straightCheck('1', '7', '8', '9', 'J') ) {
            mAwardScore = 560;
        } else if ( straightCheck('1', '6', '7', '8', '9') ) {
            mAwardScore = 550;
        } else if ( straightCheck('5', '6', '7', '8', '9') ) {
            mAwardScore = 540;
        } else if ( straightCheck('4', '5', '6', '7', '8') ) {
            mAwardScore = 530;
        } else if ( straightCheck('3', '4', '5', '6', '7') ) {
            mAwardScore = 520;
        } else if ( straightCheck('2', '3', '4', '5', '6') ) {
            mAwardScore = 510;
        } else if ( straightCheck( '2', '3', '4', '5', 'A' ) ) {
            mAwardScore = 500;
        }
    }

    public void threePoints() {
        if (threeCheck('A')) {
            mAwardScore = 512;
        } else if (threeCheck('K')) {
            mAwardScore = 511;
        } else if (threeCheck('Q')) {
            mAwardScore = 510;
        } else if (threeCheck('J')) {
            mAwardScore = 509;
        } else if (threeCheck('1')) {
            mAwardScore = 508;
        } else if (threeCheck('9')) {
            mAwardScore = 507;
        } else if (threeCheck('8')) {
            mAwardScore = 506;
        } else if (threeCheck('7')) {
            mAwardScore = 505;
        } else if (threeCheck('6')) {
            mAwardScore = 504;
        } else if (threeCheck('5')) {
            mAwardScore = 503;
        } else if (threeCheck('4')) {
            mAwardScore = 502;
        } else if (threeCheck('3')) {
            mAwardScore = 501;
        } else if (threeCheck('2')) {
            mAwardScore = 500;
        }
    }

    public void twoPointsAce() {
        if ( twoPairCheck( 'A') && twoPairCheck( 'K') ) {
            mAwardScore = 449;
        } else if ( twoPairCheck( 'A') && twoPairCheck( 'Q') ) {
            mAwardScore = 448;
        } else if ( twoPairCheck( 'A') && twoPairCheck( 'J') ) {
            mAwardScore = 447;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '1') ) {
            mAwardScore = 446;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '9') ) {
            mAwardScore = 445;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '8') ) {
            mAwardScore = 444;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '7') ) {
            mAwardScore = 443;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '6') ) {
            mAwardScore = 442;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '5') ) {
            mAwardScore = 441;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '4') ) {
            mAwardScore = 440;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '3') ) {
            mAwardScore = 439;
        } else if ( twoPairCheck( 'A') && twoPairCheck( '2') ) {
            mAwardScore = 438;
        }
    }

    public void twoPointsKing(){
        if ( twoPairCheck( 'K') && twoPairCheck( 'Q') ) {
            mAwardScore = 437;
        } else if ( twoPairCheck( 'K') && twoPairCheck( 'J') ) {
            mAwardScore = 436;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '1') ) {
            mAwardScore = 435;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '9') ) {
            mAwardScore = 434;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '8') ) {
            mAwardScore = 433;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '7') ) {
            mAwardScore = 432;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '6') ) {
            mAwardScore = 431;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '5') ) {
            mAwardScore = 430;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '4') ) {
            mAwardScore = 429;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '3') ) {
            mAwardScore = 428;
        } else if ( twoPairCheck( 'K') && twoPairCheck( '2') ) {
            mAwardScore = 427;
        }
    }

    public void twoPointsQueen() {
        if ( twoPairCheck( 'Q') && twoPairCheck( 'J') ) {
            mAwardScore = 426;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '1') ) {
            mAwardScore = 425;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '9') ) {
            mAwardScore = 424;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '8') ) {
            mAwardScore = 423;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '7') ) {
            mAwardScore = 422;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '6') ) {
            mAwardScore = 421;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '5') ) {
            mAwardScore = 420;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '4') ) {
            mAwardScore = 419;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '3') ) {
            mAwardScore = 418;
        } else if ( twoPairCheck( 'Q') && twoPairCheck( '2') ) {
            mAwardScore = 417;
        }
    }


    public void twoPointsJack() {
        if ( twoPairCheck( 'J') && twoPairCheck( '1') ) {
            mAwardScore = 416;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '9') ) {
            mAwardScore = 415;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '8') ) {
            mAwardScore = 414;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '7') ) {
            mAwardScore = 413;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '6') ) {
            mAwardScore = 412;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '5') ) {
            mAwardScore = 411;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '4') ) {
            mAwardScore = 410;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '3') ) {
            mAwardScore = 409;
        } else if ( twoPairCheck( 'J') && twoPairCheck( '2') ) {
            mAwardScore = 408;
        }
    }

    public void twoPointsTen() {
        if ( twoPairCheck( '1') && twoPairCheck( '9') ) {
            mAwardScore = 407;
        } else if ( twoPairCheck( '1') && twoPairCheck( '8') ) {
            mAwardScore = 406;
        } else if ( twoPairCheck( '1') && twoPairCheck( '7') ) {
            mAwardScore = 405;
        } else if ( twoPairCheck( '1') && twoPairCheck( '6') ) {
            mAwardScore = 404;
        } else if ( twoPairCheck( '1') && twoPairCheck( '5') ) {
            mAwardScore = 403;
        } else if ( twoPairCheck( '1') && twoPairCheck( '4') ) {
            mAwardScore = 402;
        } else if ( twoPairCheck( '1') && twoPairCheck( '3') ) {
            mAwardScore = 401;
        } else if ( twoPairCheck( '1') && twoPairCheck( '2') ) {
            mAwardScore = 400;
        }
    }

    public void twoPointsNine() {
        if ( twoPairCheck( '9') && twoPairCheck( '8') ) {
            mAwardScore = 399;
        } else if ( twoPairCheck( '9') && twoPairCheck( '7') ) {
            mAwardScore = 398;
        } else if ( twoPairCheck( '9') && twoPairCheck( '6') ) {
            mAwardScore = 397;
        } else if ( twoPairCheck( '9') && twoPairCheck( '5') ) {
            mAwardScore = 396;
        } else if ( twoPairCheck( '9') && twoPairCheck( '4') ) {
            mAwardScore = 395;
        } else if ( twoPairCheck( '9') && twoPairCheck( '3') ) {
            mAwardScore = 394;
        } else if ( twoPairCheck( '9') && twoPairCheck( '2') ) {
            mAwardScore = 393;
        }
    }

    public void twoPointsEight(){
        if ( twoPairCheck( '8') && twoPairCheck( '7') ) {
            mAwardScore = 392;
        } else if ( twoPairCheck( '8') && twoPairCheck( '6') ) {
            mAwardScore = 391;
        } else if ( twoPairCheck( '8') && twoPairCheck( '5') ) {
            mAwardScore = 390;
        } else if ( twoPairCheck( '8') && twoPairCheck( '4') ) {
            mAwardScore = 389;
        } else if ( twoPairCheck( '8') && twoPairCheck( '3') ) {
            mAwardScore = 388;
        } else if ( twoPairCheck( '8') && twoPairCheck( '2') ) {
            mAwardScore = 387;
        }
    }

    public void twoPointsSeven() {
        if ( twoPairCheck( '7') && twoPairCheck( '6') ) {
            mAwardScore = 386;
        } else if ( twoPairCheck( '7') && twoPairCheck( '5') ) {
            mAwardScore = 385;
        } else if ( twoPairCheck( '7') && twoPairCheck( '4') ) {
            mAwardScore = 384;
        } else if ( twoPairCheck( '7') && twoPairCheck( '3') ) {
            mAwardScore = 383;
        } else if ( twoPairCheck( '7') && twoPairCheck( '2') ) {
            mAwardScore = 382;
        }
    }

    public void twoPointsSix() {
        if ( twoPairCheck( '6') && twoPairCheck( '5') ) {
            mAwardScore = 381;
        } else if ( twoPairCheck( '6') && twoPairCheck( '4') ) {
            mAwardScore = 380;
        } else if ( twoPairCheck( '6') && twoPairCheck( '3') ) {
            mAwardScore = 379;
        } else if ( twoPairCheck( '6') && twoPairCheck( '2') ) {
            mAwardScore = 378;
        }
    }

    public void twoPointsFive() {
        if ( twoPairCheck( '5') && twoPairCheck( '4') ) {
            mAwardScore = 377;
        } else if ( twoPairCheck( '5') && twoPairCheck( '3') ) {
            mAwardScore = 376;
        } else if ( twoPairCheck( '5') && twoPairCheck( '2') ) {
            mAwardScore = 375;
        }
    }

    public void twoPointsFour() {
        if ( twoPairCheck( '4') && twoPairCheck( '3') ) {
            mAwardScore = 374;
        } else if ( twoPairCheck( '4') && twoPairCheck( '2') ) {
            mAwardScore = 373;}
    }

    public void twoPointsThree() {
        if ( twoPairCheck( '3') && twoPairCheck( '2') ) {
            mAwardScore = 372;
        }

    }

    public void pairPoints() {
        if ( pairCheck( 'A' ) ) {
            mAwardScore = 212;
        } else if ( pairCheck( 'K' ) ) {
            mAwardScore = 211;
        } else if ( pairCheck( 'Q' ) ) {
            mAwardScore = 210;
        } else if (pairCheck( 'J' ) ) {
            mAwardScore = 209;
        } else if ( pairCheck( '1' ) ) {
            mAwardScore = 208;
        } else if ( pairCheck('9' ) ) {
            mAwardScore = 207;
        } else if ( pairCheck( '8' ) ) {
            mAwardScore = 206;
        } else if ( pairCheck( '7' ) ) {
            mAwardScore = 205;
        } else if ( pairCheck( '6' ) ) {
            mAwardScore = 204;
        } else if (pairCheck( '5' ) ) {
            mAwardScore = 203;
        } else if ( pairCheck( '4' ) ) {
            mAwardScore = 202;
        } else if ( pairCheck( '3' ) ) {
            mAwardScore = 201;
        } else if ( pairCheck( '2' ) ) {
            mAwardScore = 200;
        }
    }

    public void setScore() {

        straightFlushPoints();
        fourPoints();
        fullPointsAce();
        fullPointsKing();
        fullPointsQueen();
        fullPointsJack();
        fullPointsTen();
        fullPointsNine();
        fullPointsEight();
        fullPointsSeven();
        fullPointsSix();
        fullPointsFive();
        fullPointsFour();
        fullPointsThree();
        flushPoints();
        straightPoints();
        threePoints();
        twoPointsAce();
        twoPointsKing();
        twoPointsQueen();
        twoPointsJack();
        twoPointsTen();
        twoPointsNine();
        twoPointsEight();
        twoPointsSeven();
        twoPointsSix();
        twoPointsFive();
        twoPointsFour();
        twoPointsThree();
        pairPoints();
        countPoints();
    }
}













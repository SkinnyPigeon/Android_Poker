public class Betting {

  private int mPlayerStart;
  private int mNoPlayers;
  private int mCurrentPlayer;
  private int mNoFolded;
  private int mPot;
  private int mLastBet;

  public Betting( int noPlayers ) {

    mPot = 0;
    mPlayerStart = 1;
    mCurrentPlayer = 1;
    mNoPlayers = noPlayers;
    mNoFolded = 0;
    mLastBet = 0;
  }

  public int seePot() {
    return mPot;
  }

  public void getBet( Player player ) {
    mPot += player.seeBet();
    mLastBet = player.seeBet();
  }

  public void resetBets() {
    mLastBet = 0;
  }

  public int seeLastBet() {
    return mLastBet;
  }

  public int seePlayerStart() {
    return mPlayerStart;
  }

  public int seeCurrentPlayer() {
    return mCurrentPlayer;
  }

  public int seeNoPlayers() {
    return mNoPlayers;
  }

  public int seeFolded() {
    return mNoFolded;
  }

  public void setCurrentPlayer( Player player ) {
    mCurrentPlayer = player.seePlayerNo();
  }



  public void endTurn() {
    if( mCurrentPlayer == mNoPlayers ) {
      mCurrentPlayer = 1;
    } else {
      mCurrentPlayer += 1;
    }
  }

  public void endHand() {
    if( mPlayerStart == mNoPlayers ) {
      mPlayerStart = 1;
    } else {
      mPlayerStart += 1;
    }
  }

  public void passOverFoldedPlayer() {
    if( mPlayerStart == mNoPlayers ) {
      mPlayerStart = 1;
    } else {
      mPlayerStart += 1;
    }
  }

  public void setFold( Player one, Player two, Player three, Player four ) {
    if( one.seeFolded() && ( one.seePlayerNo() == mPlayerStart ) ) {
      passOverFoldedPlayer();
    } 
    if( two.seeFolded() && ( two.seePlayerNo() == mPlayerStart ) ) {
      passOverFoldedPlayer();
    }
    if( three.seeFolded() && ( three.seePlayerNo() == mPlayerStart  )) {
      passOverFoldedPlayer();
    }
    if( four.seeFolded() && ( four.seePlayerNo() == mPlayerStart ) ) {
      passOverFoldedPlayer();
    } 
  }

  public void firstTurn( Player one, Player two, Player three, Player four ) {
    if( one.seePlayerNo() == mPlayerStart ) {
      one.smallBlind();
      getBet( one );
      endTurn();
      two.bigBlind();
      getBet( two );
      two.bigBlindBoolSet();
      endTurn();
      three.setFirstBet();
      setCurrentPlayer( three );
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      three.bigBlind();
      getBet( three );
      endTurn();
      four.setFirstBet();
      setCurrentPlayer( four );
    } else if ( three.seePlayerNo() == mPlayerStart ) {
      three.smallBlind();
      getBet( three );
      endTurn();
      four.bigBlind();
      getBet( four );
      endTurn();
      one.setFirstBet();
      setCurrentPlayer( one );
    } else {
      four.smallBlind();
      getBet( four );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
      setCurrentPlayer( two );
    }
  }

  public void firstTurn( Player one, Player two, Player three ) {
    if( one.seePlayerNo() == mPlayerStart ) {
      one.smallBlind();
      getBet( one );
      endTurn();
      two.bigBlind();
      getBet( two );
      endTurn();
      three.setFirstBet();
      setCurrentPlayer( three );
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      three.bigBlind();
      getBet( three );
      endTurn();
      one.setFirstBet();
      setCurrentPlayer( one );
    } else {
      three.smallBlind();
      getBet( three );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
      setCurrentPlayer( two );
    } 
  }

  public void firstTurn(Player one, Player two) {
    if( one.seePlayerNo() == mPlayerStart ) {
      one.smallBlind();
      getBet( one );
      endTurn();
      two.bigBlind();
      getBet( two );
      endTurn();
      one.setFirstBet();
      setCurrentPlayer( one );
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
      setCurrentPlayer( two );
    } 
  }

  public void megaCheck( Player one, Player two, Player three, Player four ) {
      setFold( one, two, three, four );  
      if( one.seeFolded() && two.seeFolded() ) {
        firstTurn( three, four );
      } else if( one.seeFolded() && three.seeFolded() ) {
        firstTurn( two, four );
      } else if( one.seeFolded() && four.seeFolded() ) {
        firstTurn( two, three );
      } else if( two.seeFolded() && three.seeFolded() ) {
        firstTurn( one, four );
      } else if( two.seeFolded() && four.seeFolded() ) {
        firstTurn( one, three );
      } else if( three.seeFolded() && four.seeFolded() ) {
        firstTurn( one, two );
      } else if ( one.seeFolded() ) {
        firstTurn( two, three, four );
      } else if ( two.seeFolded() ) {
        firstTurn( one, three, four );
      } else if ( three.seeFolded() ) {
        firstTurn( one, two, four );
      } else if ( four.seeFolded() ) {
        firstTurn( one, two, three );
      } else {
        firstTurn( one, two, three, four );
      }
  }

  public Player turnCheck( Player player ) {
    if( player.seePlayerNo() != seeCurrentPlayer() ) {
      return null;
    } else {
      return player;
    }
  }

  public void foldMaster( Player one ) {
    foldCheck( one );
    foldWin( one );
  }

  public void setFoldedPlayerCount() {
    mNoFolded += 1;
  }

  public void foldWin( Player player ) {
    if( mNoFolded == ( mNoPlayers - 1 )) {
      if( player.seeFolded() == false ) {
        player.winChips( mPot );
      }
    }
  }

  public void foldCheck( Player player ) {
    if( player.seeFolded() == true ) {
      setFoldedPlayerCount();
      endTurn();
    }
  }

}













public class Betting {

  private int mPlayerStart;
  private int mNoPlayers;
  private int mCurrentPlayer;
  private int mNoFolded;
  private int mPot;

  public Betting( int noPlayers ) {

    mPot = 0;
    mPlayerStart = 1;
    mCurrentPlayer = 1;
    mNoPlayers = noPlayers;
    mNoFolded = 0;
  }

  public int seePot() {
    return mPot;
  }

  public void getBet( Player player ) {
    mPot += player.seeBet();
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

  public void foldCheck( Player player ) {
    if( player.seeFolded() == true ) {
      endTurn();
    }
  }

  public void endTurn() {
    if( mCurrentPlayer == mNoPlayers ) {
      mCurrentPlayer = 1;
    } else {
      mCurrentPlayer += 1;
    }
  }

  public void endHand() {
    if( mCurrentPlayer == mNoPlayers ) {
      mPlayerStart = 1;
    } else {
      mPlayerStart += 1;
    }
  }

  public void passOverFoldedPlayer() {
    if( mCurrentPlayer == mNoPlayers ) {
      mPlayerStart = 1;
    } else {
      mPlayerStart += 1;
    }
  }

  public void setFold( Player one, Player two, Player three, Player four ) {
    if( one.seeFolded() && one.seePlayerNo() == mPlayerStart ) {
      passOverFoldedPlayer();
    } else if( two.seeFolded() && two.seePlayerNo() == mPlayerStart ) {
      passOverFoldedPlayer();
    } else if( three.seeFolded() && three.seePlayerNo() == mPlayerStart ) {
      passOverFoldedPlayer();
    } else if( four.seeFolded() && four.seePlayerNo() == mPlayerStart ) {
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
      endTurn();
      three.setFirstBet();
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      three.bigBlind();
      getBet( three );
      endTurn();
      four.setFirstBet();
    } else if ( three.seePlayerNo() == mPlayerStart ) {
      three.smallBlind();
      getBet( three );
      endTurn();
      four.bigBlind();
      getBet( four );
      endTurn();
      one.setFirstBet();
    } else {
      four.smallBlind();
      getBet( four );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
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
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      three.bigBlind();
      getBet( three );
      endTurn();
      one.setFirstBet();
    } else {
      three.smallBlind();
      getBet( three );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
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
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      getBet( two );
      endTurn();
      one.bigBlind();
      getBet( one );
      endTurn();
      two.setFirstBet();
    } 
  }

  public void megaCheck( Player one, Player two, Player three, Player four ) {

    if( one.seeFolded() && two.seeFolded() && three.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      
    } else if( one.seeFolded() && two.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );

    } else if( one.seeFolded() && three.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );

    } else if( two.seeFolded() && three.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );

    } else if( one.seeFolded() && two.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( three, four );

    } else if( one.seeFolded() && three.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( two, four ); 

    } else if( one.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( two, three );

    } else if( two.seeFolded() && three.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, four );

    } else if( two.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, three );

    } else if( three.seeFolded() && four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, two );

    } else if( one.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( two, three, four );

    } else if( two.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, three, four );

    } else if( three.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, two, four );

    } else if( four.seeFolded() ) {
      setFold( one, two, three, four );  
      foldMaster( one, two, three, four );
      firstTurn( one, two, three );

    } else {
      firstTurn( one, two, three, four );
      foldMaster( one, two, three, four );

    }
  }

  public void foldMaster( Player one, Player two, Player three, Player four ) {
    foldCheck( one );
    foldWin( one );

    foldCheck( two );
    foldWin( two );

    foldCheck( three );
    foldWin( three );

    foldCheck( four );
    foldWin( four );
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

}













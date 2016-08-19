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

  public void firstTurn(Player one, Player two, Player three) {
    if( one.seePlayerNo() == mPlayerStart ) {
      one.smallBlind();
      two.bigBlind();
      three.setFirstBet();
    } else if ( two.seePlayerNo() == mPlayerStart ) {
      two.smallBlind();
      three.bigBlind();
      one.setFirstBet();
    } else {
      three.smallBlind();
      one.bigBlind();
      two.setFirstBet();
    }
  }




}













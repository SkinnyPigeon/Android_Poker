public class Player {

  private int mChips;
  private int mBet;
  private int mLastBet;
  private int mSmallBlindValue;
  private int mBigBlindValue;
  private int mPlayerNumber;
  private boolean mFolded;
  private boolean mFirstToBet;

  public Player( int playerNumber ) {
    mPlayerNumber = playerNumber;
    mChips = 500;
    mBet = 0;
    mSmallBlindValue = 10;
    mBigBlindValue = 20;
    mFolded = false;
    mFirstToBet = false;
    mLastBet = 0;
  }

  public int seePlayerNo() {
    return mPlayerNumber;
  }

  public void setBet( int chips ) {
    mBet = chips;
    mLastBet = chips;
  }

  public int seeBet() {
    return mBet;
  }

  public void smallBlind() {
    mBet += mSmallBlindValue;
    mLastBet = mSmallBlindValue;
  }

  public void bigBlind() {
    mBet += mBigBlindValue;
    mLastBet = mBigBlindValue;
  }

  public void fold() {
    mFolded = true;
  }

  public boolean seeFolded() {
    return mFolded;
  }

  public void setFirstBet() {
    mFirstToBet = true;
  }

  public boolean seeFirstBet() {
    return mFirstToBet;
  }

  public int countChips() {
    return mChips;
  }

  public void winChips( int chips ) {
    mChips += chips;
  }

  public void call( Betting bet ) {
    int chips = bet.seeLastBet() - mLastBet;
    mChips -= chips;
    mBet = chips;
    mLastBet = chips;
    resetLastBet();
  }

  public void resetLastBet() {
    mLastBet = 0;
  }

}















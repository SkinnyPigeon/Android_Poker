import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class BettingTest {
  Betting bets;
  Player one;
  Player two;
  Player three;

  @Before
  public void before() {
    bets = new Betting(3);
    one = new Player(1);
    two = new Player(2);
    three = new Player(3);
  }

  @Test
  public void noPlayers() {
    assertEquals( 3, bets.seeNoPlayers() );
  }

  @Test
  public void playerChanged() {
    bets.endTurn();
    assertEquals( 2, bets.seeCurrentPlayer() );
  }

  @Test
  public void playersWrap() {
    bets.endTurn();
    bets.endTurn();
    bets.endTurn();
    assertEquals( 1, bets.seeCurrentPlayer() );
  }

  @Test
  public void firstBetIsSet() {
    bets.firstTurn( one, two, three );
    assertEquals( true, three.seeFirstBet() );
  }

  @Test
  public void firstBetWrapsRound() {
    bets.endHand();
    bets.firstTurn( one, two, three );
    assertEquals( true, one.seeFirstBet() );
  }


}
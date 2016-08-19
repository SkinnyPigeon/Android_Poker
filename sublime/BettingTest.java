import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class BettingTest {
  Betting bets;

  @Before
  public void before() {
    bets = new Betting(3);
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

  
}
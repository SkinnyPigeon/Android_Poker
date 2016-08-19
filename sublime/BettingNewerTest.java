import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class BettingNewerTest {
  Betting bets;
  Player one;
  Player two;
  Player three;
  Player four;

  @Before
  public void before() {
    bets = new Betting(3);
    one = new Player(1);
    two = new Player(2);
    three = new Player(3);
    four = new Player(4);
  }

  @Test
  public void canSeeFile() {
    assertEquals( false, one.seeFolded() );
  }



}
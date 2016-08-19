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

  @Test
  public void firstRunOfMegaCheck() {
    one.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( true, four.seeFirstBet() );
  }


  @Test
  public void secondRunOfMegaCheck() {
    one.fold();
    bets.megaCheck( one, two, three, four );
    two.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( true, three.seeFirstBet() );
  }

  @Test
  public void thirdRunOfMegaCheck() {
    one.fold();
    bets.megaCheck( one, two, three, four );
    three.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( true, two.seeFirstBet() );
  }

  @Test
  public void fourthRunOfMegaCheck() {
    bets.endHand();
    two.fold();
    four.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( true, three.seeFirstBet() );
  }

  @Test
  public void fifthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    assertEquals( true, three.seeFirstBet() );
  }

  @Test
  public void sixthRunOfMegaCheck() {
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    assertEquals( true, four.seeFirstBet() );
  }

  @Test
  public void seventhRunOfMegaCheck() {
    one.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( 2, bets.seeCurrentPlayer() );
  }

  @Test
  public void eigthRunOfMegaCheck() {
    one.fold();
    two.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( 3, bets.seeCurrentPlayer() );
  }



}
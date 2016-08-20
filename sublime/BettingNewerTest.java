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
    bets = new Betting(4);
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
    bets.megaCheck( one, two, three, four );
    one.fold();
    bets.foldMaster( one );
    two.fold();
    bets.foldMaster( two );
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
    bets.megaCheck( one, two, three, four );
    assertEquals( 3, bets.seeCurrentPlayer() );
  }

  @Test
  public void eigthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    bets.foldMaster( one );
    one.fold();
    bets.foldMaster( one );
    assertEquals( 4, bets.seeCurrentPlayer() );
  }

  @Test
  public void ninthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    one.fold();
    bets.foldMaster( one );
    two.fold();
    bets.foldMaster( two );
    three.setBet( 50 );
    bets.getBet( three );
    three.fold();
    bets.foldMaster( three );
    bets.foldMaster( four );
    assertEquals( 580, four.countChips() );
  }

  @Test
  public void tenthRunOfMegaCheck() {
    one.fold();
    bets.foldMaster( one );
    two.fold();
    bets.foldMaster( two );
    bets.megaCheck( one, two, three, four );
    assertEquals( 3, bets.seeCurrentPlayer() );
  }

  @Test
  public void eleventhRunOfMegaCheck() {
    two.fold();
    bets.foldMaster( two );
    three.fold();
    bets.foldMaster( three );
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    assertEquals( 4, bets.seeCurrentPlayer() );
  }



}
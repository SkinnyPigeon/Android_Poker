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
    assertEquals( true, three.seeFirstBet() );
  }

  @Test
  public void thirdRunOfMegaCheck() {
    one.fold();
    three.fold();
    bets.endHand();
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
    bets.endHand();
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    assertEquals( 1, bets.seeCurrentPlayer() );
    // assertEquals( true, one.seeFirstBet() );
  }

  @Test
  public void sixthRunOfMegaCheck() {
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    assertEquals( 4, bets.seeCurrentPlayer() );
  }

  @Test
  public void seventhRunOfMegaCheck() {
    four.fold();
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    assertEquals( 1, bets.seeCurrentPlayer() );
  }

  @Test
  public void eigthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    bets.foldMaster( one );
    one.fold();
    bets.foldMaster( one );
    assertEquals( 3, bets.seeCurrentPlayer() );
  }

  @Test
  public void ninthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    bets.endTurn();
    bets.endTurn();
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
    two.fold();
    bets.megaCheck( one, two, three, four );
    assertEquals( 3, bets.seeCurrentPlayer() );
  }

  @Test
  public void eleventhRunOfMegaCheck() {
    two.fold();
    three.fold();
    bets.endHand();
    bets.megaCheck( one, two, three, four );
    bets.endTurn();
    bets.endTurn();
    assertEquals( 4, bets.seePlayerStart() );
  }

  @Test
  public void twelvthRunOfMegaCheck() {

    bets.megaCheck( one, two, three, four );
    four.fold();

    bets.endTurn();
    bets.foldMaster( one );
    bets.foldMaster( two );
    bets.foldMaster( three );
    bets.foldMaster( four );
    assertEquals( 1, bets.seeCurrentPlayer() );
  }

  @Test
  public void thirteenthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    four.fold();
    bets.endTurn();
    bets.foldMaster( four );
    assertEquals( 1, bets.seeCurrentPlayer() );
  }

  @Test
  public void fourteenthRunOfMegaCheck() {
    bets.megaCheck( one, two, three, four );
    four.fold();
    bets.endTurn();
    bets.foldMaster( one );
    bets.foldMaster( two );
    bets.foldMaster( three );
    bets.foldMaster( four );
    bets.endTurn();
    bets.foldMaster( one );
    bets.foldMaster( two );
    bets.foldMaster( three );
    bets.foldMaster( four );
    bets.endTurn();
    bets.foldMaster( one );
    bets.foldMaster( two );
    bets.foldMaster( three );
    bets.foldMaster( four );
    bets.endTurn();
    bets.foldMaster( one );
    bets.foldMaster( two );
    bets.foldMaster( three );
    bets.foldMaster( four );
    assertEquals( 1, bets.seeCurrentPlayer() );
  }

  @Test
  public void checkPlayerReturner(){
    bets.megaCheck( one, two, three, four );
    assertEquals( three, bets.turnCheck( three ) );
  }

  @Test
  public void checkPot() {
    bets.megaCheck( one, two, three, four );
    assertEquals( 30, bets.seePot() );
  }

  @Test
  public void checkCheck() {
    one.setBet( 50 );
    bets.getBet( one );
    bets.endTurn();
    two.call( bets );
    bets.getBet( two );
    assertEquals( 100, bets.seePot() );
  }

  @Test
  public void checkCheckAgain() {
    one.setBet( 10 );
    bets.getBet( one );
    bets.endTurn();
    two.setBet( 20 );
    bets.getBet( two );
    bets.endTurn();
    bets.endTurn();
    bets.endTurn();
    one.call( bets );
    bets.getBet( one );
    assertEquals( 40, bets.seePot() );
  }

  @Test
  public void checkTest() {
    bets.megaCheck( one, two, three, four );
    three.call( bets );
    bets.getBet( three );
    bets.endTurn();
    four.call( bets );
    bets.getBet( four );
    bets.endTurn();
    one.call( bets );
    bets.getBet( one );
    assertEquals( 80, bets.seePot() );
  }

  @Test
  public void testBoolChange() {
    bets.megaCheck( one, two, three, four );
    assertEquals( true, two.seeBigBool() );
  }

  @Test
  public void testBoolChange2() {
    bets.megaCheck( one, two, three, four );
    assertEquals( false, three.seeBigBool() );
  }

  @Test
  public void testBoolChange3() {
    bets.megaCheck( one, two, three, four );
    two.call( bets );
    assertEquals( 0, two.seeLastBet() );
  }

  @Test
  public void testBoolChange4() {
    bets.megaCheck( one, two, three, four );
    two.call( bets );
    assertEquals( 0, bets.seeLastBet() );
  }

  @Test
  public void checkTest2() {
    bets.megaCheck( one, two, three, four );

    three.call( bets );
    bets.getBet( three );
    bets.endTurn();

    four.call( bets );
    bets.getBet( four );
    bets.endTurn();

    one.call( bets );
    bets.getBet( one );
    bets.endTurn();

    two.call( bets );
    bets.getBet( two );

    assertEquals( 80, bets.seePot() );
  }

  @Test
  public void checkTest3() {
    bets.megaCheck( one, two, three, four );

    three.call( bets );
    bets.getBet( three );
    bets.endTurn();

    four.call( bets );
    bets.getBet( four );
    bets.endTurn();

    one.call( bets );
    bets.getBet( one );
    bets.endTurn();

    two.call( bets );
    bets.getBet( two );
    bets.endTurn();

    three.call( bets );
    bets.getBet( three );
    bets.endTurn();

    four.setBet( 50 );
    bets.getBet( four );
    bets.endTurn();

    one.call( bets );
    bets.getBet( one );
    bets.endTurn();

    assertEquals( 180, bets.seePot() );
  }



}
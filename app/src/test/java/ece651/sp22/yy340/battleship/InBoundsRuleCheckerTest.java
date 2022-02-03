package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_InBoundRuleChecker() {
    V1ShipFactory v1shipfactory = new V1ShipFactory();
    InBoundsRuleChecker<Character> inboundsrulechecker = new InBoundsRuleChecker<Character>(null);
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 20);

    Placement v1_1 = new Placement(new Coordinate(2, 3), 'V');
    Ship<Character> dst1 = v1shipfactory.makeDestroyer(v1_1);
    assertEquals(null, inboundsrulechecker.checkMyRule(dst1, b));

    Placement v1_2 = new Placement(new Coordinate(50, 100), 'V');
    Ship<Character> dst2 = v1shipfactory.makeDestroyer(v1_2);
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        inboundsrulechecker.checkMyRule(dst2, b));

    Placement v1_3 = new Placement(new Coordinate(-50, -100), 'V');
    Ship<Character> dst3 = v1shipfactory.makeDestroyer(v1_3);
    assertEquals("That placement is invalid: the ship goes off the top of the board.", inboundsrulechecker.checkMyRule(dst3, b));

  }

}

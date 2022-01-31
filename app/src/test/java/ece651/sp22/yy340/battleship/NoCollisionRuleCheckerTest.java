package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_NoCollisionRuleChecker() {
    V1ShipFactory v1shipfactory = new V1ShipFactory();
    NoCollisionRuleChecker<Character> nocollisionrulechecker = new NoCollisionRuleChecker<>(null);
    BattleShipBoard<Character> b = new BattleShipBoard<>(10, 20, nocollisionrulechecker);

    Ship<Character> s1 = v1shipfactory.makeDestroyer(new Placement("B2V"));
    b.tryAddShip(s1);

    Ship<Character> s2 = v1shipfactory.makeDestroyer(new Placement("C3V"));
    assertTrue(nocollisionrulechecker.checkPlacement(s2, b));

    Ship<Character> s3 = v1shipfactory.makeDestroyer(new Placement("B2H"));
    assertFalse(nocollisionrulechecker.checkPlacement(s3, b));
  }

  @Test
  public void test_NoCollisionRule_multichecker() {
    V1ShipFactory v1shipfactory = new V1ShipFactory();
    NoCollisionRuleChecker<Character> multi_checker = new NoCollisionRuleChecker<>(new InBoundsRuleChecker<>(null));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(10, 10, multi_checker);
    Ship<Character> s1 = v1shipfactory.makeDestroyer(new Placement("A4H"));
    b1.tryAddShip(s1);
    Ship<Character> s2 = v1shipfactory.makeBattleship(new Placement("A7V"));
    assertEquals(true, multi_checker.checkPlacement(s2, b1));

    Ship<Character> s3 = v1shipfactory.makeDestroyer(new Placement("A5H"));
    assertEquals(false, multi_checker.checkPlacement(s3, b1));

  }

}

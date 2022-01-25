package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_NoCollisionRuleChecker() {
    V1ShipFactory v1shipfactory = new V1ShipFactory();
    NoCollisionRuleChecker<Character> inboundsrulechecker = new NoCollisionRuleChecker<Character>(null);
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(10, 20);

    Placement v1_1 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> dst1 = v1shipfactory.makeDestroyer(v1_1);
    b.tryAddShip(dst1);
    
    Placement v1_2 = new Placement(new Coordinate(2, 3), 'V');
    Ship<Character> dst2 = v1shipfactory.makeDestroyer(v1_2);
    assertTrue(inboundsrulechecker.checkMyRule(dst2,b));
    
    Placement v1_3 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> dst3 = v1shipfactory.makeDestroyer(v1_3);
    assertFalse(inboundsrulechecker.checkMyRule(dst3,b));

  }

}

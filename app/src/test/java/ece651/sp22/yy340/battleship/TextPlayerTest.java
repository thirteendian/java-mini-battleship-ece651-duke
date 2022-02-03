package ece651.sp22.yy340.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  private TextPlayer createTextPlayer(int w, int h, String inputData, ByteArrayOutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  

  @Test
  void test_read_placement() throws IOException {

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4V\n", bytes);
    String prompt = "Please enter a location for a ship:";

    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(expected[i], p);
      // assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt
      // and a new line
      bytes.reset(); // clear out bytes for next time around
    }
  }

  
  @Test
  void test_do_one_placement() throws IOException {

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3,4,"B1V\n",bytes);
    Board<Character> b = new BattleShipBoard<Character>(3, 4);
    String expected = "  0|1|2\n" +
      "A  | |  A\n" +
      "B  |d|  B\n" +
      "C  |d|  C\n" +
      "D  |d|  D\n" +
      "  0|1|2\n";
    String expected_p = "Player "+ player.name+" where would you like to place a Destroyer?\n" + expected;
    V1ShipFactory shipfactory = new V1ShipFactory();
    player.doOnePlacement("Destroyer", (p)->shipfactory.makeDestroyer(p));
    assertEquals(expected_p, bytes.toString());
  }

}

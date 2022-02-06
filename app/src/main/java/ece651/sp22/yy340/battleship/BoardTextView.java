package ece651.sp22.yy340.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board (i.e., converting it to a
 * string to show to the user). It supports two ways to display the Board: one
 * for the player's own board, and one for the enemy's board.
 */

public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.
   */
  public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  /**
   * This makes the Board context, e.g., A | A\n
   *
   * @return the String that is the Test board for the given board
   */

  String makeBoard(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder();
    char alphabetic = 'A';
    for (int row = 0; row < toDisplay.getHeight(); row++) {
      ans.append((char) (alphabetic + row));
      ans.append("  ");
      /*
       * if (toDisplay.whatIsAt(new Coordinate(row, 0)) == null) { ans.append(' '); }
       * else { ans.append(toDisplay.whatIsAt(new Coordinate(row, 0))); }
       */

      for (int column = 1; column < toDisplay.getWidth(); column++) {
        // Note that Column start from 1
        ans.append("|"); // Two space -> one space
        if (getSquareFn.apply(new Coordinate(row, column)) == null) {
          ans.append(' ');
        } else {
          ans.append(getSquareFn.apply(new Coordinate(row, column)));
        }
      }
      ans.append(" ");// two space -> one space
      ans.append((char) (alphabetic + row));
      ans.append('\n');
    }
    return ans.toString();
  }

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder();
    ans.append(makeHeader());
    ans.append(makeBoard(getSquareFn));
    ans.append(makeHeader());
    return ans.toString(); // this is a placeholder for the moment
  }

  public String displayMyOwnBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
  }

  public String displayEnemyBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
  }

  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {

    int head1_loc = 5;
    int board2_loc = 2 * toDisplay.getWidth() + 19;
    int head2_loc = 2 * toDisplay.getWidth() + 22;
    String head1_space = " ".repeat(head1_loc);
    String board2_space = " ".repeat(board2_loc);
    String head2_space = " ".repeat(head2_loc);

    StringBuilder twoview = new StringBuilder();
    // append header
    twoview.append(head1_space + myHeader + head2_space + enemyHeader + '\n');
    // append board 
    String[] myboard = displayMyOwnBoard().split("\n");
    String[] enemyboard = enemyView.displayEnemyBoard().split("\n");
    /*
     * Note that for line in myboard/enemyboard is splited by \n
     * thus for new combined board, each line should be two merged
     * with space as : myboard+board2_space+head2_space
     */
    twoview.append(myboard[0]+board2_space+"  "+enemyboard[0]+'\n');
    for(int i =1; i<myboard.length-1; i++){
      twoview.append(myboard[i]+board2_space+enemyboard[i]+'\n');
    }
        twoview.append(myboard[myboard.length-1]+board2_space+"  "+enemyboard[myboard.length-1]+'\n');
    return twoview.toString();
  }

}

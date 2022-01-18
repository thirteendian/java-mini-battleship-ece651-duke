package ece651.sp22.yy340.battleship;

public class Coordinate {
  private final int row;
  private final int column;

  public Coordinate(int row, int col) {
    this.row = row;
    this.column = col;
  }

  ///////////////////////////////////////////
  public Coordinate(String descr) {
    // Length Check
    if (descr.length() != 2) {
      throw new IllegalArgumentException("Wrong Coordinate Format!");
    }

    // Number Check
    if (!(Character.isDigit(descr.charAt(1)))) {
      throw new IllegalArgumentException("Second Input must be Number !");
    }
    // letter Check

    if (descr.charAt(0) >= 'a' && descr.charAt(0) <= 'z') {
      this.row = descr.charAt(0) - 'a';
    } else if (descr.charAt(0) >= 'A' && descr.charAt(0) <= 'Z') {
      this.row = descr.charAt(0) - 'A';
    } else {
      throw new IllegalArgumentException("Wrong Input Format !");
    }
    this.column = descr.charAt(1) - '0';
  }
  /////////////////////////////////////

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}

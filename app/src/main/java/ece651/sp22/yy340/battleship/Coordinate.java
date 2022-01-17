package ece651.sp22.yy340.battleship;

public class Coordinate {
  private final int row;
  private final int column;

  public Coordinate(int row, int col) {
    this.row = row;
    this.column = col;
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

  public Coordinate(String descr)
    
  

}

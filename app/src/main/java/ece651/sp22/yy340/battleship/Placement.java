package ece651.sp22.yy340.battleship;

public class Placement {
  private final Coordinate where;
  private final char orientation;

  /**
   * Constructor(where, orientation)
   * 
   * @param where specifies the location and orientation of the ship to make
   */
  public Placement(Coordinate where, char orientation) {
    if (orientation != 'V' && orientation != 'H' && orientation != 'v' && orientation != 'h' && orientation != 'U'
        && orientation != 'u' && orientation != 'R' && orientation != 'r' && orientation != 'D' && orientation != 'd'
        && orientation != 'L' && orientation != 'l') {
      throw new IllegalArgumentException("Wrong format !");
    }
    this.where = where;
    this.orientation = Character.toUpperCase(orientation);
  }

  /**
   * Constructor("A0V")
   * 
   * @param descr is the input from player denotes the location and orientation of
   *              the ship to make
   */
  public Placement(String descr) {
    if (descr.length() != 3) {
      throw new IllegalArgumentException("Wrong format !");
    }
    char posi = descr.charAt(2);
    if (posi != 'V' && posi != 'v' && posi != 'H' && posi != 'h' && posi != 'U' && posi != 'u' && posi != 'R'
        && posi != 'r' && posi != 'D' && posi != 'd' && posi != 'L' && posi != 'l') {
      throw new IllegalArgumentException("Wrong format !");
    }
    descr = descr.toUpperCase();
    this.where = new Coordinate(descr.substring(0, 2));
    this.orientation = descr.charAt(2);
  }

  /*
   * Two Getter function
   */
  public char getOrientation() {
    return orientation;
  }

  public Coordinate getWhere() {
    return where;
  }

  /*
   * Overwrite toString(), equals() and hasCode()
   */
  @Override
  public String toString() {
    return "AT " + this.where.toString() + " TO " + this.orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement c = (Placement) o;
      return getWhere().equals(c.getWhere()) && getOrientation() == c.getOrientation();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

}

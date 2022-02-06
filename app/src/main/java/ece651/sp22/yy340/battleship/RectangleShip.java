package ece651.sp22.yy340.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {

  final String name;

  public String getName() {
    return this.name;
  }

  /*
   * This method should generate the set of coordinates for a rectangle starting
   * at upperLeft whose width and height are as specified. E.g. if upperLeft =
   * (row=1,col=2) width = 1 height = 3
   */
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> set_of_Coordinates = new HashSet<>();
    for (int row = upperLeft.getRow(); row < upperLeft.getRow() + height; row++) {
      for (int column = upperLeft.getColumn(); column < upperLeft.getColumn() + width; column++) {
        Coordinate coordinate_c = new Coordinate(row, column);
        set_of_Coordinates.add(coordinate_c);
      }
    }
    return set_of_Coordinates;
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
  }

}

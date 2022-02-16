package ece651.sp22.yy340.battleship;

import java.util.HashSet;

public class ZshapeShip<T> extends BasicShip<T> {
  final String name;

  public String getName() {
    return this.name;
  }

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, char orientation) {
    HashSet<Coordinate> set_of_Coordinates = new HashSet<>();
    if (orientation == 'U') {
      Coordinate coordinate_a = new Coordinate(upperLeft.getRow(), upperLeft.getColumn());
      Coordinate coordinate_b = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn());
      Coordinate coordinate_c = new Coordinate(upperLeft.getRow() + 2, upperLeft.getColumn());
      Coordinate coordinate_d = new Coordinate(upperLeft.getRow() + 3, upperLeft.getColumn());
      Coordinate coordinate_e = new Coordinate(upperLeft.getRow() + 2, upperLeft.getColumn() + 1);
      Coordinate coordinate_f = new Coordinate(upperLeft.getRow() + 3, upperLeft.getColumn() + 1);
      Coordinate coordinate_g = new Coordinate(upperLeft.getRow() + 4, upperLeft.getColumn() + 1);
      set_of_Coordinates.add(coordinate_a);
      set_of_Coordinates.add(coordinate_b);
      set_of_Coordinates.add(coordinate_c);
      set_of_Coordinates.add(coordinate_d);
      set_of_Coordinates.add(coordinate_e);
      set_of_Coordinates.add(coordinate_f);
      set_of_Coordinates.add(coordinate_g);
    } else if (orientation == 'R') {
      Coordinate coordinate_a = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 1);
      Coordinate coordinate_b = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 2);
      Coordinate coordinate_c = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 3);
      Coordinate coordinate_d = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 4);
      Coordinate coordinate_e = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn());
      Coordinate coordinate_f = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 1);
      Coordinate coordinate_g = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 2);
      set_of_Coordinates.add(coordinate_a);
      set_of_Coordinates.add(coordinate_b);
      set_of_Coordinates.add(coordinate_c);
      set_of_Coordinates.add(coordinate_d);
      set_of_Coordinates.add(coordinate_e);
      set_of_Coordinates.add(coordinate_f);
      set_of_Coordinates.add(coordinate_g);
    } else if (orientation == 'D') {
      Coordinate coordinate_a = new Coordinate(upperLeft.getRow(), upperLeft.getColumn());
      Coordinate coordinate_b = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn());
      Coordinate coordinate_c = new Coordinate(upperLeft.getRow() + 2, upperLeft.getColumn());
      Coordinate coordinate_d = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 1);
      Coordinate coordinate_e = new Coordinate(upperLeft.getRow() + 2, upperLeft.getColumn() + 1);
      Coordinate coordinate_f = new Coordinate(upperLeft.getRow() + 3, upperLeft.getColumn() + 1);
      Coordinate coordinate_g = new Coordinate(upperLeft.getRow() + 4, upperLeft.getColumn() + 1);
      set_of_Coordinates.add(coordinate_a);
      set_of_Coordinates.add(coordinate_b);
      set_of_Coordinates.add(coordinate_c);
      set_of_Coordinates.add(coordinate_d);
      set_of_Coordinates.add(coordinate_e);
      set_of_Coordinates.add(coordinate_f);
      set_of_Coordinates.add(coordinate_g);
    } else if (orientation == 'L') {
      Coordinate coordinate_a = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 2);
      Coordinate coordinate_b = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 3);
      Coordinate coordinate_c = new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 4);
      Coordinate coordinate_d = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn());
      Coordinate coordinate_e = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 1);
      Coordinate coordinate_f = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 2);
      Coordinate coordinate_g = new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 3);
      set_of_Coordinates.add(coordinate_a);
      set_of_Coordinates.add(coordinate_b);
      set_of_Coordinates.add(coordinate_c);
      set_of_Coordinates.add(coordinate_d);
      set_of_Coordinates.add(coordinate_e);
      set_of_Coordinates.add(coordinate_f);
      set_of_Coordinates.add(coordinate_g);
    }

    return set_of_Coordinates;
  }

  /**
   * Constructor Attention that since parent class have my DisplayInfo and
   * enemyDisplayInfo, we need to initialise them as well
   */
  public ZshapeShip(String name, Coordinate upperLeft, char orientation, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, orientation), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  public ZshapeShip(String name, Coordinate upperLeft, char orientation, T data, T onHit) {
    this(name, upperLeft, orientation, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

}

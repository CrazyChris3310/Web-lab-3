package com.example.JSFLab;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.example.JSFLab.PointDataTest.Direction.DOWN;
import static com.example.JSFLab.PointDataTest.Direction.LEFT;
import static com.example.JSFLab.PointDataTest.Direction.RIGHT;
import static com.example.JSFLab.PointDataTest.Direction.UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointDataTest {

  private static final double EPS = 0.01;

  enum Direction {
    UP, DOWN, LEFT, RIGHT;

    private double[] transformCords(double x, double y) {
      switch (this) {
        case UP: return new double[] { x, y + EPS };
        case DOWN: return new double[] { x, y - EPS };
        case LEFT: return new double[] { x - EPS, y };
        case RIGHT: return new double[] { x + EPS, y };
        default: return new double[] { x, y };
      }
    }
  }

  private String checkPoint(double x, double y, double r) {
    PointData pd = new PointData();
    pd.setR(r);
    pd.setX(x);
    pd.setY(y);
    return pd.calculateHit().getMatch();
  }

  private boolean checkCorner(double x, double y, double r, Direction ... positives) {
    for (Direction dir : Direction.values()) {
      double[] newCords = dir.transformCords(x, y);
      String match = checkPoint(newCords[0], newCords[1], r);
      if (Arrays.asList(positives).contains(dir)) {
        if (!match.equals("Да")) {
          return false;
        }
      } else {
        if (!match.equals("Нет")) {
          return false;
        }
      }
    }
    return checkPoint(x, y, r).equals("Да");
  }

  @Test
  void calculateHitTrianglePositive() {
    assertEquals("Да", checkPoint(-2, 0.5, 4));
    assertEquals("Да", checkPoint(-0.3, 1, 3));
  }

  @Test
  void calculateHitTriangleCornerCases() {
    assertTrue(checkCorner(0, 4, 4, DOWN));
    assertTrue(checkCorner(-3, 0, 3, RIGHT));
    assertTrue(checkCorner(-1, -1 + 2, 2, RIGHT, DOWN));
    assertTrue(checkCorner(0, 3, 4, LEFT, DOWN, UP));
    assertTrue(checkCorner(-2, 0, 3, LEFT, DOWN, UP, RIGHT));
    assertTrue(checkCorner(0, 0.8, 4, LEFT, DOWN, UP, RIGHT));
  }

  @Test
  void calculateTriangleHitNegative() {
    assertEquals("Нет", checkPoint(-4, 4, 4));
    assertEquals("Нет", checkPoint(-1, 3.5, 3.5));
  }

  @Test
  void calculateCircleHitPositive() {
    assertEquals("Да", checkPoint(-2, -2, 4));
    assertEquals("Да", checkPoint(-1.2, -0.1, 2.5));
  }

  @Test
  void calculateCircleHitCornerCase() {
    assertTrue(checkCorner(-4, 0, 4, RIGHT));
    assertTrue(checkCorner(0, -2.4, 2.4, UP));
    assertTrue(checkCorner(-2, -Math.sqrt(4*4 - 2*2), 4, RIGHT, UP));
    assertTrue(checkCorner(0, -2, 2.4, LEFT, UP, DOWN));
  }

  @Test
  void calculateCircleNegative() {
    assertEquals("Нет", checkPoint(-2, -5, 4));
    assertEquals("Нет", checkPoint(-1.1, -4, 2.1));
  }

  @Test
  void calculateRectangleHitPositive() {
    assertEquals("Да", checkPoint(2, 1, 4));
    assertEquals("Да", checkPoint(2.45, 1.23, 3.1));
  }

  @Test
  void calculateRectangleHitCornerCase() {
    assertTrue(checkCorner(2, 2, 4, LEFT, DOWN, RIGHT));
    assertTrue(checkCorner(4, 2, 4, LEFT, DOWN));
    assertTrue(checkCorner(4, 1, 4, LEFT, DOWN, UP));
    assertTrue(checkCorner(1.34, 0, 1.34, UP, LEFT));
    assertTrue(checkCorner(1, 0, 3, LEFT, RIGHT, UP));
    assertTrue(checkCorner(0, 0, 2.12, LEFT, UP, DOWN, RIGHT));
    assertTrue(checkCorner(0, 2.3/2, 2.3, LEFT, UP, RIGHT, DOWN));
  }

  @Test
  void calculateRectangleNegative() {
    assertEquals("Нет", checkPoint(5, 1.3, 2));
    assertEquals("Нет", checkPoint(1.34, 3.23, 3.23));
  }

  @Test
  void calculateFreeAreaNegative() {
    assertEquals("Нет", checkPoint(2, -1, 4));
  }   

}
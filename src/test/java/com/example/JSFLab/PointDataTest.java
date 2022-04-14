package com.example.JSFLab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDataTest {

  private String checkPoint(double x, double y, double r) {
    PointData pd = new PointData();
    pd.setR(r);
    pd.setX(x);
    pd.setY(y);
    return pd.calculateHit().getMatch();
  }

  @Test
  void calculateHitTrianglePositive() {
    assertEquals("Да", checkPoint(-2, 0.5, 4));
    assertEquals("Да", checkPoint(-0.3, 1, 3));
  }

  @Test
  void calculateHitTriangleCornerCases() {
    assertEquals("Да", checkPoint(0, 4, 4));
    assertEquals("Да",  checkPoint(-3, 0, 3));
    assertEquals("Да", checkPoint(1, -1 + 2, 2));
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
    assertEquals("Да", checkPoint(-4, 0, 4));
    assertEquals("Да", checkPoint(0, -2.4, 2.4));
    assertEquals("Да", checkPoint(-2, -Math.sqrt(4*4 - 2*2), 4));
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
    assertEquals("Да", checkPoint(4, 2, 4));
    assertEquals("Да", checkPoint(1.34, 0, 1.34));
    assertEquals("Да", checkPoint(0, 0, 2.12));
    assertEquals("Да", checkPoint(0, 2.3/2, 2.3));
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
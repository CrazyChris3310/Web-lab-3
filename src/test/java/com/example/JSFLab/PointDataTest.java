package com.example.JSFLab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDataTest {

  @Test
  void calculateHitCornerCases() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(0.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(4.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(4.0);
    pd.setY(2.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(-4.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(0.0);
    pd.setY(-4.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateHitPositive() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-2.0);
    pd.setY(-1.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(-1.75);
    pd.setY(2.2);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setX(2.11);
    pd.setY(0.34);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateHitNegative() {
    PointData pd = new PointData();
    pd.setR(4.0);

    pd.setX(4.5);
    pd.setY(0.5);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setX(-1.75);
    pd.setY(4.0);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setX(2.11);
    pd.setY(5.3);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setX(-2.11);
    pd.setY(-4.5);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setX(1.3);
    pd.setY(-2.2);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());
  }

}
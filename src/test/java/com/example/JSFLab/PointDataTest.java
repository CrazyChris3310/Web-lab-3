package com.example.JSFLab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDataTest {

  @Test
  void calculateHitTrianglePositive() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-2.0);
    pd.setY(0.5);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(3.0);
    pd.setX(-0.3);
    pd.setY(1.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateHitTriangleCornerCases() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(0.0);
    pd.setY(4.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(3.0);
    pd.setX(-3.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(2.0);
    pd.setX(1.0);
    pd.setY(-1.0 + 2.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateTriangleHitNegative() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-4.0);
    pd.setY(4.0);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setR(3.5);
    pd.setX(-1.0);
    pd.setY(3.5);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());
  }

  @Test
  void calculateCircleHitPositive() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-2.0);
    pd.setY(-2.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(2.5);
    pd.setX(-1.2);
    pd.setY(-0.1);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateCircleHitCornerCase() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-4.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(2.4);
    pd.setX(0.0);
    pd.setY(-2.4);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(4.0);
    pd.setX(-2.0);
    pd.setY(-Math.sqrt(4*4 - 2*2));
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateCircleNegative() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(-2.0);
    pd.setY(-5.0);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setR(2.1);
    pd.setX(-1.1);
    pd.setY(-4.0);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());
  }

  @Test
  void calculateRectangleHitPositive() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(2.0);
    pd.setY(1.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(3.1);
    pd.setX(2.45);
    pd.setY(1.23);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateRectangleHitCornerCase() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(4.0);
    pd.setY(2.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(1.34);
    pd.setX(1.34);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(2.12);
    pd.setX(0.0);
    pd.setY(0.0);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());

    pd.setR(2.3);
    pd.setX(0.0);
    pd.setY(2.3/2);
    pd.calculateHit();
    assertEquals("Да", pd.getMatch());
  }

  @Test
  void calculateRectangleNegative() {
    PointData pd = new PointData();
    pd.setR(2.0);
    pd.setX(5.0);
    pd.setY(1.3);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());

    pd.setR(3.23);
    pd.setX(1.34);
    pd.setY(3.23);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());
  }

  @Test
  void calculateFreeAreaNegative() {
    PointData pd = new PointData();
    pd.setR(4.0);
    pd.setX(2.0);
    pd.setY(-1.0);
    pd.calculateHit();
    assertEquals("Нет", pd.getMatch());
  }   

}
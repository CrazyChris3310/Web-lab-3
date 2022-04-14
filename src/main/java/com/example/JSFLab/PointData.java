package com.example.JSFLab;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class represents all data about point that was hit. (x, y) coordinates, radius of area
 * and some additional technical information.
 */
@ManagedBean(name = "pointData")
@SessionScoped
public class PointData {
  private Double x;
  private Double y;
  private Double r;
  private String date;
  private String duration;
  private String match;

  private boolean clickedOnGraph;

  public boolean isClickedOnGraph() {
    return clickedOnGraph;
  }

  public void setClickedOnGraph(boolean clickedOnGraph) {
    this.clickedOnGraph = clickedOnGraph;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public Double getR() {
    return r;
  }

  public void setR(Double r) {
    this.r = r;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getMatch() {
    return match;
  }

  public void setMatch(String match) {
    this.match = match;
  }

  /**
   * Calculate whether this point was hit in the necessary area and stores result in
   * point's {@code match} field
   * @return this point with {@code match} field set
   */
  public PointData calculateHit() {
    long start = System.nanoTime();

    if (x > 0) {
      if (y >= 0 && y <= r / 2 && x <= r) { match = "Да"; } else { match = "Нет"; }
    } else {
      if (y >= 0 && y <= x + r) { match = "Да"; } else if (y <= 0 && x * x + y * y <= r * r) {
        match = "Да";
      } else { match = "Нет"; }
    }

    date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    duration = (System.nanoTime() - start) / 1000 + "мкс";
    return this;
  }
}

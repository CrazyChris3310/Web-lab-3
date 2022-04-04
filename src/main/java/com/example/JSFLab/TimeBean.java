package com.example.JSFLab;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * This class is get current time considering the timezone
 */
@ManagedBean
@RequestScoped
public class TimeBean {

  private OffsetDateTime odt = OffsetDateTime.now();

  /**
   * Returns current time in 'HH:mm:ss' format
   */
  public String getCurrentTime() {
    return odt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
  }

  /**
   * Returns current date in 'Thu, 12 Dec 2021' format.
   */
  public String getCurrentDate() {
    return odt.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"));
  }

  /**
   * Returns zone offset ID
   */
  public String getOffset() {
    return odt.getOffset().getId();
  }

  public void setOffset(String offset) {
    odt = odt.withOffsetSameLocal(ZoneOffset.of(offset));
  }

  /**
   * Adds 13 seconds to previous time mark
   */
  public void updateTime() {
    odt = odt.plusSeconds(13);
  }

}

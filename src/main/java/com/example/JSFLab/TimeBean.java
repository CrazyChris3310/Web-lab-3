package com.example.JSFLab;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@ManagedBean
@RequestScoped
public class TimeBean {

  private OffsetDateTime odt = OffsetDateTime.now();

  public String getCurrentTime() {
    return odt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
  }

  public String getCurrentDate() {
    return odt.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"));
  }

  public String getOffset() {
    return odt.getOffset().getId();
  }

  public void setOffset(String offset) {
    odt = odt.withOffsetSameLocal(ZoneOffset.of(offset));
  }

  public void updateTime() {
    odt = odt.plusSeconds(13);
  }

}

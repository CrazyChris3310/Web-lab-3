package com.example.JSFLab;

import jakarta.persistence.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name="pointData")
@RequestScoped
@Entity
@Table(name = "matchresults")
public class PointData {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "matchresults_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private int id;

    @Column(name="xcord")
    private Double x;

    @Column(name="ycord")
    private Double y;

    @Column(name="radius")
    private Double r;

    private String date;
    private String duration;

    @Column(name = "result")
    private String match;

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

    public PointData calculateHit() {
        long start = System.nanoTime();

        if (x > 0) {
            if (y >= 0 && y <= r/2 && x <= r)
                match = "Да";
            else
                match = "Нет";
        } else {
            if (y >= 0 && y <= x + 4)
                match = "Да";
            else if (y <= 0 && x*x + y*y <= r*r)
                match = "Да";
            else
                match = "Нет";
        }

        date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        duration = (System.nanoTime() - start) / 1000 + "мкс";
        return this;
    }
}

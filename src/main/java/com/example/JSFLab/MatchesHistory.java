package com.example.JSFLab;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name="matchesHistory")
@ApplicationScoped
public class MatchesHistory {
    private Deque<PointData> previousHits;

    public MatchesHistory() {
        previousHits = new LinkedList<>();
    }

    public Deque<PointData> getPreviousHits() {
        return previousHits;
    }

    public String addPoint(PointData point) {
        previousHits.push(point);
        System.out.println("\nX: " + point.getX() +
                "\nY: " + point.getY() +
                "\nR: " + point.getY() +
                "\ndate: " + point.getDate() +
                "\ntime: " + point.getDuration() +
                "\nmatch: " + point.getMatch());
        return "/facelets/page1";
    }

    public PointData getFirst() {
        return previousHits.peekFirst();
    }

    public boolean isEmpty() {
        return previousHits.isEmpty();
    }
}

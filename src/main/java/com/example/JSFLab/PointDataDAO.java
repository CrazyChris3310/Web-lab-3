package com.example.JSFLab;

import com.example.JSFLab.dataBase.DataBaseConnection;
import com.example.JSFLab.dataBase.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.*;
import java.util.Deque;
import java.util.LinkedList;

@ManagedBean(name="pointDataDAO", eager = true)
@ApplicationScoped
public class PointDataDAO {

    PointData lastPoint;

    public Deque<PointData> getAllPoints() {
        return new LinkedList<PointData>(HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from com.example.JSFLab.PointData").list());
    }

    public void addPoint(PointData point) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(point);
        tx.commit();
        session.close();
        lastPoint = point;
    }

    public PointData getFirst() throws SQLException {
        return getAllPoints().peek();
    }

    public boolean isEmpty() throws SQLException {
        return getAllPoints().size() == 0;
    }

    public void getTimePage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/facelets/time.xhtml");
    }

}

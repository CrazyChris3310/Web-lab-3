package com.example.JSFLab;

import com.example.JSFLab.dataBase.DataBaseConnection;
import com.example.JSFLab.dataBase.HibernateSessionFactoryUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import temp.TestBean;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name="pointDataDAO", eager = true)
@ApplicationScoped
public class PointDataDAO {

    PointData lastPoint;

    public List<PointData> getAllPoints() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<PointData> cq = cb.createQuery(PointData.class);

        Root<PointData> root = cq.from(PointData.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        List<PointData> list = query.getResultList();
        session.close();

        return list;
    }

    public void addPoint(PointData point) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(point);
        tx.commit();
        session.close();
        lastPoint = point;
    }

    public PointData getFirst() throws SQLException {
        List<PointData> list = getAllPoints();
        return list.size() == 0 ? null : list.get(0);
    }

    public boolean isEmpty() throws SQLException {
        return getAllPoints().size() == 0;
    }

    public void getTimePage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/facelets/time.xhtml");
    }

}

package com.example.JSFLab;

import com.example.JSFLab.dataBase.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@ManagedBean(name="pointDataDAO", eager = true)
@ApplicationScoped
public class PointDataDAO {

    PointData lastPoint;

    public List<PointData> getAllPoints() {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<PointData> cq = cb.createQuery(PointData.class);

        Root<PointData> root = cq.from(PointData.class);

        cq.select(root);

        cq.orderBy(cb.desc(root.get("id")));

        Query query = session.createQuery(cq);
        List<PointData> list = query.getResultList();
        tx.commit();
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

    @PreDestroy
    public void clearTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from com.example.JSFLab.PointData").executeUpdate();
        tx.commit();
        session.close();
    }

    public void getTimePage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/facelets/time.xhtml");
    }

}

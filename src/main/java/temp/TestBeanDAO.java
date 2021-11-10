package temp;

import com.example.JSFLab.dataBase.HibernateSessionFactoryUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class TestBeanDAO {

    private SessionFactory sessionFactory;

    {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    public List<TestBean> getAllBeans() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<TestBean> cq = cb.createQuery(TestBean.class);

        Root<TestBean> root = cq.from(TestBean.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        List<TestBean> list = query.getResultList();
        session.close();

        return list;

    }

    public void addBean(TestBean bean) {
        System.out.println(bean);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(bean);
        tx.commit();
        session.close();
    }

}

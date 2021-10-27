package com.example.JSFLab;

import com.example.JSFLab.dataBase.DataBaseConnection;
import sun.misc.ExtensionInstallationException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name="testBeanDAO")
@ApplicationScoped
public class TestBeanDAO {

    Connection postgresConnection;

    {
        String URL = System.getenv("dataBaseURL");
        String login = System.getenv("postgresLogin");
        String password = System.getenv("postgresPasswd");
        if (URL == null || login == null || password == null) {
            System.err.println("Not all environmental variables are defined");
            throw new ExceptionInInitializerError("Not all environmental variables are defined");
        }
        try {
            postgresConnection = DataBaseConnection.getConnection(URL, login, password);
        } catch (ClassNotFoundException e) {
            System.err.println("No database driver class found");
            throw new ExceptionInInitializerError(e);
        } catch (SQLException e) {
            System.err.println("SQL error. " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public List<TestBean> getBeans() throws SQLException {
        List<TestBean> beans = new LinkedList<>();

        Statement statement = postgresConnection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM testTable");
        while (rs.next()) {
            TestBean bean = new TestBean();
            bean.setName(rs.getString("name"));
            bean.setSurname(rs.getString("surname"));
            beans.add(bean);
        }
        statement.close();
        rs.close();

        return beans;
    }

    public void addBean(TestBean bean) throws SQLException {
        PreparedStatement st = postgresConnection.prepareStatement("INSERT INTO testtable values(?, ?)");
        st.setString(1, bean.getName());
        st.setString(2, bean.getSurname());
        st.executeUpdate();
    }
}

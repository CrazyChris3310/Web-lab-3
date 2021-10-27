package com.example.JSFLab;

import com.example.JSFLab.dataBase.DataBaseConnection;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.Deque;
import java.util.LinkedList;

@ManagedBean(name="pointDataDAO")
@ApplicationScoped
public class PointDataDAO {

    Connection connection;

    {
        String URL = System.getenv("databaseURL");
        String login = System.getenv("databaseLogin");
        String password = System.getenv("databasePasswd");
        if (URL == null || login == null || password == null) {
            System.err.println("Not all environmental variables are defined");
            throw new ExceptionInInitializerError("Not all environmental variables are defined");
        }
        try {
            connection = DataBaseConnection.getConnection(URL, login, password);
        } catch (ClassNotFoundException e) {
            System.err.println("No database driver class found");
            throw new ExceptionInInitializerError(e);
        } catch (SQLException e) {
            System.err.println("SQL error. " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public Deque<PointData> getAllPoints() throws SQLException {
        Deque<PointData> matches = new LinkedList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM matchresults");
        while (rs.next()) {
            PointData point = getPointDataFromRS(rs);
            matches.push(point);
        }
        st.close();
        rs.close();
        return matches;
    }

    public void addPoint(PointData point) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO matchresults values (?, ?, ?, ?, ?, ?)");
        ps.setDouble(1, point.getX());
        ps.setDouble(2, point.getY());
        ps.setDouble(3, point.getR());
        ps.setString(4, point.getDate());
        ps.setString(5, point.getDuration());
        ps.setString(6, point.getMatch());
        ps.executeUpdate();
        ps.close();
    }

    public PointData getFirst() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM matchresults ORDER BY id DESC LIMIT 1");
        PointData point = null;
        if (rs.next()) {
            point = getPointDataFromRS(rs);
        }
        rs.close();
        st.close();
        return point;
    }

    private PointData getPointDataFromRS(ResultSet rs) throws SQLException {
        PointData point = new PointData();
        point.setX(rs.getDouble("xCord"));
        point.setY(rs.getDouble("yCord"));
        point.setR(rs.getDouble("radius"));
        point.setDate(rs.getString("date"));
        point.setDuration(rs.getString("duration"));
        point.setMatch(rs.getString("result"));
        return point;
    }

    public boolean isEmpty() throws SQLException {
        Statement st = connection.createStatement();
        boolean isNotEmpty = st.execute("SELECT * FROM matchresults");
        st.close();
        return !isNotEmpty;
    }

    // FIXME: Method does not clear the database
    @PreDestroy
    private void clearingDB() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("DELETE FROM matchresults");
        st.close();
        connection.close();
    }

}

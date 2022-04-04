package com.example.JSFLab;

import com.example.JSFLab.dataBase.DataBaseConnection;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Class to interact with database and operate with {@link PointData} objects
 */
@ManagedBean(name = "pointDataDAO", eager = true)
@ApplicationScoped
public class PointDataDAO {

  Connection connection;
  PointData lastPoint;

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
      e.printStackTrace();
      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * Get all points in database
   * @return all queried points
   * @throws SQLException if a database access error occurs
   */
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

  private PointData getPointDataFromRS(ResultSet rs) throws SQLException {
    PointData point = new PointData();
    point.setX(rs.getDouble("xCord"));
    point.setY(rs.getDouble("yCord"));
    point.setR(rs.getDouble("radius"));
    point.setDate(rs.getString("datetime"));
    point.setDuration(rs.getString("duration"));
    point.setMatch(rs.getString("result"));
    return point;
  }

  /**
   * Adds point to database
   * @param point new point to add
   * @throws SQLException if a database access error occurs
   */
  public void addPoint(PointData point) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO matchresults values (matchresults_id_seq.nextval, ?, ?, ?, ?, ?, ?)");
    ps.setDouble(1, point.getX());
    ps.setDouble(2, point.getY());
    ps.setDouble(3, point.getR());
    ps.setString(4, point.getDate());
    ps.setString(5, point.getDuration());
    ps.setString(6, point.getMatch());
    ps.executeUpdate();
    ps.close();
    lastPoint = point;
  }

  /**
   * Returns first point in the database, which is the last point that was checked
   */
  public PointData getFirst() throws SQLException {
      if (lastPoint != null) { return lastPoint; }
    Statement st = connection.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM matchresults ORDER BY id DESC");
    PointData point = null;
    if (rs.next()) {
      point = getPointDataFromRS(rs);
    }
    rs.close();
    st.close();
    return point;
  }

  /**
   * Checks whether the database has no points saved
   * @return true if database is empty
   * @throws SQLException if a database access error occurs
   */
  public boolean isEmpty() throws SQLException {
    Statement st = connection.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM matchresults");
    boolean isNotEmpty = rs.next();
    st.close();
    rs.close();
    return !isNotEmpty;
  }

  @PreDestroy
  private void clearingDB() {
    try (Statement st = connection.createStatement()) {
      st.executeUpdate("DELETE FROM matchresults");
      st.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}

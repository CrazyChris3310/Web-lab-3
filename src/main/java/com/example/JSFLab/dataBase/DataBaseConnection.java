package com.example.JSFLab.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static Connection getConnection(String URL, String hostName, String hostPassword) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, hostName, hostPassword);
    }

}

package com.hivetech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String DB_URL = "jdbc:mariadb://localhost:3307/factory";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "hoangthanhtu123@";

    private Database(){

    }

    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

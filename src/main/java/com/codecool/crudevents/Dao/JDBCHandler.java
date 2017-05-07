package com.codecool.crudevents.Dao;

import java.sql.*;

/**
 * Created by mercutio on 07.05.17.
 */
public class JDBCHandler {

    private static JDBCHandler ourInstance = new JDBCHandler();
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private JDBCHandler() {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/eventcrud.db");
            statement = conn.createStatement();
            conn.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error!");
        }
    }

    public static JDBCHandler getInstance() {
        return ourInstance;
    }

    public Connection getJDBC() {
        return conn;
    }

    public void closeJDBC() {
        try {
            clearAndPrepare();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void executeUpdateQuery(String query) {
        try {
            clearAndPrepare();
            this.statement.executeUpdate(query);
            this.statement.close();
            this.conn.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public ResultSet executeSelectQuery(String query) {
        try {
            clearAndPrepare();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultSet;
    }

    private void clearAndPrepare() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}

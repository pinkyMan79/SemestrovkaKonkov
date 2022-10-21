package com.miusicmaker.musmk.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {

    private Connection connection;

    private final String url;
    private final String username;
    private final String password;

    public SimpleDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        openConnection();
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            openConnection();
        }
        return connection;
    }

    private void openConnection() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new RuntimeException();}

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new RuntimeException();}

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new RuntimeException();
    }
}

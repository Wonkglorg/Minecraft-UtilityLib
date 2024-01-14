package com.wonkglorg.utilitylib.manager.database;


import com.wonkglorg.utilitylib.base.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * IMPORTANT! Please add the mysql Jconnector to the project if you want to use MySql, I did not include this myself to not inflate the libraries
 * size. groupId : mysql artifactId : mysql-connector-java
 */
@SuppressWarnings("unused")
public class MySqlDatabase extends Database {

    protected final String USERNAME;
    protected final String URL;
    protected final String PASSWORD;
    private BlockingQueue<Connection> connectionPool;

    public MySqlDatabase(String url, String username, String password, String databaseName, int poolSize) {
        super(databaseName, DatabaseType.MYSQL);

        if (username == null || url == null || password == null || DATABASE_NAME == null) {
            throw new RuntimeException();
        }
        USERNAME = username;
        URL = url;
        PASSWORD = password;

        initializeConnectionPool(poolSize);
    }

    @Override
    public Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        connectionPool.offer(connection);
    }


    /**
     * Unused handled by connection pool
     */
    @Override
    public void connect() {
        //unused handled by connection pool
    }

    @Override
    public void disconnect() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.logFatal("Error closing connection: " + e.getMessage());
            }
        }
    }

    private void initializeConnectionPool(int poolSize) {
        connectionPool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connectionPool.add(createConnection());
        }
    }

    private Connection createConnection() {
        try {
            Class.forName(databaseType.getClassLoader());
            return DriverManager.getConnection(getDatabaseType().getDriver() + "//" + URL + "/" + DATABASE_NAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Logger.logFatal("Mysql-Connector-J Could not be found");
            Logger.logFatal("This is an error for the plugin developer and should not be reported back to the creator of the library");
            Logger.logFatal("I did not include this dependency myself to not inflate the libraries size.");
            Logger.logFatal(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }


}
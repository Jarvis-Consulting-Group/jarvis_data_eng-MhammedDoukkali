package ca.jrvs.apps.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnectionManager {

    private final String url;
    private final Properties proporties;

    public DatabaseConnectionManager (String host, String databaseName,
                                      String username, String password) {
        this.url = "jdbc:postgresql://"+host+"/"+databaseName;
        this.proporties = new Properties();
        this.proporties.setProperty("user", username);
        this.proporties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url, this.proporties);
    }

}

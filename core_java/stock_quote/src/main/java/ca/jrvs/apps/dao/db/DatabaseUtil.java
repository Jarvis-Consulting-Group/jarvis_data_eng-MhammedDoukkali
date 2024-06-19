package ca.jrvs.apps.dao.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    private static final String PROPORTIES_FILE = "app.properties";
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseUtil() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream(PROPORTIES_FILE)) {
                Properties props = new Properties();
                if(input == null) {
                    System.out.println("Sorry, unable to find" + PROPORTIES_FILE);
                    return null;
                }
                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

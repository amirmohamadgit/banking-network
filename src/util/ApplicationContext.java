package util;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ApplicationContext {

    private static ApplicationContext CTX;
    private Connection connection;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (CTX == null) CTX = new ApplicationContext();
        return CTX;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        ApplicationProperties.DATABASE_URL,
                        ApplicationProperties.DATABASE_USER,
                        ApplicationProperties.DATABASE_PASSWORD
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}

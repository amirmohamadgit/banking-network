package util;

import repositories.CreditCardRepository;
import repositories.CreditCardRepositoryImpl;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;

import java.sql.*;

public class ApplicationContext {

    private static ApplicationContext CTX;
    private Connection connection;
    private UserRepository userRepository;
    private CreditCardRepository creditCardRepository;

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

    public UserRepository getUserRepository() {
        if (userRepository == null) userRepository = new UserRepositoryImpl(getConnection());
        return userRepository;
    }

    public CreditCardRepository getCreditCardRepository() {
        if (creditCardRepository == null) creditCardRepository = new CreditCardRepositoryImpl(getConnection());
        return creditCardRepository;
    }
}

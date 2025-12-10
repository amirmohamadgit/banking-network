import entities.User;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;
import services.UserRegister;
import util.ApplicationContext;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        ApplicationContext CTX = ApplicationContext.getInstance();
        Connection connection = CTX.getConnection();

        UserRepository userRepository = new UserRepositoryImpl(connection);


    }
}
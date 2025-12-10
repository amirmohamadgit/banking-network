package services;

import java.util.Objects;

public class UserLogin {
    private static Integer id;
    public static boolean loginRequest(String username, String password) {
        id = BaseService.userRepository.findIdByUsername(username);
        return Validate.validateExistUsername(username) &&
                Objects.equals(BaseService.userRepository.findPasswordById(id), password);
    }

    public static Integer getId() {
        return id;
    }
}

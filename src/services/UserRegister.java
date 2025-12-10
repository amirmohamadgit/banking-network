package services;

import entities.User;


public class UserRegister {


    public static void registerRequest(User user) {
        Validate.validateUserRequest(user);
        BaseService.userRepository.save(user);
    }

}
package services;

import entities.CreditCard;
import repositories.CreditCardRepository;
import repositories.UserRepository;
import util.ApplicationContext;

public class BaseService {
    static ApplicationContext CTX = ApplicationContext.getInstance();
    static UserRepository userRepository = CTX.getUserRepository();
    static CreditCardRepository creditCardRepository = CTX.getCreditCardRepository();
}

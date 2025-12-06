package repository;

import intity.CreditCard;
import repository.base.CrudRepository;

public interface CreditCardRepository extends CrudRepository {

    CreditCard findByCardNumber(String cardNumber);
}

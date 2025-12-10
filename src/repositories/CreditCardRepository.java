package repositories;

import entities.CreditCard;
import repositories.base.CrudRepository;

import java.util.List;

public interface CreditCardRepository extends CrudRepository {

    CreditCard findByCardNumber(String cardNumber);
    boolean existByCardNumber(String cardNumber);
    boolean deleteCardByCardNumber(String cardNumber);
    String showCardInfo(String cardNumber);
    String showUserCardInfo(String cardNumber, Integer userId);
    List<String> showUserCardsByBankName(String bankName, Integer userId);
    List<String> showAllUserCards(Integer userId);

}

package services;

import entities.CreditCard;

import java.util.List;

public class CardOperations {


    public static void CardRegister(CreditCard creditCard) {
        Validate.ValidateCreditCardRequest(creditCard);
        BaseService.creditCardRepository.save(creditCard);
    }

    public static void deleteCard(String cardNumber) {
        BaseService.creditCardRepository.deleteCardByCardNumber(cardNumber);
    }

    public static String showUserCardInfo(String cardNumber, Integer userId) {
        return BaseService.creditCardRepository.showUserCardInfo(cardNumber, userId);
    }

    public static List<String> showUserCardsByBankName(String bankName, Integer userId) {
        return BaseService.creditCardRepository.showUserCardsByBankName(bankName, userId);
    }

    public static List<String> showAllUserCards(Integer userId) {
        return BaseService.creditCardRepository.showAllUserCards(userId);
    }
}

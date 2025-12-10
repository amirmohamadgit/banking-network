package services;

import entities.Banks;
import entities.CreditCard;
import entities.User;

import java.util.Objects;

public class Validate {


    public static void validateUserRequest(User user) {
        validateName(user.getFirstName(), user.getLastName());
        validateNationalCode(user.getNationalCode());
        validatePhonNumber(user.getPhonNumber());
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
    }

    public static void ValidateCreditCardRequest(CreditCard creditCard) {
        validateCardNumber(creditCard.getCardNumber());
    }

    public static void validateMoneyTransfer(long amount, long minMount, long maxMount, String typeOfTransfer) {
        if (amount < minMount) {
            throw new IllegalArgumentException("The minimum amount for" + typeOfTransfer + "must be " + minMount);
        }
        if (amount > maxMount) {
            throw new IllegalArgumentException("the maximum amount for " + typeOfTransfer + " transfer must be " + maxMount);
        }
    }

    private static void validateCardNumber(String cardNumber) {
        checkNotNull(cardNumber, "card number");

        if (!cardNumber.matches("[0-9]{16}")) {
            throw new IllegalArgumentException("card number must contain only numbers and must be 16 characters.");
        }

        if (BaseService.creditCardRepository.existByCardNumber(cardNumber)) {
            throw new IllegalArgumentException("card number exists in the database");
        }
    }


    private static void validatePassword(String password) {
        checkNotNull(password, "password");

        if (!password.matches("[a-zA-Z0-9@]{4,200}")) {
            throw new IllegalArgumentException("password must contain only letters and numbers and @ and must be At least 4 characters characters.");
        }
    }

    private static void validateUserName(String userName) {
        checkNotNull(userName, "username");

        if (!userName.matches("[a-zA-Z_]{5,50}")) {
            throw new IllegalArgumentException("username must contain only letters and numbers and _ and and must be between 5 and 50 characters.");
        }

        if (validateExistUsername(userName)) {
            throw new IllegalArgumentException("Username exists in the database.");
        }

    }

    public static boolean validateExistUsername(String userName) {
        return BaseService.userRepository.existByUsername(userName);
    }

    private static void validatePhonNumber(String phonNumber) {
        checkNotNull(phonNumber, "phon number");

        if (!phonNumber.matches("[0-9]{11}")) {
            throw new IllegalArgumentException("phon number must contain only numbers and must be 11 characters.");
        }
    }

    private static void validateNationalCode(String nationalCode) {
        checkNotNull(nationalCode, "national code");

        if (!nationalCode.matches("[0-9]{7,10}")) {
            throw new IllegalArgumentException("national code must contain only numbers and must be between 7 and 10 characters.");
        }
        if (BaseService.userRepository.existByNationalCode(nationalCode)) {
            throw new IllegalArgumentException("national code exists in the database.");
        }
    }

    private static void validateName(String firstName, String lastName) {
        validateNamePart(firstName, "firstname");
        validateNamePart(lastName, "lastname");
    }

    private static void validateNamePart(String name, String nameType) {
        checkNotNull(name, nameType);

        //if (!name.matches("[\\p{L} ]+")) * for all languages
        if (!name.matches("[a-zA-Z ]{3,20}")) {
            throw new IllegalArgumentException(nameType + " must contain only letters and must be between 3 and 20 characters.");
        }
    }

    private static void checkNotNull(String value, String nameType) {
        if (Objects.isNull(value)) throw new IllegalArgumentException(nameType + " cannot be null.");
    }
}

package entities;

public class CreditCard extends BaseEntity{

    public static final String TABLE_NAME = "CreditCards";

    public static final String BANK_COLUMN = "bank_name";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String CARD_NUMBER_COLUMN = "card_number";
    public static final String BALANCE_COLUMN = "balance";

    private Banks bank;
    private String cardNumber;
    private long balance;
    private int userID;

    public CreditCard(Banks bank, String cardNumber, long balance, int userID) {
        this.bank = bank;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.userID = userID;
    }

    public Banks getBank() {
        return bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public long getBalance() {
        return balance;
    }

    public int getUserID() {return userID;}
}

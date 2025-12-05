package intity;

public class CreditCard extends BaseEntity{

    public static final String TABLE_NAME = "CreditCards";

    public static final String BANK_COLUMN = "bank_name";
    public static final String CARD_NUMBER_COLUMN = "card_number";
    public static final String BALANCE_COLUMN = "balance";

    private Banks bank;
    private String cardNumber;
    private long balance;

    public CreditCard(Banks bank, String cardNumber, long balance) {
        this.bank = bank;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }
}

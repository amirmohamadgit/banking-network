package intity;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{

    public static final String TABLE_NAME = "Users";

    public static final String FIRSTNAME_COLUMN = "first_name";
    public static final String LASTNAME_COLUMN = "last_name";
    public static final String NATIONAL_CODE_COLUMN = "national_code";
    public static final String PHON_NUMBER_COLUMN = "phon_number";
    public static final String USERNAME_COLUMN = "user_name";
    public static final String PASSWORD_COLUMN = "password";


    private final String firstName;
    private final String lastName;
    private final String nationalCode;
    private String phonNumber;
    private String userName;
    private String password;
    private List<CreditCard> creditCards = new ArrayList<>();

    public User(String firstName, String lastName, String nationalCode, String phonNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phonNumber = phonNumber;
        this.userName = userName;
        this.password = password;
    }

    public void setAccount(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }
}

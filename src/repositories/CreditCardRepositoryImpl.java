package repositories;

import entities.BaseEntity;
import entities.CreditCard;
import repositories.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CreditCardRepositoryImpl extends AbstractCrudRepository implements CreditCardRepository {
    public CreditCardRepositoryImpl(Connection connection) {
        super(connection);
    }

    private PreparedStatement existByCardNumberStatement;
    private PreparedStatement deleteByCardNumberStatement;
    private PreparedStatement showCardInfoByCardNumberStatement;
    private PreparedStatement showUserCardInfoByCardNumberStatement;
    private PreparedStatement showUserCardsByBanksNameStatement;
    private PreparedStatement showAllUserCardsStatement;

    @Override
    public CreditCard findByCardNumber(String cardNumber) {
        return null;
    }


    @Override
    public String[] getInsertColumns() {
        return new String[]{
                CreditCard.USER_ID_COLUMN,
                CreditCard.BANK_COLUMN,
                CreditCard.CARD_NUMBER_COLUMN,
                CreditCard.BALANCE_COLUMN
        };
    }

    @Override
    public String getTableName() {
        return CreditCard.TABLE_NAME;
    }

    @Override
    protected void fillPreparedStatementForInsert(BaseEntity baseEntity) {
        try {
            insertStatement.setInt(1, (baseEntity).getId());
            insertStatement.setString(2, String.valueOf(((CreditCard) baseEntity).getBank()));
            insertStatement.setString(3, ((CreditCard) baseEntity).getCardNumber());
            insertStatement.setLong(4, ((CreditCard) baseEntity).getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByCardNumber(String cardNumber) {
        PreparedStatement statement = getExistByCardNumberStatement();
        try {

            statement.setString(1, cardNumber);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("database error while checking card number existence for: " + cardNumber, e);
        }
        return false;
    }

    @Override
    public boolean deleteCardByCardNumber(String cardNumber) {
        PreparedStatement statement = getDeleteByCardNumberStatement();
        try {
            statement.setString(1, cardNumber);
            int rs = statement.executeUpdate();
            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException("database error while deleting credit card by card number: " + cardNumber, e);
        }
    }

    @Override
    public List<String> showAllUserCards(Integer userId) {
        PreparedStatement statement = getShowUserCardsByBanksNameStatement();
        try {
            statement.setInt(1, userId);

            List<String> cardDetails = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    String number = rs.getString("card_number");
                    String bank = rs.getString("bank_name");
                    Long balance = rs.getLong("balance");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    String cardInfo = MessageFormat.format("Card Number: {0}, Bank: {1}, balance: {2}, name: {3} {4}",
                            number, bank, balance, firstName, lastName);

                    cardDetails.add(cardInfo);
                }
                return cardDetails;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<String> showUserCardsByBankName(String bankName, Integer userId) {
        PreparedStatement statement = getShowUserCardsByBanksNameStatement();
        try {
            statement.setString(1, bankName);
            statement.setInt(2, userId);

            List<String> cardDetails = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    String number = rs.getString("card_number");
                    String bank = rs.getString("bank_name");
                    Long balance = rs.getLong("balance");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    String cardInfo = MessageFormat.format("Card Number: {0}, Bank: {1}, balance: {2}, name: {3} {4}",
                            number, bank, balance, firstName, lastName);

                    cardDetails.add(cardInfo);
                }
                return cardDetails;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String showUserCardInfo(String cardNumber, Integer userId) {
        PreparedStatement statement = getShowUserCardInfoByCardNumberStatement();
        try {
            statement.setString(1, cardNumber);
            statement.setInt(2, userId);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    String number = rs.getString("card_number");
                    String bank = rs.getString("bank_name");
                    Long balance = rs.getLong("balance");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    String cardInfo = MessageFormat.format("Card Number: {0}, Bank: {1}, balance: {2}, name: {3} {4}",
                            number, bank, balance, firstName, lastName);
                    return cardInfo;

                }
                return "Card not found for card number: " + cardNumber;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String showCardInfo(String cardNumber) {
        PreparedStatement statement = getShowCardInfoByCardNumberStatement();
        try {
            statement.setString(1, cardNumber);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {
                    String number = rs.getString("card_number");
                    String bank = rs.getString("bank_name");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    String cardInfo = MessageFormat.format("Card Number: {0}, Bank: {1}, name: {2} {3}",
                            number, bank, firstName, lastName);
                    return cardInfo;

                }
                return "Card not found for card number: " + cardNumber;
            }
        } catch (SQLException e) {
            throw new RuntimeException("database error while fetching card info: " + e.getMessage(), e);
        }
    }

    public PreparedStatement getDeleteByCardNumberStatement() {
        if (Objects.isNull(deleteByCardNumberStatement)) {
            try {
                deleteByCardNumberStatement = connection.prepareStatement("DELETE FROM CreditCards WHERE card_number = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return deleteByCardNumberStatement;
    }


    public PreparedStatement getExistByCardNumberStatement() {
        if (Objects.isNull(existByCardNumberStatement)) {
            try {
                existByCardNumberStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM CreditCards WHERE card_number = ?)");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return existByCardNumberStatement;
    }

    public PreparedStatement getShowCardInfoByCardNumberStatement() {
        if (Objects.isNull(showCardInfoByCardNumberStatement)) {
            try {
                showCardInfoByCardNumberStatement = connection.prepareStatement("SELECT c.card_number, c.bank_name, u.first_name, u.last_name, FROM creditcards c JOIN users u ON c.user_id = u.user_id WHERE c.card_number = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return showCardInfoByCardNumberStatement;
    }

    public PreparedStatement getShowUserCardInfoByCardNumberStatement() {
        if (Objects.isNull(showUserCardInfoByCardNumberStatement)) {
            try {
                showUserCardInfoByCardNumberStatement = connection.prepareStatement("SELECT c.card_number, c.bank_name, c.balance u.first_name, u.last_name, FROM cards c JOIN users u ON c.user_id = u.user_id WHERE c.card_number = ? AND c.user_id = ?");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return showUserCardInfoByCardNumberStatement;
    }

    public PreparedStatement getShowUserCardsByBanksNameStatement() {
        if (Objects.isNull(showUserCardsByBanksNameStatement)) {
            try {
                showUserCardsByBanksNameStatement = connection.prepareStatement("SELECT c.card_number, c.bank_name, c.balance u.first_name, u.last_name, FROM cards c JOIN users u ON c.user_id = u.user_id WHERE c.bank_name = ? AND c.user_id = ?");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return showUserCardsByBanksNameStatement;
    }

    public PreparedStatement getShowAllUserCardsStatement() {
        if (Objects.isNull(showAllUserCardsStatement)) {
            try {
                showAllUserCardsStatement = connection.prepareStatement("SELECT c.card_number, c.bank_name, c.balance u.first_name, u.last_name, FROM cards c JOIN users u ON c.user_id = u.user_id where c.user_id = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return showAllUserCardsStatement;
    }
}

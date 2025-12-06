package repository;

import intity.BaseEntity;
import intity.CreditCard;
import intity.User;
import repository.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class CreditCardRepositoryImpl extends AbstractCrudRepository implements CreditCardRepository {
    protected CreditCardRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public CreditCard findByCardNumber(String cardNumber) {
        return null;
    }

    @Override
    public String[] getInsertColumns() {
        return new String[]{
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
            insertStatement.setString(1, String.valueOf(((CreditCard) baseEntity).getBank()));
            insertStatement.setString(2, ((CreditCard) baseEntity).getCardNumber());
            insertStatement.setLong(3, ((CreditCard) baseEntity).getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

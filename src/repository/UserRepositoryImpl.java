package repository;

import intity.BaseEntity;
import intity.User;
import repository.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepositoryImpl extends AbstractCrudRepository implements UserRepository {

    protected UserRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findByUsername(String userName) {
        return null;
    }

    @Override
    public String[] getInsertColumns() {
        return new String[]{
                User.FIRSTNAME_COLUMN,
                User.LASTNAME_COLUMN,
                User.PHON_NUMBER_COLUMN,
                User.NATIONAL_CODE_COLUMN,
                User.USERNAME_COLUMN,
                User.PASSWORD_COLUMN
        };
    }

    @Override
    public String getTableName() {
        return User.TABLE_NAME;
    }

    @Override
    protected void fillPreparedStatementForInsert(BaseEntity baseEntity) {
        try {
            insertStatement.setString(1, ((User) baseEntity).getFirstName());
            insertStatement.setString(2, ((User) baseEntity).getLastName());
            insertStatement.setString(3, ((User) baseEntity).getPhonNumber());
            insertStatement.setString(4, ((User) baseEntity).getNationalCode());
            insertStatement.setString(5, ((User) baseEntity).getUserName());
            insertStatement.setString(6, ((User) baseEntity).getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

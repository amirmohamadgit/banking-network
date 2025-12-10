package repositories;

import entities.BaseEntity;
import entities.User;
import repositories.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserRepositoryImpl extends AbstractCrudRepository implements UserRepository {

    private PreparedStatement existByUsernameStatement;
    private PreparedStatement existByNationalCodeStatement;
    private PreparedStatement findIdByUsernameStatement;
    private PreparedStatement findPasswordByIdStatement;

    public UserRepositoryImpl(Connection connection) {
        super(connection);
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

    @Override
    public String findPasswordById(Integer id) {
        PreparedStatement statement = getFindPasswordByIdStatement();
        try {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("database error while finding password for ID: " + id, e);
        }
        return null;
    }

    @Override
    public Integer findIdByUsername(String username) {
        PreparedStatement statement = getFindIdByUsernameStatement();
        try {
            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("database error while finding ID for username: " + username, e);
        }
        return null;
    }


    @Override
    public boolean existByUsername(String username) {
        PreparedStatement statement = getExistByUsernameStatement();
        try {

            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("database error while checking username existence for: " + username, e);
        }
        return false;
    }

    @Override
    public boolean existByNationalCode(String nationalCode) {
        PreparedStatement statement = getExistByNationalCodeStatement();
        try {

            statement.setString(1, nationalCode);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("database error while checking username existence for: " + nationalCode, e);
        }
        return false;
    }


    private PreparedStatement getExistByUsernameStatement() {
        if (Objects.isNull(existByUsernameStatement)) {
            try {
                existByUsernameStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM Users WHERE user_name = ?)");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return existByUsernameStatement;
    }

    private PreparedStatement getExistByNationalCodeStatement() {
        if (Objects.isNull(existByNationalCodeStatement)) {
            try {
                existByNationalCodeStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM Users WHERE national_code = ?)");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return existByNationalCodeStatement;
    }

    private PreparedStatement getFindIdByUsernameStatement() {
        if (Objects.isNull(findIdByUsernameStatement)) {
            try {
                findIdByUsernameStatement = connection.prepareStatement("SELECT user_id FROM Users WHERE user_name = ?");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findIdByUsernameStatement;
    }

    private PreparedStatement getFindPasswordByIdStatement() {
        if (Objects.isNull(findPasswordByIdStatement)) {
            try {
                findPasswordByIdStatement = connection.prepareStatement("SELECT password FROM Users WHERE user_id = ?");
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findPasswordByIdStatement;

    }
}

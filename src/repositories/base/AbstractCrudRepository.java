package repositories.base;

import entities.BaseEntity;

import java.sql.*;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCrudRepository implements CrudRepository {

    private static final CharSequence QUERY_DELIMITER = ",";
    protected final Connection connection;
    protected PreparedStatement findByIdStatement;
    protected PreparedStatement insertStatement;
    protected PreparedStatement updateStatement;

    protected AbstractCrudRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public BaseEntity save(BaseEntity baseEntity) {
            return insert(baseEntity);
    }

    private BaseEntity insert(BaseEntity baseEntity) {
        PreparedStatement statement = getInsertStatement();
        try {
            fillPreparedStatementForInsert(baseEntity);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                baseEntity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return baseEntity;
    }

    private PreparedStatement getInsertStatement() {
        if (Objects.isNull(insertStatement)) {
            try {
                String[] insertColumns = getInsertColumns();
                String[] questionMarks = new String[insertColumns.length];
                Arrays.fill(questionMarks, "?");
                insertStatement = connection.prepareStatement(
                        MessageFormat.format("insert into {0} ({1}) values ({2})",
                        getTableName(),
                        String.join(QUERY_DELIMITER, insertColumns),
                        String.join(QUERY_DELIMITER, questionMarks)),
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return insertStatement;
    }


    @Override
    public BaseEntity findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<BaseEntity> findAll() {
        return List.of();
    }

    public abstract String[] getInsertColumns();
    public abstract String getTableName();
    protected abstract void fillPreparedStatementForInsert(BaseEntity baseEntity);

}

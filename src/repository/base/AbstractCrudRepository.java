package repository.base;

import intity.BaseEntity;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.List;

public class AbstractCrudRepository implements CrudRepository {

    protected final Connection connection;
    protected PreparedStatement findByIdStatement;
    protected PreparedStatement insertStatement;
    protected PreparedStatement updateStatement;

    protected AbstractCrudRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public BaseEntity save(BaseEntity baseEntity) {
        return null;
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
}

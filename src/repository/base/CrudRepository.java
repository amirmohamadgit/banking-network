package repository.base;

import intity.BaseEntity;

import java.util.List;

public interface CrudRepository {
    BaseEntity save(BaseEntity baseEntity);

    BaseEntity findById(Integer id);

    void deleteById(Integer id);

    List<BaseEntity> findAll();
}

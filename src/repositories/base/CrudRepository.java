package repositories.base;

import entities.BaseEntity;

import java.util.List;

public interface CrudRepository {
    BaseEntity save(BaseEntity baseEntity);

    BaseEntity findById(Integer id);

    void deleteById(Integer id);

    List<BaseEntity> findAll();
}

package repositories;

import repositories.base.CrudRepository;

public interface UserRepository extends CrudRepository {

    String findPasswordById(Integer id);
    Integer findIdByUsername(String username);
    boolean existByUsername(String username);
    boolean existByNationalCode(String nationalCode);
}

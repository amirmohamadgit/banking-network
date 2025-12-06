package repository;

import intity.User;
import repository.base.CrudRepository;

public interface UserRepository extends CrudRepository {

    User findByUsername(String userName);
}

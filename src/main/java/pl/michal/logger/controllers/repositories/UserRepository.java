package pl.michal.logger.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.logger.models.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Integer> {

    // do autoryzacji
    Optional<UserModel> findByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
    UserModel findByLogin(String login);
}

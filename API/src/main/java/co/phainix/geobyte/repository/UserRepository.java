package co.phainix.geobyte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.phainix.geobyte.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}

package co.phainix.geobyte.test.user;

import static org.assertj.core.api.Assertions.assertThat;

import co.phainix.geobyte.model.UserModelStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.phainix.geobyte.model.User;
import co.phainix.geobyte.repository.UserRepository;

@ContextConfiguration(classes=co.phainix.geobyte.Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    @Test
    public void shouldFindNoUsers_ifRepositoryIsEmpty() {
        Iterable<User> users = repository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void shouldStoreAUser() {
        User user = repository.save(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));

        assertThat(user).hasFieldOrPropertyWithValue("name", "Lorem Ipsum");
        assertThat(user).hasFieldOrPropertyWithValue("email", "lorem.ipsum@gmail.com");
        assertThat(user).hasFieldOrPropertyWithValue("status", UserModelStatus.ACTIVE);
    }

    @Test
    public void shouldFindAllUsers() {
        User user1 = repository.save(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));
        entityManager.persist(user1);

        User user2 = repository.save(new User("Lorem Ipsum2", "lorem.ipsum@gmail.com2", UserModelStatus.ACTIVE));
        entityManager.persist(user2);

        User user3 = repository.save(new User("Lorem Ipsum3", "lorem.ipsum@gmail.com3", UserModelStatus.ACTIVE));
        entityManager.persist(user3);

        Iterable<User> users = repository.findAll();

        assertThat(users).hasSize(3).contains(user1, user2, user3);
    }

    @Test
    public void shouldFindUserById() {
        User user1 = repository.save(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));
        entityManager.persist(user1);

        User user2 = repository.save(new User("Lorem Ipsum2", "lorem.ipsum@gmail.com2", UserModelStatus.ACTIVE));
        entityManager.persist(user2);

        User foundUser = repository.findById(user2.getId()).get();

        assertThat(foundUser).isEqualTo(user2);
    }

    @Test
    public void shouldUpdateUserById() {
        User user1 = repository.save(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));
        entityManager.persist(user1);

        User user2 = repository.save(new User("Lorem Ipsum2", "lorem.ipsum@gmail.com2", UserModelStatus.ACTIVE));
        entityManager.persist(user2);

        User updatedUser = new User("Lorem Ipsum4", "lorem.ipsum@gmail.com4", UserModelStatus.ACTIVE);

        User user = repository.findById(user2.getId()).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        repository.save(user);

        User checkUser = repository.findById(user2.getId()).get();

        assertThat(checkUser.getId()).isEqualTo(user2.getId());
        assertThat(checkUser.getName()).isEqualTo(updatedUser.getName());
        assertThat(checkUser.getEmail()).isEqualTo(updatedUser.getEmail());
        assertThat(checkUser.getPassword()).isEqualTo(updatedUser.getPassword());
    }

    @Test
    public void shouldDeleteUserById() {
        User user1 = repository.save(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));
        entityManager.persist(user1);

        User user2 = repository.save(new User("Lorem Ipsum2", "lorem.ipsum@gmail.com2", UserModelStatus.ACTIVE));
        entityManager.persist(user2);

        User user3 = repository.save(new User("Lorem Ipsum3", "lorem.ipsum@gmail.com3", UserModelStatus.ACTIVE));
        entityManager.persist(user3);

        repository.deleteById(user2.getId());

        Iterable<User> users = repository.findAll();

        assertThat(users).hasSize(2).contains(user1, user3);
    }

    @Test
    public void shouldDeleteAllUsers() {
        entityManager.persist(new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE));
        entityManager.persist(new User("Lorem Ipsum", "lorem.ipsum@gmail.com2", UserModelStatus.ACTIVE));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}




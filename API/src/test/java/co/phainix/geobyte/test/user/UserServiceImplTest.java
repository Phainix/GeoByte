package co.phainix.geobyte.test.user;

import co.phainix.geobyte.dto.mapper.UserMapper;
import co.phainix.geobyte.dto.model.UserDto;
import co.phainix.geobyte.dto.response.UserResponseDto;
import co.phainix.geobyte.exception.EntityType;
import co.phainix.geobyte.exception.ExceptionType;
import co.phainix.geobyte.exception.GeoByteException;
import co.phainix.geobyte.model.User;
import co.phainix.geobyte.model.UserModelStatus;
import co.phainix.geobyte.repository.UserRepository;
import co.phainix.geobyte.service.UserService;
import co.phainix.geobyte.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static co.phainix.geobyte.exception.GeoByteException.getMessageTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes=co.phainix.geobyte.Application.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @BeforeEach
    public void setUp() {
        User user = new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode("lorem.ipsum$"));

        Mockito.when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(user);
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "lorem.ipsum@gmail.com";
        UserResponseDto found = userService.findUserByEmail(email);

        assertThat(found.getEmail())
                .isEqualTo(email);
    }

    @Test
    public void whenEmailExists_thenUserCanNotSignUp() {

        User user = new User("Lorem Ipsum", "lorem.ipsum@gmail.com", UserModelStatus.ACTIVE);
        user.setPassword("lorem.ipsum$");

        try {
            userService.signUp(UserMapper.toUserDto(user));
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains(getMessageTemplate(EntityType.USER, ExceptionType.DUPLICATE_ENTITY)));
        }

    }

//    @Test
//    public void whenEmailDoesNotExist_thenUserCanSignUp() {
//
//        User user = new User("Lorem Ipsum", "lorem.ipsum@gmail.com10", UserModelStatus.ACTIVE);
//        user.setPassword("lorem.ipsum$");
//
//        System.out.println(user.toString());
//        System.out.println(UserMapper.toUserDto(user));
//
//        UserDto found = userService.signUp(UserMapper.toUserDto(user));
//        System.out.println(user.toString());
//
//        // UserDto found = userService.findUserByEmail(user.getEmail());
//        System.out.println(found);
//        assertThat(found.getEmail())
//                .isEqualTo(user.getEmail());
//    }

}
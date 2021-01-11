package co.phainix.geobyte.service;

import co.phainix.geobyte.dto.mapper.UserMapper;
import co.phainix.geobyte.dto.model.UserDto;
import co.phainix.geobyte.dto.response.UserResponseDto;
import co.phainix.geobyte.exception.EntityType;
import co.phainix.geobyte.exception.ExceptionType;
import co.phainix.geobyte.exception.GeoByteException;
import co.phainix.geobyte.model.GeoByteStatus;
import co.phainix.geobyte.model.User;
import co.phainix.geobyte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static co.phainix.geobyte.exception.EntityType.USER;
import static co.phainix.geobyte.exception.ExceptionType.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto signUp(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setName(userDto.getName());
            user.setGeoByteStatus(GeoByteStatus.ACTIVE);

            User savedUser = userRepository.save(user);
            if(savedUser == null) {
                throw exception(USER, ENTITY_EXCEPTION, userDto.getEmail());
            }
            return UserMapper.toUserResponseDto(savedUser);
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return UserMapper.toUserResponseDto(user.get());
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserResponseDto signIn(String email, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            User userModel = user.get();
            System.out.println(userModel.getPassword() + " " + bCryptPasswordEncoder.encode(password));
            if(bCryptPasswordEncoder.matches(password, userModel.getPassword())) {
                return UserMapper.toUserResponseDto(userRepository.save(userModel));
            }
            throw exception(USER, ENTITY_NOT_FOUND, email);
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }


    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return GeoByteException.throwException(entityType, exceptionType, args);
    }
}

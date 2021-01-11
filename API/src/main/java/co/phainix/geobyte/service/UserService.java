package co.phainix.geobyte.service;

import co.phainix.geobyte.dto.model.UserDto;
import co.phainix.geobyte.dto.response.UserResponseDto;
import co.phainix.geobyte.exception.GeoByteException;

public interface UserService {

    UserResponseDto signUp(UserDto userDto);

    UserResponseDto findUserByEmail(String email);

    UserResponseDto signIn(String email, String newPassword);

}

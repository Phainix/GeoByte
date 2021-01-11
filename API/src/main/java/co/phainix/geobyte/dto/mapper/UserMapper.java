package co.phainix.geobyte.dto.mapper;

import co.phainix.geobyte.dto.model.UserDto;
import co.phainix.geobyte.dto.response.UserResponseDto;
import co.phainix.geobyte.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setGeoByteStatus(user.getGeoByteStatus());
        return userDto;
    }

    public static UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setGeoByteStatus(user.getGeoByteStatus());
        userResponseDto.setDate_created(user.getDate_created());
        return userResponseDto;
    }

}

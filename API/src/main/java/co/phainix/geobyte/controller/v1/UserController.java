package co.phainix.geobyte.controller.v1;

import co.phainix.geobyte.controller.v1.request.UserSigninRequest;
import co.phainix.geobyte.controller.v1.request.UserSignupRequest;
import co.phainix.geobyte.dto.model.UserDto;
import co.phainix.geobyte.dto.response.Response;
import co.phainix.geobyte.dto.response.UserResponseDto;
import co.phainix.geobyte.model.GeoByteStatus;
import co.phainix.geobyte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public Response signUp(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        Response resp =  Response.ok();
        resp.setData(signUpUser(userSignupRequest));
        return resp;
    }

    private UserResponseDto signUpUser(UserSignupRequest userSignupRequest) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userSignupRequest.getEmail());
        userDto.setPassword(userSignupRequest.getPassword());
        userDto.setName(userSignupRequest.getName());
        userDto.setGeoByteStatus(GeoByteStatus.ACTIVE);

        return userService.signUp(userDto);
    }

    @PostMapping("/signIn")
    public Response signIn(@RequestBody @Valid UserSigninRequest userSigninRequest) {
        Response resp =  Response.ok();
        resp.setData(signInUser(userSigninRequest));
        return resp;
    }

    private UserResponseDto signInUser(UserSigninRequest userSigninRequest) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userSigninRequest.getEmail());
        userDto.setPassword(userSigninRequest.getPassword());

        return userService.signIn(userSigninRequest.getEmail(), userSigninRequest.getPassword());
    }

}

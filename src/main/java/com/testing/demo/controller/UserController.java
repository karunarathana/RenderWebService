package com.testing.demo.controller;

import com.testing.demo.constant.APIConstants;
import com.testing.demo.dto.UserDto;
import com.testing.demo.model.UserEntity;
import com.testing.demo.response.user.CreateUserResponse;
import com.testing.demo.service.JwtService;
import com.testing.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstants.API_ROOT)
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = APIConstants.CREATE_USER, method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        logger.info("Request Started In createUser |userDetails={}",userDto);
        CreateUserResponse response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value = APIConstants.CREATE_ACCESS_TOKEN, method = RequestMethod.GET)
    public ResponseEntity<?> generateAccessToken(@RequestParam String userName,@RequestParam String password) {
        logger.info("Request Started In generateAccessToken |userName={}  |password={}",userName,password);
        JwtService service = new JwtService();
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @RequestMapping(value = APIConstants.GET_ALL_USERS, method = RequestMethod.GET)
    public ResponseEntity<?> allUsers() {
        logger.info("Request Started In allUsers");
        List<UserEntity> allUserDetails = userService.getAllUserDetails();
        return new ResponseEntity<>(allUserDetails, HttpStatus.OK);
    }
}

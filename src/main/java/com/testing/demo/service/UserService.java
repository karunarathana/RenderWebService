package com.testing.demo.service;

import com.testing.demo.dto.UserDto;
import com.testing.demo.model.UserEntity;
import com.testing.demo.response.user.CreateUserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(UserDto userDto);
    List<UserEntity> getAllUserDetails();
}

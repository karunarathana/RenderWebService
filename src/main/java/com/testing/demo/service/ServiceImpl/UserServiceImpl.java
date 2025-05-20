package com.testing.demo.service.ServiceImpl;

import com.testing.demo.dto.UserDto;
import com.testing.demo.model.UserEntity;
import com.testing.demo.repo.UserRepo;
import com.testing.demo.response.user.CreateUserResponse;
import com.testing.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponse createUser(UserDto userDto) {
        logger.info("Method Executing Starting In createUser | userDTO={}", userDto);
        CreateUserResponse response = new CreateUserResponse();
        if (checkExistingUserInTheDatabase(userDto.getUserEmail())) {
            logger.info("Existing User | userDTO={}", userDto);
            response.setResponseMessage("Existing User");
            response.setResponseCode("450");
            return response;
        }

        UserEntity userEntity = assignValueUserEntity(userDto);
        UserEntity savedUser = userRepo.save(userEntity);

        if (String.valueOf(savedUser.getUserId()) != null) {
            logger.info("User saved successfully | userId={}", savedUser.getUserId());
            response.setResponseMessage("User saved successfully");
            response.setResponseCode("200");
        } else {
            logger.warn("User save failed for DTO={}", userDto);
            response.setResponseMessage("User save failed");
            response.setResponseCode("400");
        }

        return response;
    }

    private boolean checkExistingUserInTheDatabase(String userEmail) {
        logger.info("Method Executing Starting In checkExistingUserInTheDatabase |userEmail={}", userEmail);
        Optional<UserEntity> byUserEmail = userRepo.findByUserEmail(userEmail);
        return byUserEmail.isPresent();
    }

    private UserEntity assignValueUserEntity(UserDto userDto) {
        logger.info("Method Executing Starting In assignValueUserEntity |userDto={}", userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setEmail(userDto.getUserEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(userDto.getRole());
        logger.info("Method Executing Completed In assignValueUserEntity |userEntity={}", userEntity);
        return userEntity;
    }

    public List<UserEntity> getAllUserDetails(){
        logger.info("Method Executing Starting In getAllUserDetails");
        List<UserEntity> allUserDetails = userRepo.findAll();
        logger.info("Method Executing Completed In getAllUserDetails |allDetails={}",allUserDetails);
        return allUserDetails;
    }
}

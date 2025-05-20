package com.testing.demo.repo;

import com.testing.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Query(value = "SELECT * FROM t_user WHERE email = :userEmail", nativeQuery = true)
    Optional<UserEntity> findByUserEmail(String userEmail);
}

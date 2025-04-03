package com.example.userservice.domain.repository;

import com.example.userservice.domain.entity.UserId;
import com.example.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, UserId> {

    Users findByUserName(String userName);
    Users findByCi(String ci);
    Users findById_UserId(String userId);

}

package com.example.userservice.domain.repository;

import com.example.userservice.domain.entity.UserId;
import com.example.userservice.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, UserId> {
}

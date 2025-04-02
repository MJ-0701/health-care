package com.example.lifelogservice.domain.repository;

import com.example.lifelogservice.domain.entity.Lifelog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LifelogRepository extends JpaRepository<Lifelog, UUID> {
}

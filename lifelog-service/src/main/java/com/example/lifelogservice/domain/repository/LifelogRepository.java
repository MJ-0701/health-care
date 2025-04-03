package com.example.lifelogservice.domain.repository;

import com.example.lifelogservice.domain.entity.Lifelog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface LifelogRepository extends JpaRepository<Lifelog, UUID>, LifelogCustomRepository {
}

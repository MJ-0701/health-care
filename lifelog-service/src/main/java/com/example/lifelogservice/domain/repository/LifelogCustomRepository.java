package com.example.lifelogservice.domain.repository;

import com.example.storagemodule.dto.response.LifelogResponseDto;

import java.util.List;

public interface LifelogCustomRepository {

    List<LifelogResponseDto> getUserBloodPressure(String userCi);
}

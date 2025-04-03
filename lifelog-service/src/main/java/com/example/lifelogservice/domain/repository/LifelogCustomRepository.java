package com.example.lifelogservice.domain.repository;

import com.example.lifelogservice.domain.entity.LogType;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.LifelogResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface LifelogCustomRepository {

    List<LifelogResponseDto> getUserBloodPressure(String userCi);

    List<LifelogResponseDto> getUserBloodPressureRaw(String ci, LocalDate startDate, LocalDate endDate);

    LocalDate findMaxDateByCiAndLogType(String ci, LogType logType);
}

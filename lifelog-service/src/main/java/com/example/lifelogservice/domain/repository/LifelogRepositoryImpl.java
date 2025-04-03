package com.example.lifelogservice.domain.repository;

import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.support.QueryDslPageAndSort;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.LifelogResponseDto;
import com.example.storagemodule.dto.response.QBloodPressureTimelineDto;
import com.example.storagemodule.dto.response.QLifelogResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.lifelogservice.domain.entity.QLifelog.*;

public class LifelogRepositoryImpl extends QueryDslPageAndSort implements LifelogCustomRepository {

    private final JPAQueryFactory queryFactory;

    public LifelogRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        super(em,Lifelog.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public List<LifelogResponseDto> getUserBloodPressure(String userCi) {

        return queryFactory.select(
                new QLifelogResponseDto(
                        lifelog.logType.stringValue().as("logType"),
                        lifelog.isActive.as("isActive"),
                        lifelog.payload.as("payload"),
                        lifelog.startTime.as("startTime"),
                        lifelog.createdAt.as("createdAt"),
                        lifelog.updatedAt.as("updatedAt")
                )

        ).from(lifelog
        ).where(
                lifelog.ci.eq(userCi).and(lifelog.logType.eq(LogType.BLOOD_PRESSURE))
        ).fetch();

    }

    @Override
    public List<LifelogResponseDto> getUserBloodPressureRaw(String ci, LocalDate startDate, LocalDate endDate) {
        return queryFactory.select(
                        new QLifelogResponseDto(
                                lifelog.logType.stringValue().as("logType"),
                                lifelog.isActive.as("isActive"),
                                lifelog.payload.as("payload"),
                                lifelog.startTime.as("startTime"),
                                lifelog.createdAt.as("createdAt"),
                                lifelog.updatedAt.as("updatedAt")
                        )
                ).from(lifelog)
                .where(
                        lifelog.ci.eq(ci),
                        lifelog.logType.eq(LogType.BLOOD_PRESSURE),
                        lifelog.startTime.between(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay())
                )
                .fetch();
    }

    @Override
    public LocalDate findMaxDateByCiAndLogType(String ci, LogType logType) {
        java.sql.Date sqlDate = queryFactory
                .select(Expressions.dateTemplate(java.sql.Date.class,
                        "cast(date_trunc('day', max({0})) as date)", lifelog.startTime))
                .from(lifelog)
                .where(userCiEq(ci), logTypeEq(logType))
                .fetchOne();
        return sqlDate != null ? sqlDate.toLocalDate() : null;
    }

    // 동적 조건 헬퍼 메서드들
    private BooleanExpression userCiEq(String userCi) {
        return (userCi != null && !userCi.isEmpty()) ? lifelog.ci.eq(userCi) : null;
    }

    private BooleanExpression logTypeEq(LogType logType) {
        return logType != null ? lifelog.logType.eq(logType) : null;
    }

    private BooleanExpression startTimeBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();
        return lifelog.startTime.between(startDateTime, endDateTime);
    }
}

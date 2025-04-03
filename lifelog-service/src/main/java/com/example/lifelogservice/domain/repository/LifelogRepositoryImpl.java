package com.example.lifelogservice.domain.repository;

import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.support.QueryDslPageAndSort;
import com.example.storagemodule.dto.response.LifelogResponseDto;
import com.example.storagemodule.dto.response.QLifelogResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

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
                lifelog.ci.eq(userCi).and(lifelog.logType.eq(LogType.valueOf("BLOOD_PRESSURE")))
        ).fetch();

    }
}

package com.example.userservice.messaging;

import java.util.Map;

public class LifelogRoutingKeyMapper {

    private static final Map<String, String> routingKeyMap = Map.of(
        "BLOOD_PRESSURE", "lifelog.bloodpressure",
        "STEP_COUNT", "lifelog.stepcount",
        "WEIGHT", "lifelog.weight"
    );

    public static String getRoutingKey(String logType) {
        if (!routingKeyMap.containsKey(logType)) {
            throw new IllegalArgumentException("지원하지 않는 logType입니다: " + logType);
        }
        return routingKeyMap.get(logType);
    }
}

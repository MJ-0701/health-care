package com.example.userservice.messaging;

import java.util.Map;

public class LifelogRoutingKeyMapper {

    // 저장용 키
    private static final Map<String, String> SAVE_ROUTING_KEY_MAP = Map.of(
            "BLOOD_PRESSURE", "lifelog.bloodpressure.save",
            "STEP_COUNT", "lifelog.stepcount",
            "WEIGHT", "lifelog.weight"
    );

    // 조회용 키
    private static final Map<String, String> QUERY_ROUTING_KEY_MAP = Map.of(
            "BLOOD_PRESSURE", "lifelog.bloodpressure.query",
            "BLOOD_PRESSURE_TIMELINE", "lifelog.bloodpressure.timeline.query",
            "STEP_COUNT", "lifelog.stepcount",
            "WEIGHT", "lifelog.weight"
    );

    public static String getRoutingKey(String logType, boolean isQuery) {
        if (isQuery) {
            if (!QUERY_ROUTING_KEY_MAP.containsKey(logType)) {
                throw new IllegalArgumentException("지원하지 않는 logType입니다: " + logType);
            }
            return QUERY_ROUTING_KEY_MAP.get(logType);
        } else {
            if (!SAVE_ROUTING_KEY_MAP.containsKey(logType)) {
                throw new IllegalArgumentException("지원하지 않는 logType입니다: " + logType);
            }
            return SAVE_ROUTING_KEY_MAP.get(logType);
        }
    }
}

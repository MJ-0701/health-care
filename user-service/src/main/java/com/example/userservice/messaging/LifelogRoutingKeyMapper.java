package com.example.userservice.messaging;

import java.util.Map;

public class LifelogRoutingKeyMapper {

    // 저장용, 조회용 라우팅 키를 별도로 정의합니다.
    private static final Map<String, String> SAVE_ROUTING_KEY_MAP = Map.of(
            "BLOOD_PRESSURE", "lifelog.bloodpressure.save",
            "STEP_COUNT", "lifelog.stepcount",  // 필요에 따라 분리 가능
            "WEIGHT", "lifelog.weight"           // 필요에 따라 분리 가능
    );

    private static final Map<String, String> QUERY_ROUTING_KEY_MAP = Map.of(
            "BLOOD_PRESSURE", "lifelog.bloodpressure.query",
            "STEP_COUNT", "lifelog.stepcount",  // 예시로 조회와 저장이 동일한 키일 수도 있음
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

package com.example.common.utils;

import org.json.JSONObject;

public class JsonUtil {

    /**
     * 주어진 JSON 문자열에서, 단순 플랫 구조를 가정하고, 
     * JSON path 형식("$.key")에 해당하는 값을 추출하여 반환합니다.
     *
     * @param json     JSON 문자열 (예: {"systolic": "120", "diastolic": "80"})
     * @param jsonPath JSON path 형식, 단순 지원: "$.systolic", "$.diastolic" 등
     * @return 해당 key의 값 (문자열), 없으면 null
     */
    public static String jsonValue(String json, String jsonPath) {
        if (json == null || jsonPath == null || !jsonPath.startsWith("$.")) {
            return null;
        }
        String key = jsonPath.substring(2); // "$." 제거
        JSONObject obj = new JSONObject(json);
        return obj.optString(key, null);
    }
}

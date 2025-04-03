package com.example.lifelogservice.domain.entity;

import com.example.common.utils.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.text.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Converter
public class JsonPayloadConverter implements AttributeConverter<Object, String> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Set<String> SENSITIVE_KEYS = Set.of("systolic", "diastolic");

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON 직렬화 실패", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        String json = dbData.trim();
        if (!json.startsWith("{")) {
            if (json.startsWith("\"") && json.endsWith("\"")) {
                json = json.substring(1, json.length() - 1);
                json = StringEscapeUtils.unescapeJson(json);
            }
        }
        try {
            Map<String, Object> map = mapper.readValue(json, Map.class);
            for (String key : SENSITIVE_KEYS) {
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    if (value instanceof Number) {
                        continue;
                    }
                    String strValue = value.toString();
                    try {
                        String decrypted = CryptoUtil.decrypt(strValue);
                        map.put(key, decrypted);
                    } catch (Exception e) {
                        // 복호화 실패 시 원본을 그대로 저장
                        map.put(key, strValue);
                    }
                }
            }
            return map;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON 역직렬화 실패", e);
        }
    }
}

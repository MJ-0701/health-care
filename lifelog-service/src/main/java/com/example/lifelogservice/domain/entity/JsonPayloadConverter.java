package com.example.lifelogservice.domain.entity;

import com.example.common.utils.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
            return mapper.writeValueAsString(attribute); // 저장 시 암호화된 값 그대로 저장
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON 직렬화 실패", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            Map<String, Object> map = mapper.readValue(dbData, Map.class);
            for (String key : SENSITIVE_KEYS) {
                if (map.containsKey(key)) {
                    String enc = map.get(key).toString();
                    try {
                        map.put(key, CryptoUtil.decrypt(enc));
                    } catch (Exception e) {
                        // 복호화 실패 시 원본 유지
                        map.put(key, enc);
                    }
                }
            }
            return map;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON 역직렬화 실패", e);
        }
    }
}



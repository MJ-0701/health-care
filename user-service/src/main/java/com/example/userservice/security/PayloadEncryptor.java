package com.example.userservice.security;

import com.example.common.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PayloadEncryptor {

    /**
     * 혈압 로그(BLOOD_PRESSURE)의 민감 데이터를 암호화합니다.
     *
     * @param originalPayload 평문 payload (예: systolic, diastolic 포함)
     * @return systolic/diastolic 필드가 암호화된 Map
     */
    public Map<String, Object> encryptBloodPressurePayload(Map<String, Object> originalPayload) {
        Map<String, Object> result = new HashMap<>(originalPayload);
        result.put("systolic", encryptValue(originalPayload.get("systolic")));
        result.put("diastolic", encryptValue(originalPayload.get("diastolic")));
        return result;
    }

    /**
     * 체중 로그(WEIGHT)의 민감 데이터를 암호화합니다.
     * 필요 시 확장 예시 (현재는 주석 처리)
     */
//    public Map<String, Object> encryptWeightPayload(Map<String, Object> originalPayload) {
//        Map<String, Object> result = new HashMap<>(originalPayload);
//        result.put("weight", encryptValue(originalPayload.get("weight")));
//        return result;
//    }

    /**
     * 값이 null이 아닌 경우 암호화, 그 외에는 null 반환
     */
    private String encryptValue(Object value) {
        return value == null ? null : CryptoUtil.encrypt(value.toString());
    }

}

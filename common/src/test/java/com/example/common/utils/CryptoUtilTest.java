package com.example.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilTest {

    @Test
    void testEncryptDecrypt() {
        String plainText = "120.5";

        // 암호화 수행
        String encrypted = CryptoUtil.encrypt(plainText);
        // 암호화된 문자열은 평문과 달라야 합니다.
        assertNotEquals(plainText, encrypted, "암호화된 값은 평문과 달라야 합니다.");

        // 복호화 수행
        String decrypted = CryptoUtil.decrypt(encrypted);
        // 복호화된 결과가 원본과 동일한지 확인합니다.
        assertEquals(plainText, decrypted, "복호화된 값이 원본과 일치해야 합니다.");
    }
}
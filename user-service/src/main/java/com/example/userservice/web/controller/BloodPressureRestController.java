package com.example.userservice.web.controller;

import com.example.common.http.ResponseObject;
import com.example.userservice.service.BloodPressureService;
import com.example.userservice.web.dto.BloodPressureRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BloodPressureRestController {

    private final BloodPressureService bloodPressureService;

    @PostMapping("/{userId}/blood-pressure")
    public ResponseEntity<ResponseObject<Void>> postBloodPressure(
            @PathVariable String userId,
            @RequestBody @Valid BloodPressureRequestDto requestDto
    ) {
        bloodPressureService.save(userId, requestDto); // payload는 평문 상태
        return ResponseEntity.ok(ResponseObject.of(null));
    }


    @GetMapping("/{userId}/blood-pressure")
    public ResponseEntity<ResponseObject<String>> getBloodPressure(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().body(ResponseObject.of("유저의 혈압 로그"));
    }


    @GetMapping("/{userId}/blood-pressure/timeline")
    public ResponseEntity<ResponseObject<String>> getBloodPressureTimeline(
            @PathVariable String userId
            ) {
        return ResponseEntity.ok().body(ResponseObject.of("혈압 타임라인"));
    }


}

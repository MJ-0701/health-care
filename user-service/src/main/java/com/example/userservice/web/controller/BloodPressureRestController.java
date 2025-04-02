package com.example.userservice.web.controller;

import com.example.common.http.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BloodPressureRestController {

    @PostMapping("/{userId}/blood-pressure")
    public void postBloodPressure(
            @PathVariable String userId,
            @RequestBody Map<String, Object> payload
    ) {

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

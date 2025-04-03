package com.example.userservice.web.controller;

import com.example.common.http.ResponseObject;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import com.example.userservice.service.BloodPressureService;
import com.example.userservice.web.dto.request.BloodPressureRequestDto;
import com.example.userservice.web.dto.response.UserBloodPressureResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BloodPressureRestController {

    private final BloodPressureService bloodPressureService;

    @PostMapping("/{userId}/blood-pressure")
    public ResponseEntity<ResponseObject<Void>> postBloodPressure(
            @PathVariable String userId,
            @RequestBody Map<String, Object> requestData
    ) {
        bloodPressureService.save(userId, requestData);
        return ResponseEntity.ok(ResponseObject.of(null));
    }


    @GetMapping("/{userId}/blood-pressure")
    public ResponseEntity<ResponseObject<UserBloodPressureResponseDto>> getBloodPressure(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().body(ResponseObject.of(bloodPressureService.getUserBloodPressure(userId)));
    }


    @GetMapping("/{userId}/blood-pressure/timeline")
    public ResponseEntity<ResponseObject<BloodPressureTimelineResponseDto>> getBloodPressureTimeline(
            @PathVariable String userId,
            @RequestParam(name = "periodType", defaultValue = "7d") String periodType)
    {

        return ResponseEntity.ok().body(ResponseObject.of(bloodPressureService.getUserBloodPressureTimeline(userId, periodType)));
    }

}

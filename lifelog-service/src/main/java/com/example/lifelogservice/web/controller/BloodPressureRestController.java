package com.example.lifelogservice.web.controller;

import com.example.common.http.ResponseObject;
import com.example.lifelogservice.service.BloodPressureService;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BloodPressureRestController {

    private final BloodPressureService bloodPressureService;

    @GetMapping("/{ci}/blood-pressure/log")
    public ResponseEntity<ResponseObject<List<BloodPressureResponseDto>>> getBloodPressureLog(
            @PathVariable String ci
    ) {
        return ResponseEntity.ok().body(ResponseObject.of(bloodPressureService.getUserBloodPressure(ci)));
    }
}

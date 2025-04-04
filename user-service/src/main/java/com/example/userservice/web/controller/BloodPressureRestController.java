package com.example.userservice.web.controller;

import com.example.common.http.ResponseObject;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import com.example.userservice.service.BloodPressureService;
import com.example.userservice.web.dto.request.BloodPressureRequestDto;
import com.example.userservice.web.dto.response.UserBloodPressureResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Blood Pressure API", description = "혈압 데이터 관리 API")
public class BloodPressureRestController {

    private final BloodPressureService bloodPressureService;

    @PostMapping("/{userId}/blood-pressure")
    @Operation(summary = "혈압 데이터 저장", description = "사용자의 혈압 데이터를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "저장 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class)))
    })
    public ResponseEntity<ResponseObject<Void>> postBloodPressure(
            @PathVariable String userId,
            @RequestBody Map<String, Object> requestData
    ) {
        bloodPressureService.save(userId, requestData);
        return ResponseEntity.ok(ResponseObject.of(null));
    }


    @GetMapping("/{userId}/blood-pressure")
    @Operation(summary = "혈압 데이터 조회", description = "사용자의 최신 혈압 데이터를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserBloodPressureResponseDto.class)))
    })
    public ResponseEntity<ResponseObject<UserBloodPressureResponseDto>> getBloodPressure(
            @Parameter(
                    description = "사용자 식별자",
                    required = true,
                    example = "UID20250403A1B2C3D4E5F60718293A4B5C6D7E8F9A"
            )
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().body(ResponseObject.of(bloodPressureService.getUserBloodPressure(userId)));
    }


    @GetMapping("/{userId}/blood-pressure/timeline")
    @Operation(summary = "혈압 타임라인 조회", description = "사용자의 기간별 혈압 기록 타임라인을 조회합니다. (사진의 예시처럼 7일, 1개월, 12개웙 탭이 있다고 가정)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BloodPressureTimelineResponseDto.class)))
    })
    public ResponseEntity<ResponseObject<BloodPressureTimelineResponseDto>> getBloodPressureTimeline(
            @Parameter(
                    description = "사용자 식별자",
                    required = true,
                    example = "UID20250403A1B2C3D4E5F60718293A4B5C6D7E8F9A"
            )
            @PathVariable String userId,
            @Parameter(description = "조회 기간 (예: 7d, 1m, 12m)", required = false, example = "7d")
            @RequestParam(name = "periodType", defaultValue = "7d") String periodType)
    {

        return ResponseEntity.ok().body(ResponseObject.of(bloodPressureService.getUserBloodPressureTimeline(userId, periodType)));
    }

}

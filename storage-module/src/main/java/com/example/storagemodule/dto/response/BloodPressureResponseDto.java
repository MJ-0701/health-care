package com.example.storagemodule.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class BloodPressureResponseDto extends BaseLifelogResponseDto{


    private Double systolic;
    private Double diastolic;


}

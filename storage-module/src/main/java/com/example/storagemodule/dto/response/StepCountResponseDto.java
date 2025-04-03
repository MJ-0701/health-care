package com.example.storagemodule.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
public class StepCountResponseDto extends BaseLifelogResponseDto{

    private Integer stepCount;
    private Double calorie;
}

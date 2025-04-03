package com.example.storagemodule.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class WeightResponseDto extends BaseLifelogResponseDto {

    private Double weight;
    private Double height;
}

package com.actionman.swagger.inbound;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ExampleOutDto {

    @Schema(description = "결과값")
    private String result;
}
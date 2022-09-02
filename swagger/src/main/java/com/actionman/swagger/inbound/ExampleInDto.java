package com.actionman.swagger.inbound;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Schema(title = "테스트")
class ExampleInDto {

    @Parameter(name = "title", description = "타이틀", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(title="이름", description = "이름")
    private String name;

    @Parameter(name = "title", description = "스낵브랜드", in = ParameterIn.QUERY, schema = @Schema(description = "", type = "string", allowableValues = {"nongshim","samyang","ottugi"}))
    private SnackBrand snackBrand;
}

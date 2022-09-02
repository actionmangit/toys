package com.actionman.swagger.inbound;

import java.util.List;

import com.actionman.swagger.config.common.ErrorRes;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "example-controller", description = "스웨거 샘플 작성입니다.")
@RestController
class ExampleController {
    
    @Operation(
        summary = "간단 정리 테스트", 
        description = "자세한 정리 테스트 입니다.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "간단 정리 테스트 결과값",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExampleOutDto.class))
            ),
            @ApiResponse(
                responseCode = "500",
                description = "error",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorRes.class))
            )
        }
    )
    @GetMapping("/v1/snack/{snackPath}")
    ExampleOutDto find(
            @Parameter(name="snackPath", description = "스낵브랜드 입니다.", in = ParameterIn.PATH, schema = @Schema(enumAsRef = true, type = "string", allowableValues = {"samyang", "ottogi"}))
            @PathVariable(name = "snackPath") SnackBrand snackPath,
            @Parameter(in = ParameterIn.QUERY, schema = @Schema(implementation = ExampleInDto.class))
            ExampleInDto dto
        ) {

        // @formatter:off
        return ExampleOutDto.builder()
                            .result("성공이야!!")
                            .build();
        // @formatter:on
    }

    @Operation(
        summary = "파일 업로드 테스트", 
        description = "파일 업로드 테스트 입니다.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "파일 업로드 테스트 결과값",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExampleOutDto.class))
            ),
            @ApiResponse(
                responseCode = "500",
                description = "error",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorRes.class))
            )
        }
    )
    @PostMapping(value = "/v1/file")
    ExampleOutDto upload(
            @Parameter(name = "file", required = true, description = "업로드할 파일")
            @RequestPart(name = "file", required = true) MultipartFile file
    ) {

        // @formatter:off
        return ExampleOutDto.builder()
                            .result("성공이야 File!!")
                            .build();
        // @formatter:on
    }

    @Operation(
        summary = "파일 업로드 리스트 테스트", 
        description = "파일 업로드 리스트 테스트 입니다.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "파일 업로드 리스트 테스트 결과값",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExampleOutDto.class))
            ),
            @ApiResponse(
                responseCode = "500",
                description = "error",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorRes.class))
            )
        }
    )
    @PostMapping(value = "/v1/file-list")
    ExampleOutDto uploadAll(
            @Parameter(name = "file", required = true, description = "업로드할 파일들", array = @ArraySchema(schema = @Schema(type = "string", format = "binary")))
            @RequestPart(name = "file", required = true) List<MultipartFile> file
    ) {

        // @formatter:off
        return ExampleOutDto.builder()
                            .result("성공이야 File List!!")
                            .build();
        // @formatter:on
    }
}

package com.d205.foorrng.bigdata;


import com.d205.foorrng.common.model.BaseResponseBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BigdataController {
    private final BigdataService bigdataService;

    @GetMapping(value = "/api/recommend")
    @ApiResponse(responseCode = "200", description = "위치 추천")
    public ResponseEntity<? extends BaseResponseBody> recommendPosition(){
        List<String> positionlist = bigdataService.recommendPosition();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, ""));
    }


}

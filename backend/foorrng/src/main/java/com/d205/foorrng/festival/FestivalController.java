package com.d205.foorrng.festival;

import com.d205.foorrng.common.model.BaseResponseBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/festival")
public class FestivalController {
    private final FestivalRepository festivalRepository;
    @GetMapping(value = "/list")
    @ApiResponse(responseCode = "200", description = "축제 조회")
    public ResponseEntity<? extends BaseResponseBody> getFestivals(){
        List<Festival> festivalList = festivalRepository.findFutureFestivals();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, festivalList));
    }
}

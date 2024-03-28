package com.d205.foorrng.festival;

import com.d205.foorrng.common.model.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/festival")
public class FestivalController {

    private final FestivalService festivalService;
    @GetMapping("/now")
    public ResponseEntity<? extends BaseResponseBody> getFestivalListInThisMonth() {

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, festivalService.searchFestivalInThisMonth()));
    }
}

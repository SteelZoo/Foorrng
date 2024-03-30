package com.d205.foorrng.bigdata.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Map;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
public class Boundary {

    @Id
    private String areaName;

    private Double latitude;

    private Double longitude;

}

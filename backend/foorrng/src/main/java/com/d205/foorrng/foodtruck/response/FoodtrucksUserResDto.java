package com.d205.foorrng.foodtruck.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FoodtrucksUserResDto {
    private Long foodtruckId;
    private String name;
    private String img;
    private List<String> category;
    private Integer reviewCnt;

    public FoodtrucksUserResDto(Long foodtruckId, String name, String img, List<String> category, Integer reviewCnt) {
        this.foodtruckId = foodtruckId;
        this.name = name;
        this.img = img;
        this.category = category;
        this.reviewCnt = reviewCnt;
    }

    public void add(FoodtrucksUserResDto foodtrucksResDto) {
    }
}

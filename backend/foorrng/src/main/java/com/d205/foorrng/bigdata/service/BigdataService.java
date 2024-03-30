package com.d205.foorrng.bigdata.service;

import com.d205.foorrng.bigdata.dto.BoundaryDto;
import com.d205.foorrng.bigdata.entity.Boundary;
import com.d205.foorrng.bigdata.repository.BoundaryRepository;
import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.food.repository.FoodRepository;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.Boundary;
import com.d205.foorrng.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BigdataService {

    private final UserRepository userRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    private final FoodRepository foodRepository;
    private final BoundaryRepository boundaryRepository;


    public List<String> recommendPosition(){
        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
        Foodtrucks foodtrucks = foodtrucksRepository.findByUserUserUid(user.getUserUid())
                .orElseThrow(()->new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Food> category = foodRepository.findAllByFoodtrucks(foodtrucks);

        // 로직

        List<String> positionlist = new ArrayList<>();
        return positionlist;
    }


    public List<BoundaryDto> searchRegionBoundaryPoints(String areaName) {

        List<Boundary> boundaryPoints = boundaryRepository.findAllByAreaName(areaName)
                .orElseThrow(() -> new Exceptions(ErrorCode.BOUNDARY_NOT_EXIST));

        return boundaryPoints.stream()
                .map(boundary -> new BoundaryDto(boundary.getLatitude(), boundary.getLongitude()))
                .collect(Collectors.toList());


    }
}

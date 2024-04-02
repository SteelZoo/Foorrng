package com.d205.foorrng.bigdata.service;

import com.d205.foorrng.bigdata.dto.BoundaryDto;
import com.d205.foorrng.bigdata.dto.RecommendDto;
import com.d205.foorrng.bigdata.dto.VillageInfoDto;
import com.d205.foorrng.bigdata.entity.Bigdata;
import com.d205.foorrng.bigdata.entity.Boundary;
import com.d205.foorrng.bigdata.repository.BigdataRepository;
import com.d205.foorrng.bigdata.repository.BoundaryRepository;
import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.food.repository.FoodRepository;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BigdataService {

    private final UserRepository userRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    private final FoodRepository foodRepository;
    private final BoundaryRepository boundaryRepository;
    private final BigdataRepository bigdataRepository;


    public List<RecommendDto> recommendPosition(){
        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
        Foodtrucks foodtrucks = foodtrucksRepository.findByUserUserUid(user.getUserUid())
                .orElseThrow(()->new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Food> category = foodRepository.findAllByFoodtrucks(foodtrucks);

        List<RecommendDto> positionlist = new ArrayList<>();
        for(Food food:category){
            List<Bigdata> bigdataList = bigdataRepository.findByFoodOrderByScoreDesc(food.getName());
            List<VillageInfoDto> villageInfoDtoList = new ArrayList<>();
            for(Bigdata bigdata: bigdataList){
                VillageInfoDto villageInfoDto = new VillageInfoDto(bigdata.getCity(), searchRegionBoundaryPoints(bigdata.getCity().substring(0,2) + "%"));
                villageInfoDtoList.add(villageInfoDto);
            }
            RecommendDto recommendDto = new RecommendDto(food.getName(), villageInfoDtoList);
            positionlist.add(recommendDto);
        }
        return positionlist;
    }


    public List<BoundaryDto> searchRegionBoundaryPoints(String areaName) {
        List<Boundary> boundaryPoints = boundaryRepository.findAllByAreaName(areaName);
//                .orElseThrow(() -> new Exceptions(ErrorCode.BOUNDARY_NOT_EXIST));

        return boundaryPoints.stream()
                .map(boundary -> new BoundaryDto(boundary.getLatitude(), boundary.getLongitude()))
                .collect(Collectors.toList());
    }
}

package com.d205.foorrng.bigdata;

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

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BigdataService {

    private final UserRepository userRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    private final FoodRepository foodRepository;

    public List<String> recommendPosition(){
        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
        Foodtrucks foodtrucks = foodtrucksRepository.findByUserUserUid(user.getUserUid())
                .orElseThrow(()->new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Food> category = foodRepository.findAllByFoodtrucks(foodtrucks);

        // 로직

        List<String> positionlist = new ArrayList<>();
        return positionlist;
    }
}

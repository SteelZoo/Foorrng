package com.d205.foorrng.food.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.food.dto.FavoritefoodDto;
import com.d205.foorrng.food.repository.FavoritefoodRepository;
import com.d205.foorrng.food.repository.FoodRepository;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.user.entity.FavoriteFood;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter //?
@Service
@RequiredArgsConstructor
public class FoodService {

    private final UserRepository userRepository;
    private final FavoritefoodRepository favoritefoodRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    private final FoodRepository foodRepository;

    public void saveFavoriteFood(FavoritefoodDto favoritefoodDto) {

        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
        List<String> FavoriteFoodList = favoritefoodDto.getFavoriteFoods();

        for (String food: FavoriteFoodList) {
            FavoriteFood favoriteFood = FavoriteFood.builder()
                    .user(user)
                    .menu(food)
                    .latitude(favoritefoodDto.getLatitude())
                    .longitude(favoritefoodDto.getLongitude())
                    .createdTime(LocalDate.now(ZoneId.of("Asia/Seoul")).toString())
                    .build();
            favoritefoodRepository.save(favoriteFood);
        }
    }

    public void saveFoodtruckFood(Long Id, List<String> FoodtruckFoodList){
        Foodtrucks foodtrucks = foodtrucksRepository.findById(Id).get();

        for(String food: FoodtruckFoodList){
            Food foodtruckfood = Food.builder()
                    .name(food)
                    .foodtrucks(foodtrucks)
                    .build();
            foodRepository.save(foodtruckfood);
        }
    }

    public List<String> getFoodtruckFood(Long foodtruckId){
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckId)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Food> foods = foodRepository.findAllByFoodtrucks(foodtrucks);
        List<String> category = new ArrayList<>();
        for(Food food:foods){
            category.add(food.getName());
        }
        return category;
    }

}

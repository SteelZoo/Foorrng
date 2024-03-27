package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodtrucksRepository extends JpaRepository<Foodtrucks, Long> {
    Optional<Foodtrucks> findById(Long id);

    @Override
    List<Foodtrucks> findAll();

    Optional<Foodtrucks> findByUserUserUid(Long userUid);

    // 점주 -> foodtruck API
    // 소비자 -> foodtruckReport API
    // 점주 Uid -> 한개 foodtruck

    Optional<Object> findById(Optional<Foodtrucks> foodtrucksSeq);
}

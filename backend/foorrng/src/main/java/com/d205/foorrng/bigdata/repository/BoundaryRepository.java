package com.d205.foorrng.bigdata.repository;

import com.d205.foorrng.bigdata.entity.Boundary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoundaryRepository extends JpaRepository<Boundary, String> {

    Optional<List<Boundary>> findAllByAreaName(String areaName);
}

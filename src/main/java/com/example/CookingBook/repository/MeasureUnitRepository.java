package com.example.CookingBook.repository;

import com.example.CookingBook.enums.MeasureUnits;
import com.example.CookingBook.models.entity.MeasureUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MeasureUnitRepository extends JpaRepository<MeasureUnitEntity, Long> {

    Optional<MeasureUnitEntity> findByUnitType(MeasureUnits unitType);

    @Query("select distinct u.unitType from MeasureUnitEntity u")
    List<String> findAllNames ();
}

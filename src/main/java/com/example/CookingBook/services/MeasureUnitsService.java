package com.example.CookingBook.services;

import com.example.CookingBook.enums.MeasureUnits;
import com.example.CookingBook.models.DTO.UserRegisterDTO;
import com.example.CookingBook.models.entity.MeasureUnitEntity;
import com.example.CookingBook.repository.MeasureUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class MeasureUnitsService {

    private final MeasureUnitRepository measureUnitRepository;


    @Autowired
    public MeasureUnitsService(MeasureUnitRepository measureUnitRepository) {
        this.measureUnitRepository = measureUnitRepository;
    }

    public List<String> getMeasureUnitesNames() {
        return measureUnitRepository.findAllNames();
    }

    public MeasureUnitEntity getMeasuringUnit(MeasureUnits measureUnit){
        return measureUnitRepository.findByUnitType(measureUnit)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

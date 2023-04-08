package com.example.CookingBook.models.DTO;

import java.util.List;

public class MeasureUnitsDTO {

    List<String> unitName;

    public MeasureUnitsDTO() {
    }

    public List<String> getUnitName() {
        return unitName;
    }

    public MeasureUnitsDTO setUnitName(List<String> unitName) {
        this.unitName = unitName;
        return this;
    }
}

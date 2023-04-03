package com.example.CookingBook.models.entity;

import com.example.CookingBook.enums.MeasureUnits;
import jakarta.persistence.*;

@Entity
@Table(name = "measure_units")
public class MeasureUnitEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private MeasureUnits unitType;

    @Column
    private String unitName;

    public MeasureUnitEntity() {
    }

    public MeasureUnits getUnitType() {
        return unitType;
    }

    public MeasureUnitEntity setUnitType(MeasureUnits unitType) {
        this.unitType = unitType;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public MeasureUnitEntity setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }
}

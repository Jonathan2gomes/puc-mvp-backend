package com.sistemas.senior.stockcontrol.model.enums;

import com.sistemas.senior.stockcontrol.exception.NotFoundException;

public enum MeasureUnit {
    LITRO ("lt"),
    QUILOGRAMA ("kg"),
    UNIDADE ("un");

    private String abbreviation;

    MeasureUnit(String abbreviation) {
        this.abbreviation=abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}

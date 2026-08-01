package org.example.web.stock.stockDetail.domain;

import java.util.ArrayList;
import java.util.List;

public class TheoreticalPriceValuationDto {

    private List<ValuationModelDto> models = new ArrayList<>();

    // Getter and Setter
    public List<ValuationModelDto> getModels() {
        return models;
    }

    public void setModels(List<ValuationModelDto> models) {
        this.models = (models != null) ? models : new ArrayList<>();
    }

}

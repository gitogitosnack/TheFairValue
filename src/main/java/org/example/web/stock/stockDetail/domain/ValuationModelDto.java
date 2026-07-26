package org.example.web.stock.stockDetail.domain;

import java.util.ArrayList;
import java.util.List;

public class ValuationModelDto {
    private Integer modelId;
    private String modelName; // 例: "DCF法"
    private List<ValuationParameterDto> parameters = new ArrayList<>();

    // getters and setters
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<ValuationParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<ValuationParameterDto> parameters) {
        this.parameters = parameters;
    }

}

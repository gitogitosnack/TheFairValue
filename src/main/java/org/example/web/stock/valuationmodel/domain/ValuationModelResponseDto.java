package org.example.web.master.valuationmodel.domain;

public class ValuationModelResponseDto {
    private final Integer id;
    private final String modelName;
    private final String formulaDescription;

    public ValuationModelResponseDto(Integer id, String modelName, String formulaDescription) {
        this.id = id;
        this.modelName = modelName;
        this.formulaDescription = formulaDescription;
    }

    public Integer getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public String getFormulaDescription() {
        return formulaDescription;
    }
}

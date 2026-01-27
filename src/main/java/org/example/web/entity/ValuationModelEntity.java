package org.example.web.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity(immutable = false)
@Table(name = "valuation_models")
public class ValuationModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "formula_description")
    private String formulaDescription;

    // Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public String getFormulaDescription() { return formulaDescription; }
    public void setFormulaDescription(String formulaDescription) { this.formulaDescription = formulaDescription; }
}
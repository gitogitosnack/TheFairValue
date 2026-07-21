package org.example.web.stock.stockDetail.domain; // パッケージ名は環境に合わせて調整してください

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "valuation_parameters")
public class ValuationParametersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "valuation_model_id")
    private Integer valuationModelId;

    @Column(name = "parameter_code")
    private String parameterCode;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "display_order")
    private Integer displayOrder;

}
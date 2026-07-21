package org.example.web.stock.stockDetail.domain; // パッケージ名は環境に合わせて変更してください

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "company_valuation_model_parameters")
public class CompanyValuationModelParameterEntity {

    @Id
    @Column(name = "company_id")
    private Integer companyId;

    @Id
    @Column(name = "valuation_parameter_id")
    private Integer valuationParameterId;

    @Column(name = "default_value")
    private BigDecimal defaultValue;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}
package org.example.web.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(immutable = false)
@Table(name = "user_simulations")
public class UserSimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "custom_growth_rate")
    private BigDecimal customGrowthRate;

    @Column(name = "custom_wacc")
    private BigDecimal customWacc;

    @Column(name = "simulated_value")
    private BigDecimal simulatedValue;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getter and Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
    public Integer getModelId() { return modelId; }
    public void setModelId(Integer modelId) { this.modelId = modelId; }
    public BigDecimal getCustomGrowthRate() { return customGrowthRate; }
    public void setCustomGrowthRate(BigDecimal customGrowthRate) { this.customGrowthRate = customGrowthRate; }
    public BigDecimal getCustomWacc() { return customWacc; }
    public void setCustomWacc(BigDecimal customWacc) { this.customWacc = customWacc; }
    public BigDecimal getSimulatedValue() { return simulatedValue; }
    public void setSimulatedValue(BigDecimal simulatedValue) { this.simulatedValue = simulatedValue; }
    public Boolean getIsPublic() { return isPublic; }
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
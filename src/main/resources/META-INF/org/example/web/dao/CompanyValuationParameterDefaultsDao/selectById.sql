SELECT
    company_id,
    valuation_parameter_id,
    default_value,
    last_updated
FROM
    company_valuation_parameter_defaults
WHERE
    company_id = /* companyId */1
ORDER BY
    valuation_parameter_id ASC;
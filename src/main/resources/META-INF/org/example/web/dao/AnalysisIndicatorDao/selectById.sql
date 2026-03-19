select
  /*%expand*/*
from
  analysis_indicators
where
  company_id = /* company_id */1
and fiscal_year = /* fiscal_year */'2026'
and fiscal_quarter = /* fiscal_quarter */'q4'
select
  /*%expand*/*
from
  analysis_indicators
order by
  company_id, fiscal_year desc, fiscal_quarter desc
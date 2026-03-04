select
  /*%expand*/*
from
  financial_statements
order by
  company_id, fiscal_year desc, fiscal_quarter desc
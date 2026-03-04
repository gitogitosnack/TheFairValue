select
  /*%expand*/*
from
  analysis_indicators t1
inner join companies t2 on t1.company_id = t2.id
where
  t2.ticker_symbol = /* code */'7203'
order by
  t1.fiscal_year desc, t1.fiscal_quarter desc
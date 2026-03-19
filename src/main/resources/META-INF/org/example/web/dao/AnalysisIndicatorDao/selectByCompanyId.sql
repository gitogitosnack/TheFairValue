select
  /*%expand*/*
from
  analysis_indicators
where
  company_id = /* company_id */1
-- 最新の年度から順に並べて、指定された件数（年数）分だけ取得する
order by fiscal_year desc, fiscal_quarter desc
limit /* display_years */5
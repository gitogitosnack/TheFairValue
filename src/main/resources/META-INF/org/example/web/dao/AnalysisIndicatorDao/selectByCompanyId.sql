select
  *
from (
  -- ① まず「最新の5年分」を降順（desc）で絞り込む
  select
    /*%expand*/*
  from
    analysis_indicators
  where
    company_id = /* company_id */1
  and
    fiscal_quarter = 'Q4'
  order by fiscal_year desc, fiscal_quarter desc
  limit /* display_years */5
) sub
-- ② 抽出した5件を「昇順（asc）」に並べ替える
order by fiscal_year asc, fiscal_quarter asc
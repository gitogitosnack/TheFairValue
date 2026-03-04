select
  /*%expand*/*
from
  companies
where
  ticker_symbol = /* code */'7203'
  and delete_flg = 0
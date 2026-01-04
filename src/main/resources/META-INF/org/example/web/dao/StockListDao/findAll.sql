SELECT
    /*%expand*/*
FROM
    companies com
WHERE
    com.delete_flg = 0
;
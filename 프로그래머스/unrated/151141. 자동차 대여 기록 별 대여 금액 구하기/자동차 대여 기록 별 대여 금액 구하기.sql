
SELECT  A.HISTORY_ID
,       ROUND(CASE WHEN B.DISCOUNT_RATE IS NULL THEN A.DAILY_FEE * A.DAYS
        ELSE A.DAILY_FEE * A.DAYS * (100-B.DISCOUNT_RATE)/100 END)
        AS FEE
FROM(
        SELECT  A.*
        ,       CASE    WHEN DAYS<7 THEN 0 
                        WHEN DAYS>=7 AND DAYS<30 THEN 7
                        WHEN DAYS>=30 AND DAYS<90 THEN 3
                        ELSE 9 END DISC
        FROM (
                SELECT  A.HISTORY_ID
                ,       B.DAILY_FEE
                ,       B.CAR_TYPE
                ,       DATEDIFF(DATE_ADD(A.END_DATE,INTERVAL 1 DAY),A.START_DATE) DAYS
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY A
                INNER JOIN CAR_RENTAL_COMPANY_CAR B ON B.CAR_ID = A.CAR_ID
                WHERE B.CAR_TYPE = '트럭'
            ) A
    )A
LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN B 
            ON  B.CAR_TYPE = A.CAR_TYPE
            AND LEFT(B.DURATION_TYPE,1) = DISC
ORDER BY FEE DESC, A.HISTORY_ID DESC

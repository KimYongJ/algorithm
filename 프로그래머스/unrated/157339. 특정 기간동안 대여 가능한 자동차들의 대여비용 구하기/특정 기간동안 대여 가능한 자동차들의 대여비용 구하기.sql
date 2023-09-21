SELECT *
FROM(
SELECT  A.CAR_ID
,       A.CAR_TYPE
,       ROUND(A.DAILY_FEE * 30 * (100-C.DISCOUNT_RATE)/100) AS FEE
FROM CAR_RENTAL_COMPANY_CAR A
LEFT OUTER JOIN (
                    SELECT *
                    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                    WHERE LEFT(DURATION_TYPE,1) = '3'
                )C ON C.CAR_TYPE = A.CAR_TYPE
WHERE   A.CAR_ID NOT IN(
                        SELECT CAR_ID
                        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                        WHERE   (
                                    DATE_FORMAT(START_DATE,'%Y-%m-%d') <='2022-11-01' 
                                    AND     '2022-11-01' <= DATE_FORMAT(END_DATE,'%Y-%m-%d')
                                )
                        OR
                                (   DATE_FORMAT(START_DATE,'%Y-%m-%d') <='2022-11-30' 
                                    AND     '2022-11-30' <= DATE_FORMAT(END_DATE,'%Y-%m-%d')
                                )
                        GROUP BY CAR_ID
                        )
AND     A.CAR_TYPE IN ('세단','SUV')
ORDER BY FEE DESC , A.CAR_TYPE , A.CAR_ID DESC
)A
WHERE   FEE>=500000
AND     FEE<2000000
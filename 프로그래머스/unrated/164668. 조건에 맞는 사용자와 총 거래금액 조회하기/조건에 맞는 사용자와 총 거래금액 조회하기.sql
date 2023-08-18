-- 코드를 입력하세요
SELECT 
A.USER_ID,
A.NICKNAME,
B.TOTAL_SALES
FROM USED_GOODS_USER A
LEFT OUTER JOIN (
                    SELECT
                    WRITER_ID,
                    SUM(PRICE)TOTAL_SALES
                    FROM USED_GOODS_BOARD 
                    WHERE STATUS = 'DONE'
                    GROUP BY WRITER_ID
                ) B ON B.WRITER_ID = A.USER_ID
WHERE B.TOTAL_SALES >=700000
ORDER BY B.TOTAL_SALES
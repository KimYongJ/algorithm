

SELECT  YEAR(A.SALES_DATE) AS YEAR
,       MONTH(A.SALES_DATE) AS MONTH
,       COUNT(DISTINCT A.USER_ID)AS PUCHASED_USERS
,       ROUND(COUNT(DISTINCT A.USER_ID)/ MAX(TOTAL_CNT),1) AS PUCHASED_RATIO
FROM ONLINE_SALE A
INNER JOIN (
                SELECT  USER_ID -- 유저 ID
                ,       COUNT(*) OVER(
                            PARTITION BY YEAR(JOINED)
                        ) AS TOTAL_CNT -- 21년도에 가입한 전체 회원 수
                FROM USER_INFO
                WHERE YEAR(JOINED) = '2021'
                GROUP BY USER_ID
            )B ON B.USER_ID =   A.USER_ID
GROUP BY YEAR,MONTH
ORDER BY YEAR,MONTH
SELECT 
        A.FOOD_TYPE
,       A.REST_ID
,       A.REST_NAME
,       A.FAVORITES
FROM REST_INFO A
INNER JOIN (
                SELECT
                        FOOD_TYPE
                ,       MAX(FAVORITES) AS FAVORITES
                FROM REST_INFO
                GROUP BY FOOD_TYPE
            ) B ON  B.FOOD_TYPE = A.FOOD_TYPE
                AND B.FAVORITES = A.FAVORITES
ORDER BY A.FOOD_TYPE DESC

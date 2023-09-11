SELECT
        ID
,       NAME
,       HOST_ID
FROM PLACES A
WHERE EXISTS (
                SELECT 1
                FROM PLACES
                WHERE A.HOST_ID = HOST_ID
                GROUP BY HOST_ID
                HAVING COUNT(*) >=2
                )
ORDER BY ID
SELECT  ID
,       CASE WHEN RN <= FLAG THEN 'CRITICAL'
             WHEN RN <= FLAG*2 THEN 'HIGH'
             WHEN RN <= FLAG*3 THEN 'MEDIUM'
             ELSE 'LOW'
        END AS COLONY_NAME
FROM(
        SELECT  ID
        ,       ROW_NUMBER() OVER(ORDER BY SIZE_OF_COLONY DESC) RN
        ,       COUNT(ID) OVER() / 4 AS FLAG
        FROM ECOLI_DATA
    )TMP
ORDER BY ID
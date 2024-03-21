
SELECT  SUM(SCORE) SCORE
,       EMP.EMP_NO
,       EMP.EMP_NAME
,       EMP.POSITION
,       EMP.EMAIL
FROM HR_EMPLOYEES EMP
INNER JOIN HR_GRADE HG
    ON HG.EMP_NO = EMP.EMP_NO
GROUP BY    EMP.EMP_NO
,           EMP.EMP_NAME
,           EMP.POSITION
,           EMP.EMAIL
HAVING SCORE = (
                    SELECT  SUM(SCORE) SCORE
                    FROM HR_GRADE HG
                    GROUP BY HG.EMP_NO
                    ORDER BY SCORE DESC
                    LIMIT 1
                )
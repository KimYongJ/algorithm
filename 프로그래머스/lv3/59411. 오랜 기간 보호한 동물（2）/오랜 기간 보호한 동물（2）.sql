-- 코드를 입력하세요
SELECT
ANIMAL_ID,
NAME
FROM(
        SELECT
                B.NAME
        ,       B.ANIMAL_ID
        FROM ANIMAL_INS A
        JOIN ANIMAL_OUTS B
                ON B.ANIMAL_ID  =   A.ANIMAL_ID
        ORDER BY B.DATETIME - A.DATETIME DESC
    )
WHERE ROWNUM <=2

SELECT NAME
FROM (
        SELECT *
        FROM ANIMAL_INS
        ORDER BY DATETIME
)
WHERE rownum =1
        
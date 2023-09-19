SELECT  ANIMAL_ID
,       ANIMAL_TYPE
,       NAME
FROM ANIMAL_OUTS
WHERE ANIMAL_ID IN (
                    SELECT ANIMAL_ID
                    FROM ANIMAL_INS
                    WHERE SEX_UPON_INTAKE IN ('Intact Male','Intact Female')
                    )
AND SEX_UPON_OUTCOME IN ('Spayed Female','Neutered Male')
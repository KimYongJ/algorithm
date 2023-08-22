-- 코드를 입력하세요
SELECT
        AO.ANIMAL_ID
,       AO.NAME
FROM ANIMAL_OUTS AO
WHERE AO.ANIMAL_ID NOT IN (
                                SELECT ANIMAL_ID
                                FROM ANIMAL_INS    
                           )
ORDER BY AO.ANIMAL_ID,AO.NAME
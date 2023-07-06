-- 코드를 입력하세요
SELECT
'Cat' as ANIMAL_TYPE
,count(*) as count
FROM ANIMAL_INS
WHERE ANIMAL_TYPE = 'Cat'

union all

SELECT
'Dog' as ANIMAL_TYPE
,count(*) as count
FROM ANIMAL_INS
WHERE ANIMAL_TYPE = 'Dog'
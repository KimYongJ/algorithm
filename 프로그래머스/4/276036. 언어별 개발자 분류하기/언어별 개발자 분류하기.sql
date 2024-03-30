WITH    FRONT_SKILL AS (SELECT  CODE FROM SKILLCODES WHERE CATEGORY = 'Front End')
,       PYTHON_SKILL AS(SELECT  CODE FROM SKILLCODES WHERE NAME = 'Python')
,       C_SKILL AS(SELECT  CODE FROM SKILLCODES WHERE NAME = 'C#')
,       A_GRADE AS(
                    SELECT  'A' AS GRADE
                    ,       ID
                    ,       EMAIL
                    FROM DEVELOPERS  D
                    WHERE EXISTS(SELECT 1 FROM FRONT_SKILL F WHERE F.CODE | D.SKILL_CODE = D.SKILL_CODE)
                    AND EXISTS(SELECT 1 FROM PYTHON_SKILL P WHERE P.CODE | D.SKILL_CODE = D.SKILL_CODE)
)
,       B_GRADE AS(
                    SELECT  'B' AS GRADE
                    ,       ID
                    ,       EMAIL
                    FROM DEVELOPERS  D
                    WHERE EXISTS(SELECT 1 FROM C_SKILL C WHERE C.CODE | D.SKILL_CODE = D.SKILL_CODE)
                    AND NOT EXISTS(SELECT 1 FROM A_GRADE A WHERE A.ID = D.ID)
)
,       C_GRADE AS(
                    SELECT  'C' AS GRADE
                    ,       ID
                    ,       EMAIL
                    FROM DEVELOPERS  D
                    WHERE EXISTS(SELECT 1 FROM FRONT_SKILL F WHERE F.CODE | D.SKILL_CODE = D.SKILL_CODE)
                    AND NOT EXISTS(SELECT 1 FROM A_GRADE A WHERE A.ID = D.ID)
                    AND NOT EXISTS(SELECT 1 FROM B_GRADE B WHERE B.ID = D.ID)
)
,       RESULT AS(
                    SELECT * FROM A_GRADE  UNION ALL
                    SELECT * FROM B_GRADE  UNION ALL
                    SELECT * FROM C_GRADE 
)
SELECT * FROM RESULT ORDER BY GRADE,ID
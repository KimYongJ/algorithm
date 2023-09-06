SELECT  A.USER_ID
,       A.NICKNAME
,       A.CITY ||' '|| A.STREET_ADDRESS1 || ' ' || A.STREET_ADDRESS2 AS 전체주소
,       REGEXP_REPLACE(A.TLNO, '(.{3})(.+)(.{4})', '\1-\2-\3') AS 전화번호
FROM USED_GOODS_USER A
INNER JOIN (
                SELECT WRITER_ID
                FROM USED_GOODS_BOARD
                GROUP BY WRITER_ID
                HAVING COUNT(WRITER_ID) >= 3
            ) B ON  B.WRITER_ID = A.USER_ID
ORDER BY A.USER_ID DESC
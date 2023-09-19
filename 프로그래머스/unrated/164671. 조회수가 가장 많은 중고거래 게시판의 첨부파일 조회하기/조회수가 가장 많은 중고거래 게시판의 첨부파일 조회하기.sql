SELECT
'/home/grep/src/'||A.BOARD_ID||'/'||A.FILE_ID||A.FILE_NAME||A.FILE_EXT AS FILE_PATH
FROM USED_GOODS_FILE A
WHERE A.BOARD_ID = (
                        SELECT BOARD_ID
                        FROM (
                            SELECT BOARD_ID
                            FROM USED_GOODS_BOARD
                            ORDER BY VIEWS DESC
                            )
                        WHERE ROWNUM = 1
                    )
ORDER BY A.FILE_ID DESC
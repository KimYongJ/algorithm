SELECT SUM(CASE WHEN RARITY = 'LEGEND' THEN PRICE ELSE 0 END) TOTAL_PRICE
FROM ITEM_INFO
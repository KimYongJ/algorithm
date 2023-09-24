from collections import deque

# 위, 아래, 오른쪽, 왼쪽
direction = {
    "U" : (-1, 0), "D" : (1, 0), "R" : (0, 1), "L" : (0, -1)
}
dir_index = {
    "U" : 0, "D" : 1, "R" : 2, "L" : 3
}
reverse = {
    "U" : 1, "D" : 0, "R" : 3, "L" : 2
}
def solution(dirs):
    # 좌표평면 (0, 0) = (5, 5)
    visited = [list(list(0 for _ in range(4)) for _ in range(11)) for _ in range(11)]
    curr = [5, 5]
    answer = 0
    for dir in dirs:
        x, y = curr
        a, b = direction[dir]
        nx, ny = a+curr[0], b+curr[1]
        order = dir_index[dir]
        if 0<=nx<=10 and 0<=ny<=10:
            if not visited[nx][ny][order] and not visited[x][y][reverse[dir]]:
                answer += 1
            curr = [nx, ny]
            visited[nx][ny][order] = 1
            visited[x][y][reverse[dir]] = 1
                
        
    return answer
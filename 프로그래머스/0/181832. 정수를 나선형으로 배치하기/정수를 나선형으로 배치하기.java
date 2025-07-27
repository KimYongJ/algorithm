//https://school.programmers.co.kr/learn/courses/30/lessons/181832?language=java
//프로그래머스 : 정수를 나선형으로 배치하기
class Solution {
    public int[][] solution(int n) {
        final int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};// 오른쪽, 아래, 왼쪽, 위
        int[][] ans = new int[n][n];
        
        int x = 0, y = 0, dir = 0;
        for(int value = 1; value<=n*n; value++){
            ans[y][x] = value;
            int ny = y + dxy[dir][0];
            int nx = x + dxy[dir][1];
            
            if(ny<0 || nx <0 || n<=ny || n<=nx || ans[ny][nx] != 0){
                dir = (dir + 1) % 4;
                ny = y + dxy[dir][0];
                nx = x + dxy[dir][1];
            }
            
            x = nx;
            y = ny;
        }
        
        
        return ans;
    }
}
//https://school.programmers.co.kr/learn/courses/30/lessons/181832?language=java
//프로그래머스 : 정수를 나선형으로 배치하기
class Solution {
    public int[][] solution(int n) {
        int[][] ans = new int[n][n];
        int limit = n*n;
        int value = 0;
        ans[0][0] = ++value;
        
        int Y = 0;
        int X = 0;
        
        int right = n - 1;// x만 증가 후 - 1
        int down = n - 1;// y만 증가 후 - 1
        int left = 0;// x만 감소 후 + 1
        int up = 1;// y만 감소 후 + 1
        
        while(value != limit)
        {
            while(value != limit && X<right)
                ans[Y][++X] = ++value;

            while(value != limit && Y<down)
                ans[++Y][X] = ++value;

            while(value != limit && left < X)
                ans[Y][--X] = ++value;
            
            while(value != limit && up < Y)
                ans[--Y][X] = ++value;
            
            --right;
            --down;
            ++left;
            ++up;
        }
        
        return ans;
    }
}
// https://github.com/KimYongJ/algorithm
class Solution {
    int xlen, ylen;
    public int[] solution(int m, int n, int x, int y, int[][] balls) {
        xlen = m;
        ylen = n;
        
        int[] answer = new int[balls.length];
        
        for(int i=0; i<balls.length; i++){
            int x1 = balls[i][0];
            int y1 = balls[i][1];
            answer[i] = find(x,y,x1,y1);
        }
        
        return answer;
    }
    public int find(int x1,int y1, int x2, int y2){
        int min = Integer.MAX_VALUE;
        // 왼쪽 벽에 맞았을 때
        if( !(y1==y2 && x1>x2) ){
            min = Math.min(min, getDist(x1*-1,y1,x2,y2));
        }
        // 오른쪽 벽에 맞았을 때
        if( !(y1==y2 && x1<x2) ){
            min = Math.min(min, getDist(xlen+(xlen-x1), y1, x2, y2));
        }
        // 위쪽 벽에 맞았을 때 
        if( !(x1==x2 && y1>y2) ){
            min = Math.min(min, getDist(x1, y1*-1, x2, y2));
        }
        // 아래쪽 벽에 맞았을 때
        if( !(x1==x2 && y1<y2) ){
            min = Math.min(min, getDist(x1, ylen+ylen-y1,x2, y2));
        }

        return min;
    }
    public int getDist(int x1,int y1, int x2, int y2){
        int x = x1-x2;
        int y = y1-y2;
        return x*x + y*y;
    }
}
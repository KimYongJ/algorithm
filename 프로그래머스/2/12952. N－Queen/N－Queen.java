// https://github.com/KimYongJ/algorithm
class Solution {
    
    static int n, idx, result, queens[][];
    
    public int solution(int n) {
        this.n = n;
        queens = new int[n][2];
        DFS(0);// 순서 : 세로인덱스
        return result;
    }
    public void DFS(int y){// y는 깊이(depth)를 의미하며 세로 인덱스(y)로 사용된다.
        if(idx==n){
            result++;
            return;
        }
        for(int x=0; x<n; x++){
            if(isPossible(y,x)){// 퀸을 놓을 수 있는 곳인지 체크 
                queens[idx][0] = y;// 퀸의 y좌표
                queens[idx][1] = x;// 퀸의 x좌표
                idx++;// 저장한 퀸의 갯수 및 퀸의 좌표를담은 배열의 idx
                DFS(y+1);
                idx--;
            }
        }
    }
    /*
    * 퀸을 놓을 수 있다면 true, 없다면 false
    */
    public boolean isPossible(int i,int j){
        for(int x=0; x<idx; x++)
            if(  queens[x][1]==j ||// 가로를 체크한다. 세로체크는 할필요가없다.세로는 DFS에서 전달되는 인자인 깊이(depth)로 겹칠일이 없기 때문이다.
                Math.abs(queens[x][0]-i)==Math.abs(queens[x][1]-j))// 대각선 체크
                return false;
        
        return true;
    }
}
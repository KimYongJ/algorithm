// https://github.com/KimYongJ/algorithm
class Solution {
    
    static int n, idx, result, queens[][];
    static boolean col[];
    
    public int solution(int n) {
        this.n = n;
        queens = new int[n][2];
        col = new boolean[n+1];// 가로를 이미 방문했는지 체크
        DFS(0);// 순서 : 세로인덱스
        return result;
    }
    public void DFS(int a){
        if(idx==n){
            result++;
            return;
        }
        for(int i=0; i<n; i++){
            if(col[i])
                continue;
            if(isPossible(a,i)){// 퀸을 놓을 수 있는 곳인지 체크 
                col[i] = true;
                queens[idx][0] = a;// 퀸의 y좌표
                queens[idx][1] = i;// 퀸의 x좌표
                idx++;// 저장한 퀸의 갯수 및 퀸의 좌표를담은 배열의 idx
                DFS(a+1);
                idx--;
                col[i] = false;
            }
        }
    }
    /*
    * 퀸을 놓을 수 있다면 true, 없다면 false
    */
    public boolean isPossible(int i,int j){
        for(int x=0; x<idx; x++)
            if( Math.abs(queens[x][0]-i)==Math.abs(queens[x][1]-j))// 대각선 체크
                return false;
        
        return true;
    }
}